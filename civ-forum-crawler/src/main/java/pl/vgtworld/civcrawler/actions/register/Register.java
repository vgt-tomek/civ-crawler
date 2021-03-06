package pl.vgtworld.civcrawler.actions.register;

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
import pl.vgtworld.civcrawler.services.ForumReadMarkersService;
import pl.vgtworld.civcrawler.services.UsersService;
import pl.vgtworld.civcrawler.services.UsersServiceException;
import pl.vgtworld.civcrawler.services.dto.RegisterFormDto;

@WebServlet("/register")
public class Register extends CivServlet {
	
	private static final String REGISTER_VIEW = "register";
	
	private static final String REGISTER_SUCCESS_VIEW = "register-success";
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Register.class);
	
	@Inject
	private UsersService usersService;
	
	@Inject
	private ForumReadMarkersService forumReadService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		hideTopLoginForm(req);
		render(REGISTER_VIEW, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		hideTopLoginForm(req);
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		String passwordRepeat = req.getParameter("password-repeat");
		RegisterFormDto dto = new RegisterFormDto(login, password, passwordRepeat);
		
		RegisterFormValidator validator = new RegisterFormValidator();
		boolean validationResult = validator.validate(dto, usersService);
		if (validationResult) {
			try {
				User user = usersService.createNewUser(dto);
				forumReadService.createInitialForumReadMarker(user.getId());
				LOGGER.info("New user ({}) registered.", login);
				render(REGISTER_SUCCESS_VIEW, req, resp);
			} catch (UsersServiceException e) {
				String message = "Unexpected error while trying to register new user.";
				LOGGER.error(message, e);
				req.setAttribute("message", message);
				resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				render("errors/error-message", req, resp);
			}
		} else {
			req.setAttribute("errors", validator.getErrors());
			req.setAttribute("dto", dto);
			render(REGISTER_VIEW, req, resp);
		}
	}
}
