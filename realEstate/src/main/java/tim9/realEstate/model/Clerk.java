package tim9.realEstate.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Clerk extends Person implements Serializable{

	private static final long serialVersionUID = 1993253230077699603L;
	
	private double rate;
	private int bankAccount;
	@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> publishedAdvertisments = new HashSet<Advertisment>();
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Advertisment> buyedAdvertisments = new HashSet<Advertisment>();
	@ManyToOne
	private Company company;
	
	public Clerk() {
		super();
	}

	public Clerk(String email, String username, String password, String name, String surname, String phoneNumber,
			String address, String city, String image, double rate, int bankAccount, Set<Advertisment> myAdvertisments,Set<Advertisment> buyedAdvertisments, Company company) {
		super(email, username, password, name, surname, phoneNumber, address, city, image);
		this.rate = rate;
		this.bankAccount = bankAccount;
		this.publishedAdvertisments = myAdvertisments;
		this.buyedAdvertisments = buyedAdvertisments;
		this.company = company;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Clerk [rate=" + rate + ", bankAccount=" + bankAccount + ", publishedAdvertisments="
				+ publishedAdvertisments + ", buyedAdvertisments=" + buyedAdvertisments + ", company=" + company + "]";
	}
	
}
