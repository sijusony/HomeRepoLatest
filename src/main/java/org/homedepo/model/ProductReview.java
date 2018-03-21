package org.homedepo.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;


/**
 * 
 * @author siju
 * Model for Product
 */
//@Data
@Table(value="price_info")
public class ProductReview implements Serializable{
	
	private static final long serialVersionUID = 273821766508130875L;

	@NotBlank
	@NotEmpty(message = "Product Id cannot be empty.")
	@PrimaryKey("product_id") 
	private long productId;		
	
	//@PrimaryKeyColumn(name = "customer_id",type = PrimaryKeyType.CLUSTERED, ordinal = 1, ordering = Ordering.DESCENDING)
	@NotBlank
	@Column("customer_id") 
	private long customerId;		
	
	//@PrimaryKeyColumn(name = "update_ts", type = PrimaryKeyType.CLUSTERED, ordinal = 2, ordering = Ordering.DESCENDING)
	@NotBlank
	@Column("update_ts") 
	private Date updateTs;
	
	@Column("customer_rating") 
	@NotBlank
	private long customerRating;
	
	@Column("comments") 
	@NotBlank
	private String comments;
	
	public ProductReview() {
		super();
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Date getUpdateTs() {
		return updateTs;
	}

	public void setUpdateTs(Date updateTs) {
		this.updateTs = updateTs;
	}

	public long getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(long customerRating) {
		this.customerRating = customerRating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + (int) (customerId ^ (customerId >>> 32));
		result = prime * result + (int) (customerRating ^ (customerRating >>> 32));
		result = prime * result + (int) (productId ^ (productId >>> 32));
		result = prime * result + ((updateTs == null) ? 0 : updateTs.hashCode());
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
		ProductReview other = (ProductReview) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (customerId != other.customerId)
			return false;
		if (customerRating != other.customerRating)
			return false;
		if (productId != other.productId)
			return false;
		if (updateTs == null) {
			if (other.updateTs != null)
				return false;
		} else if (!updateTs.equals(other.updateTs))
			return false;
		return true;
	}

	

}
