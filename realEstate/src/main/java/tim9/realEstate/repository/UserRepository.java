package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}