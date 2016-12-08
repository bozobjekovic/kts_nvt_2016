package tim9.realEstate.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Verifier extends Person implements Serializable{

	private static final long serialVersionUID = -2991029351829583301L;

	public Verifier() {
		super();
	}
}
