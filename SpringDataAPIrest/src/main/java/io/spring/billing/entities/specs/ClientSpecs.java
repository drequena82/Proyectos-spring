package io.spring.billing.entities.specs;

import org.springframework.data.jpa.domain.Specification;

import io.spring.billing.entities.Client;

public class ClientSpecs {
	
	public static Specification<Client> hasName(String name){
		return (root,query,builder) -> builder.like(root.get("name"), "%" + name + "%");
	}
	
	public static Specification<Client> hasSurname(String surname){
		return (root,query,builder) -> builder.like(root.get("surname"), "%" + surname + "%");
	}
	
	public static Specification<Client> hasEmail(String email){
		return (root,query,builder) -> builder.like(root.get("email"), "%" + email + "%");
	}
}
