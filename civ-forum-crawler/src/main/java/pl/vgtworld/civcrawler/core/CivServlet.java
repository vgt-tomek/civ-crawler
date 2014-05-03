package pl.vgtworld.civcrawler.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CivServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void render(String view, HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/" + view + ".jsp").forward(req, resp);
	}
}
