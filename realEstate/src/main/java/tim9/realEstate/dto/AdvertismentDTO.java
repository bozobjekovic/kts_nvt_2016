package tim9.realEstate.dto;

import java.util.Date;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Status;

/**
 * This class represent data transfer object for Advertisement class
 */
public class AdvertismentDTO {

	private Long id;
	private String name;
	private String city;
	private double price;
	private int landSize;
	private String image;
	private String type;
	private Date publicationDate;
	private Status status;
	private String address;
	private String purpose;

	/**
	 * Constructor created from Superclass
	 */
	public AdvertismentDTO() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param a
	 *            represents Advertisement object
	 */
	public AdvertismentDTO(Advertisment a) {
		id = a.getId();
		name = a.getName();
		city = a.getRealEstate().getLocation().getCity();
		price = a.getPrice();
		landSize = a.getRealEstate().getLandSize();
		image = a.getBackgroundImage();
		type = a.getRealEstate().getType();
		publicationDate = a.getPublicationDate();
		status = a.getRealEstate().getStatus();
		address = a.getRealEstate().getAddress();
		purpose = a.getPurpose();
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Override
	public String toString() {
		return "AdvertismentDTO [id=" + id + ", name=" + name + ", city=" + city + ", price=" + price + ", landSize="
				+ landSize + ", image=" + image + ", type=" + type + ", publicationDate=" + publicationDate + "]";
	}

}
