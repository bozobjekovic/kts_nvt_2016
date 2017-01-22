package tim9.realEstate.dto;

import java.io.Serializable;

/**
 * Class represents response from server
 *
 */
public class StringResponse implements Serializable {

	private static final long serialVersionUID = -6267494759416829815L;

	private String response;

	/**
	 * Constructor
	 */
	public StringResponse() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param s
	 */
	public StringResponse(String s) {
		response = s;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
