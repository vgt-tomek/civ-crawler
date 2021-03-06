package pl.vgtworld.civcrawler.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.entities.User;

public abstract class CivServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected User getLoggedUser(HttpServletRequest req) {
		return (User) req.getAttribute("user");
	}
	
	protected void hideTopLoginForm(HttpServletRequest req) {
		req.setAttribute("hideTopLoginForm", true);
	}
	
	protected void render(String view, HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/" + view + ".jsp").forward(req, resp);
	}
	
	protected String getRemoteAddr(HttpServletRequest req) {
		String xForwardedFor = req.getHeader("X-Forwarded-For");
		if (xForwardedFor != null) {
			return xForwardedFor;
		}
		return req.getRemoteAddr();
	}
}
