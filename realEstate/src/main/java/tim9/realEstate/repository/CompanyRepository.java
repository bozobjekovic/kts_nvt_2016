package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	Company findByName(String name);
	
	Company findByPhoneNumber(String phoneNumber);
}
