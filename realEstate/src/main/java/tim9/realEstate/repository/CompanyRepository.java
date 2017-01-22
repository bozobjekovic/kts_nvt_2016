package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Company;

/**
 * This interface represents Company repository
 *
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

	/**
	 * This method finds company with specified company name
	 * 
	 * @param name
	 * @return object of Company
	 */
	Company findByName(String name);

	/**
	 * This method finds company with specified phone number
	 * 
	 * @param phoneNumber
	 * @return object of Company
	 */
	Company findByPhoneNumber(String phoneNumber);

	/**
	 * This method finds all verified Companies
	 * 
	 * @return list objects of Company
	 */
	List<Company> findByVerifiedTrue();
}
