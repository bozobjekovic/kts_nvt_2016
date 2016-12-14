package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * This class represent Person bean class
 */
@MappedSuperclass
public abstract class Person implements Serializable{

	private static final long serialVersionUID = 1156219148979456655L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	private String phoneNumber;
	private String address;
	private String city;
	@ManyToOne
	private Authority authority;
	@Lob
	private String image;

	/**
	 * Constructor created from Superclass
	 */
	public Person() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param email represents Email of the Person
	 * @param username represents User name of the Person
	 * @param password represents Password of the Person
	 * @param name represents Name of the Person
	 * @param surname represents Surname of the Person
	 * @param phoneNumber represents Phone Number of the Person
	 * @param address represents Address of the Person
	 * @param city represents City of the Person
	 * @param authority represents Authority that the Person possesses
	 * @param image represents Image of the Person
	 */
	public Person(String email, String username, String password, String name, String surname, String phoneNumber,
			String address, String city, Authority authority, String image) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.authority = authority;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + ", name="
				+ name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", address=" + address + ", city="
				+ city + ", authority=" + authority + ", image=" + image + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
