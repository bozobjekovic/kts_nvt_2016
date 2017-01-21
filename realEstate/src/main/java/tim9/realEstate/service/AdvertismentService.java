package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Status;
import tim9.realEstate.model.User;
import tim9.realEstate.repository.AdvertismentRepository;

@Service
public class AdvertismentService {

	@Autowired
	AdvertismentRepository advertismentRepository;

	/**
	 * This method gets all advertisements from the database.
	 * 
	 * @return List of elements
	 */
	public List<Advertisment> findAll() {
		return advertismentRepository.findAll();
	}

	/**
	 * This method finds element with specified id and gets it from the
	 * database.
	 * 
	 * @param id
	 *            Element id
	 * @return Element if found, null if doesn't exist
	 */
	public Advertisment findOne(Long id) {
		return advertismentRepository.findOne(id);
	}

	/**
	 * This method saves element to the database.
	 * 
	 * @param advertisment
	 *            element to be saved
	 * @return Saved element
	 */
	public Advertisment save(Advertisment advertisment) {
		return advertismentRepository.save(advertisment);
	}

	/**
	 * This method removes element from the database.
	 * 
	 * @param id
	 *            id of element to be removed
	 */
	public void remove(Long id) {
		advertismentRepository.delete(id);
	}

	/**
	 * This method finds all elements by specified Specification.
	 * 
	 * @param specifications
	 *            Specification<Advertisment>
	 * @return List of elements
	 */
	public List<Advertisment> findAllBySpecification(Specification<Advertisment> specifications) {
		return advertismentRepository.findAll(specifications);
	}

	/**
	 * This method finds all elements by specified Specification.
	 * 
	 * @param specifications
	 *            Specification<Advertisment>
	 * @return List of elements
	 */
	public Page<Advertisment> findAllBySpecification(Specification<Advertisment> specifications, Pageable page) {
		return advertismentRepository.findAll(specifications, page);
	}

	/**
	 * This method finds all elements by specified phone number.
	 * 
	 * @param phone
	 *            phone number
	 * @return List of elements
	 */
	public Advertisment findByPhoneNumber(String phone) {
		return advertismentRepository.findByPhoneNumberAndIsDeletedFalse(phone);
	}

	/**
	 * This method finds all elements by status and publisher.
	 * 
	 * @param status
	 *            real estate status
	 * @param publisher
	 *            publisher
	 * @return List of elements
	 */
	public List<Advertisment> findBySatusAndPublisher(Status status, User publisher) {
		return advertismentRepository.findByIsDeletedFalseAndRealEstate_StatusAndPublisher(status,
				publisher);
	}
	
	/**
	 * This method finds all advertisements from specified
	 * publisher.
	 * 
	 * @param publisher
	 *            publisher
	 * @return List of elements
	 */
	public List<Advertisment> findByPublisherAndIsDeletedFalseOrderById(User publisher){
		return advertismentRepository.findByPublisherAndIsDeletedFalseOrderById(publisher);
	}
	
	/**
	 * This method finds all advertisements from
	 * specified company.
	 * 
	 * @param publisher
	 *            publisher
	 * @return List of elements
	 */
	public List<Advertisment> findByPublisher_Company_IdAndIsDeletedFalseOrderById(Long id){
		return advertismentRepository.findByPublisher_Company_IdAndIsDeletedFalseOrderById(id);
	}
	
	/**
	 * This method finds all elements by status and company.
	 * 
	 * @param status
	 *            real estate status
	 * @param id
	 *            company id
	 * @return List of elements
	 */
	public List<Advertisment> findByPublisher_Company_IdAndIsDeletedFalseAndRealEstate_StatusOrderById(Status status, Long id) {
		return advertismentRepository.findByPublisher_Company_IdAndIsDeletedFalseAndRealEstate_StatusOrderById(id, status);
	}

}
