package tim9.realEstate.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Verifier;
import tim9.realEstate.service.AdvertismentService;

@RestController
@RequestMapping(value="realEstate/advertisments")
public class AdvertismentController {

    @Autowired
    AdvertismentService advertismentService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> getAllAdvertisment() {
        List<Advertisment> advertisments = advertismentService.findAll();
        return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Advertisment>> getAdvertisementPage(Pageable page) {
		Page<Advertisment> advertisements = advertismentService.findAll(page);
		
		List<Advertisment> adverts = new ArrayList<>();
		for (Advertisment a : advertisements) {
			adverts.add(a);
		}
		return new ResponseEntity<>(adverts, HttpStatus.OK);
	}

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Advertisment> saveAdvertismentt(@RequestBody Advertisment advertisment){
        advertisment = advertismentService.save(advertisment);
        return new ResponseEntity<>(advertisment, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/purpose/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> getAdvertismentsByPurpose(@PathVariable String name){
    	List<Advertisment> advertisments = advertismentService.findByPurpose(name);
    	return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }
    
    @RequestMapping(value="/rate", method = RequestMethod.PUT)
    public ResponseEntity<Advertisment> rateAdvertisment(@RequestParam Long id, @RequestParam int rate){
    	Advertisment advertisment = advertismentService.findOne(id);
    	advertisment.setNumberOfRates(advertisment.getNumberOfRates() + 1);
    	advertisment.setRate((advertisment.getRate()*advertisment.getNumberOfRates() + rate) / advertisment.getNumberOfRates());
    	advertismentService.save(advertisment);
    	return new ResponseEntity<>(advertisment, HttpStatus.OK);
    }
    
    @RequestMapping(value="/verification", method = RequestMethod.PUT)
    public ResponseEntity<Advertisment> verifyAdvertisment(@RequestParam Long idAdvertisment, @RequestParam Verifier verifier){
    	Advertisment advertisment = advertismentService.findOne(idAdvertisment);
    	advertisment.setVerifier(verifier);
    	advertismentService.save(advertisment);
    	return new ResponseEntity<>(advertisment, HttpStatus.OK);
    }
    
    @RequestMapping(value="/prolong", method = RequestMethod.PUT)
    public ResponseEntity<Advertisment> prolongAdvertisment(@RequestParam Long idAdvertisment, @RequestParam Date date){
    	Advertisment advertisment = advertismentService.findOne(idAdvertisment);
    	advertisment.setActiveUntil(date);
    	advertismentService.save(advertisment);
    	return new ResponseEntity<>(advertisment, HttpStatus.OK);
    }
    
    @RequestMapping(value="/popular", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> getPopularAdvertisements(){
    	List<Advertisment> advertisments = advertismentService.orderByRate();
    	return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }

}
