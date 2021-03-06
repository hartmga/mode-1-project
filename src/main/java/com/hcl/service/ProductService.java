package com.hcl.service;

import java.util.List;

import com.hcl.exception.ProductNotFoundException;
import com.hcl.model.Product;

public interface ProductService {

	public Product getProductById(long id) throws ProductNotFoundException;

	public List<Product> getAllProducts();

	public Product addProduct(Product prod);

	public Product updateProduct(Product prod, long id) throws ProductNotFoundException;

	public void deleteProduct(long id) throws ProductNotFoundException;
	
	public double getProductTotal(List<Product> products);

}
