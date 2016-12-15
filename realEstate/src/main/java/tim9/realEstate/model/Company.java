package tim9.realEstate.model;

import java.io.Serializable;
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

import tim9.realEstate.dto.RegistrateUserDTO;

/**
 * This class represent Company bean class
 */
@Entity
public class Company implements Serializable{

	private static final long serialVersionUID = 6601870539652405141L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Location location;
	@Column(unique = true)
	private String phoneNumber;
	private String site;
	@OneToMany(mappedBy = "appliedCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> usersToApprove = new HashSet<User>(0);
	
	/**
	 * Constructor created from Superclass
	 */
	public Company() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param regCompany represents RegistrateUserDTO object
	 */
	public Company(RegistrateUserDTO regCompany) {
		this(regCompany.getCompanyName(), new Location(regCompany.getCompanyAddress(), 
				regCompany.getCompanyCity(), regCompany.getZipCode(), regCompany.getPartOfTheCity()), 
				regCompany.getCompanyPhoneNumber(), regCompany.getSite());
	}

	/**
	 * Constructor
	 * 
	 * @param name represents Name of the Company
	 * @param location represents Location of the Company
	 * @param phoneNumber represents Phone number of the Company
	 * @param site represents Site of the Company
	 */
	public Company(String name, Location location, String phoneNumber, String site) {
		super();
		this.name = name;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.site = site;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Set<User> getUsersToApprove() {
		return usersToApprove;
	}

	public void setUsersToApprove(Set<User> usersToApprove) {
		this.usersToApprove = usersToApprove;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", site=" + site + "]";
	}
	

}
