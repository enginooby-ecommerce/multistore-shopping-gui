package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import asystem.Database;

public class Account {
	private String username;
	private String password;

	public Account() {
	};

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
