package org.homedepo.dao.impl;

import java.util.List;

import org.homedepo.dao.ProductPriceInfoDao;
import org.homedepo.model.ProductReview;
import org.homedepo.util.MyCassandraTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author siju
 * DAO layer which connects to database
 */
@Repository
public class ProductInfoDaoImpl implements ProductPriceInfoDao{
	
	 @Autowired
	    private MyCassandraTemplate cassandraTemplate;


	@Override
	public ProductReview createProductReview(ProductReview product) {
		return cassandraTemplate.create(product);
	}

	@Override
	public ProductReview getProductPriceInfo(int id) {
		return cassandraTemplate.findById(id, ProductReview.class);
	}

	@Override
	public ProductReview updateProductPriceInfo(ProductReview product) {
		return cassandraTemplate.update(product, ProductReview.class);
	}

	@Override
	public void deleteProductPriceInfo(int id) {
		cassandraTemplate.deleteById(id, ProductReview.class);
	}

	@Override
	public List<ProductReview> getAllProductReviewInfo() {
		return cassandraTemplate.findAll(ProductReview.class);
	}

	@Override
	public List<ProductReview> select(int id,int min,int max) {
		return cassandraTemplate.select("select * from customer_review where customer_rating >="+min+"and  customer_rating <="+max, ProductReview.class);
	}

	@Override
	public List<String> getCurseWords() {
		return cassandraTemplate.select("select value from reference_data where type='improper_words'", String.class);
	}
	

}
