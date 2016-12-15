package tim9.realEstate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.dto.InappropriateDTO;
import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.service.InappropriateService;

/**
 * This class represents controller for Inappropriate
 * Advertisement.
 */
@Controller
@RequestMapping(value="realEstate/inappropriates")
public class InappropriateController {
	
	@Autowired
	InappropriateService inappropriateService;
	
	/**
     * This method creates new Inappropriate Advertisement
     * and saves it to the database.
     * @param		companyDTO		a DTO Object
     * @return      ResponseEntity DTO Company and HttpStatus CREATED
     */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<InappropriateDTO> saveInappropriate(@RequestBody InappropriateDTO inappropriateDTO){
		Inappropriate inappropriate = new Inappropriate();
		inappropriate.setDescription(inappropriateDTO.getDescription());
		inappropriate.setTitle(inappropriateDTO.getTitle());
		//set Advert
		
		inappropriate = inappropriateService.save(inappropriate);
		return new ResponseEntity<>(new InappropriateDTO(inappropriate), HttpStatus.CREATED);
	}

}
