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
	private boolean isApplied;
	private CompanyDTO company;

	/**
	 * Constructor created from Superclass
	 */
	public UserDTO() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param u
	 *            represents User object
	 */
	public UserDTO(User u) {
		id = u.getId();
		email = u.getEmail();
		username = u.getUsername();
		name = u.getName();
		surname = u.getSurname();
		phoneNumber = u.getPhoneNumber();
		address = u.getAddress();
		city = u.getCity();
		rate = u.getRate();
		bankAccount = u.getBankAccount();
		image = u.getImage();

		if (u.getCompany() != null) {
			this.company = new CompanyDTO(u.getCompany());
		}
		if (u.getAppliedCompany() != null) {
			this.isApplied = true;
		}
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

	public boolean isApplied() {
		return isApplied;
	}

	public void setApplied(boolean isApplied) {
		this.isApplied = isApplied;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", username=" + username + ", name=" + name + ", surname="
				+ surname + ", phoneNumber=" + phoneNumber + ", address=" + address + ", city=" + city + ", rate="
				+ rate + ", bankAccount=" + bankAccount + ", image=" + image + ", isApplied=" + isApplied + "]";
	}

}
