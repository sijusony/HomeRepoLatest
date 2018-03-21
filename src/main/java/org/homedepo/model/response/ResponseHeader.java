package org.homedepo.model.response;

import java.io.Serializable;

/**
 * 
 * @author siju
 *
 */

public class ResponseHeader implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6677265402983757750L;
	
	
	private String errorCode;
	private String errorMessage;
	
	
	
	public ResponseHeader() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseHeader(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		return "ResponseHeader [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseHeader other = (ResponseHeader) obj;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		return true;
	}
	
	
	
}
