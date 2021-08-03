package com.hcl.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorityId implements Serializable {
	private String username;
	private String role;
}
