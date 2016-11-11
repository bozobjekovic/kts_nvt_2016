package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;
import tim9.realEstate.repository.AdvertismentRepository;

@Service
public class AdvertismentService {
	
	@Autowired
	AdvertismentRepository advertismentRepository;
	
	public Advertisment findOne(Long id) {
		return advertismentRepository.findOne(id);
	}

	public List<Advertisment> findAll() {
		return advertismentRepository.findAll();
	}

	public Advertisment save(Advertisment advertisment) {
		return advertismentRepository.save(advertisment);
	}

	public void remove(Long id) {
		advertismentRepository.delete(id);
	}
	
	public List<Advertisment> findByPurpose(String purpose){
		return advertismentRepository.findByPurpose(purpose);
	}
	
	public List<Advertisment> findByRealEstate_Category(Category category){
		return advertismentRepository.findByRealEstate_Category(category);
	}
	
	public List<Advertisment> findByRealEstate_Type(String type){
		return advertismentRepository.findByRealEstate_Type(type);
	}

}
