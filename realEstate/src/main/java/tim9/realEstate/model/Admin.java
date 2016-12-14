package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * This class represent Admin bean class
 */
@Entity
public class Admin extends Person implements Serializable{

	private static final long serialVersionUID = 76277246610653572L;
	
	/**
	 * Constructor created from Superclass
	 */
	public Admin() {
		super();
	}

}
