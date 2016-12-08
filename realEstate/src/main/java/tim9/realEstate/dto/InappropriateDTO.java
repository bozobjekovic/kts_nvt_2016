package tim9.realEstate.dto;

import tim9.realEstate.model.Inappropriate;

public class InappropriateDTO {
	
	private Long id;
	private String title;
	private String description;
	
	public InappropriateDTO() {
		super();
	}
	
	public InappropriateDTO(Inappropriate i) {
		this(i.getId(), i.getTitle(), i.getDescription());
	}
	
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
