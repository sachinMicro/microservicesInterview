package com.interview.ldapserver.entity;

import org.springframework.boot.context.properties.bind.Name;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(base = "ou=users", objectClasses = { "person", "inetOrgPerson", "top" })
public class User {
	
	@Id
	private Name id;

	private @Attribute(name = "cn") String username;
	private @Attribute(name = "userPassword") String password;
	
	
	public Name getId() {
		return id;
	}
	public void setId(Name id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
			
}