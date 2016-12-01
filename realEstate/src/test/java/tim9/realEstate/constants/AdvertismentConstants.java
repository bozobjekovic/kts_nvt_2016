package tim9.realEstate.constants;

import java.sql.Date;

import tim9.realEstate.model.Category;

public class AdvertismentConstants {

	public static final String NEW_PURPOSE = "buy";
    public static final String NEW_PHONE_NUMBER = "+2141441";
    public static final Date NEW_DATE = new Date(0);
    public static final double NEW_RATE = 4;
    public static final int NEW_GIVEN_RATE = 2;
    public static final int NEW_NUM_OF_RATES = 45;
    
    public static final Long DB_ID = 3L;
    public static final String DB_PURPOSE = "rent";
    public static final String DB_PHONE_NUMBER = "+52432342";
    public static final double DB_RATE = 3.2;
    public static final Category DB_CATEGORY = Category.Office;
    public static final String DB_TYPE = "Stan"; //2016-12-20
    //public static final Date DB_ACTIVE_UNTIL = new Date(2016, 12, 20);
    //public static final Date DB_MODIFICATION_DATE = new Date(2016-12-23);
    public static final int DB_NUM_OF_RATES = 47;
    //public static final Date DB_PUBLICATION_DATE = new Date(2017, 12, 20); 
    
    public static final int DB_COUNT_WITH_PURPOSE_SELL = 2; 
    
    public static final Long DB_ID_REFERENCED = 1L; 
    
    //number of courses enrolled by student with ID DB_ID_REFERENCED
    public static final int DB_COUNT_STUDENT_COURSES = 2;
    
    //number of exams for student with ID DB_ID_REFERENCED
    public static final int DB_COUNT_STUDENT_EXAMS = 2;
    
    public static final int DB_COUNT = 3;
    public static final int PAGE_SIZE = 2;
    public static final int PAGE_SIZE_CONTROLLER = 3;

}
