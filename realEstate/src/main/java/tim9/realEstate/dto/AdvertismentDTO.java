package tim9.realEstate.dto;

import tim9.realEstate.model.Advertisment;

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
	
	/**
	 * Constructor created from Superclass
	 */
	public AdvertismentDTO() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param a represents Advertisement object
	 */
	public AdvertismentDTO(Advertisment a) {
		this(a.getId(), a.getName(), a.getRealEstate().getLocation().getCity(), a.getPrice()
				, a.getRealEstate().getLandSize(), a.getBackgroundImage(), a.getRealEstate().getType());
	}

	/**
	 * Constructor
	 * 
	 * @param id represents Advertisement ID
	 * @param name represents Name of the Advertisement
	 * @param location represents Location of the Real Estate
	 * @param price represents Price of the Real Estate
	 * @param landSize represents Land Size of the Real Estate
	 * @param image represents Image of the Real Estate
	 * @param type represents Type of the Real Estate
	 */
	public AdvertismentDTO(Long id, String name, String location, double price, int landSize, String image,
			String type) {
		super();
		this.id = id;
		this.name = name;
		this.city = location;
		this.price = price;
		this.landSize = landSize;
		this.image = image;
		this.type = type;
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

}
