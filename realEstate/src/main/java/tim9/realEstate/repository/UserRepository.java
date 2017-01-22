package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.User;

/**
 * This interface represents User repository
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * This method finds user with specified username
	 * 
	 * @param username
	 * @return object of user
	 */
	public User findByUsername(String username);

	/**
	 * This method finds user with specified email
	 * 
	 * @param email
	 * @return object of user
	 */
	public User findByEmail(String email);

	/**
	 * This method finds all users which is not approved
	 * 
	 * @return list objects of user
	 */
	public List<User> findByIsClerkTrueAndIsApprovedFalse();

}
