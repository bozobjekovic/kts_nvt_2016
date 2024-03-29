package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Verifier;
import tim9.realEstate.repository.VerifierRepository;

/**
 * This class represents VerifierService
 *
 */
@Service
public class VerifierService {

	@Autowired
	VerifierRepository verifierRepository;

	/**
	 * This method finds element with specified id and gets it from the
	 * database.
	 * 
	 * @param id
	 *            Element id
	 * @return Element if found, null if doesn't exist
	 */
	public Verifier findOne(Long id) {
		return verifierRepository.findOne(id);
	}

	/**
	 * This method finds element with specified user name and gets it
	 * 
	 * @param username
	 * @return Element if found, null if doesn't exists
	 */
	public Verifier findByUsername(String username) {
		return verifierRepository.findByUsername(username);
	}

	/**
	 * This method finds element with specified email and gets it
	 * 
	 * @param email
	 * @return Element if found, null if doesn't exists
	 */
	public Verifier findByEmail(String email) {
		return verifierRepository.findByEmail(email);
	}

	/**
	 * This method finds all elements from specified Table.
	 * 
	 * @return List of elements
	 */
	public List<Verifier> findAll() {
		return verifierRepository.findAll();
	}

	/**
	 * This method saves element to the database.
	 * 
	 * @param verifier
	 *            element to be saved
	 * @return Saved element
	 */
	public Verifier save(Verifier verifier) {
		return verifierRepository.save(verifier);
	}

	/**
	 * This method removes element from the database.
	 * 
	 * @param id
	 *            id of element to be removed
	 */
	public void remove(Long id) {
		verifierRepository.delete(id);
	}

}
