package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Admin;

/**
 * This interface represents Admin repository
 *
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

	/**
	 * This method finds amind with specified user name
	 * 
	 * @param username
	 * @return object of admin
	 */
	public Admin findByUsername(String username);

	/**
	 * This method finds amind with specified email
	 * 
	 * @param email
	 * @return object of admin
	 */
	public Admin findByEmail(String email);

}
