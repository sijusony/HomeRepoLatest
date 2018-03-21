package org.homedepo.dao;


import java.util.List;

import org.homedepo.model.ProductReview;

/**
 * 
 * @author siju
 * DAO layer Interface
 */
public interface ProductPriceInfoDao {
	
    public ProductReview createProductReview(ProductReview product);
    
    public ProductReview getProductPriceInfo(int id);
    
    public ProductReview updateProductPriceInfo(ProductReview product);
    
    public void deleteProductPriceInfo(int id);
    
    public List<ProductReview> getAllProductReviewInfo();
    
    public List<ProductReview> select(int id, int min, int max) ;

	public List<String> getCurseWords();
    
    
	

}
