package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.model.Inappropriate;
import tim9.realEstate.service.InappropriateService;

@Controller
@RequestMapping(value="realEstate/inappropriates")
public class InappropriateController {
	
	@Autowired
	InappropriateService inappropriateService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Inappropriate>> getAllInappropriates() {
		List<Inappropriate> inappropriates = inappropriateService.findAll();
		return new ResponseEntity<>(inappropriates, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Inappropriate> saveInappropriate(Inappropriate inappropriate){
		inappropriate = inappropriateService.save(inappropriate);
		return new ResponseEntity<>(inappropriate, HttpStatus.CREATED);
	}

}
