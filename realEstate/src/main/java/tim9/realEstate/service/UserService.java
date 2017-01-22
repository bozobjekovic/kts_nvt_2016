package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.User;
import tim9.realEstate.repository.UserRepository;

/**
 * This class represents UserService
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * This method finds element with specified id and gets it from the
	 * database.
	 * 
	 * @param id
	 *            Element id
	 * @return Element if found, null if doesn't exist
	 */
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	/**
	 * This method finds element with specified user name and gets it
	 * 
	 * @param username
	 * @return Element if found, null if doesn't exists
	 */
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * This method finds element with specified user email and gets it
	 * 
	 * @param email
	 * @return Element if found, null if doesn't exists
	 */
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * This method finds all elements from specified Table.
	 * 
	 * @return List of elements
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * This method saves element to the database.
	 * 
	 * @param user
	 *            element to be saved
	 * @return Saved element
	 */
	public User save(User user) {
		return userRepository.save(user);
	}

	/**
	 * This method removes element from the database.
	 * 
	 * @param id
	 *            id of element to be removed
	 */
	public void remove(Long id) {
		userRepository.delete(id);
	}

	/**
	 * This method finds all clerks that are not approved
	 * 
	 * @return List of elements
	 */
	public List<User> findUnapprovedClerks() {
		return userRepository.findByIsClerkTrueAndIsApprovedFalse();
	}

}
