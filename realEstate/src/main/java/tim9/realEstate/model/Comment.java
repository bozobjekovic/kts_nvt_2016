package tim9.realEstate.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment implements Serializable{

	private static final long serialVersionUID = 6599823651768601791L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private Date date;
	private String title;
	private String description;
	@ManyToOne
	private User user;
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Advertisment advertisment;
	
	public Comment() {
		super();
	}

	public Comment(Date date, String title, String description, User user, Advertisment advertisment) {
		super();
		this.date = date;
		this.title = title;
		this.description = description;
		this.user = user;
		this.advertisment = advertisment;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Advertisment getAdvertisment() {
		return advertisment;
	}

	public void setAdvertisment(Advertisment advertisment) {
		this.advertisment = advertisment;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", date=" + date + ", title=" + title + ", description=" + description + "]";
	}
	

}
