package pl.vgtworld.civcrawler.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreadParser {
	
	private static final String THREAD_ID_PATTERN = "print\\.php\\?TopicID=([0-9]+)";
	
	private static final String PAGE_NUMBER_PATTERN = "option value=\"([0-9]+)\" SELECTED";

	public ThreadDto parse(String page) throws ParseException {
		ThreadDto dto = new ThreadDto();
		dto.setThreadId(findThreadId(page));
		dto.setPage(findPageNumber(page));
		dto.setTitle(findThreadTitle(page));
		return dto;
	}
	
	private int findThreadId(String page) throws ParseException {
		return findInteger(page, THREAD_ID_PATTERN);
	}
	
	private int findPageNumber(String page) {
		try {
			return findInteger(page, PAGE_NUMBER_PATTERN);
		} catch (ParseException e) {
			return 0;
		}
	}
	
	private String findThreadTitle(String page) throws ParseException {
		return findString(page, "<title>(.*)</title>");
	}
	
	private int findInteger(String page, String pattern) throws ParseException {
		return Integer.parseInt(findString(page, pattern));
	}
	
	private String findString(String page, String pattern) throws ParseException {
		Pattern compiledPattern = Pattern.compile(pattern);
		Matcher matcher = compiledPattern.matcher(page);
		if (matcher.find()) {
			return matcher.group(1);
		}
		throw new ParseException("Unable to find pattern match.");
	}
}
