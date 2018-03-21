package org.homedepo.model.response;

import java.io.Serializable;

import org.homedepo.model.ProductReview;


public class ProductResponse implements Serializable {
	
	private static final long serialVersionUID = -4853889535234399674L;

	private ProductReview productInfo;
	
	private ErrorResponse errorMessage;

	public ProductResponse() {
		super();
	}

	public ProductResponse(ProductReview productInfo, ErrorResponse errorMessage) {
		this.productInfo = productInfo;
		this.errorMessage = errorMessage;
	}

	public ProductReview getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(ProductReview productInfo) {
		this.productInfo = productInfo;
	}

	public ErrorResponse getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(ErrorResponse errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ProductResponse [productInfo=" + productInfo + ", errorMessage=" + errorMessage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + ((productInfo == null) ? 0 : productInfo.hashCode());
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
		ProductResponse other = (ProductResponse) obj;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (productInfo == null) {
			if (other.productInfo != null)
				return false;
		} else if (!productInfo.equals(other.productInfo))
			return false;
		return true;
	}
	
	

}
