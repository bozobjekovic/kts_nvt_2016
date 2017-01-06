package tim9.realEstate.dto;

import tim9.realEstate.model.Verifier;

/**
 * This class represent data transfer object for Verifier class
 */
public class VerifierDTO {
	
	private String email;
	private String username;
	private String password;

	/**
	 * Constructor created from Superclass
	 */
	public VerifierDTO() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param email represents Email of the Verifier
	 * @param username represents User name of the Verifier
	 * @param password represents Password of the Verifier
	 */
	public VerifierDTO(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public VerifierDTO(Verifier verifier) {
		this.email = verifier.getEmail();
		this.username = verifier.getUsername();
		this.password = verifier.getPassword();
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
