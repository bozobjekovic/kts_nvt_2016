package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Company;
import tim9.realEstate.repository.CompanyRepository;

/**
 * This class represents CompanyService
 *
 */
@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	/**
	 * This method finds element with specified id and gets it from the
	 * database.
	 * 
	 * @param id
	 *            Element id
	 * @return Element if found, null if doesn't exist
	 */
	public Company findOne(Long id) {
		return companyRepository.findOne(id);
	}

	/**
	 * This method finds element with specified name and gets it from the
	 * database.
	 * 
	 * @param name
	 *            Element name
	 * @return Element if found, null if doesn't exist
	 */
	public Company findByName(String name) {
		return companyRepository.findByName(name);
	}

	/**
	 * This method finds element with specified phone number and gets it from
	 * the database.
	 * 
	 * @param phoneNumber
	 *            Element phoneNumber
	 * @return Element if found, null if doesn't exist
	 */
	public Company findByPhoneNumber(String phoneNumber) {
		return companyRepository.findByPhoneNumber(phoneNumber);
	}

	/**
	 * This method finds all elements from specified Table.
	 * 
	 * @return List of elements
	 */
	public List<Company> findAll() {
		return companyRepository.findByVerifiedTrue();
	}

	/**
	 * This method saves element to the database.
	 * 
	 * @param company
	 *            element to be saved
	 * @return Saved element
	 */
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	/**
	 * This method removes element from the database.
	 * 
	 * @param id
	 *            id of element to be removed
	 */
	public void remove(Long id) {
		companyRepository.delete(id);
	}
}
