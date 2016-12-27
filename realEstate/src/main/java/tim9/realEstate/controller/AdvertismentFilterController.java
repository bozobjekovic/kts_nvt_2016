package tim9.realEstate.controller;

import java.util.ArrayList;
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

import tim9.realEstate.dto.AdvertismentDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.specification.AdvertismentSpecificationsBuilder;

/**
 * This class represents controller for filtering Advertisement.
 */
@RestController
@RequestMapping(value="realEstate/advertisments")
public class AdvertismentFilterController {
	
	@Autowired
    AdvertismentService advertismentService;
	
	/**
     * This method gets all Advertisements from the database
     * that have given category.
     * @param		category	Advertisment's category
     * @return      ResponseEntity List with all DTO Advertisements and HttpStatus OK
     */
	@RequestMapping(value="/category/{category}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertismentDTO>> getAdvertismentsByCategory(@PathVariable Category category){
		if(category == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	List<Advertisment> advertisements = advertismentService.findByRealEstate_Category(category);
    	
    	List<AdvertismentDTO> advertsDTO = new ArrayList<>();
		for (Advertisment a : advertisements) {
			advertsDTO.add(new AdvertismentDTO(a));
		}
    	return new ResponseEntity<>(advertsDTO, HttpStatus.OK);
    }
    
	/**
     * This method gets all Advertisements from the database
     * that have given type.
     * @param		category	Advertisment's category
     * @param		type	Advertisment's type
     * @return      ResponseEntity List with all DTO Advertisements and HttpStatus OK
     */
    @RequestMapping(value="/purpose/{purpose}/category/{category}/type/{type}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertismentDTO>> getAdvertismentsByType(@PathVariable String purpose, @PathVariable Category category, @PathVariable String type){
    	if(purpose == null || category == null || type == null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	List<Advertisment> advertisements = advertismentService.findByRealEstate_Type(purpose, type);
    	
    	List<AdvertismentDTO> advertsDTO = new ArrayList<>();
		for (Advertisment a : advertisements) {
			advertsDTO.add(new AdvertismentDTO(a));
		}
    	return new ResponseEntity<>(advertsDTO, HttpStatus.OK);
    }
    
    /**
     * This method gets all Advertisements from the database
     * that have given category and all parameters from filter.
     * @param		category	Advertisment's category
     * @param		filter	filter for Advertisement search
     * @return      ResponseEntity List with all DTO Advertisements and HttpStatus OK
     */
    @RequestMapping(value="/category/{category}/filters", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertismentDTO>> filters(@PathVariable Category category, @RequestParam(value = "filter") String filter){
    	AdvertismentSpecificationsBuilder builder = new AdvertismentSpecificationsBuilder();
    	
    	Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
    	Matcher matcher = pattern.matcher(filter + ",");
    	
    	while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
    	builder.with("category", ":", category);
    	
    	Specification<Advertisment> spec = builder.build();
    	List<Advertisment> filteredAdvertisements = advertismentService.findAllBySpecification(spec);
    	List<AdvertismentDTO> filteredAdvertisementsDTO = new ArrayList<>();
    	
    	for (Advertisment advertisment : filteredAdvertisements) {
			filteredAdvertisementsDTO.add(new AdvertismentDTO(advertisment));
		}
    	
    	return new ResponseEntity<>(filteredAdvertisementsDTO, HttpStatus.OK);
    }

}
