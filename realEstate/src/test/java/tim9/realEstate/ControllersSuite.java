package tim9.realEstate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tim9.realEstate.controller.AdvertismentControllerTest;
import tim9.realEstate.controller.CommentControllerTest;
import tim9.realEstate.controller.CompanyControllerTest;
import tim9.realEstate.controller.InappropriateControllerTest;
import tim9.realEstate.controller.LocationControllerTest;
import tim9.realEstate.controller.VerifierControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	//AdminControllerTest.class,
    AdvertismentControllerTest.class,
    //AuthorityControllerTest.class,
    CommentControllerTest.class,
    CompanyControllerTest.class,
    InappropriateControllerTest.class,
    LocationControllerTest.class,
    //UserControllerTest.class,
    VerifierControllerTest.class    
})
public class ControllersSuite {

}
