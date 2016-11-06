package tim9.realEstate.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class User extends Person implements Serializable{

	private static final long serialVersionUID = -9118880198314430296L;
	
	private double rate;
	private int bankAccount;
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> publishedAdvertisments = new HashSet<Advertisment>();
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> buyedAdvertisments = new HashSet<Advertisment>();

	public User() {
		super();
	}

	public User(String email, String username, String password, String name, String surname, String phoneNumber,
			String address, String city, String image, double rate, int bankAccount, Set<Advertisment> myAdvertisments,Set<Advertisment> buyedAdvertisments) {
		super(email, username, password, name, surname, phoneNumber, address, city, image);
		this.rate = rate;
		this.bankAccount = bankAccount;
		this.publishedAdvertisments = myAdvertisments;
		this.buyedAdvertisments = buyedAdvertisments;
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

	public Set<Advertisment> getMyAdvertisments() {
		return publishedAdvertisments;
	}

	public void setMyAdvertisments(Set<Advertisment> myAdvertisments) {
		this.publishedAdvertisments = myAdvertisments;
	}

	public Set<Advertisment> getBuyedAdvertisments() {
		return buyedAdvertisments;
	}

	public void setBuyedAdvertisments(Set<Advertisment> buyedAdvertisments) {
		this.buyedAdvertisments = buyedAdvertisments;
	}

	@Override
	public String toString() {
		return "User [rate=" + rate + ", bankAccount=" + bankAccount + ", myAdvertisments=" + publishedAdvertisments
				+ ", buyedAdvertisments=" + buyedAdvertisments + "]";
	}

	
}
