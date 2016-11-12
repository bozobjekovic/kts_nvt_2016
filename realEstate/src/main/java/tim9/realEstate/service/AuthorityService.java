package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Authority;
import tim9.realEstate.repository.AuthorityRepository;

@Service
public class AuthorityService {
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	public Authority findOne(Long id) {
		return authorityRepository.findOne(id);
	}

	public List<Authority> findAll() {
		return authorityRepository.findAll();
	}

	public Authority save(Authority authority) {
		return authorityRepository.save(authority);
	}

	public void remove(Long id) {
		authorityRepository.delete(id);
	}
	
	public Authority findByName(String name) {
		return authorityRepository.findByName(name);
	}

}
