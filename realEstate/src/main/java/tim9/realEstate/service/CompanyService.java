package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Company;
import tim9.realEstate.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	public Company findOne(Long id) {
		return companyRepository.findOne(id);
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public void remove(Long id) {
		companyRepository.delete(id);
	}
}
