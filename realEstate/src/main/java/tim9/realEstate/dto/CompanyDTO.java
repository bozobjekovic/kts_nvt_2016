package tim9.realEstate.dto;

import tim9.realEstate.model.Company;

public class CompanyDTO {
	
	private Long id;
	private String name;
	private LocationDTO location;
	private String phoneNumber;
	private String site;
	
	public CompanyDTO() {
		super();
	}

	public CompanyDTO(Company c) {
		this(c.getId(), c.getName(), new LocationDTO(c.getLocation()), c.getPhoneNumber(), c.getSite());
	}

	public CompanyDTO(Long id, String name, LocationDTO location, String phoneNumber, String site) {
		super();
		this.id = id;
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
