package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	public List<User> findByIsClerkTrueAndIsApprovedFalse();

}
