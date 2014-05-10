package pl.vgtworld.civcrawler.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.civcrawler.dao.PostsDao;
import pl.vgtworld.civcrawler.dao.ThreadsDao;
import pl.vgtworld.civcrawler.entities.Post;
import pl.vgtworld.civcrawler.entities.Thread;
import pl.vgtworld.civcrawler.services.dao.ThreadWithNewPosts;

@Stateless
public class ThreadsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsService.class);
	
	@Inject
	ThreadsDao dao;
	
	@Inject
	PostsDao postsDao;
	
	public void add(Thread thread) {
		dao.add(thread);
	}
	
	public Thread findById(int id) {
		return dao.findById(id);
	}
	
	public ThreadWithNewPosts[] findWithNewPostsSince(Date date) {
		LOGGER.debug("Load threads with new posts since {}.", date);
		Post[] newPosts = postsDao.findNewSince(date);
		
		Map<Integer, ThreadWithNewPosts> dtoMap = new HashMap<>();
		for (Post post : newPosts) {
			ThreadWithNewPosts dto = dtoMap.get(post.getThread().getId());
			if (dto == null) {
				dto = new ThreadWithNewPosts(post.getThread().getTitle(), post.getId());
				dtoMap.put(post.getThread().getId(), dto);
			}
			dto.addPost(post.getCreatedAt());
		}
		
		return dtoMap.values().toArray(new ThreadWithNewPosts[dtoMap.size()]);
	}
}
