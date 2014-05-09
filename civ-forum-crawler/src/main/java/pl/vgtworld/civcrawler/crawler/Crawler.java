package pl.vgtworld.civcrawler.crawler;

import java.io.IOException;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

import pl.vgtworld.civcrawler.parsers.PostDto;
import pl.vgtworld.civcrawler.parsers.TodayPostsParser;

@Stateless
public class Crawler {
	
	private static final String HOST = "http://forums.civ.org.pl/";
	
	private static final String TODAY_POSTS_URL = HOST + "search.php?action=viewtoday";
	
	@Schedule(persistent=false, second="0", minute="*/5", hour="*")
	public void searchNewPosts() throws IOException {
		String todayPostsPage = HttpUtils.downloadUrl(TODAY_POSTS_URL);
		TodayPostsParser todayParser = new TodayPostsParser();
		
		PostDto[] posts = todayParser.parse(todayPostsPage);
		
		//TODO Find threadId for each post and store result in database.
	}
}
