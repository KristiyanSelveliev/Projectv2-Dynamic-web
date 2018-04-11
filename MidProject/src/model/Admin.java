package model;



import java.util.HashMap;

import javax.naming.directory.SearchControls;

import myExceptions.InvalidFormatInput;
import validator.Validator;

public class Admin extends User{
	
	

	public Admin(String name, String lastName, String username, String password, String email) {
		super(name, lastName, username, password, email);
	
	}



	@Override	
	public void login() {		
		super.login();		
	}

	public void search() {
		getMarket().search(this);
	}


	@Override
	public void addProduct(Product product, int quantity) {
		if(Validator.checkForPositiveNum(quantity)) {
			if(getMarket().getProducts().get(product.getType()).containsKey(product)) {
				getMarket().getProducts().get(product.getType())
				.put(product, getMarket().getProducts().get(product.getType()).get(product) + quantity);
			
			}
		}
		
		
	}
	
	public void editProductQuantity(Product product, int quantity) {	
		if(Validator.checkForPositiveNum(quantity)) {
			if(getMarket().getProducts().get(product.getType()).containsKey(product)) {
				getMarket().getProducts().get(product.getType())
				.put(product, quantity);
			
			}
		}
		
	}
	
	public void editProductPrice(Product product, double price) {
		product.setPrice(price);
	}
	
	public void editProductDescription(Product product, String description) {
		product.setDescription(description);
	}
	
	
	
	public void createProduct(String model, String description, double price, Product.TYPES type, int quantity) {
		try {
		if(Validator.validateString(model) && Validator.validateString(description) &&
				Validator.checkForPositiveNum(price) && Validator.checkForPositiveNum(quantity)) {
			if(this.getMarket().getProducts().containsKey(type)) {
				Product product = new Product(model, description, price, type);
				getMarket().getProducts().get(product.getType()).put(product, quantity);
			}
			else {
				Product product = new Product(model, description, price, type);
				getMarket().getProducts().get(product.getType()).put(product, quantity);				
			}			
		}
		else {
			String cause;
			if(!Validator.validateString(model) | !Validator.validateString(description)) {
				cause = "Please enter a valid string";
			}
			else {
				cause = "Please enter a positive number";
			}
			throw new InvalidFormatInput(cause);
		}
		}catch(InvalidFormatInput e) {
			e.getMessage();
		}
		
		
		
	}
	
	public void createProduct(Product product, int quantity) {
		if(getMarket().getProducts().containsKey(product.getType())) {
			getMarket().getProducts().get(product.getType()).put(product, quantity);
		}
		else {
			getMarket().getProducts().put(product.getType(), new HashMap<>());
			getMarket().getProducts().get(product.getType()).put(product, quantity);
		}
		
		
	}


	@Override
	public void removeProduct(Product product) {
		if(getMarket().getProducts().get(product.getType()).containsKey(product)) {
			getMarket().getProducts().get(product.getType()).remove(product);
		}
		
	}

	@Override
	public void logout() {
		super.logout();
		
	}



	@Override
	public boolean isAdmin() {
		return true;
	}
	
	

}
