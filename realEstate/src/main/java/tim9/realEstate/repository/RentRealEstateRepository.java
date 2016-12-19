package tim9.realEstate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.RentRealEstate;

public interface RentRealEstateRepository extends JpaRepository<RentRealEstate, Long> {
	
	List<RentRealEstate> findByRealEstateAndRentedToAfter(RealEstate realEstate, Date dateRentedTo);
	
}
