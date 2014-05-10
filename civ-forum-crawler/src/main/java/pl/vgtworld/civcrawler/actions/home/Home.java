package pl.vgtworld.civcrawler.actions.home;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.core.CivServlet;
import pl.vgtworld.civcrawler.services.ThreadsService;
import pl.vgtworld.civcrawler.services.dao.ThreadWithNewPosts;

@WebServlet("/")
public class Home extends CivServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ThreadsService threadsService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.HOUR, -24);
		ThreadWithNewPosts[] threads = threadsService.findWithNewPostsSince(cal.getTime());
		req.setAttribute("threads", threads);
		
		render("home", req, resp);
	}
}
