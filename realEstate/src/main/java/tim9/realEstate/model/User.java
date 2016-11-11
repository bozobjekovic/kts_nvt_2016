package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class User extends Person implements Serializable{

	private static final long serialVersionUID = -9118880198314430296L;
	
	private double rate;
	private int bankAccount;
	private boolean clerk;
	/*@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> publishedAdvertisments = new HashSet<Advertisment>();
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> buyedAdvertisments = new HashSet<Advertisment>();*/
	@ManyToOne
	private Company company;

	public User() {
		super();
	}

	public User(String email, String username, String password, String name, String surname, String phoneNumber,
			String address, String city, Authority authority, String image, double rate, int bankAccount, boolean clerk,
			Company company) {
		super(email, username, password, name, surname, phoneNumber, address, city, authority, image);
		this.rate = rate;
		this.bankAccount = bankAccount;
		this.clerk = clerk;
		this.company = company;
	}

	@Override
	public String toString() {
		return "User [rate=" + rate + ", bankAccount=" + bankAccount + ", clerk=" + clerk + ", company=" + company
				+ "]";
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
		return clerk;
	}

	public void setClerk(boolean clerk) {
		this.clerk = clerk;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
