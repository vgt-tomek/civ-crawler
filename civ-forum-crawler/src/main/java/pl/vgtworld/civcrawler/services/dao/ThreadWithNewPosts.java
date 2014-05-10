package pl.vgtworld.civcrawler.services.dao;

import java.util.Comparator;
import java.util.Date;

public class ThreadWithNewPosts {
	
	public static final Comparator<ThreadWithNewPosts> COMPARATOR_LAST_POST_DESCENDING = new Comparator<ThreadWithNewPosts>() {

		@Override
		public int compare(ThreadWithNewPosts o1, ThreadWithNewPosts o2) {
			long difference = o2.getLastPostTimestamp().getTime() - o1.getLastPostTimestamp().getTime();
			return (int)difference;
		}
		
	};
	
	private String name;
	
	private int messageId;
	
	private int newPostCount;
	
	private Date lastPostTimestamp;
	
	public ThreadWithNewPosts(String name, int messageId) {
		this.name = name;
		this.messageId = messageId;
	}
	
	public String getName() {
		return name;
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
	
	public void addPost(Date date) {
		++newPostCount;
		lastPostTimestamp = date;
	}
	
	public String getUrl() {
		return "http://forums.civ.org.pl/misc.php?action=gotomsg&MsgID=" + messageId;
	}
	
}
