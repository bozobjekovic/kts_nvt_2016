package tim9.realEstate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	private Long id;
	@Column(nullable = false)
	private Date publicationDate;
	private Date modificationDate;
	private Date activeUntil;
	@Column(nullable = false)
	private String purpose;
	private double rate;
	private int numberOfRates;
	@Column(unique = true)
	private String phoneNumber;
	@ManyToOne
	private Verifier verifier;
	@ManyToOne
	private User publisher;
	@ManyToOne
	private User buyer;
	@ManyToOne(cascade = CascadeType.ALL)
	private RealEstate realEstate;
	@OneToMany(mappedBy = "advertisment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Comment> comments = new HashSet<Comment>(0);
	private boolean isDeleted;
	
	public Advertisment() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
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

	public int getNumberOfRates() {
		return numberOfRates;
	}

	public void setNumberOfRates(int numberOfRates) {
		this.numberOfRates = numberOfRates;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Advertisment [id=" + id + ", publicationDate=" + publicationDate + ", modificationDate="
				+ modificationDate + ", activeUntil=" + activeUntil + ", purpose=" + purpose + ", rate=" + rate
				+ ", phoneNumber=" + phoneNumber + ", verifier=" + verifier + ", publisher=" + publisher + ", buyer="
				+ buyer + ", realEstate=" + realEstate + ", comments="  + "]";
	}

}
