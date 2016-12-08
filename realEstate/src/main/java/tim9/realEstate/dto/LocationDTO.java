package tim9.realEstate.dto;

import tim9.realEstate.model.Location;

public class LocationDTO {
	
	private Long id;
	private String address;
	private String city;
	private int zipCode;
	private String partOfTheCity;
	
	public LocationDTO() {
		super();
	}
	
	public LocationDTO(Location l) {
		this(l.getId(), l.getAddress(), l.getCity(), l.getZipCode(), l.getPartOfTheCity());
	}

	public LocationDTO(Long id, String address, String city, int zipCode, String partOfTheCity) {
		super();
		this.id = id;
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

}
