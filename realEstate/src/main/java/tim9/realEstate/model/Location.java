package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import tim9.realEstate.dto.LocationDTO;

/**
 * This class represent Location bean class
 */
@Entity
public class Location implements Serializable{

	private static final long serialVersionUID = -7237744594366208227L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private int zipCode;
	private String partOfTheCity;

	/**
	 * Constructor created from Superclass
	 */
	public Location() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param l represents LocationDTO object
	 */
	public Location(LocationDTO l) {
		this(l.getAddress(), l.getCity(), l.getZipCode(), l.getPartOfTheCity());
	}

	/**
	 * Constructor
	 * 
	 * @param address represents Address of the Location
	 * @param city represents City of the Location
	 * @param zipCode represents Zip code of the Location
	 * @param partOfTheCity represents Part of the City of the Location
	 */
	public Location(String address, String city, int zipCode, String partOfTheCity) {
		super();
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.partOfTheCity = partOfTheCity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getPartOfTheCity() {
		return partOfTheCity;
	}

	public void setPartOfTheCity(String partOfTheCity) {
		this.partOfTheCity = partOfTheCity;
	}

	@Override
	public String toString() {
		return "Location [address=" + address + ", city=" + city + ", zipCode=" + zipCode + ", partOfTheCity="
				+ partOfTheCity + "]";
	}
}
