package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import tim9.realEstate.dto.VerifierDTO;

/**
 * This class represent Verifier bean class
 */
@Entity
public class Verifier implements Serializable{

	private static final long serialVersionUID = -2991029351829583301L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@ManyToOne(cascade = CascadeType.ALL)
	private Authority authority;

	/**
	 * Constructor
	 */
	public Verifier() {

	}

	/**
	 * Constructor
	 * 
	 * @param verifierDTO represents VerifierDTO object
	 * @param authority represents Authority that the Verifier possesses
	 */
	public Verifier(VerifierDTO verifierDTO, Authority authority) {
		this.email = verifierDTO.getEmail();
		this.username = verifierDTO.getUsername();
		this.password = verifierDTO.getPassword();
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
