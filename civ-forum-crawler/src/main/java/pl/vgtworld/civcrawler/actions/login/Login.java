package pl.vgtworld.civcrawler.actions.login;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.core.CivServlet;
import pl.vgtworld.civcrawler.services.UsersService;

@WebServlet("/login")
public class Login extends CivServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	UsersService usersService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		render("login", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		LoginFormDto dto = new LoginFormDto(login, password);
		
		LoginFormValidator validator = new LoginFormValidator();
		boolean validationResult = validator.validate(dto, usersService);
		if (validationResult) {
			//TODO Set cookie for logged user.
			render("login-success", req, resp);
		} else {
			req.setAttribute("errors", validator.getErrors());
			req.setAttribute("dto", dto);
			render("login", req, resp);
		}
	}
}
