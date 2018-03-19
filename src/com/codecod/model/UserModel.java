package com.codecod.model;

public class UserModel {

	String id;
	String name;
	String email;
	String role;
	String password;

	public UserModel() {

	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public static String selectByEmailAndPassword(String email, String password) {
		return String.format("select * from user where email='%s' and password = '%s'", email, password);
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", email=" + email + ", role=" + role + ", password="
				+ password + "]";
	}

}
