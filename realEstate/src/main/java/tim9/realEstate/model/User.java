package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import tim9.realEstate.dto.RegistrateUserDTO;

@Entity
public class User extends Person implements Serializable{

	private static final long serialVersionUID = -9118880198314430296L;
	
	private int numOfRates;
	private double rate;
	@Column(unique = true)
	private int bankAccount;
	private boolean isClerk;
	private boolean isApproved;
	/*@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> publishedAdvertisments = new HashSet<Advertisment>();
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> buyedAdvertisments = new HashSet<Advertisment>();*/
	@ManyToOne(cascade = CascadeType.ALL)
	private Company company;

	public User() {
		super();
	}
	
	public User(RegistrateUserDTO registrateUser, String type, Authority authority) {
		this(registrateUser.getEmail(), registrateUser.getUsername(), registrateUser.getPassword(), 
				registrateUser.getName(), registrateUser.getSurname(), registrateUser.getPhoneNumber(), 
				registrateUser.getAddress(), registrateUser.getCity(), authority, registrateUser.getImage(), 
				0, 0, registrateUser.getBankAccount(), false, false, null);
		if (type.equals("clerk")) {
			this.setClerk(true);
			this.setCompany(new Company(registrateUser.getCompanyName(), new Location(registrateUser.getCompanyAddress(), 
				registrateUser.getCompanyCity(), registrateUser.getZipCode(), registrateUser.getPartOfTheCity()), 
				registrateUser.getCompanyPhoneNumber(), registrateUser.getSite()));
		}
	}

	public User(String email, String username, String password, String name, String surname, String phoneNumber,
			String address, String city, Authority authority, String image, int numOfRates, double rate,
			int bankAccount, boolean isClerk, boolean isApproved, Company company) {
		super(email, username, password, name, surname, phoneNumber, address, city, authority, image);
		this.numOfRates = numOfRates;
		this.rate = rate;
		this.bankAccount = bankAccount;
		this.isClerk = isClerk;
		this.isApproved = isApproved;
		this.company = company;
	}

	@Override
	public String toString() {
		return "User [numOfRates=" + numOfRates + ", rate=" + rate + ", bankAccount=" + bankAccount + ", clerk=" + isClerk
				+ ", company=" + company + "]";
	}

	public int getNumOfRates() {
		return numOfRates;
	}

	public void setNumOfRates(int numOfRates) {
		this.numOfRates = numOfRates;
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

	public boolean isClerk() {
		return isClerk;
	}

	public void setClerk(boolean isClerk) {
		this.isClerk = isClerk;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
