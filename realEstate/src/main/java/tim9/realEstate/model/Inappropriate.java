package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Inappropriate implements Serializable{

	private static final long serialVersionUID = 2874124356009468934L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	@Column(nullable = false)
	private String description;
	@ManyToOne
	private Advertisment advertisment;
	
	public Inappropriate() {
		super();
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

	public Advertisment getAdvertisment() {
		return advertisment;
	}

	public void setAdvertisment(Advertisment advertisment) {
		this.advertisment = advertisment;
	}

	@Override
	public String toString() {
		return "Inappropriate [id=" + id + ", title=" + title + ", description=" + description + "]";
	}


}
