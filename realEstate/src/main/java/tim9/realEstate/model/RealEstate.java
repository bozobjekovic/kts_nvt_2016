package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class RealEstate implements Serializable{
	
	private static final long serialVersionUID = -1133371741893571966L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToOne(cascade = CascadeType.ALL)
	private Location location;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private int landSize;
	private String techEquipment;
	private String heatingType;
	@Lob
	private String image;
	private int numOfBathRooms;
	private int numOfBedRooms;
	private int numOfFlors;
	private int buildYear;
	@Enumerated(EnumType.ORDINAL)
	private Category category;
	private String type;
	
	public RealEstate() {
		super();
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

	@Override
	public String toString() {
		return "RealEstate [id=" + id + ", name=" + name + ", location=" + location + ", price=" + price + ", landSize="
				+ landSize + ", techEquipment=" + techEquipment + ", heatingType=" + heatingType + ", image=" + image
				+ ", category=" + category + ", type=" + type + "]";
	}

		

}
