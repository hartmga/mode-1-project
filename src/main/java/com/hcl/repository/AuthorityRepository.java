package com.hcl.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hcl.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long>{

	public Optional<Authority> findByRole(String role);
	
}
