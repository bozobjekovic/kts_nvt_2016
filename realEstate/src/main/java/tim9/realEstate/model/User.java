package tim9.realEstate.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import tim9.realEstate.dto.RegistrateUserDTO;

/**
 * This class represent User bean class
 */
@Entity
public class User extends Person implements Serializable{

	private static final long serialVersionUID = -9118880198314430296L;
	
	private int numOfRates;
	private double rate;
	private int bankAccount;
	private boolean isClerk;
	private boolean isApproved;
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> publishedAdvertisments = new HashSet<>(0);
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> buyedAdvertisments = new HashSet<>(0);
	@ManyToOne(cascade = CascadeType.ALL)
	private Company company;
	@ManyToOne(cascade = CascadeType.ALL)
	private Company appliedCompany;

	/**
	 * Constructor created from Superclass
	 */
	public User() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param registrateUser represents RegistrateUserDTO object
	 * @param type represents Type of the User
	 * @param authority represents Authority that the User possesses
	 */
	public User(RegistrateUserDTO registrateUser, String type, Authority authority) {
		super(registrateUser.getEmail(), registrateUser.getUsername(), registrateUser.getPassword(), 
				registrateUser.getName(), registrateUser.getSurname(), registrateUser.getPhoneNumber(), 
				registrateUser.getAddress(), registrateUser.getCity(), authority, registrateUser.getImage());
		numOfRates = 0;
		rate = 0;
		bankAccount = registrateUser.getBankAccount();
		isClerk = false;
		isApproved = true;
		company = null;

		if ("clerk".equals(type)) {
			this.setClerk(true);
			this.setApproved(false);
			this.setCompany(new Company(registrateUser.getCompanyName(), registrateUser.getAddress(), new Location(
				registrateUser.getCompanyLocation().getCity(), registrateUser.getCompanyLocation().getZipCode(), registrateUser.getCompanyLocation().getPartOfTheCity()), 
				registrateUser.getCompanyPhoneNumber(), registrateUser.getSite()));
		}
	}

	@Override
	public String toString() {
		return "User id : " + super.getId();
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

	public Set<Advertisment> getPublishedAdvertisments() {
		return publishedAdvertisments;
	}

	public void setPublishedAdvertisments(Set<Advertisment> publishedAdvertisments) {
		this.publishedAdvertisments = publishedAdvertisments;
	}

	public Set<Advertisment> getBuyedAdvertisments() {
		return buyedAdvertisments;
	}

	public void setBuyedAdvertisments(Set<Advertisment> buyedAdvertisments) {
		this.buyedAdvertisments = buyedAdvertisments;
	}

	public Company getAppliedCompany() {
		return appliedCompany;
	}

	public void setAppliedCompany(Company appliedCompany) {
		this.appliedCompany = appliedCompany;
	}
}
