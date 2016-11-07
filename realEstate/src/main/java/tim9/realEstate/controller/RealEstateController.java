package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.model.RealEstate;
import tim9.realEstate.service.RealEstateService;

@Controller
@RequestMapping(value="realEstate/realestates")
public class RealEstateController {
	
	@Autowired
	RealEstateService realEstateService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<RealEstate>> getAllRealEstates() {
		List<RealEstate> realEstates = realEstateService.findAll();
		return new ResponseEntity<>(realEstates, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<RealEstate> saveRealEstates(RealEstate realEstate){
		realEstate = realEstateService.save(realEstate);
		return new ResponseEntity<>(realEstate, HttpStatus.CREATED);
	}

}
