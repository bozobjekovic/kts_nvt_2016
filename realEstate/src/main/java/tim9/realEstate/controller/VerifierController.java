package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.model.Verifier;
import tim9.realEstate.service.VerifierService;

@Controller
@RequestMapping(value="realEstate/verifiers")
public class VerifierController {
	
	@Autowired
	VerifierService verifierService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Verifier>> getAllVerifiers() {
		List<Verifier> verifiers = verifierService.findAll();
		return new ResponseEntity<>(verifiers, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Verifier> saveVerifier(Verifier verifier){
		verifier = verifierService.save(verifier);
		return new ResponseEntity<>(verifier, HttpStatus.CREATED);
	}

}