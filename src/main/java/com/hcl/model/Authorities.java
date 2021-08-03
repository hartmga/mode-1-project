package com.hcl.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "authorities")
@IdClass(AuthorityId.class)
public class Authorities {

	@Id
	private String username;

	@Id
	private String role;

}
