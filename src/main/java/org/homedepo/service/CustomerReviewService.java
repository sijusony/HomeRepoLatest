package org.homedepo.service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.homedepo.util.ProductUtil;
import org.homedepo.dao.ProductPriceInfoDao;
import org.homedepo.handler.ProductInfoHandler;
import org.homedepo.model.ProductReview;
import org.homedepo.model.response.CustomerReviewResponse;
import org.homedepo.model.response.ResponseHeader;
import org.homedepo.util.MessageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.homedepo.exception.ProductReviewException;

/**
 * 
 * @author siju
 * Service method for Product Information
 */
@Service
public class CustomerReviewService {

	

	private static final String IMPROPER_WORDS = "improperWords";

		private static final String CALLING_PRODUCT_INFO_SERVICE_GET_PRODUCT_ID = "calling ProductInfoService.getProduct, id= ";

	private static final String EXCEPTION_IN_PRODUCT_INFO_SERVICE_DELETE_PRODUCT_ID = "Exception in ProductInfoService.deleteProduct, id= ";

	private static final String CALLING_PRODUCT_INFO_SERVICE_UPDATE_PRODUCT_PRODUCT = "calling ProductInfoService.updateProduct, Product= ";

	private static final String NO_PRODUCT_INFO_AVAIALBE = "No Reviews availalble for the Product for the given range";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductPriceInfoDao productInfoDao; 

	@Autowired
	ProductInfoHandler productHandler;
	
	/**
	 * Service to get Product for the given id
	 * combines the pricing information from cassandra with product name from redsky service
	 * @param id
	 * @return
	 */
	public CustomerReviewResponse getProductReview(int id,int min, int max){
		
		CustomerReviewResponse response = new CustomerReviewResponse();
    	try {
    			logger.info(CALLING_PRODUCT_INFO_SERVICE_GET_PRODUCT_ID+id );
    			//get all products for the primary key
    			List<ProductReview> products = productInfoDao.select(id,min,max);
    			if(null == products || products.size()==0) {
    				handleError(NO_PRODUCT_INFO_AVAIALBE+id );
    			}
    			
    			//product.setProductName(productName);
				populateResponse(products, response, MessageConstant.SUCCESS, HttpStatus.OK.toString());
		} catch (Exception e) {
			logger.error("Exception in ProductInfoService.getProduct, id= "+id , e);
			throw new ProductReviewException(e);
		}
    	return response;
	}

	private void handleError( String message) {
			logger.error(message );
			throw new ProductReviewException(message );
	}
	
	/**
	 * Service to Insert/update pricing information in cassandra DB
	 * @param product
	 * @return
	 */
	public CustomerReviewResponse createCustomerReview(ProductReview product) {
		
		CustomerReviewResponse response = new CustomerReviewResponse();
		
    	try {
    			logger.info("calling ProductInfoService.createProductReview, Product= "+product );
    			ProductUtil.validateProductRequest(product);
    			//validate curse words
    			// List<String> curseWords = productInfoDao.getCurseWords();
    			List<String> curseWords = productHandler.getvalueFromCache(IMPROPER_WORDS);
    			 boolean improperComment  = curseWords.parallelStream().anyMatch(product.getComments()::contains); 
    			if(improperComment) {
    				throw new ProductReviewException("Comments with obscene words cannot be accepted");
    			}
    			updateProductModel(product);
    			ProductReview productResponse = productInfoDao.createProductReview( product);
    			List<ProductReview> products = new ArrayList<ProductReview>();
    			products.add(productResponse);
    			populateResponse(products, response, MessageConstant.SUCCESS, HttpStatus.CREATED.toString());
    			
		} catch (Exception e) {
			logger.error("Exception in ProductInfoService.createProduct, Product= "+product , e);
			throw new ProductReviewException(e);
		}
    	return response;
	}

	

	public CustomerReviewResponse updateCustomerReview(ProductReview product) {
		CustomerReviewResponse response = new CustomerReviewResponse();
		try {
			logger.info(CALLING_PRODUCT_INFO_SERVICE_UPDATE_PRODUCT_PRODUCT+product );
			ProductUtil.validateProductRequest(product);
			updateProductModel(product);
			ProductReview productResponse =  productInfoDao.updateProductPriceInfo(product);
			List<ProductReview> products = new ArrayList<ProductReview>();
			products.add(productResponse);
			populateResponse(products, response, MessageConstant.SUCCESS, HttpStatus.CREATED.toString());
		} catch (Exception e) {
			logger.error("Exception in ProductInfoService.createProduct, Product= "+product , e);
			throw new ProductReviewException(e);
		}
    	return response;
		
	}


	/**
	 * Serive to Delete a product based on the give ProductId
	 * @param id
	 * @return
	 */
	public ResponseHeader deleteProductReview(int id) {
		ResponseHeader responseHeader = null;
		try {
			productInfoDao.deleteProductPriceInfo(id);
			responseHeader = populateErrorCode(MessageConstant.SUCCESS, HttpStatus.ACCEPTED.toString());
		} catch (Exception e) {
			logger.error(EXCEPTION_IN_PRODUCT_INFO_SERVICE_DELETE_PRODUCT_ID+id , e);
			throw new ProductReviewException(e);
		}
		return responseHeader;

	}

	/**
	 * Serivice to get all products 
	 * @return
	 */
	public List<ProductReview> getAllProducts() {
		return productInfoDao.getAllProductReviewInfo();
	}
	
	public String getPrdInfoFrmRedsky(int id) throws JsonProcessingException, IOException {
		return productHandler.getPrdInfoFrmRedsky(id);

	}
	
	
	private CustomerReviewResponse populateResponse(List<ProductReview> products, CustomerReviewResponse response, 
			String errorMessage, String errorCode) {
		
		response.setProducts(products);
		if(null != products && products.size()>0)
		response.setNumReviews(products.size());
		ResponseHeader responseHeader = populateErrorCode(errorMessage, errorCode);
		response.setHeaderResponse(responseHeader);
		return response;
		
	}

	private ResponseHeader populateErrorCode(String errorMessage, String errorCode) {
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setErrorMessage(errorMessage);
		responseHeader.setErrorCode(errorCode);
		return responseHeader;
	}

	/**
	 *  If timestamp is not populated from request then set the values to current Date
		update ts, refractor code 
	 * @param product
	 */
	private void updateProductModel(ProductReview product) {
		//i
		if(null==product.getUpdateTs() ){
			product.setUpdateTs(new Date());
		}
	}
	
	private ProductReview getLatestProductData(List<ProductReview> products) {
		if(null != products && products.size() > 0 ) {
			return products.get(0);
		}
		return null;
	}

	
}
