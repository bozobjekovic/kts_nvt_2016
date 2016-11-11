package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;
import tim9.realEstate.service.AdvertismentService;

@Controller
@RequestMapping(value="realEstate/advertisments")
public class AdvertismentController {

    @Autowired
    AdvertismentService advertismentService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> getAllAdvertisment() {
        List<Advertisment> advertisments = advertismentService.findAll();
        return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Advertisment> saveAdvertismentt(Advertisment advertisment){
        advertisment = advertismentService.save(advertisment);
        return new ResponseEntity<>(advertisment, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/purpose/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> getAdvertismentsByPurpose(@PathVariable String name){
    	List<Advertisment> advertisments = advertismentService.findByPurpose(name);
    	return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }
    
    @RequestMapping(value="/category/{category}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> getAdvertismentsByCategory(@PathVariable Category category){
    	List<Advertisment> advertisments = advertismentService.findByRealEstate_Category(category);
    	return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }
    
    @RequestMapping(value="/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> getAdvertismentsByType(@PathVariable String type){
    	List<Advertisment> advertisments = advertismentService.findByRealEstate_Type(type);
    	return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }

}
