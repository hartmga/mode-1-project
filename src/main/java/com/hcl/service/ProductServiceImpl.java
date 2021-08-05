package com.hcl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.exception.ProductNotFoundException;
import com.hcl.model.Product;
import com.hcl.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository pr;

	@Override
	public Product getProductById(long id) throws ProductNotFoundException {
		log.info("getting product with id"+id);
		return pr.findById(id).orElseThrow(() -> new ProductNotFoundException(
				String.format("Cannot update product with id %d. Product does not exist.", id)));
	}

	@Override
	public List<Product> getAllProducts() {
		log.info("getting all products");
		Iterable<Product> allProds = pr.findAll();
		List<Product> productList = new ArrayList<>();
		allProds.forEach(productList::add);
		return productList;
	}

	@Override
	public Product addProduct(Product prod) {
		Product newProd = pr.save(prod);
		log.info("created product "+newProd);
		return newProd;
	}

	@Override
	public Product updateProduct(Product prod, long id) throws ProductNotFoundException {
		pr.findById(id).orElseThrow(() -> new ProductNotFoundException(
				String.format("Cannot update product with id %d. Product does not exist.", id)));
		prod.setId(id);
		log.info("updating product: "+prod);
		return pr.save(prod);
	}

	@Override
	public void deleteProduct(long id) throws ProductNotFoundException {
		pr.findById(id).orElseThrow(() -> new ProductNotFoundException(
				String.format("Cannot update product with id %d. Product does not exist.", id)));
		log.info("deleting product with id "+id);
		pr.deleteById(id);
	}

	@Override
	public double getProductTotal(List<Product> products) {
		return products.stream().map(p -> p.getPrice() * p.getQuantity()).reduce(0d, (t1, t2) -> t1 + t2);
	}

}
