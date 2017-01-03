package tim9.realEstate.dto;

import tim9.realEstate.model.Location;

/**
 * This class represent data transfer object for Location class
 */
public class LocationDTO {
	
	private Long id;
	private String companyCity;
	private int zipCode;
	private String partOfTheCity;

	/**
	 * Constructor created from Superclass
	 */
	public LocationDTO() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param l represents Location ID
	 */
	public LocationDTO(Location l) {
		this(l.getId(), l.getCity(), l.getZipCode(), l.getPartOfTheCity());
	}

	/**
	 * Constructor
	 * 
	 * @param id represents Location ID
	 * @param address represents Address of the Real Estate
	 * @param city represents City where the Real Estate is
	 * @param zipCode represents Zip code of City where the Real Estate is
	 * @param partOfTheCity represents Part of the City where the Real Estate is
	 */
	public LocationDTO(Long id, String city, int zipCode, String partOfTheCity) {
		super();
		this.id = id;
		this.companyCity = city;
		this.zipCode = zipCode;
		this.partOfTheCity = partOfTheCity;
	}
	
	@Override
	public String toString() {
		return "LocationDTO [id=" + id + ", companyCity=" + companyCity + ", zipCode=" + zipCode + ", partOfTheCity="
				+ partOfTheCity + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return companyCity;
	}

	public void setCity(String city) {
		this.companyCity = city;
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

}
