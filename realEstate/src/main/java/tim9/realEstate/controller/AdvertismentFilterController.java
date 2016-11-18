package tim9.realEstate.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.specification.AdvertismentSpecificationsBuilder;

@RestController
@RequestMapping(value="realEstate/advertisments")
public class AdvertismentFilterController {
	
	@Autowired
    AdvertismentService advertismentService;
	
	@RequestMapping(value="/category/{category}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> getAdvertismentsByCategory(@PathVariable Category category){
    	List<Advertisment> advertisments = advertismentService.findByRealEstate_Category(category);
    	return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }
    
    @RequestMapping(value="/category/{category}/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> getAdvertismentsByType(@PathVariable Category category, @PathVariable String type){
    	List<Advertisment> advertisments = advertismentService.findByRealEstate_Type(type);
    	return new ResponseEntity<>(advertisments, HttpStatus.OK);
    }
    
    @RequestMapping(value="/category/{category}/filters", method = RequestMethod.GET)
    public ResponseEntity<List<Advertisment>> filters(@PathVariable Category category, @RequestParam(value = "filter") String filter){
    	AdvertismentSpecificationsBuilder builder = new AdvertismentSpecificationsBuilder();
    	
    	Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
    	Matcher matcher = pattern.matcher(filter + ",");
    	
    	while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
    	builder.with("category", ":", category);
    	
    	Specification<Advertisment> spec = builder.build();
    	return new ResponseEntity<>(advertismentService.findAllBySpecification(spec), HttpStatus.OK);
    }

}
