package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;

public interface AdvertismentRepository extends JpaRepository<Advertisment, Long>{
	
	List<Advertisment> findByPurpose(String purpose);
	
	List<Advertisment> findByRealEstate_Category(Category category);
	
	List<Advertisment> findByRealEstate_Type(String type);
	
	List<Advertisment> OrderByRateDesc();

}
