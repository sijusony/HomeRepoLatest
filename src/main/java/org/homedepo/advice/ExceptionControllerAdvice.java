package org.homedepo.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.homedepo.exception.InputValidationException;
import org.homedepo.exception.ProductReviewException;
import org.homedepo.model.response.CustomerReviewResponse;
import org.homedepo.model.response.ResponseHeader;

/**
 * 
 * @author siju
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Custom exception handler
	 * @param ex
	 * @return
	 */
	 @ExceptionHandler({ProductReviewException.class,Exception.class})
	    public ResponseEntity<CustomerReviewResponse> exceptionHandler(RuntimeException ex) {
	    	logger.error("Exception calling Pricing application :" +ex.getMessage());
	    	CustomerReviewResponse response = createErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR.toString());
	    	return new ResponseEntity<CustomerReviewResponse>(response, HttpStatus.OK);
	    }


	 /**
	  * Handles validation errors
	  * @param ex
	  * @return
	  */
	 @ExceptionHandler(InputValidationException.class)
	    public ResponseEntity<CustomerReviewResponse> exceptionHandlerValidation(RuntimeException ex) {
	    	logger.error("Exception calling Pricing application :" +ex.getMessage());
	    	CustomerReviewResponse response = createErrorResponse(ex, HttpStatus.BAD_REQUEST.toString());
	    	return new ResponseEntity<CustomerReviewResponse>(response, HttpStatus.OK);
	    }

	private CustomerReviewResponse createErrorResponse(RuntimeException ex, String tst) {
		CustomerReviewResponse response = new CustomerReviewResponse();
		ResponseHeader header = new ResponseHeader();
		header.setErrorCode(tst);
		header.setErrorMessage(ex.getMessage());
		response.setHeaderResponse(header);
		return response;
	}
}
