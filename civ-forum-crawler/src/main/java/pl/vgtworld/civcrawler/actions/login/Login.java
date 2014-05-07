package pl.vgtworld.civcrawler.actions.login;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.core.CivServlet;
import pl.vgtworld.civcrawler.services.UsersService;
import pl.vgtworld.civcrawler.services.UsersServiceException;

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
			try {
				usersService.login(login, (HttpServletRequest) req, (HttpServletResponse) resp);
				render("login-success", req, resp);
			} catch (UsersServiceException e) {
				e.printStackTrace();
				// TODO display proper error page
			}
		} else {
			req.setAttribute("errors", validator.getErrors());
			req.setAttribute("dto", dto);
			render("login", req, resp);
		}
	}
}