package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	
	public Admin findByUsername(String username);
	
	public Admin findByEmail(String email);

}
