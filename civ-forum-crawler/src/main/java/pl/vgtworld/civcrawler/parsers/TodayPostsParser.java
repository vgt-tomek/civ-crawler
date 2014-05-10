package pl.vgtworld.civcrawler.parsers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodayPostsParser {
	
	private static final String PATTERN = "misc\\.php\\?action=gotomsg&amp;MsgID=([0-9]+)"
		+ ".*?member\\.php\\?action=viewprofile&amp;UserID=([0-9]+)\">(.*?)</a>";
	
	public PostDto[] parse(String page) throws ParseException {
		try {
			List<PostDto> posts = new ArrayList<>();
			
			Pattern pattern = Pattern.compile(PATTERN);
			Matcher matcher = pattern.matcher(page);
			while (matcher.find()) {
				PostDto dto = new PostDto();
				dto.setMessageId(Integer.parseInt(matcher.group(1)));
				dto.setUserId(Integer.parseInt(matcher.group(2)));
				dto.setUserName(matcher.group(3));
				posts.add(dto);
				System.out.println(matcher.group());
			}
			Collections.reverse(posts);
			return posts.toArray(new PostDto[posts.size()]);
		} catch (NumberFormatException e) {
			throw new ParseException("Unable to parse integer from html.", e);
		}
	}
}
