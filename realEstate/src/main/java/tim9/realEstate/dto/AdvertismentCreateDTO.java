package tim9.realEstate.dto;

import java.util.Date;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;
import tim9.realEstate.model.RealEstate;

public class AdvertismentCreateDTO {
	
	private Long advertismentId;
	private Date activeUntil;
	private String purpose;
	private String phoneNumber;
	private double rate;
	
	private Long realEstateId;
	private String name;
	private LocationDTO location;
	private double price;
	private int landSize;
	private String techEquipment;
	private String heatingType;
	private String image;
	private int numOfBathRooms;
	private int numOfBedRooms;
	private int numOfFlors;
	private int buildYear;
	private Category category;
	private String type;
	
	public AdvertismentCreateDTO() {
		super();
	}
	
	public AdvertismentCreateDTO(Advertisment a, RealEstate e) {
		this(a.getId(), a.getActiveUntil(), a.getPurpose(), a.getPhoneNumber(), a.getRate(), e.getId(), e.getName(),
				new LocationDTO(e.getLocation()), e.getPrice(), e.getLandSize(), e.getTechEquipment(),
				e.getHeatingType(), e.getImage(), e.getNumOfBathRooms(),e.getNumOfBedRooms(), e.getNumOfFlors(),
				e.getBuildYear(), e.getCategory(), e.getType());
	}

	public AdvertismentCreateDTO(Long advertismentId, Date activeUntil, String purpose, String phoneNumber, double rate,
			Long realEstateId, String name, LocationDTO location, double price, int landSize, String techEquipment,
			String heatingType, String image, int numOfBathRooms, int numOfBedRooms, int numOfFlors, int buildYear,
			Category category, String type) {
		super();
		this.advertismentId = advertismentId;
		this.activeUntil = activeUntil;
		this.purpose = purpose;
		this.phoneNumber = phoneNumber;
		this.rate = rate;
		this.realEstateId = realEstateId;
		this.name = name;
		this.location = location;
		this.price = price;
		this.landSize = landSize;
		this.techEquipment = techEquipment;
		this.heatingType = heatingType;
		this.image = image;
		this.numOfBathRooms = numOfBathRooms;
		this.numOfBedRooms = numOfBedRooms;
		this.numOfFlors = numOfFlors;
		this.buildYear = buildYear;
		this.category = category;
		this.type = type;
	}

	public Long getAdvertismentId() {
		return advertismentId;
	}

	public void setAdvertismentId(Long advertismentId) {
		this.advertismentId = advertismentId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getRealEstateId() {
		return realEstateId;
	}

	public void setRealEstateId(Long realEstateId) {
		this.realEstateId = realEstateId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getLandSize() {
		return landSize;
	}

	public void setLandSize(int landSize) {
		this.landSize = landSize;
	}

	public String getTechEquipment() {
		return techEquipment;
	}

	public void setTechEquipment(String techEquipment) {
		this.techEquipment = techEquipment;
	}

	public String getHeatingType() {
		return heatingType;
	}

	public void setHeatingType(String heatingType) {
		this.heatingType = heatingType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNumOfBathRooms() {
		return numOfBathRooms;
	}

	public void setNumOfBathRooms(int numOfBathRooms) {
		this.numOfBathRooms = numOfBathRooms;
	}

	public int getNumOfBedRooms() {
		return numOfBedRooms;
	}

	public void setNumOfBedRooms(int numOfBedRooms) {
		this.numOfBedRooms = numOfBedRooms;
	}

	public int getNumOfFlors() {
		return numOfFlors;
	}

	public void setNumOfFlors(int numOfFlors) {
		this.numOfFlors = numOfFlors;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
