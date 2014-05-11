package pl.vgtworld.civcrawler.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	
	public void createInitialForumReadMarker(int userId) {
		User user = usersDao.findById(userId);
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.HOUR, -24);
		ForumReadMarker marker = new ForumReadMarker();
		marker.setUser(user);
		marker.setReadAt(cal.getTime());
		marker.setExecution(Executions.AUTOMATIC);
		markersDao.add(marker);
	}
	
	public void markForumRead(int userId, Executions executionType) {
		User user = usersDao.findById(userId);
		ForumReadMarker marker = new ForumReadMarker();
		marker.setUser(user);
		marker.setReadAt(new Date());
		marker.setExecution(executionType);
		markersDao.add(marker);
	}
	
}
