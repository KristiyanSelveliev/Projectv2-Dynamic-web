package controller;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

import dao.OrderDAO;
import dao.ProductDAO;
import dao.UserDAO;
import market.Market;
import model.Customer;
import model.Order;
import model.Product;
import model.User;
import model.UserPojo;
import myExceptions.InvalidFormatInput;
import myExceptions.LoginException;
import validator.Validator;

public class UserManager {
	
	private static UserManager instance;
	private static HashMap<String, User> users = new HashMap<>();
	ProductManager productManager= ProductManager.getInstance();

	
	public synchronized static UserManager getInstance() {
		
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
			
	}
	
	private UserManager() {
		
	}
	
	public void register(String name, String lastName, String username, String password, String email) {
		//Customer customer = new Customer(name, lastName, username, password, email);
		UserPojo user=new UserPojo(name,lastName,username,password,email);
		try {			
			
			if(Validator.validUsername(username) && Validator.validateString(name)&& Validator.validPassword(password) &&
					Validator.validateString(lastName) && Validator.validEMail(email)) {
				if(!UserDAO.getInstance().checkUsernameAndPass(username, password)) {
					UserDAO.getInstance().addUser(user);
					return;
				}
				throw new InvalidFormatInput("The username is already taken. Choose another username.");				
			}
			else {
				if(!Validator.validateString(name) && !Validator.validateString(lastName)) {
					throw new InvalidFormatInput("Invalid name format.");
				}
				else if(!Validator.validUsername(username)) {
					throw new InvalidFormatInput("Enter a valid username - al least 4 characters, without spaces.");
				}
				else if(!Validator.validEMail(email)) {
					throw new InvalidFormatInput("Invalid email format.");
				}
				else if(!Validator.validPassword(password)) {
					throw new InvalidFormatInput("Invalid password - password must contain at 8 characters, at least 1 lower case letter,"
							+ " at least 1 upper case letter at least 1 numeric character, without spaces ");
				}				
			}
			
		}catch (Exception e) {
			System.out.println("Sorry! Unsuccessful registration." + e.getMessage());
		}
	}
	
	public void login(String username, String password) {
		// TODO add max_login_request
		try {
			if(Validator.validUsername(username) && Validator.validPassword(password)) {
				if(UserDAO.getInstance().checkUsernameAndPass(username, password)) {
					users.get(username).setLoginStatus(true);					
					users.get(username).setLastLogin(LocalDateTime.now());
					System.out.println("Successful login");
					UserDAO.getInstance().login(username, password);
					return;
				}
				throw new InvalidFormatInput("Not existing user!");
			}
			throw new InvalidFormatInput("Invalid username or password!");			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void logout(String username, String password)  {
		try {
			UserDAO.getInstance().logout(username, password);
		} catch (SQLException e) {
			System.out.println("Invalid operation" + e.getMessage());
		}	
		
	}
	
	public void changePassword(String username, String password) {
		try {
			if (UserDAO.getInstance().checkUsernameAndPass(username, password)) {
			try {
				UserDAO.getInstance().changePassword(username, password);
				return;
			}catch (SQLException e) {
				System.out.println("Invalid operation" + e.getMessage());
			}
			}
			throw new LoginException("Not existing username or password");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void search(String product) {
		HashSet<String> products;
		try {
			products = UserDAO.getInstance().searchProduct(product);
			
		
		}catch (SQLException e) {
			System.out.println("This product is not found");
		}
		//TODO da se opravi
		
		
		
	}
	
	public void removeProduct(UserPojo user, Product product) {
		if(user.getCart().containsKey(product)) {
			user.getCart().remove(product);
		}
	}
	
	public void addProduct(UserPojo user, Product product, int quantity) {
		if(quantity > 0 && product != null) {
			user.getCart().put(product, user.getCart().get(product) + quantity);
		}
	}
	
	public void rateProduct(UserPojo user, Product product, int rating) {
		if(user.isLoginStatus()) {
			try {
				ProductDAO.getInstance().changeRating(product, rating);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}    
			
		}
		else {
			System.out.println("please log in");
		}
			
		
	}
	
	public void addToFavorites(UserPojo user, Product product) {
		if(user.isLoginStatus()) {
			try {
				UserDAO.getInstance().addToFavorite(user, product);
			} catch (SQLException e) {
				System.out.println("Invalid operation");
			}			
		}
		else {
			System.out.println("Please log in");
		}
			
	}
	
	void finishOrder(UserPojo user) {
		if(user.isLoginStatus()) {
			Order order = new Order(user, user.getCart());
			try {
				OrderDAO.getInstance().addOrder(order);
				OrderManager.getInstance().removeProductsFromCart(order);
			} catch (SQLException e) {
				System.out.println("Something went wrong. Please try again " + e.getMessage());
			}
			
			
		}
	}
	
	

}
