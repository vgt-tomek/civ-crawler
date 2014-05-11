package pl.vgtworld.civcrawler.actions.viewunread;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.core.CivServlet;
import pl.vgtworld.civcrawler.entities.ForumReadMarker.Executions;
import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.services.ForumReadMarkersService;
import pl.vgtworld.civcrawler.services.ThreadsService;
import pl.vgtworld.civcrawler.services.dao.ThreadWithNewPosts;

@WebServlet("/view-unread")
public class ViewUnread extends CivServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ThreadsService service;
	
	@Inject
	private ForumReadMarkersService forumReadService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User loggedUser = getLoggedUser(req);
		if (loggedUser == null) {
			//TODO Display proper message for visitor.
			return;
		}
		
		ThreadWithNewPosts[] threads = service.findWithUnreadPosts(loggedUser);
		
		if (threads.length == 0) {
			forumReadService.markForumRead(loggedUser.getId(), Executions.AUTOMATIC);
		}
		
		req.setAttribute("threads", threads);
		render("view-unread", req, resp);
	}
}
