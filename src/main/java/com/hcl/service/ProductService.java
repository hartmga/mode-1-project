package com.hcl.service;

import java.util.List;

import com.hcl.model.Product;

import exception.ProductNotFoundException;

public interface ProductService {

	public Product getProductById(long id);

	public List<Product> getAllProducts();

	public Product addProduct(Product prod);

	public Product updateProduct(Product prod, long id) throws ProductNotFoundException;

	public void deleteProduct(long id) throws ProductNotFoundException;

}
