package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.model.User;

/**
 * This interface represents Inappropriate repository
 *
 */
public interface InappropriateRepository extends JpaRepository<Inappropriate, Long> {

	/**
	 * This method finds all Inappropriate with specified advertisement
	 * 
	 * @param advertisment
	 * @return list objects of Inappropriate
	 */
	List<Inappropriate> findByAdvertisment(Advertisment advertisment);

	/**
	 * This method finds all Inappropriate orders by advertisement id
	 * 
	 * @return list objects of Inappropriate
	 */
	List<Inappropriate> findAllByOrderByAdvertisment_Id();

	/**
	 * This method finds Inappropriate with specified advertisement and user
	 * 
	 * @param advertisment
	 * @param user
	 * @return object of Inappropriate
	 */
	Inappropriate findByAdvertismentAndUser(Advertisment advertisment, User user);

}
