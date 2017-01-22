package tim9.realEstate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tim9.realEstate.service.AdminServiceTest;
import tim9.realEstate.service.AdvertismentServiceTest;
import tim9.realEstate.service.AuthorityServiceTest;
import tim9.realEstate.service.CommentServiceTest;
import tim9.realEstate.service.CompanyServiceTest;
import tim9.realEstate.service.InappropriateServiceTest;
import tim9.realEstate.service.LocationServiceTest;
import tim9.realEstate.service.RealEstateServiceTest;
import tim9.realEstate.service.RentRealEstateServiceTest;
import tim9.realEstate.service.UserServiceTest;
import tim9.realEstate.service.VerifierServiceTest;

/**
 * This class represents ServicesSuite
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	AdminServiceTest.class,
    AdvertismentServiceTest.class,
    AuthorityServiceTest.class,
    CommentServiceTest.class,
    CompanyServiceTest.class,
    InappropriateServiceTest.class,
    LocationServiceTest.class,
    RealEstateServiceTest.class,
    UserServiceTest.class,
    VerifierServiceTest.class,
    RentRealEstateServiceTest.class
})
public class ServicesSuite {

}
