package com.hcl.service;

import com.hcl.exception.AuthorityDoesNotExistException;
import com.hcl.exception.UsernameUnavailableException;
import com.hcl.model.User;

public interface UserService {

	User registerUser(String username, String password) throws AuthorityDoesNotExistException, UsernameUnavailableException;
		
}
