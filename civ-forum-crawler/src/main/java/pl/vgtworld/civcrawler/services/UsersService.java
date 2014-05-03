package pl.vgtworld.civcrawler.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.vgtworld.civcrawler.actions.register.RegisterFormDto;
import pl.vgtworld.civcrawler.dao.UsersDao;
import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.utils.UserUtils;

@Stateless
public class UsersService {
	
	@Inject
	UsersDao dao;
	
	public void createNewUser(RegisterFormDto registerDto) throws UsersServiceException {
		try {
			User user = new User();
			String salt = UserUtils.generateSalt();
			String passwordHash = UserUtils.generatePasswordHash(registerDto.getPassword(), salt);
			user.setLogin(registerDto.getLogin());
			user.setSalt(salt);
			user.setPassword(passwordHash);
			user.setCreatedAt(new Date());
			dao.add(user);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new UsersServiceException("Error while creating new user", e);
		}
	}
	
	public boolean isLoginAvailable(String login) {
		User user = dao.findByLogin(login);
		return user == null;
	}
}
