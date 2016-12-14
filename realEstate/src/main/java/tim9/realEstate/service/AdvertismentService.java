package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;
import tim9.realEstate.repository.AdvertismentRepository;

@Service
public class AdvertismentService {
	
	@Autowired
	AdvertismentRepository advertismentRepository;
	
	/**
	* This method finds element with specified id and gets it
	* from the database.
	* @param		id  Element id
	* @return		Element if found, null if doesn't exist
	*/
	public Advertisment findOne(Long id) {
		return advertismentRepository.findOne(id);
	}
	
	/**
	* This method finds all elements from specified Table.
	* @return		List of elements
	*/
	public List<Advertisment> findAll() {
		return advertismentRepository.findByIsDeletedFalse();
	}
	
	/**
	* This method finds all elements from specified Table
	* by pages.
	* @param		page Spring Pageable object
	* @return		Page List of elements
	*/
	public Page<Advertisment> findAll(Pageable page) {
		return advertismentRepository.findAll(page);
	}

	/**
	* This method saves element to the database.
	* @param		advertisment element to be saved
	* @return		Saved element
	*/
	public Advertisment save(Advertisment advertisment) {
		return advertismentRepository.save(advertisment);
	}
	
	/**
	* This method removes element from the database.
	* @param		id id of element to be removed
	*/
	public void remove(Long id) {
		advertismentRepository.delete(id);
	}
	
	/**
	* This method finds all elements by specified Specification.
	* @param		specifications Specification<Advertisment>
	* @return		List of elements
	*/
	public List<Advertisment> findAllBySpecification(Specification<Advertisment> specifications) {
		return advertismentRepository.findAll(specifications);
	}
	
	/**
	* This method finds all elements by specified phone number.
	* @param		phone phone number
	* @return		List of elements
	*/
	public Advertisment findByPhoneNumber(String phone){
		return advertismentRepository.findByPhoneNumber(phone);
	}
	
	/**
	* This method finds all elements by it's purpose.
	* @param		purpose Advertisement's purpose
	* @param		page Spring Pageable object
	* @return		Page List of elements
	*/
	public Page<Advertisment> findByPurpose(String purpose, Pageable page){
		return advertismentRepository.findByPurpose(purpose, page);
	}
	
	/**
	* This method finds all elements by category.
	* @param		category Advertisement's category
	* @return		List of elements
	*/
	public List<Advertisment> findByRealEstate_Category(Category category){
		return advertismentRepository.findByRealEstate_Category(category);
	}
	
	/**
	* This method finds all elements by type.
	* @param		type Advertisement's type
	* @return		List of elements
	*/
	public List<Advertisment> findByRealEstate_Type(String type){
		return advertismentRepository.findByRealEstate_Type(type);
	}
	
	/**
	* This method sorts elements by rate.
	* @param		page Spring Pageable object
	* @return		List of elements
	*/
	public List<Advertisment> orderByRate(Pageable page){
		return advertismentRepository.OrderByRateDesc(page);
	}
}
