package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.repository.InappropriateRepository;

@Service
public class InappropriateService {
	
	@Autowired
	InappropriateRepository inappropriateRepository;
	
	public Inappropriate findOne(Long id) {
		return inappropriateRepository.findOne(id);
	}

	public List<Inappropriate> findAll() {
		return inappropriateRepository.findAll();
	}

	public Inappropriate save(Inappropriate inappropriate) {
		return inappropriateRepository.save(inappropriate);
	}

	public void remove(Long id) {
		inappropriateRepository.delete(id);
	}

}
