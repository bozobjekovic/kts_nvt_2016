package tim9.realEstate.dto;

import java.util.Date;
import java.util.List;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;
import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.Status;

/**
 * This class represent data transfer object for Advertisement class
 */
public class AdvertismentCreateDTO {

	private Long advertismentId;
	private Date activeUntil;
	private String purpose;
	private String phoneNumber;
	private double price;
	private double rate;

	private Long realEstateId;
	private String name;
	private LocationDTO location;
	private int landSize;
	private String techEquipment;
	private String address;
	private String heatingType;
	private String backgroungImage;
	private List<String> images;
	private int numOfBathRooms;
	private int numOfBedRooms;
	private int numOfFlors;
	private int buildYear;
	private Category category;
	private String type;
	private Status status;

	/**
	 * Constructor created from Superclass
	 */
	public AdvertismentCreateDTO() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param a
	 *            represents Advertisement object
	 * @param e
	 *            represents RealEstate object
	 */
	public AdvertismentCreateDTO(Advertisment a, RealEstate e) {
		advertismentId = a.getId();
		activeUntil = a.getActiveUntil();
		purpose = a.getPurpose();
		phoneNumber = a.getPhoneNumber();
		address = e.getAddress();
		rate = a.getRate();
		realEstateId = e.getId();
		name = a.getName();
		location = new LocationDTO(e.getLocation());
		price = a.getPrice();
		landSize = e.getLandSize();
		techEquipment = e.getTechEquipment();
		heatingType = e.getHeatingType();
		backgroungImage = a.getBackgroundImage();
		images = a.getImages();
		numOfBathRooms = e.getNumOfBathRooms();
		numOfBedRooms = e.getNumOfBedRooms();
		numOfFlors = e.getNumOfFlors();
		buildYear = e.getBuildYear();
		category = e.getCategory();
		type = e.getType();
		status = e.getStatus();
	}

	@Override
	public String toString() {
		return "AdvertismentCreateDTO [advertismentId=" + advertismentId + ", activeUntil=" + activeUntil + ", purpose="
				+ purpose + ", phoneNumber=" + phoneNumber + ", price=" + price + ", rate=" + rate + ", realEstateId="
				+ realEstateId + ", name=" + name + ", location=" + location + ", landSize=" + landSize
				+ ", techEquipment=" + techEquipment + ", address=" + address + ", heatingType=" + heatingType
				+ ", images=" + images + ", numOfBathRooms=" + numOfBathRooms + ", numOfBedRooms=" + numOfBedRooms
				+ ", numOfFlors=" + numOfFlors + ", buildYear=" + buildYear + ", category=" + category + ", type="
				+ type + "]";
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBackgroungImage() {
		return backgroungImage;
	}

	public void setBackgroungImage(String backgroungImage) {
		this.backgroungImage = backgroungImage;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
