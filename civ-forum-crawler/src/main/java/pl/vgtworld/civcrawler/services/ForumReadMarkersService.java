package pl.vgtworld.civcrawler.services;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.civcrawler.dao.ForumReadMarkersDao;
import pl.vgtworld.civcrawler.entities.ForumReadMarker;
import pl.vgtworld.civcrawler.entities.ForumReadMarker.Executions;
import pl.vgtworld.civcrawler.entities.User;

@Stateless
public class ForumReadMarkersService {
	
	private static final int HOURS_IN_PAST = 24;

	@Inject
	private ForumReadMarkersDao markersDao;
	
	@Inject
	private UsersService usersDao;
	
	@Inject
	private ForumScansService forumScansService;
	
	public void createInitialForumReadMarker(int userId) {
		User user = usersDao.findById(userId);
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.HOUR, -HOURS_IN_PAST);
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
		marker.setReadAt(forumScansService.findLastScanDate());
		marker.setExecution(executionType);
		markersDao.add(marker);
	}
	
}
