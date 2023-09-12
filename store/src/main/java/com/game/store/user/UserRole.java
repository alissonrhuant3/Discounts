package com.game.store.user;

public enum UserRole {

	ADMIN("admin"),

	USER("user");
	
	private String role;
	
	public void setRole(String role) {
		this.role = role;
	}

	UserRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
