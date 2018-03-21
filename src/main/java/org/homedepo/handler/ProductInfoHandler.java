package org.homedepo.handler;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.homedepo.dao.ProductPriceInfoDao;
import org.homedepo.exception.ProductReviewException;
import org.homedepo.util.MessageConstant;
import org.homedepo.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
//@CacheConfig(cacheNames = "productName")
public class ProductInfoHandler {

	private static final String ERROR_CONNECTING_TO_REDSKY_SERVICE = "Error connecting to redsky service";

	private static final String ERROR_PARSING_JSON = "Error parsing json in getPrdInfoFrmRedsky() for id";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PropertiesUtil propertiesUtil;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	ProductPriceInfoDao productInfoDao; 

	
	@Cacheable(value="improperWords", key="#key")
	public List<String> getvalueFromCache(String key) {
		List<String> curseWords = productInfoDao.getCurseWords();
		return curseWords;
	}

	/**
	 * Gets product name from redsky api
	 * Product name for a given id will be cached for 24 hours
	 * @param id
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	@Cacheable(value="productName", key="#id")
	public String getPrdInfoFrmRedsky(int id) throws JsonProcessingException, IOException {

		JsonNode product = null;
		int counter = 0;
		do {
			logger.info("Calling Redsky API for product ="+id);
			try {
				ResponseEntity<String> responseEntity = restTemplate.getForEntity(propertiesUtil.getRedskyURL()+id+MessageConstant.EXCLUSIONS,
								String.class);
						
				if(responseEntity.getStatusCode().equals(HttpStatus.OK)) {
					product = retreiveProductName(responseEntity);
					if(StringUtils.isEmpty(product.asText())) {
						logger.error(ERROR_PARSING_JSON +id );
						throw new ProductReviewException(ERROR_PARSING_JSON +id);
					}
					break;
				}else {
					counter++;
					logger.error("Error calling redsky service, id"+id);
					
				}
			}catch (ProductReviewException e) { 
				throw new ProductReviewException(e.getMessage());
			}catch (Exception e) {
			
				//isProductInfoAvailable = false;
				counter++;
				sleepThread();
				if(null == product && counter>=3) {
					logger.error(ERROR_CONNECTING_TO_REDSKY_SERVICE + e.getMessage());
					throw new ProductReviewException(ERROR_CONNECTING_TO_REDSKY_SERVICE+e.getMessage());
				}
			}
			//retry 3 times in case there is no response from redsky
		} while(counter <=3 );
		
		return product.asText();
	}

	private void sleepThread()  {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	private JsonNode retreiveProductName(ResponseEntity<String> responseEntity)
			throws IOException, JsonProcessingException {
		JsonNode product;
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(responseEntity.getBody());
		product = root.path(MessageConstant.PRODUCT)
				.path(MessageConstant.ITEM).path(MessageConstant.PRODUCT_DESCRIPTION).path(MessageConstant.TITLE);
		return product;
	}
	
	/*@CacheEvict(allEntries = true)
    public void clearCache(){}*/


}
