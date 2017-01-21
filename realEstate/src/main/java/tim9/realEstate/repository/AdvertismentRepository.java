package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Status;
import tim9.realEstate.model.User;

public interface AdvertismentRepository extends JpaRepository<Advertisment, Long>, JpaSpecificationExecutor<Advertisment> {

	Advertisment findByPhoneNumberAndIsDeletedFalseAndVerifierNotNull(String phone);

	List<Advertisment> findByIsDeletedFalseAndVerifierIsNotNullAndRealEstate_StatusAndPublisher(Status status, User publisher);
	
	List<Advertisment> findByPublisherAndIsDeletedFalseOrderById(User publisher);
	
	List<Advertisment> findByPublisher_Company_IdAndIsDeletedFalseOrderById(Long id);

}
