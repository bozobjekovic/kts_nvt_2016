package tim9.realEstate.controller;

import java.util.Date;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.dto.InappropriateDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.model.User;
import tim9.realEstate.security.UserUtils;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.service.InappropriateService;

/**
 * This class represents controller for Inappropriate Advertisement.
 */
@Controller
@RequestMapping(value = "realEstate/inappropriates")
public class InappropriateController {

	@Autowired
	InappropriateService inappropriateService;

	@Autowired
	AdvertismentService advertismentService;

	@Autowired
	UserUtils userUtils;

	/**
	 * This method creates new Inappropriate Advertisement and saves it to the
	 * database.
	 * 
	 * @param companyDTO
	 *            a DTO Object
	 * @return ResponseEntity DTO Company and HttpStatus CREATED
	 */
	@RequestMapping(value = "/{id}/new", method = RequestMethod.POST)
	public ResponseEntity<Void> saveInappropriate(@PathVariable Long id, @RequestBody InappropriateDTO inappropriateDTO,
			ServletRequest request) {
		User user = (User) userUtils.getLoggedUser(request);

		if (inappropriateDTO.getDescription() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Advertisment advertisment = advertismentService.findOne(id);
		if (advertisment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (inappropriateService.findByAdvertisementAndUser(advertisment, user) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		Inappropriate inappropriate = new Inappropriate();
		inappropriate.setDescription(inappropriateDTO.getDescription());
		inappropriate.setTitle(inappropriateDTO.getTitle());
		inappropriate.setDate(new Date());
		inappropriate.setAdvertisment(advertisment);
		inappropriate.setUser(user);

		inappropriate = inappropriateService.save(inappropriate);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
