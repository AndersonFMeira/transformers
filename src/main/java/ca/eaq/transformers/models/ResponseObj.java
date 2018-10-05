package ca.eaq.transformers.models;

public class ResponseObj {

	private String message;
	
	public ResponseObj(String message) {
		setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
