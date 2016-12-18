package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * This class represent RealEstate bean class
 */
@Entity
public class RealEstate implements Serializable{
	
	private static final long serialVersionUID = -1133371741893571966L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String address;
	@ManyToOne
	private Location location;
	private int landSize;
	private String techEquipment;
	private String heatingType;
	private int numOfBathRooms;
	private int numOfBedRooms;
	private int numOfFlors;
	private int buildYear;
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	@Enumerated(EnumType.ORDINAL)
	private Category category;
	@Column(nullable = false)
	private String type;

	/**
	 * Constructor created from Superclass
	 */
	public RealEstate() {
		super();
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "RealEstate [id=" + id + ", address=" + address + ", location=" + location + ", landSize=" + landSize
				+ ", techEquipment=" + techEquipment + ", heatingType=" + heatingType + ", numOfBathRooms="
				+ numOfBathRooms + ", numOfBedRooms=" + numOfBedRooms + ", numOfFlors=" + numOfFlors + ", buildYear="
				+ buildYear + ", status=" + status + ", category=" + category + ", type=" + type + "]";
	}

}