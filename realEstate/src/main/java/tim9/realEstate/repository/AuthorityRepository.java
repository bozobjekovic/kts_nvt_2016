package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
	public Authority findByName(String name);

}
