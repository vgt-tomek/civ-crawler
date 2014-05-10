package pl.vgtworld.civcrawler.actions.viewunread;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.core.CivServlet;
import pl.vgtworld.civcrawler.entities.User;

@WebServlet("/view-unread")
public class ViewUnread extends CivServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User loggedUser = getLoggedUser(req);
		if (loggedUser == null) {
			//TODO Display proper message for visitor.
			return;
		}
		
		render("view-unread", req, resp);
	}
}
