package com.hcl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.exception.ProductNotFoundException;
import com.hcl.model.Product;
import com.hcl.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository pr;

	@Override
	public Product getProductById(long id) throws ProductNotFoundException {
		return pr.findById(id).orElseThrow(() -> new ProductNotFoundException(
				String.format("Cannot update product with id %d. Product does not exist.", id)));
	}

	@Override
	public List<Product> getAllProducts() {
		Iterable<Product> allProds = pr.findAll();
		List<Product> productList = new ArrayList<>();
		allProds.forEach(productList::add);
		return productList;
	}

	@Override
	public Product addProduct(Product prod) {
		return pr.save(prod);
	}

	@Override
	public Product updateProduct(Product prod, long id) throws ProductNotFoundException {
		pr.findById(id).orElseThrow(() -> new ProductNotFoundException(
				String.format("Cannot update product with id %d. Product does not exist.", id)));
		prod.setId(id);
		return pr.save(prod);
	}

	@Override
	public void deleteProduct(long id) throws ProductNotFoundException {
		pr.findById(id).orElseThrow(() -> new ProductNotFoundException(
				String.format("Cannot update product with id %d. Product does not exist.", id)));
		pr.deleteById(id);
	}

}
