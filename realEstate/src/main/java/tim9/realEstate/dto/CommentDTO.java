package tim9.realEstate.dto;

import java.util.Date;

import tim9.realEstate.model.Comment;

/**
 * This class represent data transfer object for Comment class
 */
public class CommentDTO {
	
	private Long id;
	private Date date;
	private String title;
	private String description;
	
	/**
	 * Constructor created from Superclass
	 */
	public CommentDTO() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param c represents Comment object
	 */
	public CommentDTO(Comment c) {
		this(c.getId(), c.getDate(), c.getTitle(), c.getDescription());
	}

	/**
	 * Constructor
	 * 
	 * @param id represents Comment ID
	 * @param date represents Date the Comment was created
	 * @param title represents Title of the Comment
	 * @param description represents Description of the Comment
	 */
	public CommentDTO(Long id, Date date, String title, String description) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
