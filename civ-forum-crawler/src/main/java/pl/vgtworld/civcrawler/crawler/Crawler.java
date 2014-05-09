package pl.vgtworld.civcrawler.crawler;

import java.io.IOException;
import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

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
	
	@Inject
	PostsService postsService;
	
	@Inject
	ThreadsService threadsService;
	
	@Schedule(persistent=false, second="0", minute="*/5", hour="*")
	public void searchNewPosts() throws IOException, ParseException {
		String todayPostsPage = HttpUtils.downloadUrl(TODAY_POSTS_URL);
		TodayPostsParser todayParser = new TodayPostsParser();
		ThreadParser threadParser = new ThreadParser();
		
		PostDto[] posts = todayParser.parse(todayPostsPage);
		
		for (PostDto postDto : posts) {
			Post postEntity = postsService.findById(postDto.getMessageId());
			
			if (postEntity == null) {
				String threadUrl = String.format(THREAD_URL, postDto.getMessageId());
				String threadPage = HttpUtils.downloadUrl(threadUrl);
				ThreadDto threadDto = threadParser.parse(threadPage);
				
				Thread thread = threadsService.findById(threadDto.getThreadId());
				if (thread == null) {
					thread = new Thread();
					thread.setId(threadDto.getThreadId());
					thread.setTitle(threadDto.getTitle());
				}
				Post post = new Post();
				post.setId(postDto.getMessageId());
				post.setThread(thread);
				post.setPage(threadDto.getPage());
				post.setCreatedAt(new Date());
				postsService.add(post);
			}
		}
	}
	
}
