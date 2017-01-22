package tim9.realEstate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import tim9.realEstate.mail.MailConfig;

/**
 * This class represents the main class and runs
 * the Application.
 */
@SpringBootApplication
@Import(MailConfig.class)
public class RealEstateApplication {
	
	/**
	 * Constructor
	 */
	private RealEstateApplication() {}
	
	/**
     * This is the main method that runs the
     * Spring application.
     * @param	args Array of arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RealEstateApplication.class, args);
    }

}
