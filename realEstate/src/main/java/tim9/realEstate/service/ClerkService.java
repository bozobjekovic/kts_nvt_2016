package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Clerk;
import tim9.realEstate.repository.ClerkRepository;

@Service
public class ClerkService {
	
	@Autowired
	ClerkRepository clerkRepository;
	
	public Clerk findOne(Long id) {
		return clerkRepository.findOne(id);
	}

	public List<Clerk> findAll() {
		return clerkRepository.findAll();
	}

	public Clerk save(Clerk clerk) {
		return clerkRepository.save(clerk);
	}

	public void remove(Long id) {
		clerkRepository.delete(id);
	}

}
