package tim9.realEstate.controller;

import java.io.Serializable;

public class StringResponse implements Serializable {

	private static final long serialVersionUID = -6267494759416829815L;
	
	private String response;
	
	public StringResponse() {
		super();
	}
	
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
