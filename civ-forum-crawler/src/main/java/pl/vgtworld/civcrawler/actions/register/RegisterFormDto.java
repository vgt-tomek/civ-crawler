package pl.vgtworld.civcrawler.actions.register;

public class RegisterFormDto {
	
	private String login;
	
	private String password;
	
	private String passwordRepeat;
	
	public RegisterFormDto() {
	}
	
	public RegisterFormDto(String login, String password, String passwordRepeat) {
		this.login = login;
		this.password = password;
		this.passwordRepeat = passwordRepeat;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordRepeat() {
		return passwordRepeat;
	}
	
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
	
}
