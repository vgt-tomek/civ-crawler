package pl.vgtworld.civcrawler.actions.login;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import pl.vgtworld.civcrawler.entities.User;
import pl.vgtworld.civcrawler.services.UsersService;
import pl.vgtworld.civcrawler.utils.UserUtils;

class LoginFormValidator {
	
	enum ErrorMessages {
		LOGIN_ERROR("Wrong login or password."),
		SYSTEM_ERROR("Unexpected system error. Please try again.");
		
		private String message;
		
		private ErrorMessages(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
	};
	
	private List<String> validationErrors = new ArrayList<>();
	
	public String[] getErrors() {
		return validationErrors.toArray(new String[validationErrors.size()]);
	}

	public boolean validate(LoginFormDto dto, UsersService usersService) {
		try {
			String login = dto.getLogin();
			if (login == null) {
				validationErrors.add(ErrorMessages.LOGIN_ERROR.getMessage());
				return false;
			}
			User user = usersService.findByLogin(login);
			if (user == null) {
				validationErrors.add(ErrorMessages.LOGIN_ERROR.getMessage());
				return false;
			}
			String passwordHash = UserUtils.generatePasswordHash(dto.getPassword(), user.getSalt());
			if (!passwordHash.equals(user.getPassword())) {
				validationErrors.add(ErrorMessages.LOGIN_ERROR.getMessage());
				return false;
			}
			
			return true;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			validationErrors.add(ErrorMessages.SYSTEM_ERROR.getMessage());
			return false;
		}
	}
	
}
