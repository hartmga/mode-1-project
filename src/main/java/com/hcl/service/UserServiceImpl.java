package com.hcl.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.exception.AuthorityDoesNotExistException;
import com.hcl.exception.UsernameUnavailableException;
import com.hcl.model.Authority;
import com.hcl.model.User;
import com.hcl.repository.AuthorityRepository;
import com.hcl.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	AuthorityRepository ar;

	@Override
	public User registerUser(String username, String password) throws AuthorityDoesNotExistException, UsernameUnavailableException {
		User newUser = new User();
		newUser.setUsername(username);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		newUser.setPassword(passwordEncoder.encode(password));
		if(ur.getUserByUsername(newUser.getUsername())!=null) {
			throw new UsernameUnavailableException("This username is already taken");
		}
		Authority userAuthority = new Authority();
		userAuthority.setRole("ROLE_USER");
		newUser.setAuthorities(new HashSet<Authority>());
		newUser.getAuthorities().add(userAuthority);
		for(Authority a: newUser.getAuthorities()) {
			long authId = ar.findByRole(a.getRole()).orElseThrow(() -> new AuthorityDoesNotExistException("This role does not exist")).getId();
			a.setId(authId);
			ar.save(a);
		}
		newUser.setEnabled(true);
		User saved =  ur.save(newUser);
		log.info("saved new user: "+saved);
		return saved;
	}
	
	
}
