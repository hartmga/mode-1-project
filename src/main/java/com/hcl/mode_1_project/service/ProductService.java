package com.hcl.mode_1_project.service;

import java.util.List;

import com.hcl.mode_1_project.model.Product;

import exception.ProductNotFoundException;

public interface ProductService {

	public Product getProductById(long id);

	public List<Product> getAllProducts();

	public Product addProduct(Product prod);

	public Product updateProduct(Product prod, long id) throws ProductNotFoundException;

	public void deleteProduct(long id) throws ProductNotFoundException;

}
