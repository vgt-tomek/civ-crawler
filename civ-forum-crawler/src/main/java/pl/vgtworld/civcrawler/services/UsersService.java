package pl.vgtworld.civcrawler.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.vgtworld.civcrawler.actions.register.RegisterFormDto;
import pl.vgtworld.civcrawler.dao.UserTokensDao;
import pl.vgtworld.civcrawler.dao.UsersDao;
import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.entities.UserToken;
import pl.vgtworld.civcrawler.utils.UserUtils;

@Stateless
public class UsersService {
	
	@Inject
	UsersDao usersDao;
	
	@Inject
	UserTokensDao userTokensDao;
	
	public void createNewUser(RegisterFormDto registerDto) throws UsersServiceException {
		try {
			User user = new User();
			String salt = UserUtils.generateSalt();
			String passwordHash = UserUtils.generatePasswordHash(registerDto.getPassword(), salt);
			user.setLogin(registerDto.getLogin());
			user.setSalt(salt);
			user.setPassword(passwordHash);
			user.setCreatedAt(new Date());
			usersDao.add(user);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new UsersServiceException("Error while creating new user", e);
		}
	}
	
	public User findById(int userId) {
		return usersDao.findById(userId);
	}
	
	public User findByLogin(String login) {
		return usersDao.findByLogin(login);
	}
	
	public boolean isLoginAvailable(String login) {
		User user = usersDao.findByLogin(login);
		return user == null;
	}
	
	public void login(String login, HttpServletRequest request, HttpServletResponse response)
		throws UsersServiceException {
		try {
			User user = usersDao.findByLogin(login);
			
			UserToken token = new UserToken();
			token.setUser(user);
			token.setCreatedAt(new Date());
			token.setToken(UserUtils.generateToken(user.getLogin() + System.currentTimeMillis()));
			token.setIp(request.getRemoteAddr());
			userTokensDao.add(token);
			
			Cookie userCookie = new Cookie("user", user.getLogin());
			Cookie tokenCookie = new Cookie("token", token.getToken());
			userCookie.setHttpOnly(true);
			tokenCookie.setHttpOnly(true);
			response.addCookie(userCookie);
			response.addCookie(tokenCookie);
			request.setAttribute("user", user);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new UsersServiceException("Error while trying to login user.", e);
		}
	}
	
	public User validateLoginCookies(Cookie userCookie, Cookie tokenCookie) {
		//TODO Also check if ip address changed.
		String login = userCookie.getValue();
		User user = usersDao.findByLogin(login);
		
		if (user == null) {
			return null;
		}
		
		UserToken token = userTokensDao.findLastTokenForUser(user);
		
		if (token.getToken().equals(tokenCookie.getValue())) {
			return user;
		}
		return null;
	}

}
