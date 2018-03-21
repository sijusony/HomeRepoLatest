package org.homedepo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.homedepo.dao.ProductPriceInfoDao;
import org.homedepo.exception.ProductReviewException;
import org.homedepo.handler.ProductInfoHandler;
import org.homedepo.model.ProductReview;
import org.homedepo.model.response.CustomerReviewResponse;
import org.homedepo.model.response.ResponseHeader;
import org.homedepo.util.MessageConstant;
import org.homedepo.util.ProductUtil;
import org.homedepo.util.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@DirtiesContext
public class ProductInfoServiceTest {
	
	
	@Mock
	ProductPriceInfoDao productInfoDao; 

	@Mock
	ProductInfoHandler productHandler;
	
	
	@InjectMocks
	private CustomerReviewService prodService;
	
	@Autowired
    private CacheManager cacheManager;
	
	/*@Before
	public void setUp() {
		//System.setProperty(org.springframework.cache.ehcache.EhCacheCacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY, "true");
	}
	
	@After
	public void tearDown() {
		//cacheManager.getCache("productName").clear();
		//cacheManager.getCache("productName").evict("productName");
		Runtime.getRuntime().addShutdownHook(Thread.currentThread());
	}*/
	
	
	@Test
	public void getProduct_SUCCESS(){
		
		int productId = 123;
		try {
			List<ProductReview> products  = new ArrayList<>();
			ProductReview product = TestUtil.createProduct(new Date(), productId, "USD");
			products.add(product);
			
			//mocking dao call
			when(productInfoDao.select(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(products);
			CustomerReviewResponse res = prodService.getProductReview(1234, 2, 3);
			
			System.out.println(res);
			assertEquals("SUCCESS", res.getHeaderResponse().getErrorMessage());
			//assertTrue(false);
		} catch (Exception e) {
			//String errorMessage = "No Reviews availalble for the Product for the given range";
			//assertTrue(e.getMessage().contains(errorMessage));
			
		}
	}
	
	@Test
	public void getProduct_PRODUCT_NOT_AVAILABLE_IN_DB(){
		
		int productId = 1234;
		try {
			List<ProductReview> products  = new ArrayList<>();
			ProductReview product = TestUtil.createProduct(new Date(), productId, "USD");
			products.add(product);
			
			//when(productInfoDao.select(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt())).thenReturn(products);
			CustomerReviewResponse res = prodService.getProductReview(1234, 2, 3);
			
			System.out.println(res);
			//assertEquals("SUCCESS", res.getHeaderResponse().getErrorMessage());
			assertTrue(false);
		} catch (Exception e) {
			String errorMessage = "No Reviews availalble for the Product for the given range"+productId;
			assertTrue(e.getMessage().contains(errorMessage));
			
		}
	}
	
	
	@Test
	public void createProduct_SUCCESS(){
		int productId = 123;
		try {
			ProductReview product = TestUtil.createProduct(new Date(), productId, "USD");
			
			when(productInfoDao.createProductReview( product)).thenReturn(product);
			CustomerReviewResponse res = prodService.createCustomerReview(product);
			assertEquals("SUCCESS", res.getHeaderResponse().getErrorMessage());
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
			
		}

	}
	
	
	@Test
	public void createProduct_throwing_vaidation_error_productId(){
		int productId = 0;
		try {
			ProductReview product = TestUtil.createProduct(new Date(), productId, "USD");
			
			when(productInfoDao.createProductReview( product)).thenReturn(product);
			CustomerReviewResponse res = prodService.createCustomerReview(product);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
			assertTrue(e.getMessage().contains("Product ID is mandatory parameter"));
		}

	}
	
	@Test
	public void updateProduct_SUCCESS(){
		int productId = 123;
		try {
			ProductReview product = TestUtil.createProduct(new Date(), productId, "USD");
			
			when(productInfoDao.updateProductPriceInfo( product)).thenReturn(product);
			CustomerReviewResponse res = prodService.updateCustomerReview(product);
			assertEquals("SUCCESS", res.getHeaderResponse().getErrorMessage());
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}

	}
	
	@Test
	public void updateProduct_throwing_vaidation_error_productId(){
		int productId = 0;
		try {
			ProductReview product = TestUtil.createProduct(new Date(), productId, "USD");
			
			when(productInfoDao.updateProductPriceInfo( product)).thenReturn(product);
			CustomerReviewResponse res = prodService.updateCustomerReview(product);
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
			assertTrue(e.getMessage().contains("Product ID is mandatory parameter"));
		}

	}
	
	@Test
	public void deleteProduct_SUCCESS(){
		int productId = 0;
		try {
			
			ResponseHeader res = prodService.deleteProductReview(123);
			//populateErrorCode(MessageConstant.SUCCESS, HttpStatus.ACCEPTED.toString());
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}

	}
	
	@Test
	public void getAll_SUCCESS(){
		try {
			List<ProductReview> products  = new ArrayList<>();
			ProductReview product = TestUtil.createProduct(new Date(), 123, "USD");
			ProductReview product1 = TestUtil.createProduct(new Date(), 456, "USD");
			products.add(product);
			products.add(product1);
			
			when(productInfoDao.getAllProductReviewInfo()).thenReturn(products);
			List<ProductReview> productsResp = prodService.getAllProducts();
			assertTrue(productsResp.size()==2);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}

	}
	
	
	//@Test
	public void assert_true() {
			assertTrue(true);
		}
	


}
