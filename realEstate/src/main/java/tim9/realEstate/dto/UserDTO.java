package tim9.realEstate.dto;

import tim9.realEstate.model.User;

/**
 * This class represent data transfer object for User class
 */
public class UserDTO {
	
	private Long id;
	private String email;
	private String username;
	private String name;
	private String surname;
	private String phoneNumber;
	private String address;
	private String city;
	private double rate;
	private int bankAccount;
	private String image;
	
	/**
	 * Constructor created from Superclass
	 */
	public UserDTO() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param u represents User object
	 */
	public UserDTO(User u) {
		this(u.getId(), u.getEmail(), u.getUsername(), u.getName(), u.getSurname(), u.getPhoneNumber(), u.getAddress(),
				u.getCity(), u.getRate(), u.getBankAccount(), u.getImage());
	}
	
	/**
	 * Constructor
	 * 
	 * @param id represents User ID
	 * @param email represents Email of the User
	 * @param username represents User name of the User
	 * @param name represents Name of the User
	 * @param surname represents Surname of the User
	 * @param phoneNumber represents Phone Number of the User
	 * @param address represents Address of the User
	 * @param city represents City of the User
	 * @param rate represents average rate of the User
	 * @param bankAccount represents Bank account of the User
	 * @param image represents Image of the User
	 */
	public UserDTO(Long id, String email, String username, String name, String surname, String phoneNumber, String address,
			String city, double rate, int bankAccount, String image) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.city = city;
		this.rate = rate;
		this.bankAccount = bankAccount;
		this.image = image;
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(int bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", username=" + username + ", name=" + name + ", surname="
				+ surname + ", phoneNumber=" + phoneNumber + ", address=" + address + ", city=" + city + ", rate="
				+ rate + ", bankAccount=" + bankAccount + ", image=" + image + "]";
	}
	
	

}
