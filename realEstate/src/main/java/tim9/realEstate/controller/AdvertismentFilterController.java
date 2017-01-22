package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.AdvertismentDTO;
import tim9.realEstate.dto.FilterDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Category;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.specification.AdvertismentSpecificationsBuilder;

/**
 * This class represents controller for filtering Advertisement.
 */
@RestController
@RequestMapping(value = "realEstate/advertisments")
public class AdvertismentFilterController {

	@Autowired
	AdvertismentService advertismentService;

	/**
	 * This method gets all Advertisements from the database that have given
	 * category and all parameters from filter.
	 * 
	 * @param category
	 *            Advertisment's category
	 * @param filter
	 *            filter for Advertisement search
	 * @param page            
	 * @return ResponseEntity List with all DTO Advertisements and HttpStatus OK
	 */
	@RequestMapping(value = "/category/{category}/filters", method = RequestMethod.GET)
	public ResponseEntity<FilterDTO> filters(@PathVariable Category category,
			@RequestParam(value = "filter") String filter, Pageable page) {
		AdvertismentSpecificationsBuilder builder = new AdvertismentSpecificationsBuilder();

		Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?(\\s\\w+?)?(\\_?(\\w+?(\\s\\w+?)?)?)*),");
		Matcher matcher = pattern.matcher(filter + ",");

		while (matcher.find()) {
			builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
		}
		builder.with("category", ":", category);
		builder.with("isDeleted", ":", false);

		Specification<Advertisment> spec = builder.build();

		Page<Advertisment> filteredAdvertisements = advertismentService.findAllBySpecification(spec, page);
		int count = advertismentService.findAllBySpecification(spec).size();
		List<AdvertismentDTO> filteredAdvertisementsDTO = new ArrayList<>();

		for (Advertisment advertisment : filteredAdvertisements) {
			if(advertisment.getActiveUntil().after(new Date())){
				filteredAdvertisementsDTO.add(new AdvertismentDTO(advertisment));
			}
		}

		return new ResponseEntity<>(new FilterDTO(filteredAdvertisementsDTO, count), HttpStatus.OK);
	}

}
