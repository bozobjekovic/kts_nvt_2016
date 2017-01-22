package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.model.User;
import tim9.realEstate.repository.InappropriateRepository;

/**
 * This class represents InappropriateService
 *
 */
@Service
public class InappropriateService {

	@Autowired
	InappropriateRepository inappropriateRepository;

	/**
	 * This method finds element with specified id and gets it from the
	 * database.
	 * 
	 * @param id
	 *            Element id
	 * @return Element if found, null if doesn't exist
	 */
	public Inappropriate findOne(Long id) {
		return inappropriateRepository.findOne(id);
	}

	/**
	 * This method finds all elements from specified Table.
	 * 
	 * @return List of elements
	 */
	public List<Inappropriate> findAll() {
		return inappropriateRepository.findAllByOrderByAdvertisment_Id();
	}

	/**
	 * This method finds all elements with specified Advertisement.
	 * 
	 * @param advertisment
	 * @return List of elements
	 */
	public List<Inappropriate> findByAdvertisement(Advertisment advertisment) {
		return inappropriateRepository.findByAdvertisment(advertisment);
	}

	/**
	 * This method finds all elements with specified Advertisement and user.
	 * 
	 * @param advertisment
	 * @param user
	 * @return Element
	 */
	public Inappropriate findByAdvertisementAndUser(Advertisment advertisment, User user) {
		return inappropriateRepository.findByAdvertismentAndUser(advertisment, user);
	}

	/**
	 * This method saves element to the database.
	 * 
	 * @param inappropriate
	 *            element to be saved
	 * @return Saved element
	 */
	public Inappropriate save(Inappropriate inappropriate) {
		return inappropriateRepository.save(inappropriate);
	}

	/**
	 * This method removes element from the database.
	 * 
	 * @param id
	 *            id of element to be removed
	 */
	public void remove(Long id) {
		inappropriateRepository.delete(id);
	}

}
