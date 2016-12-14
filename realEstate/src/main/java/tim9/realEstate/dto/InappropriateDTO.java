package tim9.realEstate.dto;

import tim9.realEstate.model.Inappropriate;

/**
 * This class represent data transfer object for Inappropriate class
 */
public class InappropriateDTO {
	
	private Long id;
	private String title;
	private String description;
	
	/**
	 * Constructor created from Superclass
	 */
	public InappropriateDTO() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param i represents Inappropriate object
	 */
	public InappropriateDTO(Inappropriate i) {
		this(i.getId(), i.getTitle(), i.getDescription());
	}
	
	/**
	 * Constructor
	 * 
	 * @param id represents Inappropriate ID
	 * @param title represents Title of the Inappropriate comment
	 * @param description represents Description of the Inappropriate comment
	 */
	public InappropriateDTO(Long id, String title, String description) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
