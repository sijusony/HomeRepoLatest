package org.homedepo.controller;

import java.util.List;

import org.homedepo.model.ProductReview;
import org.homedepo.model.response.CustomerReviewResponse;
import org.homedepo.model.response.ResponseHeader;
import org.homedepo.service.CustomerReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/rest/myretail")
public class ProductInfoController {
	


	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CustomerReviewService customerService;
	
	/**
	 * Create pricing information for the given product in cassandra
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/productReview", method = RequestMethod.POST)    
	CustomerReviewResponse create(@RequestBody ProductReview product) {      
		logger.info("Inside ProductInfoController.create, Product="+product);
      return customerService.createCustomerReview(product);
    }
	
	/**
	 * Get latest pricing information for the given product id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/productReview/{id}/{minRating}/{maxRating}}", method = RequestMethod.GET)
    CustomerReviewResponse findProductReview(@PathVariable("id") int id,@PathVariable("minRating") int minRating,@PathVariable("maxRating") int maxRating) {   
		logger.info("Inside ProductInfoController.findById, id="+id);
		return customerService.getProductReview(id,minRating,maxRating);
    }
 
	/**
	 * Delete product record for the given product
	 * @param id
	 */
    @RequestMapping(value = "/productReview/{id}", method = RequestMethod.DELETE)
    ResponseHeader delete(@PathVariable(value="id") int id) {
    	logger.info("Inside ProductInfoController.delete, id="+id);
    	return customerService.deleteProductReview(id); 
    }
 
    /**
     * Get all product information available in database
     * @return
     */
    @RequestMapping(value="/productReview", method = RequestMethod.GET)
    List<ProductReview> findAll() {
    		logger.info("Inside ProductInfoController.findAll");
			return customerService.getAllProducts();
    }
 
    
	 /**
	  * Updates the given product infromation
	  * @param product
	  * @return
	  */
    @RequestMapping(value = "/productReview", method = RequestMethod.PUT)
    CustomerReviewResponse update(@RequestBody ProductReview product) {
    	logger.info("Inside ProductInfoController.update, Product="+product);
        return customerService.updateCustomerReview(product);
    }
    
    /**
     * Custom exception handler for ProductInfoController
     * @param ex
     * @return
     */
   /* @ExceptionHandler(TargetPricingException.class)
    public ResponseEntity<ProductInfoResponse> exceptionHandler(RuntimeException ex) {
    	logger.error("Exception calling Pricing application :" +ex.getMessage());
    	ProductInfoResponse response = new ProductInfoResponse();
    	ResponseHeader header = new ResponseHeader();
    	header.setErrorCode(MessageUtil.ERROR);
    	header.setErrorMessage(ex.getMessage());
    	response.setHeaderResponse(header);
    	return new ResponseEntity<ProductInfoResponse>(response, HttpStatus.OK);
    }*/
	
}
