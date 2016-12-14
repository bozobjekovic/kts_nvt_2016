package tim9.realEstate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * This class represent Comment bean class
 */
@Entity
public class Comment implements Serializable{

	private static final long serialVersionUID = 6599823651768601791L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private Date date;
	private String title;
	@Column(nullable = false)
	private String description;
	@ManyToOne
	private User user;
	@ManyToOne
	private Advertisment advertisment;
	
	/**
	 * Constructor created from Superclass
	 */
	public Comment() {
		super();
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
		return "Comment [id=" + id + ", date=" + date + ", title=" + title + ", description=" + description + ", user="
				+ user + ", advertisment=" + advertisment + "]";
	}

}
