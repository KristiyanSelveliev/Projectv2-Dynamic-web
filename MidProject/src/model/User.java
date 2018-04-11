package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import market.Market;
import myExceptions.InvalidFormatInput;
import myExceptions.LoginException;
import validator.Validator;

 public abstract class User {
	
	
	private String name;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phone;
	private LocalDateTime lastLogin;
	protected LocalDate registrationDate;	

	private Market market = Market.getInstance();
	private boolean loginStatus;
	
	public User(String name, String lastName, String username, String password, String email) {
		this.setName(name);
		this.setLastName(lastName);
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	

	
	public void login() {
		market.loginRequest(this);
		
	}
	public void logout() {
		market.logout(this);
	}

	
	public void changePassword(String password) {
		try {
			if(Validator.validPassword(password)) {
				this.password = password;
			}
			else {
				throw new InvalidFormatInput("Invalid password - password must contain at 8 characters, at least 1 lower case letter, at least 1 upper case letter,\\\\r\\\\n\\\" + \\r\\n\"\r\n" + 
						"											+ \"								\\\"at least 1 numeric character, without spaces ");
			}
		}catch (InvalidFormatInput e) {
			System.out.println(e.getMessage());
		}
	}
	
	abstract public void addProduct(Product product, int quantity);
	abstract public void removeProduct(Product product);
	abstract public boolean isAdmin();
	
	
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public Market getMarket() {
		return market;
	}


	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
	
	
	

	
	
	
	
	

}
