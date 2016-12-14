package tim9.realEstate.dto;

/**
 * This class represent data transfer object for LoginUser class
 */
public class LoginUserDTO {
	
	private String username;
	private String password;
	
	/**
	 * Constructor created from Superclass
	 */
	public LoginUserDTO() { super(); }
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginUserDTO [username=" + username + ", password=" + password + "]";
	}
	
}
