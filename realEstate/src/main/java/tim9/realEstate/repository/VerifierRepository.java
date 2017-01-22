package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Verifier;

/**
 * This interface represents Verifier repository
 *
 */
public interface VerifierRepository extends JpaRepository<Verifier, Long> {

	/**
	 * This method finds verifier with specified username
	 * 
	 * @param username
	 * @return object of Verifier
	 */
	public Verifier findByUsername(String username);

	/**
	 * This method finds verifier with specified email
	 * 
	 * @param email
	 * @return object of Verifier
	 */
	public Verifier findByEmail(String email);

}
