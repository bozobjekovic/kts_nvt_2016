package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Authority;
import tim9.realEstate.repository.AuthorityRepository;

/**
 * This class represents AuthorityService
 *
 */
@Service
public class AuthorityService {

	@Autowired
	AuthorityRepository authorityRepository;

	/**
	 * This method finds element with specified id and gets it from the
	 * database.
	 * 
	 * @param id
	 *            Element id
	 * @return Element if found, null if doesn't exist
	 */
	public Authority findOne(Long id) {
		return authorityRepository.findOne(id);
	}

	/**
	 * This method finds all elements from specified Table.
	 * 
	 * @return List of elements
	 */
	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}

	/**
	 * This method saves element to the database.
	 * 
	 * @param authority
	 *            element to be saved
	 * @return Saved element
	 */
	public Authority save(Authority authority) {
		return authorityRepository.save(authority);
	}

	/**
	 * This method removes element from the database.
	 * 
	 * @param id
	 *            id of element to be removed
	 */
	public void remove(Long id) {
		authorityRepository.delete(id);
	}

	/**
	 * This method finds element by name.
	 * 
	 * @param name
	 *            name of an element
	 * @return Element if found, else null
	 */
	public Authority findByName(String name) {
		return authorityRepository.findByName(name);
	}

}
