package org.homedepo.aop;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.homedepo.metrics.MetricsData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class ActivityLoggingAspect {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private @Autowired HttpServletRequest request;
    @Around("execution (* org.myretail.controller..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	Object proceed;
    	long startTs = System.currentTimeMillis();
        MetricsData mspMetrics = getMetrics(joinPoint);
		try {
            proceed = joinPoint.proceed();
        } catch (DataAccessResourceFailureException dare) {
            // Retry 2 times for database error
            LOGGER.warn("Error occurred while connecting to database", dare);
            try {
            	proceed =  joinPoint.proceed();
            } catch (DataAccessResourceFailureException dare2) {
                LOGGER.warn("Error occurred while connecting to database 2", dare2);
                proceed =  joinPoint.proceed();
            } 
        } catch (Throwable t) {
        	mspMetrics.setResponseTime((System.currentTimeMillis() - startTs));
        	if(t != null){
        		mspMetrics.setErrMsg(t +" , exmsg="+t.getMessage());
        	}
        	LOGGER.error(mspMetrics.toString());
            throw t;
        }
		finally{
			mspMetrics.setSuccess(true);
	        mspMetrics.setResponseTime((System.currentTimeMillis() - startTs));
	        LOGGER.info(mspMetrics.toString());
		}
		
        return proceed;
    }
    

	private MetricsData getMetrics(ProceedingJoinPoint joinPoint) {
		MetricsData mspMetrics = new MetricsData();
		try {
			mspMetrics.setClassName(joinPoint.getSignature().getDeclaringType().getSimpleName());
			mspMetrics.setOperation(joinPoint.getSignature().getName());
			mspMetrics.setInputParams(Arrays.toString(joinPoint.getArgs()));
			mspMetrics.setClientIp(request.getRemoteHost());
			
		} catch (Exception e) {
			LOGGER.warn("Unable to collect MetricsData:"+ e);
		}
		return mspMetrics;
	}
	
}

