package tim9.realEstate.dto;

public class VerifierDTO {
	
	private String email;
	private String username;
	private String password;
	
	public VerifierDTO() {
		super();
	}

	public VerifierDTO(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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


}
