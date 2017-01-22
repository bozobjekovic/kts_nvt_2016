package tim9.realEstate.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * This class represent RentRealEstate bean class
 */
@Entity
public class RentRealEstate implements Serializable {

	private static final long serialVersionUID = 7905390562386934010L;

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private RealEstate realEstate;
	private Date rentedFrom;
	private Date rentedTo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RealEstate getRealEstate() {
		return realEstate;
	}

	public void setRealEstate(RealEstate realEstate) {
		this.realEstate = realEstate;
	}

	public Date getRentedFrom() {
		return rentedFrom;
	}

	public void setRentedFrom(Date rentedFrom) {
		this.rentedFrom = rentedFrom;
	}

	public Date getRentedTo() {
		return rentedTo;
	}

	public void setRentedTo(Date rentedTo) {
		this.rentedTo = rentedTo;
	}

}
