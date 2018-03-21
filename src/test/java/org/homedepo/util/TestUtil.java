package org.homedepo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.homedepo.model.ProductReview;
import org.homedepo.model.response.CustomerReviewResponse;
import org.homedepo.model.response.ResponseHeader;

public class TestUtil {

	
	public static CustomerReviewResponse createMockResponseObject(Date date,int productId,String currency) {
		CustomerReviewResponse mockResponse = new CustomerReviewResponse();
		    ProductReview product = createProduct(date, productId, currency);
		    ResponseHeader header = new ResponseHeader();
		    header.setErrorCode("200");
		    header.setErrorMessage("SUCCESS");
		    List<ProductReview> products = new ArrayList<ProductReview>();
		    products.add(product);
		    mockResponse.setProducts(products);
		    mockResponse.setHeaderResponse(header);
		return mockResponse;
	}

	public static ProductReview createProduct(Date date, int productId, String currency) {
		ProductReview product = new ProductReview();
		product.setComments("comment");;
		product.setCustomerId(1234);
		product.setCustomerRating(4);;
		product.setUpdateTs(date);
		product.setProductId(productId);
		return product;
	}
	
	
	public static void main(String[] args) {
		  List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		  System.out.println(values.stream().filter(x -> x < 5).filter(x -> x > 3)/*.mapToInt(x -> x)*/.collect(Collectors.toList()));
		  
		  
	}
	
}
