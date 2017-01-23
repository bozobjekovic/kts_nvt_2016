package tim9.realEstate.constants;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class represents constants of User
 *
 */
public class UserConstants {
	
	public static final Calendar startDateCalendar;				// NOSONAR
	public static final Calendar endDateCalendar;				// NOSONAR

	static {
	    startDateCalendar = GregorianCalendar.getInstance();
	    startDateCalendar.clear();
	    startDateCalendar.set(2017, 03, 03);
	    endDateCalendar = GregorianCalendar.getInstance();
	    endDateCalendar.clear();
	    endDateCalendar.set(2017, 04, 04);
	}

	public static final String NEW_ADDRESS = "Address";
	public static final String NEW_CITY = "New York";
	public static final String NEW_EMAIL = "user11@gmail.com";
	public static final String NEW_IMAGE = "image1.jpg";
	public static final String NEW_NAME = "New name";
	public static final String NEW_PASSWORD = "pass";
	public static final String NEW_PHONE_NUMBER = "+42342";
	public static final String NEW_SURNAME = "New surname";
	public static final String NEW_USERNAME = "New username";
	public static final int NEW_NUM_OF_RATES = 100;
	public static final double NEW_RATE = 3.8;
	public static final int NEW_BANK_ACCOUNT = 35248925;
	public static final boolean NEW_IS_CLERK = false;
	public static final boolean NEW_IS_APPROVED = true;

	public static final Long DB_ID = 2L;

	public static final String DB_ADDRESS = "Niska";
	public static final String DB_CITY = "Nis";
	public static final String DB_EMAIL = "user2@gmail.com";
	public static final String DB_IMAGE = "image";
	public static final String DB_NAME = "User2";
	public static final String DB_PASSWORD = "u";
	public static final String DB_PHONE_NUMBER = "+523525";
	public static final String DB_SURNAME = "Useran2";
	public static final String DB_USERNAME = "user2";
	public static final int DB_NUM_OF_RATES = 3;
	public static final double DB_RATE = 2;
	public static final int DB_BANK_ACCOUNT = 646546;
	public static final boolean DB_IS_CLERK = false;
	public static final boolean DB_IS_APPROVED = false;

	public static final Long DB_ID_REFERENCED = 1L;
	public static final Long DB_DELETE = 3L;
	public static final int DB_COUNT = 5;

	public static final Long DB_ID_COMPANY = 1L;
	public static final String DB_PASSWORD_REAL = "$2a$08$qOdUxqqtQG0v6ZetGVFDBO0cET/mK5tQZ8cOrkivyPqMP3WkJCJYq";
	public static final Long DB_ID_REAL = 5L;

	public static final String DATE_RENT_TO = "2018/02/02";
	public static final String DATE_RENT_FROM = "2017/06/23";
	public static final String DATE_RENT_FROM_INVALID = "2017/05/20";
	public static final String DATE_RENT_FROM_INV = "2015/05/20";

	public static final int DB_CLERK_COUNT = 1;

	/**
	 * Constructor
	 */
	private UserConstants() {

	}
}
