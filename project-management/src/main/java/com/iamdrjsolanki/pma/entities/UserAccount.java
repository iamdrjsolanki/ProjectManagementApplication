package com.iamdrjsolanki.pma.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user_accounts")
public class UserAccount {
	
	@Id
	@SequenceGenerator(name="user_accounts_seq", sequenceName="user_accounts_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq")
	private long userId;
	@Column(name = "username")
	private String userName;
	private String email;
	private String password;
	public String role;
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	private boolean enabled;
	
	public UserAccount() {
	}
	
	public UserAccount(String userName, String email, String password, String role, boolean enabled) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
