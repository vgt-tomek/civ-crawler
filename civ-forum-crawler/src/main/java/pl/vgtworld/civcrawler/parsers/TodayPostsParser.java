package pl.vgtworld.civcrawler.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodayPostsParser {

	public PostDto[] parse(String page) {
		List<PostDto> posts = new ArrayList<>();
		
		Pattern pattern = Pattern.compile("misc\\.php\\?action=gotomsg&amp;MsgID=([0-9]+)");
		Matcher matcher = pattern.matcher(page);
		while (matcher.find()) {
			PostDto dto = new PostDto();
			int messageId = Integer.parseInt(matcher.group(1));
			dto.setMessageId(messageId);
			posts.add(dto);
		}
		return posts.toArray(new PostDto[posts.size()]);
	}
}
