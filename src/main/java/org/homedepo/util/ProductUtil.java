package org.homedepo.util;


import org.springframework.http.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.homedepo.exception.InputValidationException;
import org.homedepo.model.ProductReview;

public class ProductUtil {

	public static void validateProductRequest(ProductReview product) {

		if(product.getProductId() == 0) {
			throw new InputValidationException("Product ID is mandatory parameter",HttpStatus.BAD_REQUEST.value());
		}
		if( product.getCustomerRating()<=0 || product.getCustomerRating()>5){
			throw new InputValidationException("Provide Valid customer rating (1-5)",HttpStatus.BAD_REQUEST.value());
		}
		
		
	}
}
