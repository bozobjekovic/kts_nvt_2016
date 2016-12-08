package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Admin extends Person implements Serializable{

	private static final long serialVersionUID = 76277246610653572L;

	public Admin() {
		super();
	}

}
