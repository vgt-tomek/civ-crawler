package pl.vgtworld.civcrawler.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.civcrawler.entities.Post;
import pl.vgtworld.civcrawler.entities.Thread;
import pl.vgtworld.civcrawler.parsers.ParseException;
import pl.vgtworld.civcrawler.parsers.PostDto;
import pl.vgtworld.civcrawler.parsers.ThreadDto;
import pl.vgtworld.civcrawler.parsers.ThreadParser;
import pl.vgtworld.civcrawler.parsers.TodayPostsParser;
import pl.vgtworld.civcrawler.services.PostsService;
import pl.vgtworld.civcrawler.services.ThreadsService;

@Stateless
public class Crawler {
	
	private static final String HOST = "http://forums.civ.org.pl/";
	
	private static final String TODAY_POSTS_URL = HOST + "search.php?action=viewtoday";
	
	private static final String THREAD_URL = HOST + "misc.php?action=gotomsg&MsgID=%1$d";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Crawler.class);
	
	@Inject
	private PostsService postsService;
	
	@Inject
	private ThreadsService threadsService;
	
	private ThreadParser threadParser = new ThreadParser();
	
	@Schedule(persistent=false, second="0", minute="*/3", hour="*")
	public void searchNewPosts() {
		try {
			LOGGER.info("Start searching new posts.");
			
			String todayPostsPage = HttpUtils.downloadUrl(TODAY_POSTS_URL);
			TodayPostsParser todayParser = new TodayPostsParser();
			PostDto[] posts = todayParser.parse(todayPostsPage);
			PostDto[] newPosts = filterOutNewPosts(posts);
			LOGGER.info("Found {} new out of {} posts.", newPosts.length, posts.length);
			
			for (PostDto postDto : newPosts) {
				storePostInDatabase(postDto);
			}
			LOGGER.info("Searching new posts finished successfully.");
		} catch (IOException e) {
			LOGGER.error("Error while trying to download html page.", e);
		} catch (ParseException e) {
			LOGGER.error("Error while trying to parse html page.", e);
		}
	}

	private void storePostInDatabase(PostDto postDto) throws IOException, ParseException {
		String threadUrl = String.format(THREAD_URL, postDto.getMessageId());
		String threadPage = HttpUtils.downloadUrl(threadUrl);
		ThreadDto threadDto = threadParser.parse(threadPage);
		
		Thread thread = threadsService.findById(threadDto.getThreadId());
		if (thread == null) {
			LOGGER.debug("Thread #{} doesn't exist. Creating new.", threadDto.getThreadId());
			thread = new Thread();
			thread.setId(threadDto.getThreadId());
			thread.setTitle(threadDto.getTitle());
		}
		Post post = new Post();
		post.setId(postDto.getMessageId());
		post.setThread(thread);
		post.setPage(threadDto.getPage());
		post.setCreatedAt(new Date());
		LOGGER.debug("Creating post #{}", postDto.getMessageId());
		postsService.add(post);
	}
	
	private PostDto[] filterOutNewPosts(PostDto[] posts) {
		List<PostDto> filteredResult = new ArrayList<>();
		for (PostDto post : posts) {
			Post postEntity = postsService.findById(post.getMessageId());
			if (postEntity == null) {
				filteredResult.add(post);
			}
		}
		return filteredResult.toArray(new PostDto[filteredResult.size()]);
	}
	
}
