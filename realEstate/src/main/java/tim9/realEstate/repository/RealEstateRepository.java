package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.RealEstate;

public interface RealEstateRepository extends JpaRepository<RealEstate, Long>{
	
	RealEstate findByAddress(String address);
	
	List<RealEstate> findByAddressAndLocation_City(String address, String city);

}
