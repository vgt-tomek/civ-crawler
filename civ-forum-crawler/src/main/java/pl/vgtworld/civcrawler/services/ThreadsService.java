package pl.vgtworld.civcrawler.services;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.civcrawler.dao.ForumReadMarkersDao;
import pl.vgtworld.civcrawler.dao.PostsDao;
import pl.vgtworld.civcrawler.dao.ThreadsDao;
import pl.vgtworld.civcrawler.entities.Post;
import pl.vgtworld.civcrawler.entities.Thread;
import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.services.dto.ThreadWithNewPosts;

@Stateless
public class ThreadsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsService.class);
	
	@Inject
	private ThreadsDao dao;
	
	@Inject
	private PostsDao postsDao;
	
	@Inject
	private ForumReadMarkersDao forumReadMarkersDao;
	
	public void add(Thread thread) {
		dao.add(thread);
	}
	
	public Thread findById(int id) {
		return dao.findById(id);
	}
	
	public ThreadWithNewPosts[] findWithNewPostsSince(Date date) {
		LOGGER.debug("Load threads with new posts since {}.", date);
		Post[] newPosts = postsDao.findNewSince(date);
		
		return mergePostsIntoThreads(newPosts);
	}

	public ThreadWithNewPosts[] findWithUnreadPosts(User user) {
		Date date = forumReadMarkersDao.findLastDateForUser(user.getId());
		Post[] newPosts = postsDao.findUnread(user, date);
		
		return mergePostsIntoThreads(newPosts);
	}
	
	private ThreadWithNewPosts[] mergePostsIntoThreads(Post[] newPosts) {
		Map<Integer, ThreadWithNewPosts> dtoMap = new HashMap<>();
		for (Post post : newPosts) {
			ThreadWithNewPosts dto = dtoMap.get(post.getThread().getId());
			if (dto == null) {
				dto = new ThreadWithNewPosts(post.getThread().getTitle(), post.getId());
				dtoMap.put(post.getThread().getId(), dto);
			}
			dto.addPost(post.getCreatedAt(), post.getAuthor().getName());
		}
		
		ThreadWithNewPosts[] result = dtoMap.values().toArray(new ThreadWithNewPosts[dtoMap.size()]);
		Arrays.sort(result, ThreadWithNewPosts.COMPARATOR_LAST_POST_DESCENDING);
		return result;
	}
	
}
