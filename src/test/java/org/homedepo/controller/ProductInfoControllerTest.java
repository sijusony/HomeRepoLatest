//package org.myretail.controller;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.myretail.TargetProductInfoApplication;
//import org.myretail.model.Product;
//import org.myretail.model.response.ProductInfoResponse;
//import org.myretail.model.response.ResponseHeader;
//import org.myretail.service.ProductInfoService;
//import org.myretail.util.TestUtil;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes =  TargetProductInfoApplication.class)
//@SpringBootTest
////@FixedMethod
//public class ProductInfoControllerTest {
//	
//	 //@Autowired
//	 private MockMvc mockMvc;
//	 
//	 @Autowired
//	 private WebApplicationContext wac;
//	 
//	@MockBean
//	private ProductInfoService productService;
//	
//	@Before
//	public void setup() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//	}
//	
//	@Test
//	public void findById_product_SUCCESS()
//	  throws Exception {
//	     
//		Date date = new Date();
//	    ProductInfoResponse mockResponse = TestUtil.createMockResponseObject(date,123,"USD");
//	    
//	    
//	    Mockito.when(productService.getProduct(Mockito.anyInt())).thenReturn(mockResponse);
//
//	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/rest/myretail/product/123").accept(
//				MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//	    		
//		ObjectMapper mapper = new ObjectMapper();
//		ProductInfoResponse response = mapper.readValue(result.getResponse().getContentAsString(), ProductInfoResponse.class); 
//		System.out.println(response);
//		assertEquals("SUCCESS", response.getHeaderResponse().getErrorMessage());
//		
//	}
//	
//	@Test
//	public void findById_product_EMPTY_PRODUCT()
//	  throws Exception {
//	     
//		Date date = new Date();
//	    ProductInfoResponse mockResponse = TestUtil.createMockResponseObject(date,0,"USD");
//	    
//	    
//	    Mockito.when(productService.getProduct(Mockito.anyInt())).thenReturn(mockResponse);
//
//	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/rest/myretail/product/123").accept(
//				MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//	    		
//		ObjectMapper mapper = new ObjectMapper();
//		ProductInfoResponse response = mapper.readValue(result.getResponse().getContentAsString(), ProductInfoResponse.class); 
//		
//		assertEquals("SUCCESS", response.getHeaderResponse().getErrorMessage());
//		
//	}
//	
//	@Test
//	public void findById_product_NONVALID_CURRENCY()
//	  throws Exception {
//	     
//		Date date = new Date();
//	    ProductInfoResponse mockResponse = TestUtil.createMockResponseObject(date,0,"USD!@#");
//	    
//	    
//	    Mockito.when(productService.getProduct(Mockito.anyInt())).thenReturn(mockResponse);
//
//	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				"/rest/myretail/product/123").accept(
//				MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//	    		
//		ObjectMapper mapper = new ObjectMapper();
//		ProductInfoResponse response = mapper.readValue(result.getResponse().getContentAsString(), ProductInfoResponse.class); 
//		assertEquals("SUCCESS", response.getHeaderResponse().getErrorMessage());
//		
//	}
//	
//	
//	@Test
//	public void createProduct_ERROR() throws Exception {
//		  
//			Date date = new Date();
//			ProductInfoResponse mockResponse = TestUtil.createMockResponseObject(date,123,"USD");
//		    
//		    Mockito.when(
//		    		productService.createProduct(Mockito.any(Product.class))).thenReturn(mockResponse);
//		    
//		    RequestBuilder requestBuilder = MockMvcRequestBuilders
//					.post("/rest/myretail/product")
//					.accept(MediaType.APPLICATION_JSON)//.content(mockResponse)
//					.contentType(MediaType.APPLICATION_JSON);
//		    
//		    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//			ObjectMapper mapper = new ObjectMapper();
//			ProductInfoResponse response = mapper.readValue(result.getResponse().getContentAsString(), ProductInfoResponse.class); 
//			System.out.println(response);
//			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), Integer.parseInt(response.getHeaderResponse().getErrorCode()));
//		 
//	} 
//	
//	//@Test
//	public void assert_true() {
//			assertTrue(true);
//		}
//
//	
//	
//	
//}
