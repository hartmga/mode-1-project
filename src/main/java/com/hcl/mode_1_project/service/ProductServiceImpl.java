package com.hcl.mode_1_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mode_1_project.model.Product;
import com.hcl.mode_1_project.repository.ProductRepository;

import exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository pr;

	@Override
	public Product getProductById(long id) {
		return pr.findById(id).orElse(null);
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
		if (pr.findById(id).isEmpty()) {
			throw new ProductNotFoundException(
					String.format("Cannot update product with id %d. Product does not exist.", id));
		}
		prod.setId(id);
		return pr.save(prod);
	}

	@Override
	public void deleteProduct(long id) throws ProductNotFoundException {
		if (pr.findById(id).isEmpty()) {
			throw new ProductNotFoundException(
					String.format("Cannot delete product with id %d. Product does not exist.", id));
		}
		pr.deleteById(id);
	}

}
