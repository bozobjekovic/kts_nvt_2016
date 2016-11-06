package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RealEstate implements Serializable{
	
	private static final long serialVersionUID = -1133371741893571966L;
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@ManyToOne
	private Location location;
	private double price;
	private String landSize;
	private String techEquipment;
	private String heatingType;
	private String image;
	@Enumerated(EnumType.ORDINAL)
	private Category category;
	private String type;
	
	public RealEstate() {
		super();
	}

	public RealEstate(String name, Location location, double price, String landSize, String techEquipment, String heatingType,
			String image, Category category, String type) {
		super();
		this.name = name;
		this.location = location;
		this.price = price;
		this.landSize = landSize;
		this.techEquipment = techEquipment;
		this.heatingType = heatingType;
		this.image = image;
		this.category = category;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getLandSize() {
		return landSize;
	}

	public void setLandSize(String landSize) {
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
