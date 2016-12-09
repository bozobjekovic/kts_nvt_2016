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
	
	/**
	* This method finds element with specified id and gets it
	* from the database.
	* @param		id  Element id
	* @return		Element if found, null if doesn't exist
	*/
	public Admin findOne(Long id) {
		return adminRepository.findOne(id);
	}
	
	/**
	* This method finds all elements from specified Table.
	* @return		List of elements
	*/
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}
	
	/**
	* This method saves element to the database.
	* @param		admin element to be saved
	* @return		Saved element
	*/
	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}
	
	/**
	* This method removes element from the database.
	* @param		id id of element to be removed
	*/
	public void remove(Long id) {
		adminRepository.delete(id);
	}

}
