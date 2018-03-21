package org.homedepo.model.response;

import java.io.Serializable;
import java.util.List;

import org.homedepo.model.ProductReview;
import org.homedepo.model.response.ResponseHeader;


public class CustomerReviewResponse implements Serializable{
	
	private static final long serialVersionUID = -5869768973878452544L;

	private List<ProductReview> products;
	
	private ResponseHeader headerResponse;
	
	private int numReviews;

	@Override
	public String toString() {
		return "CustomerReviewResponse [products=" + products + ", headerResponse=" + headerResponse + ", numReviews="
				+ numReviews + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((headerResponse == null) ? 0 : headerResponse.hashCode());
		result = prime * result + numReviews;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
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
		CustomerReviewResponse other = (CustomerReviewResponse) obj;
		if (headerResponse == null) {
			if (other.headerResponse != null)
				return false;
		} else if (!headerResponse.equals(other.headerResponse))
			return false;
		if (numReviews != other.numReviews)
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}

	public List<ProductReview> getProducts() {
		return products;
	}

	public void setProducts(List<ProductReview> products) {
		this.products = products;
	}

	public ResponseHeader getHeaderResponse() {
		return headerResponse;
	}

	public void setHeaderResponse(ResponseHeader headerResponse) {
		this.headerResponse = headerResponse;
	}

	public int getNumReviews() {
		return numReviews;
	}

	public void setNumReviews(int numReviews) {
		this.numReviews = numReviews;
	}

	
	
	
	}
