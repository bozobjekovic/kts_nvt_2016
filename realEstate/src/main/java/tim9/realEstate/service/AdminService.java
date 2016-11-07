package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Admin;
import tim9.realEstate.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	public Admin findOne(Long id) {
		return adminRepository.findOne(id);
	}

	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}

	public void remove(Long id) {
		adminRepository.delete(id);
	}

}
