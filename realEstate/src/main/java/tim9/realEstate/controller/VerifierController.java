package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tim9.realEstate.dto.AdvertismentDTO;
import tim9.realEstate.dto.ChangePasswordDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Verifier;
import tim9.realEstate.security.UserUtils;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.service.VerifierService;


/**
 * This class represents controller for Verifier
 * and manages with all Verifier functionalities.
 */
@Controller
@RequestMapping(value="realEstate/verifiers")
public class VerifierController {
	
	@Autowired
	VerifierService verifierService;
	
	@Autowired
	AdvertismentService advertisementService;
	
	@Autowired
	UserUtils userUtils;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * This method return all unverified advertisement
	 * @return  List of advertisementsDTO
	 */
	@RequestMapping(value="/advertisement/unverified", method = RequestMethod.GET)
	public ResponseEntity<List<AdvertismentDTO>> getAllUnverifiedAdvertisement() {
		List<Advertisment> advertisements = advertisementService.findAllUnverified(); 
		
		List<AdvertismentDTO> advertisementsDTO = new ArrayList<>();
		for (Advertisment advertisment : advertisements) {
			advertisementsDTO.add(new AdvertismentDTO(advertisment));
		}
		
		return new ResponseEntity<>(advertisementsDTO, HttpStatus.OK);
	}
	
	/**
	 * This method changes password for verifiers
	 * @param modifiedVerifier
	 * @return HttpStatus OK if exists, NOT FOUND if not
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
		if (!checkInputParams(changePasswordDTO)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Verifier verifier = verifierService.findOne(changePasswordDTO.getId());

		if (verifier == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), verifier.getPassword())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		verifier.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
		verifierService.save(verifier);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * This method enables verifier to accepts advertisements
	 * @param id
	 * @param request
	 * @return HTTPSTATUS OK if advertisement exists, HTTPSTATUS NOT_FOUND if not
	 */
	@RequestMapping(value="/accept", method = RequestMethod.PUT)
	public ResponseEntity<Void> acceptAdvertisement(@RequestParam Long id, ServletRequest request) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Advertisment advertisement = advertisementService.findOne(id);
		
		if (advertisement == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		advertisement.setVerifier((Verifier)userUtils.getLoggedUser(request));
		advertisementService.save(advertisement);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * This method enables verifier to reject advertisements
	 * @param id
	 * @return HTTPSTATUS OK if advertisement exists, HTTPSTATUS NOT_FOUND if not
	 */
	@RequestMapping(value="/reject", method = RequestMethod.DELETE)
	public ResponseEntity<Void> rejectAdvertisement(@RequestParam Long id) {
		if (id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Advertisment advertisement = advertisementService.findOne(id);
		
		if (advertisement == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		advertisementService.remove(advertisement.getId());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * This method checks input parameters
	 * @param changePasswordDTO
	 * @return True if parameters are OK, false if not
	 */
	private boolean checkInputParams(ChangePasswordDTO changePasswordDTO) {
		if (changePasswordDTO.getId() == null) {
			return false;
		} else if (changePasswordDTO.getNewPassword() == null || changePasswordDTO.getNewPassword().trim().equals("")) {
			return false;
		} else if (changePasswordDTO.getOldPassword() == null || changePasswordDTO.getOldPassword().trim().equals("")) {
			return false;
		} else if (changePasswordDTO.getConfirmPassword() == null || changePasswordDTO.getConfirmPassword().trim().equals("")) {
			return false;
		} else if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
			return false;
		}
		return true;
	}
	
}
