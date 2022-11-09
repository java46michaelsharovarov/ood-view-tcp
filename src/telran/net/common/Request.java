package telran.net.common;

import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	public String requestType;
	public Serializable requestData;
	
	public Request(String requestType, Serializable requestData) {		
		this.requestType = requestType;
		this.requestData = requestData;
	}	

}