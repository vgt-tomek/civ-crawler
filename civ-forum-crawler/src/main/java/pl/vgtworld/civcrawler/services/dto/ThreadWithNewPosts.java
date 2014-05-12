package pl.vgtworld.civcrawler.services.dto;

import java.util.Comparator;
import java.util.Date;

public class ThreadWithNewPosts {
	
	public static final Comparator<ThreadWithNewPosts> COMPARATOR_LAST_POST_DESCENDING = new Comparator<ThreadWithNewPosts>() {
		
		@Override
		public int compare(ThreadWithNewPosts o1, ThreadWithNewPosts o2) {
			return (int)(o2.getLastPostTimestamp().getTime() - o1.getLastPostTimestamp().getTime());
		}
		
	};
	
	private String name;
	
	private String board;
	
	private int messageId;
	
	private int newPostCount;
	
	private Date lastPostTimestamp;
	
	private String lastPostUserName;
	
	public ThreadWithNewPosts(String name, int messageId) {
		int index = name.lastIndexOf('>');
		if (index < 0) {
			this.name = name;
			board = "";
		} else {
			int otherIndex = name.indexOf('>') < 0 ? 0 : name.indexOf('>');
			this.name = name.substring(index + 2);
			board = name.substring(otherIndex + 2, index - 1);
		}
		this.messageId = messageId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBoard() {
		return board;
	}
	
	public int getMessageId() {
		return messageId;
	}
	
	public int getNewPostCount() {
		return newPostCount;
	}
	
	public Date getLastPostTimestamp() {
		return lastPostTimestamp;
	}
	
	public void addPost(Date date, String userName) {
		++newPostCount;
		lastPostTimestamp = date;
		lastPostUserName = userName;
	}
	
	public String getLastPostUserName() {
		return lastPostUserName;
	}
	
	public void setLastPostUserName(String lastPostUserName) {
		this.lastPostUserName = lastPostUserName;
	}
	
}
