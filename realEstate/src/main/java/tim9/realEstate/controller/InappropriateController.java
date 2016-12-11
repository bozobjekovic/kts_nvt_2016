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

import tim9.realEstate.dto.InappropriateDTO;
import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.service.InappropriateService;

@Controller
@RequestMapping(value="realEstate/inappropriates")
public class InappropriateController {
	
	@Autowired
	InappropriateService inappropriateService;
	
	/**
     * This method gets all Inappropriate comments from the database
     * and then creates a list of DTO objects.
     * @return      ResponseEntity List with all DTO Inappropriate comments and HttpStatus OK
     */
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<InappropriateDTO>> getAllLocations() {
		List<Inappropriate> inappropriates = inappropriateService.findAll();
		
		List<InappropriateDTO> inappropriateDTO = new ArrayList<>();
		for(Inappropriate i : inappropriates){
			inappropriateDTO.add(new InappropriateDTO(i));
		}
		return new ResponseEntity<>(inappropriateDTO, HttpStatus.OK);
	}
	
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
