/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moddel;

/**
 *
 * @author Kaine
 */
public class User {

	private String userId;
	private String fullName;
	private Integer password;
	private Integer role;

	public User() {
	}

	public User(String userId, String fullName, Integer password, Integer role) {
		this.userId = userId;
		this.fullName = fullName;
		this.password = password;
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return this.userId + " - " + this.fullName + " - " + this.role;
	}
}
