package com.hcl.mode_1_project.repository;

import org.springframework.data.repository.CrudRepository;

import com.hcl.mode_1_project.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
