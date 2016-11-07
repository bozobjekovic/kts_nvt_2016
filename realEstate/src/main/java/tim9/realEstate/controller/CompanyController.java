package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.model.Company;
import tim9.realEstate.service.CompanyService;

@Controller
@RequestMapping(value="realEstate/companies")
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Company>> getAllCompanies() {
		List<Company> companies = companyService.findAll();
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Company> saveCompany(Company company){
		company = companyService.save(company);
		return new ResponseEntity<>(company, HttpStatus.CREATED);
	}

}
