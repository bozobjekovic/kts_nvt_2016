package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Verifier;

public interface VerifierRepository extends JpaRepository<Verifier, Long>{
	
	public Verifier findByUsername(String username);
	
	public Verifier findByEmail(String email);

}
