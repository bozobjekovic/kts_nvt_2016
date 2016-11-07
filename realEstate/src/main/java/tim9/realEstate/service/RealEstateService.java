package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.RealEstate;
import tim9.realEstate.repository.RealEstateRepository;

@Service
public class RealEstateService {
	
	@Autowired
	RealEstateRepository realEstateRepository;
	
	public RealEstate findOne(Long id) {
		return realEstateRepository.findOne(id);
	}

	public List<RealEstate> findAll() {
		return realEstateRepository.findAll();
	}

	public RealEstate save(RealEstate realEstate) {
		return realEstateRepository.save(realEstate);
	}

	public void remove(Long id) {
		realEstateRepository.delete(id);
	}

}
