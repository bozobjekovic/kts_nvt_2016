package tim9.realEstate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tim9.realEstate.controller.AdminControllerTest;
import tim9.realEstate.controller.AdvertismentControllerTest;
import tim9.realEstate.controller.CommentControllerTest;
import tim9.realEstate.controller.CompanyControllerTest;
import tim9.realEstate.controller.InappropriateControllerTest;
import tim9.realEstate.controller.LocationControllerTest;
import tim9.realEstate.controller.LoginRegistrateControllerTest;
import tim9.realEstate.controller.UserControllerTest;
import tim9.realEstate.controller.VerifierControllerTest;

/**
 * This class represents ControllersSuite
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	AdminControllerTest.class,
    AdvertismentControllerTest.class,
    CommentControllerTest.class,
    CompanyControllerTest.class,
    InappropriateControllerTest.class,
    LocationControllerTest.class,
    LoginRegistrateControllerTest.class,
    UserControllerTest.class,
    VerifierControllerTest.class    
})
public class ControllersSuite {

}
