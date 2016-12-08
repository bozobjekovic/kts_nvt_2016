package tim9.realEstate.dto;

import java.util.Date;

import tim9.realEstate.model.Comment;

public class CommentDTO {
	
	private Long id;
	private Date date;
	private String title;
	private String description;
	
	public CommentDTO() {
		super();
	}
	
	public CommentDTO(Comment c) {
		this(c.getId(), c.getDate(), c.getTitle(), c.getDescription());
	}

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
