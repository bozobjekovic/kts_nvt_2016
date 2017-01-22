package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Status;
import tim9.realEstate.model.User;

/**
 * This interface represents Advertisement repository
 *
 */
public interface AdvertismentRepository
		extends JpaRepository<Advertisment, Long>, JpaSpecificationExecutor<Advertisment> {

	/**
	 * This method finds Advertisement with specified phone number and that is
	 * not deleted
	 * 
	 * @param phone
	 * @return object of Advertisements
	 */
	Advertisment findByPhoneNumberAndIsDeletedFalse(String phone);

	/**
	 * This method finds all Advertisements with specified status and publisher
	 * 
	 * @param status
	 * @param publisher
	 * @return list objects of Advertisements
	 */
	List<Advertisment> findByIsDeletedFalseAndRealEstate_StatusAndPublisher(Status status, User publisher);

	/**
	 * This method finds all Advertisements with specified publisher and not
	 * deleted
	 * 
	 * @param publisher
	 * @return list objects of Advertisements
	 */
	List<Advertisment> findByPublisherAndIsDeletedFalseOrderById(User publisher);

	/**
	 * This method finds all Advertisements with specified company id and not
	 * deleted
	 * 
	 * @param id
	 * @return list objects of Advertisements
	 */
	List<Advertisment> findByPublisher_Company_IdAndIsDeletedFalseOrderById(Long id);

	/**
	 * This method finds all Advertisements with specified company id and status
	 * of real estate
	 * 
	 * @param id
	 * @param status
	 * @return list objects of Advertisements
	 */
	List<Advertisment> findByPublisher_Company_IdAndIsDeletedFalseAndRealEstate_StatusOrderById(Long id, Status status);

}
