package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.dto.CompanyDTO;
import tim9.realEstate.model.Company;
import tim9.realEstate.service.CompanyService;
import tim9.realEstate.service.LocationService;

/**
 * This class represents controller for Company and manages with all Company
 * functionalities.
 */
@Controller
@RequestMapping(value = "realEstate/companies")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@Autowired
	LocationService locationService;

	/**
	 * This method gets all Comments from the database and then creates a list
	 * of DTO objects.
	 * 
	 * @return ResponseEntity List with all DTO Companies and HttpStatus OK
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		List<Company> companies = companyService.findAll();

		List<CompanyDTO> companyDTO = new ArrayList<>();
		for (Company c : companies) {
			companyDTO.add(new CompanyDTO(c));
		}

		return new ResponseEntity<>(companyDTO, HttpStatus.OK);
	}

	/**
	 * This method creates new Company and saves it to the database.
	 * 
	 * @param companyDTO
	 *            a DTO Object
	 * @return ResponseEntity DTO Company and HttpStatus CREATED
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO) {
		if (companyDTO.getName() == null) {
			return new ResponseEntity<>(companyDTO, HttpStatus.BAD_REQUEST);
		}

		if (companyService.findByName(companyDTO.getName()) != null
				|| companyService.findByPhoneNumber(companyDTO.getPhoneNumber()) != null) {
			return new ResponseEntity<>(companyDTO, HttpStatus.CONFLICT);
		}

		Company company = new Company();
		company.setLocation(locationService.findOne(companyDTO.getLocation().getId()));
		company.setName(companyDTO.getName());
		company.setAddress(companyDTO.getAddress());
		company.setPhoneNumber(companyDTO.getPhoneNumber());
		company.setSite(companyDTO.getSite());

		company = companyService.save(company);

		return new ResponseEntity<>(new CompanyDTO(company), HttpStatus.CREATED);
	}
}
