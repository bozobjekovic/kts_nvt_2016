package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Company implements Serializable{

	private static final long serialVersionUID = 6601870539652405141L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToOne
	private Location location;
	private String phoneNumber;
	private String site;
	
	public Company() {
		super();
	}

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

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", site=" + site + "]";
	}
	

}
