package com.hcl.mode_1_project.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorityId implements Serializable {
	private String username;
	private String role;
}
