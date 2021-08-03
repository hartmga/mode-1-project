package com.hcl.repository;

import org.springframework.data.repository.CrudRepository;

import com.hcl.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
