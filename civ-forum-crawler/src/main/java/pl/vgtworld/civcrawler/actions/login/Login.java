package pl.vgtworld.civcrawler.actions.login;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.vgtworld.civcrawler.core.CivServlet;
import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.services.UsersService;
import pl.vgtworld.civcrawler.services.UsersServiceException;

@WebServlet("/login")
public class Login extends CivServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);
	
	@Inject
	private UsersService usersService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		hideTopLoginForm(req);
		render("login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		hideTopLoginForm(req);
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		LoginFormDto dto = new LoginFormDto(login, password);
		
		LoginFormValidator validator = new LoginFormValidator();
		boolean validationResult = validator.validate(dto, usersService);
		if (validationResult) {
			try {
				User user = usersService.login(login, getRemoteAddr(req), resp);
				req.setAttribute("user", user);
				render("login-success", req, resp);
			} catch (UsersServiceException e) {
				String message = "Unexpected error while trying to login user.";
				LOGGER.error(message, e);
				req.setAttribute("message", message);
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				render("errors/error-message", req, resp);
			}
		} else {
			LOGGER.info("Failed login attempt for user {} from ip {}.", login, getRemoteAddr(req));
			req.setAttribute("errors", validator.getErrors());
			req.setAttribute("dto", dto);
			render("login", req, resp);
		}
	}
}
