package org.homedepo.exception;

@SuppressWarnings("serial")
public class InputValidationException extends RuntimeException {
	
	private int errorCode;

	public InputValidationException(String message) {
	        super(message);
	    }

	    public InputValidationException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    
	    public InputValidationException(String message, int errorCode) {
	    	super(message);
	    	setErrorCode(errorCode);
	    }
	    
	    public InputValidationException(Throwable cause) {
	        super( cause);
	    }

		public int getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}
	    
	    

}
