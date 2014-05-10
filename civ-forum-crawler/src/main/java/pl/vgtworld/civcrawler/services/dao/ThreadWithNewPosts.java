package pl.vgtworld.civcrawler.services.dao;

import java.util.Date;

public class ThreadWithNewPosts {
	
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
