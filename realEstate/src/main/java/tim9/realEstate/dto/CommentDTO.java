package tim9.realEstate.dto;

import java.util.Date;

import tim9.realEstate.model.Comment;

/**
 * This class represent data transfer object for Comment class
 */
public class CommentDTO {
	
	private Long id;
	private Date date;
	private String description;
	private UserDTO user;
	
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
		this(c.getId(), c.getDate(), c.getDescription(), new UserDTO(c.getUser()));
	}

	/**
	 * Constructor
	 * 
	 * @param id represents Comment ID
	 * @param date represents Date the Comment was created
	 * @param title represents Title of the Comment
	 * @param description represents Description of the Comment
	 */
	public CommentDTO(Long id, Date date, String description, UserDTO user) {
		super();
		this.id = id;
		this.date = date;
		this.description = description;
		this.user = user;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	

}
