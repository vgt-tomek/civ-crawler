package pl.vgtworld.civcrawler.actions.register;

import java.util.ArrayList;
import java.util.List;

import pl.vgtworld.civcrawler.services.UsersService;

class RegisterFormValidator {
	
	enum ErrorMessages {
		LOGIN_REQUIRED("Login is required."),
		LOGIN_TOO_SHORT("Login is too short. Minimum length: " + LOGIN_MIN_LENGTH + " characters."),
		LOGIN_TOO_LONG("Login is too long. Maximum length: " + LOGIN_MAX_LENGTH + " characters."),
		LOGIN_TAKEN("Login is already taken."),
		PASSWORD_REQUIRED("Password is required."),
		PASSWORD_MISMATCH("Passwords doesn't match.");
		
		private String message;
		
		private ErrorMessages(String message) {
			this.message = message;
		}
		
		String getMessage() {
			return message;
		}
	};

	private static final int LOGIN_MIN_LENGTH = 3;
	
	private static final int LOGIN_MAX_LENGTH = 50;
	
	private List<String> validationErrors = new ArrayList<>();
	
	public String[] getErrors() {
		return validationErrors.toArray(new String[validationErrors.size()]);
	}

	public boolean validate(RegisterFormDto dto, UsersService service) {
		validateLogin(dto, service);
		validatePassword(dto);
		return validationErrors.size() == 0;
	}
	
	private boolean validateLogin(RegisterFormDto dto, UsersService service) {
		String login = dto.getLogin();
		
		if (login == null || login.length() == 0) {
			validationErrors.add(ErrorMessages.LOGIN_REQUIRED.getMessage());
			return false;
		}
		if (login.length() < LOGIN_MIN_LENGTH) {
			validationErrors.add(ErrorMessages.LOGIN_TOO_SHORT.getMessage());
			return false;
		}
		if (login.length() > LOGIN_MAX_LENGTH) {
			validationErrors.add(ErrorMessages.LOGIN_TOO_LONG.getMessage());
			return false;
		}
		if (!service.isLoginAvailable(login)) {
			validationErrors.add(ErrorMessages.LOGIN_TAKEN.getMessage());
		}
		
		return true;
	}
	
	private boolean validatePassword(RegisterFormDto dto) {
		String password = dto.getPassword();
		String passwordRepeat = dto.getPasswordRepeat();
		
		if (password == null || passwordRepeat == null || password.length() == 0) {
			validationErrors.add(ErrorMessages.PASSWORD_REQUIRED.getMessage());
			return false;
		}
		if (!password.equals(passwordRepeat)) {
			validationErrors.add(ErrorMessages.PASSWORD_MISMATCH.getMessage());
		}
		
		return true;
	}
}
