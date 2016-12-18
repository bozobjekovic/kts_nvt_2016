package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.RentRealEstate;

public interface RentRealEstateRepository extends JpaRepository<RentRealEstate, Long> {
	
	RentRealEstate findByRealEstate(RealEstate realEstate);
	
}
