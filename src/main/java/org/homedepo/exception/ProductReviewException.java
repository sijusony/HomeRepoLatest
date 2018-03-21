package org.homedepo.exception;

@SuppressWarnings("serial")
public class ProductReviewException extends RuntimeException {

	public ProductReviewException(String message) {
	        super(message);
	    }

	    public ProductReviewException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    
	    public ProductReviewException(Throwable cause) {
	        super( cause);
	    }

}
