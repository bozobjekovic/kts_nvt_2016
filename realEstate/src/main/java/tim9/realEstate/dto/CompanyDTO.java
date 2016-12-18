package tim9.realEstate.dto;

import tim9.realEstate.model.Company;

/**
 * This class represent data transfer object for Company class
 */
public class CompanyDTO {
	
	private Long id;
	private String address;
	private String name;
	private LocationDTO location;
	private String phoneNumber;
	private String site;
	
	/**
	 * Constructor created from Superclass
	 */
	public CompanyDTO() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param c represents Company object
	 */
	public CompanyDTO(Company c) {
		this(c.getId(), c.getAddress(), c.getName(), new LocationDTO(c.getLocation()), c.getPhoneNumber(), c.getSite());
	}

	/**
	 * Constructor
	 * 
	 * @param id represents Company ID
	 * @param name represents Name of the Company
	 * @param location represents Location of the Company
	 * @param phoneNumber represents Phone Number of the Company
	 * @param site represents Site of the Company
	 */
	public CompanyDTO(Long id, String address, String name, LocationDTO location, String phoneNumber, String site) {
		super();
		this.id = id;
		this.address = address;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
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
	
	

}
