package tim9.realEstate.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Advertisment implements Serializable{

	private static final long serialVersionUID = 6321063725126000005L;
	
	@Id
	@GeneratedValue
	private int id;
	private Date publicationDate;
	private Date modificationDate;
	private Date activeUntil;
	private double rate;
	private String phoneNumber;
	@ManyToOne
	private Verifier verifier;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User publisher;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User buyer;
	@ManyToOne
	private RealEstate realEstate;
	@OneToMany(mappedBy = "advertisment", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Set<Comment> comments = new HashSet<Comment>();
	
	public Advertisment() {
		super();
	}

	public Advertisment(Date publicationDate, Date modificationDate, Date activeUntil, double rate, String phoneNumber,
			Verifier verifier, User publisher, User buyer, RealEstate realEstate, Set<Comment> comments) {
		super();
		this.publicationDate = publicationDate;
		this.modificationDate = modificationDate;
		this.activeUntil = activeUntil;
		this.rate = rate;
		this.phoneNumber = phoneNumber;
		this.verifier = verifier;
		this.publisher = publisher;
		this.buyer = buyer;
		this.realEstate = realEstate;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Date getActiveUntil() {
		return activeUntil;
	}

	public void setActiveUntil(Date activeUntil) {
		this.activeUntil = activeUntil;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Verifier getVerifier() {
		return verifier;
	}

	public void setVerifier(Verifier verifier) {
		this.verifier = verifier;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public RealEstate getRealEstate() {
		return realEstate;
	}

	public void setRealEstate(RealEstate realEstate) {
		this.realEstate = realEstate;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Advertisment [id=" + id + ", publicationDate=" + publicationDate + ", modificationDate="
				+ modificationDate + ", activeUntil=" + activeUntil + ", rate=" + rate + ", phoneNumber=" + phoneNumber
				+ "]";
	}

}
