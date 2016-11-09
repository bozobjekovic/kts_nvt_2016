package tim9.realEstate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.service.AdvertismentService;

import java.util.List;

/**
 * Created by bozob on 11/7/2016.
 */
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

}
