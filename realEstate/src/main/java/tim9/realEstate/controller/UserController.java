package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.AdvertismentDTO;
import tim9.realEstate.dto.CompanyDTO;
import tim9.realEstate.dto.UserDTO;
import tim9.realEstate.mail.MailUtil;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Company;
import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.RentRealEstate;
import tim9.realEstate.model.Status;
import tim9.realEstate.model.User;
import tim9.realEstate.security.UserUtils;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.service.CompanyService;
import tim9.realEstate.service.RealEstateService;
import tim9.realEstate.service.RentRealEstateService;
import tim9.realEstate.service.UserService;

/**
 * This class represents controller for User and manages with all User
 * functionalities.
 */
@RestController
@RequestMapping(value = "realEstate/users")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	CompanyService companyService;

	@Autowired
	UserUtils userUtils;

	@Autowired
	AdvertismentService advertisementService;

	@Autowired
	RealEstateService realEstateService;

	@Autowired
	RentRealEstateService rentRealEstateService;

	@Autowired
	MailUtil mailUtil;

	/**
	 * This method gets Users data and then creates DTO object.
	 * 
	 * @param request
	 * @return ResponseEntity List with all DTO Users and HttpStatus OK
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(ServletRequest request) {
		User user = (User) userUtils.getLoggedUser(request);

		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

	/**
	 * This method gets all Users publications from the database and then
	 * creates a list of DTO objects.
	 * 
	 * @param id
	 * @return ResponseEntity List with all DTO Users and HttpStatus OK
	 */
	@RequestMapping(value = "/published/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<AdvertismentDTO>> getAllPublications(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User user = userService.findOne(id);

		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<AdvertismentDTO> advertismentDTOs = new ArrayList<>();
		if (user.isClerk()) {
			List<Advertisment> advertisments = advertisementService
					.findByPublisherCompanyIdAndIsDeletedFalseOrderById(user.getCompany().getId());
			for (Advertisment a : advertisments) {
				advertismentDTOs.add(new AdvertismentDTO(a));
			}

			return new ResponseEntity<>(advertismentDTOs, HttpStatus.OK);
		}
		List<Advertisment> advertisments = advertisementService.findByPublisherAndIsDeletedFalseOrderById(user);

		for (Advertisment a : advertisments) {
			advertismentDTOs.add(new AdvertismentDTO(a));
		}

		return new ResponseEntity<>(advertismentDTOs, HttpStatus.OK);
	}

	/**
	 * This method gets all Users publications that are active, sold or rented
	 * 
	 * @param status
	 *            advertisement status
	 * @param id
	 *            user id
	 * @return ResponseEntity List with all DTO Users and HttpStatus OK
	 */
	@RequestMapping(value = "/published/{status}/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<AdvertismentDTO>> getAllActivePublications(@PathVariable Status status,
			@PathVariable Long id) {
		if (id == null || status == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<AdvertismentDTO> advertismentDTOs = new ArrayList<>();
		if (user.isClerk()) {
			List<Advertisment> advertisments = advertisementService
					.findByPublisherCompanyIdAndIsDeletedFalseAndRealEstateStatusOrderById(status,
							user.getCompany().getId());

			for (int i = 0; i < advertisments.size(); i++) {
				advertismentDTOs.add(new AdvertismentDTO(advertisments.get(i)));
			}
			return new ResponseEntity<>(advertismentDTOs, HttpStatus.OK);
		}
		List<Advertisment> advertisments = advertisementService.findBySatusAndPublisher(status, user);

		for (int i = 0; i < advertisments.size(); i++) {
			advertismentDTOs.add(new AdvertismentDTO(advertisments.get(i)));
		}
		return new ResponseEntity<>(advertismentDTOs, HttpStatus.OK);
	}

	/**
	 * This method gets User Company and then returns it.
	 * 
	 * @param id
	 *            user id
	 * @return ResponseEntity List with all DTO Users and HttpStatus OK
	 */
	@RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
	public ResponseEntity<CompanyDTO> getUsersCompany(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (user.getCompany() == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}

		return new ResponseEntity<>(new CompanyDTO(user.getCompany()), HttpStatus.OK);
	}

	/**
	 * This method should rent real estate with specified id, rented date from
	 * and rented date to. Expected: Status OK
	 * 
	 * @param id
	 * @param rentDateFrom
	 * @param rentDateTo
	 * @return
	 */
	@RequestMapping(value = "/rent/{id}/from/{rentDateFrom}/to/{rentDateTo}", method = RequestMethod.PUT)
	public ResponseEntity<Void> rentRealEstate(@PathVariable Long id, @PathVariable Date rentDateFrom,
			@PathVariable Date rentDateTo) {

		if (id == null || rentDateFrom == null || rentDateTo == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Advertisment advertisment = advertisementService.findOne(id);
		if (advertisment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		RealEstate realEstate = advertisment.getRealEstate();
		if (realEstate.getStatus() == Status.Sold) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (rentDateFrom.before(new Date()) || rentDateTo.before(new Date()) || rentDateFrom.after(rentDateTo)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<RentRealEstate> rentRealEstates = rentRealEstateService.findByRealEstate(realEstate);
		for (RentRealEstate rentRealEstate : rentRealEstates) {
			if (rentRealEstate.getRentedFrom().after(rentDateFrom)) {
				if (!rentRealEstate.getRentedFrom().after(rentDateTo)) {
					return new ResponseEntity<>(HttpStatus.CONFLICT);
				}
			} else if (rentRealEstate.getRentedTo().before(rentDateTo)
					&& !rentRealEstate.getRentedTo().before(rentDateFrom)) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		}

		RentRealEstate rentRealEstate = new RentRealEstate();
		rentRealEstate.setRealEstate(realEstate);
		rentRealEstate.setRentedFrom(rentDateFrom);
		rentRealEstate.setRentedTo(rentDateTo);

		rentRealEstateService.save(rentRealEstate);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method allows user to apply to be part of a company. Sending request
	 * to the clerk, clerk decides if user will be accepted or denied.
	 * 
	 * @param request
	 * @param idCompany
	 *            id of Company
	 * @return HttpStatus OK if OK, else NOT_FOUND
	 */
	@RequestMapping(value = "/apply/{idCompany}", method = RequestMethod.PUT)
	public ResponseEntity<Void> applyToCompany(@PathVariable Long idCompany, ServletRequest request) {
		if (idCompany == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = (User) userUtils.getLoggedUser(request);
		Company company = companyService.findOne(idCompany);
		if (company == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.setAppliedCompany(company);
		userService.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method gets all applied user to specified company.
	 * 
	 * @param id
	 *            id of a Company
	 * @return HttpStatus OK if OK, else NOT_FOUND
	 */
	@RequestMapping(value = "/company/{id}/applied", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getAppliedUsers(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Company company = companyService.findOne(id);
		if (company == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User u : company.getUsersToApprove()) {
			userDTOs.add(new UserDTO(u));
		}
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}

	/**
	 * This method allows clerk to accept user to join the company. After that,
	 * mail is being sent to the user.
	 * 
	 * @param id
	 *            id of an User
	 * @param idCompany
	 *            id of a Company
	 * @return HttpStatus OK if OK, else NOT_FOUND
	 */
	@RequestMapping(value = "/accept/{id}/company/{idCompany}", method = RequestMethod.PUT)
	public ResponseEntity<Void> acceptUser(@PathVariable Long id, @PathVariable Long idCompany) {
		if (id == null || idCompany == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = userService.findOne(id);
		Company company = companyService.findOne(idCompany);
		if (user == null || company == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.setAppliedCompany(null);
		user.setCompany(company);
		userService.save(user);

		String email = user.getEmail();
		String subject = "Join Our Company";
		String text = "Hello \n Your have been accepted to join our company! \n Welcome";
		mailUtil.sendMail(email, subject, text);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method allows clerk to deny user to join the company. After that,
	 * mail is being sent to the user with the reason why he is denied.
	 * 
	 * @param id
	 *            id of an User
	 * @return HttpStatus OK if OK, else NOT_FOUND
	 */
	@RequestMapping(value = "/deny/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> denyClerk(@PathVariable Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		user.setAppliedCompany(null);
		userService.save(user);

		String email = user.getEmail();
		String subject = "Join Our Company";
		String text = "Hello \n We are sorry but your request has been rejected";
		mailUtil.sendMail(email, subject, text);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * This method updates user
	 * 
	 * @param userDTO
	 * @return DTO of updated user
	 */
	@RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
		User user = userService.findByEmail(userDTO.getEmail());
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setAddress(userDTO.getAddress());
		user.setCity(userDTO.getCity());

		userService.save(user);

		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

}
