package tim9.realEstate;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class represents test utils
 */
public class TestUtil {
	
	/**
	 * Constructor
	 */
	private TestUtil() {}
	
	/**
	 * This method convert specified object to json 
	 * @param object
	 * @return JSON
	 * @throws IOException
	 */
	public static String json(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        return mapper.writeValueAsString(object);
    }
	
	
}
