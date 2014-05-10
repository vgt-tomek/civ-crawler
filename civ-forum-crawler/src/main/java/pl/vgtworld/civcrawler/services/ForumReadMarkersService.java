package pl.vgtworld.civcrawler.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.civcrawler.dao.ForumReadMarkersDao;
import pl.vgtworld.civcrawler.entities.ForumReadMarker;
import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.entities.ForumReadMarker.Executions;

@Stateless
public class ForumReadMarkersService {
	
	@Inject
	private ForumReadMarkersDao markersDao;
	
	@Inject
	private UsersService usersDao;
	
	public void markForumRead(int userId, Executions executionType) {
		User user = usersDao.findById(userId);
		ForumReadMarker marker = new ForumReadMarker();
		marker.setUser(user);
		marker.setReadAt(new Date());
		marker.setExecution(executionType);
		markersDao.add(marker);
	}
	
}
