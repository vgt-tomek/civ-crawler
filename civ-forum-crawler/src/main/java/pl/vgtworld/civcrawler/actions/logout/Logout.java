package pl.vgtworld.civcrawler.actions.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.core.CivServlet;

@WebServlet("/logout")
public class Logout extends CivServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie userCookie = new Cookie("user", "");
		Cookie tokenCookie = new Cookie("token", "");
		userCookie.setMaxAge(0);
		tokenCookie.setMaxAge(0);
		
		resp.addCookie(userCookie);
		resp.addCookie(tokenCookie);
		
		req.setAttribute("user", null);
		
		render("logout", req, resp);
	}
}
