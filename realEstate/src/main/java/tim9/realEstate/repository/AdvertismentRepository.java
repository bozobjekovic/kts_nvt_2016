package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;

public interface AdvertismentRepository extends JpaRepository<Advertisment, Long>, JpaSpecificationExecutor<Advertisment> {
	
	Page<Advertisment> findByPurposeAndIsDeletedFalseAndVerifierNotNull(String purpose, Pageable page);
	
	List<Advertisment> findByRealEstate_CategoryAndIsDeletedFalseAndVerifierNotNull(Category category);
	
	List<Advertisment> findByRealEstate_TypeAndIsDeletedFalseAndVerifierNotNull(String type);
	
	List<Advertisment> findByIsDeletedFalseAndVerifierNotNull();
	
	Page<Advertisment> findByIsDeletedFalseAndVerifierNotNull(Pageable page);
	
	Advertisment findByPhoneNumberAndIsDeletedFalseAndVerifierNotNull(String phone);
	
	List<Advertisment> findByIsDeletedFalseAndVerifierNotNullOrderByRateDesc(Pageable page);
	
	List<Advertisment> findByIsDeletedFalseAndVerifierIsNull();

}
