package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Verifier;
import tim9.realEstate.repository.VerifierRepository;

@Service
public class VerifierService {
	
	@Autowired
	VerifierRepository verifierRepository;
	
	public Verifier findOne(Long id) {
		return verifierRepository.findOne(id);
	}

	public List<Verifier> findAll() {
		return verifierRepository.findAll();
	}

	public Verifier save(Verifier verifier) {
		return verifierRepository.save(verifier);
	}

	public void remove(Long id) {
		verifierRepository.delete(id);
	}

}
