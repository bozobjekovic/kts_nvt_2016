package tim9.realEstate.constants;

import tim9.realEstate.model.Category;

/**
 * Constants for Advertisement tests
 *
 */
public class AdvertismentConstants {
	
	private AdvertismentConstants() {
		
	}

	public static final String NEW_PURPOSE = "rent";
    public static final String NEW_PHONE_NUMBER = "+2141441";
    public static final String NEW_DATE_STR = "2018/06/06";
    public static final double NEW_RATE = 4;
    public static final int NEW_GIVEN_RATE = 2;
    public static final int NEW_NUM_OF_RATES = 45;
    
    public static final Long DB_ID = 3L;
    public static final String DB_PURPOSE = "rent";
	public static final String DB_CITY = "Nis";
	public static final String DB_NAME = "Kuca sa bazenom";
	public static final double DB_PRICE = 423424;
    public static final String DB_PHONE_NUMBER = "+52432342";
    public static final double DB_RATE = 3.2;
    public static final Category DB_CATEGORY = Category.Office;
    public static final String DB_TYPE = "Stan";
    public static final int DB_NUM_OF_RATES = 47;
    public static final String DB_IMAGE = "img";
    
    public static final Long DB_ID_UNVERIFIED = 4L;
	public static final String DB_CITY_UNVERIFIED = "Nis";
	public static final String DB_NAME_UNVERIFIED = "Kuca sa bazenom";
	public static final double DB_PRICE_UNVERIFIED = 423424;
    public static final int DB_LAND_SIZE_UNVERIFIED = 3506;
    public static final String DB_TYPE_UNVERIFIED = "Kancelarija";
    public static final String DB_IMAGE_UNVERIFIED = "img";
    
    public static final int DB_COUNT_WITH_PURPOSE_SELL = 2; 
    
    public static final Long DB_ID_REFERENCED = 1L; 
    
    public static final int DB_COUNT = 4;
    public static final int DB_COUNT_UNVERIFIED = 1;
    public static final int PAGE_SIZE = 2;
    public static final int PAGE_SIZE_CONTROLLER = 3;
    public static final int DB_COUNT_PURPOSE = 1;

    public static final int DB_PUBLISHER_COUNT = 1;
    
    public static final Long DB_NONEXISTING_ID = 11L; 

}
