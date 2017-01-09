package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;
import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.Status;
import tim9.realEstate.model.User;

public interface AdvertismentRepository extends JpaRepository<Advertisment, Long>, JpaSpecificationExecutor<Advertisment> {
	
	Page<Advertisment> findByPurposeAndIsDeletedFalseAndVerifierNotNull(String purpose, Pageable page);
	
	Advertisment findByRealEstate(RealEstate realEstate);
	
	List<Advertisment> findByPurposeAndRealEstate_CategoryAndIsDeletedFalseAndVerifierNotNull(String purpose, Category category);
	
	List<Advertisment> findByPurposeAndRealEstate_TypeAndIsDeletedFalseAndVerifierNotNull(String purpose, String type);
	
	List<Advertisment> findByIsDeletedFalseAndVerifierNotNull();
	
	Page<Advertisment> findByIsDeletedFalseAndVerifierNotNull(Pageable page);
	
	Advertisment findByPhoneNumberAndIsDeletedFalseAndVerifierNotNull(String phone);
	
	List<Advertisment> findByIsDeletedFalseAndVerifierNotNullOrderByRateDesc(Pageable page);
	
	List<Advertisment> findByIsDeletedFalseAndVerifierIsNull();
	
	List<Advertisment> findByIsDeletedFalseAndVerifierIsNotNullAndRealEstate_StatusAndPublisher(Status status, User publisher);

}
