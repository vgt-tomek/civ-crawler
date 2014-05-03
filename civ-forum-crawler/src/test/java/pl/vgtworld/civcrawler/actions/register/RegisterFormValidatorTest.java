package pl.vgtworld.civcrawler.actions.register;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RegisterFormValidatorTest {
	
	private static final String LOGIN = "login";

	private static final String SHORT_LOGIN = "a";
	
	private static final String LONG_LOGIN = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy";
	
	private static final String PASSWORD = "password";
	
	private static final String EMPTY_STRING = "";
	
	@Test
	public void shouldValidateProperDto() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		
		boolean result = validator.validate(dto);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).isEmpty();
	}
	
	@Test
	public void shouldNotAcceptNullLogin() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setLogin(null);
		
		boolean result = validator.validate(dto);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.LOGIN_REQUIRED.getMessage());
	}

	@Test
	public void shouldNotAcceptEmptyLogin() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setLogin(EMPTY_STRING);
		
		boolean result = validator.validate(dto);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.LOGIN_REQUIRED.getMessage());
	}

	@Test
	public void shouldNotAcceptTooShortLogin() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setLogin(SHORT_LOGIN);
		
		boolean result = validator.validate(dto);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.LOGIN_TOO_SHORT.getMessage());
	}

	@Test
	public void shouldNotAcceptTooLongLogin() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setLogin(LONG_LOGIN);
		
		boolean result = validator.validate(dto);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.LOGIN_TOO_LONG.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNullPassword() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setPassword(null);
		
		boolean result = validator.validate(dto);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.PASSWORD_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNullPasswordRepeat() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setPasswordRepeat(null);
		
		boolean result = validator.validate(dto);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.PASSWORD_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptEmptyPassword() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setPassword(EMPTY_STRING);
		
		boolean result = validator.validate(dto);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.PASSWORD_REQUIRED.getMessage());
	}
	
	@Test
	public void shouldNotAcceptDifferentPasswords() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setPasswordRepeat("other-password");
		
		boolean result = validator.validate(dto);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.PASSWORD_MISMATCH.getMessage());
	}
	
	private RegisterFormDto createValidDto() {
		RegisterFormDto dto = new RegisterFormDto();
		dto.setLogin(LOGIN);
		dto.setPassword(PASSWORD);
		dto.setPasswordRepeat(PASSWORD);
		return dto;
	}
}
