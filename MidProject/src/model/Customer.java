package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import myExceptions.LoginException;

import java.util.HashMap;

public class Customer extends User{
	
	
		
	private HashMap<Product, Integer> cart = new HashMap<>();
	private HashSet<Product> favoritesProduct = new HashSet<>();
	private HashSet<Order> orders = new HashSet<>();
	public HashSet<Product> recentlyViewedProduct = new HashSet<>();
	Scanner scanner = new Scanner(System.in);
	
	public Customer(String name, String lastName, String username, String password, String email) {
		super(name, lastName, username, password, email);
		this.registrationDate = LocalDate.now();
	}
	

	@Override
	public void login() {
		super.login();
			
	}

	
	public void register() {
		this.getMarket().registrationRequest(this);
		
	}

	@Override
	public void addProduct(Product product, int quantity) {
		if(getMarket().checkQuantity(product, quantity)) {
			if(!cart.containsKey(product.getModel())) {
				cart.put(product, quantity);
			}
			else {
				cart.put(product, cart.get(product) + quantity);
			}	
		}
		else {
			System.out.println("Not enough quantity of this product!");
		}
			
		
	}	
	
	public void addToFavorites(Product product) {
		if(this.isLoginStatus()) {
			if(!favoritesProduct.contains(product)) {
				favoritesProduct.add(product);
			}
			else {
				System.out.println("This product is already in your favorite list");
			}
		}
		else {
			System.out.println("Please login");
		}
		
	}
	
	public void finishOrder() {
		try {
			if(this.isLoginStatus()) {
				Order order = null;
				orders.add(order);
				getMarket().removeProducts(this.cart);
				this.cart.clear();
				System.out.println("Please enter delivery address: ");
				this.setAddress(scanner.nextLine());
				System.out.println("Your order is finished");
				
			}
			else {
				throw new LoginException("Please log in");
			}			
		}
		catch (LoginException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
	public void rate(Product product, int rating) {
		if(this.isLoginStatus()) {
			if(this.getMarket().getProducts().containsKey(product.getType()) && 
					this.getMarket().getProducts().get(product.getType()).containsKey(product)) {
				for (Product p : this.getMarket().getProducts().get(product.getType()).keySet()) {

					if(p.equals(product)) {
						p.addRating(rating);
						
					}
				}			
			}			
		}
		else {
			System.out.println("Can't rate! Please log in");
		}
		
	}

	@Override
	public void removeProduct(Product product) {
		if(this.cart.containsKey(product)) {
			this.cart.remove(product);
		}
		
	}

	
	public void subscribe() {
		getMarket().subscribeUser(this);
	}
	
	public void unsubscribe() {
		getMarket().unsubscribe(this);
	}


	@Override
	public boolean isAdmin() {
		return false;
	}
	
	public void search() {
		getMarket().search(this);
	}


	public HashSet<Product> getRecentlyViewedProduct() {
		return recentlyViewedProduct;
	}


	public void setRecentlyViewedProduct(HashSet<Product> recentlyViewedProduct) {
		this.recentlyViewedProduct = (HashSet<Product>) Collections.unmodifiableSet(recentlyViewedProduct);
	}
	
	
	
	
	
	

}
