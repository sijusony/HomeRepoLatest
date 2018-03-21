package org.homedepo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.google.common.base.Optional;

public class AuthenticationFilter extends GenericFilterBean  {
	

	    private final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	    public static final String TOKEN_SESSION_KEY = "token";
	    public static final String USER_SESSION_KEY = "user";
	  //  private AuthenticationManager authenticationManager;

	 /*   public AuthenticationFilter(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }*/

	   /* @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest httpRequest = asHttp(request);
	        HttpServletResponse httpResponse = asHttp(response);

	        Optional<String> username = Optional.fromNullable(httpRequest.getHeader("X-Auth-Username"));
	        Optional<String> password = Optional.fromNullable(httpRequest.getHeader("X-Auth-Password"));
	        Optional<String> token = Optional.fromNullable(httpRequest.getHeader("X-Auth-Token"));
	        
	        
	    }*/

		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {

			  HttpServletRequest httpRequest = asHttp(request);
		        HttpServletResponse httpResponse = asHttp(response);

		        Optional<String> username = Optional.fromNullable(httpRequest.getHeader("X-Auth-Username"));
		        Optional<String> password = Optional.fromNullable(httpRequest.getHeader("X-Auth-Password"));
		        Optional<String> token = Optional.fromNullable(httpRequest.getHeader("X-Auth-Token"));
			
			logger.info("Inside filter chain");
			System.out.println(" testing -------------------------------------");
			
			  chain.doFilter(request, response);
			
		}
		
		
		 private HttpServletRequest asHttp(ServletRequest request) {
		        return (HttpServletRequest) request;
		    }
		 
		 private HttpServletResponse asHttp(ServletResponse response) {
		        return (HttpServletResponse) response;
		    }


}
