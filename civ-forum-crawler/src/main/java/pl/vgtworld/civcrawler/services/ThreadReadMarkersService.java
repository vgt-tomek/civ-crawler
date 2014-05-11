package pl.vgtworld.civcrawler.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.civcrawler.dao.ThreadReadMarkersDao;
import pl.vgtworld.civcrawler.dao.ThreadsDao;
import pl.vgtworld.civcrawler.dao.UsersDao;
import pl.vgtworld.civcrawler.entities.Thread;
import pl.vgtworld.civcrawler.entities.ThreadReadMarker;
import pl.vgtworld.civcrawler.entities.User;

@Stateless
public class ThreadReadMarkersService {
	
	@Inject
	private ThreadReadMarkersDao dao;
	
	@Inject
	private UsersDao usersDao;
	
	@Inject
	private ThreadsDao threadsDao;
	
	public void markThreadRead(int userId, int threadId) {
		Date timestamp = new Date();
		User user = usersDao.findById(userId);
		Thread thread = threadsDao.findById(threadId);
		ThreadReadMarker marker = dao.findByThreadForUser(threadId, userId);
		if (marker == null) {
			marker = new ThreadReadMarker();
			marker.setUser(user);
			marker.setThread(thread);
			marker.setReadAt(timestamp);
			dao.add(marker);
		} else {
			marker.setReadAt(timestamp);
		}
	}
}
