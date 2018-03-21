package org.homedepo;


import org.homedepo.handler.ProductInfoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@EnableAsync
@SpringBootApplication
public class TargetProductInfoApplication {
	
	@Autowired
    private CacheManager cacheManager;
	@Autowired
	private ProductInfoHandler handler;

	
	public static void main(String[] args) 
	{
		SpringApplication.run(TargetProductInfoApplication.class, args); 
		
	}
	
	

	
	

}
