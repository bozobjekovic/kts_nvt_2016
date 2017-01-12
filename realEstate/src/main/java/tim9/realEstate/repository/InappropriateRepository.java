package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.model.User;

public interface InappropriateRepository extends JpaRepository<Inappropriate, Long>{
	
	List<Inappropriate> findByAdvertisment(Advertisment advertisment);
	
	Inappropriate findByAdvertismentAndUser(Advertisment advertisment, User user);

}
