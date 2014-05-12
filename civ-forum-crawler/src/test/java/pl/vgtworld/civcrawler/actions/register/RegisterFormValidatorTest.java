package pl.vgtworld.civcrawler.actions.register;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.vgtworld.civcrawler.services.UsersService;

@RunWith(MockitoJUnitRunner.class)
public class RegisterFormValidatorTest {
	
	private static final String LOGIN = "login";

	private static final String SHORT_LOGIN = "a";
	
	private static final String LONG_LOGIN = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxy";
	
	private static final String SAMPLE_VALID_LOGIN = "Valid login_123";
	
	private static final String SAMPLE_INVALID_LOGIN = "$ample invalid login-123";
	
	private static final String PASSWORD = "password";
	
	private static final String EMPTY_STRING = "";
	
	@Mock
	UsersService service;
	
	@Test
	public void shouldValidateProperDto() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).isEmpty();
	}
	
	@Test
	public void shouldNotAcceptAlreadyTakenLogin() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		when(service.isLoginAvailable(anyString())).thenReturn(false);
		
		boolean result = validator.validate(dto, service);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.LOGIN_TAKEN.getMessage());
	}
	
	@Test
	public void shouldNotAcceptNullLogin() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setLogin(null);
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
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
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
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
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
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
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
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
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
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
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
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
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
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
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.PASSWORD_MISMATCH.getMessage());
	}
	
	@Test
	public void shouldAcceptLoginWithValidCharacters() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setLogin(SAMPLE_VALID_LOGIN);
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
		String[] errors = validator.getErrors();
		
		assertThat(result).isTrue();
		assertThat(errors).isEmpty();
	}
	
	@Test
	public void shouldNotAcceptLoginWithInvalidCharacters() {
		RegisterFormValidator validator = new RegisterFormValidator();
		RegisterFormDto dto = createValidDto();
		dto.setLogin(SAMPLE_INVALID_LOGIN);
		when(service.isLoginAvailable(anyString())).thenReturn(true);
		
		boolean result = validator.validate(dto, service);
		String[] errors = validator.getErrors();
		
		assertThat(result).isFalse();
		assertThat(errors).hasSize(1);
		assertThat(errors[0]).isEqualTo(RegisterFormValidator.ErrorMessages.LOGIN_NOT_ALLOWED_CHARACTERS.getMessage());
	}
	
	private RegisterFormDto createValidDto() {
		RegisterFormDto dto = new RegisterFormDto();
		dto.setLogin(LOGIN);
		dto.setPassword(PASSWORD);
		dto.setPasswordRepeat(PASSWORD);
		return dto;
	}
}
