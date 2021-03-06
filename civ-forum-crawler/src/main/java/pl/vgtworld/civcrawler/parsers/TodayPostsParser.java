package pl.vgtworld.civcrawler.parsers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodayPostsParser {
	
	private static final int MESSAGE_ID_EXPRESSION_INDEX = 1;

	private static final int USER_ID_EXPRESSION_INDEX = 2;

	private static final int USER_NAME_EXPRESSION_INDEX = 3;

	private static final int CREATED_AT_EXPRESSION_INDEX = 4;

	private static final String PATTERN = "misc\\.php\\?action=gotomsg&amp;MsgID=([0-9]+)"
		+ ".*?member\\.php\\?action=viewprofile&amp;UserID=([0-9]+)\">(.*?)</a>"
		+ ".*?<span class=\"date\">(.*?)</td>";
	
	private DateFormat df = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.ENGLISH);
	
	public PostDto[] parse(String page) throws ParseException {
		try {
			df.setTimeZone(TimeZone.getTimeZone("GMT+1"));
			List<PostDto> posts = new ArrayList<>();
			
			Pattern pattern = Pattern.compile(PATTERN);
			Matcher matcher = pattern.matcher(page);
			while (matcher.find()) {
				PostDto dto = new PostDto();
				dto.setMessageId(Integer.parseInt(matcher.group(MESSAGE_ID_EXPRESSION_INDEX)));
				dto.setUserId(Integer.parseInt(matcher.group(USER_ID_EXPRESSION_INDEX)));
				dto.setUserName(matcher.group(USER_NAME_EXPRESSION_INDEX));
				dto.setCreatedAt(parseDate(matcher.group(CREATED_AT_EXPRESSION_INDEX)));
				posts.add(dto);
			}
			Collections.reverse(posts);
			return posts.toArray(new PostDto[posts.size()]);
		} catch (NumberFormatException e) {
			throw new ParseException("Unable to parse integer from html.", e);
		}
	}
	
	private Date parseDate(String date) throws ParseException {
		try {
			return df.parse(date);
		} catch (java.text.ParseException e) {
			System.out.println(date);
			throw new ParseException(String.format("Unable to parse date from %s.", date), e);
		}
	}
}
