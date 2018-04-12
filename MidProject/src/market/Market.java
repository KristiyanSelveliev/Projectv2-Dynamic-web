package market;
/*
import java.awt.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import model.Admin;
import model.Customer;
import model.Order;
import model.Product;
import model.User;
import model.Product.TYPES;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

import myExceptions.InvalidFormatInput;
import myExceptions.LoginException;
import validator.Validator;

*/
public class Market {
	
	
	public static final int MIN_RATING = 1;
	public static final int MAX_RATING = 5;
	/*
	
        public static void main(String[] args) {
		
		Market market = Market.getInstance();
		
		ArrayList<Customer> customersList = new ArrayList<>();
		
		Admin admin = new Admin("Admin", "Adminov", "admincho", "admin40@@", "admin@abv.bg");
		
		 ArrayList<Product> productsList = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			Product pro = new Product("product" + (i + 1), "name" + (i + 1), Validator.randomInt(50, 3000), Market.types[Validator.randomInt(0, Market.types.length)]);
			productsList.add(pro);
		}
	
		for(int j = 0; j < 1; j++) {
			Customer customer = new Customer("User" + (j + 1), "Petrov" + (j + 1), "usercho" + (j + 1), "user40@@@", "user@abv.bg");
			customersList.add(customer);
			customer.register();
			customer.login();
			Market.users.put(customer.getUsername(), customer);
		}
		
	           Market.admins.add(admin);
		for (Product product : productsList) {
			if(!Market.products.containsKey(product.getType())) {
				Market.products.put(product.getType(), new HashMap<>());
			}
			Market.products.get(product.getType()).put(product, Validator.randomInt(20,  50));
		
		
		
		//market.getRandomAdmin();
		//market.generateAdmins(10);
	}
	}
	
	
	
	
	

	public static final int MIN_RATING = 1;
	public static final int MAX_RATING = 5;
	private static final int MAX_LOGIN_REQUEST = 5;
	private static TYPES[] types = new TYPES[]{TYPES.PC, TYPES.GSM, TYPES.TV};
	private static Market instance;
	private String name;
	private HashSet<Order> orders = new HashSet<>();
	private static HashSet<Admin> admins = new HashSet<>();
	private static HashMap<String, User> users = new HashMap<>();
	private static HashMap<Product.TYPES, HashMap<Product, Integer>> products = new HashMap<>();
	private static HashMap<Product, Integer> mostWanted = new HashMap<>();
	private static HashSet<Customer> subscribers = new HashSet<>();
	Scanner scanner = new Scanner(System.in);

	private Market() {
		this.name = "TechnoMarket";
	}

	public static Market getInstance() {
		if (instance == null) {
			instance = new Market();
			/*generateAdmins(3);//TODO ne e neobhodimo, ima static block
			generateCust(3);// -||-
			
			
			
			*/
	/*
		    products.put(TYPES.GSM, new HashMap());
			products.put(TYPES.PC, new HashMap());
			products.put(TYPES.TV, new HashMap());
			
		}
		return instance;

	}

	public void loginRequest(User user) {
		int counter = MAX_LOGIN_REQUEST;
		while (counter > 0) {
			System.out.println("Enter a username: ");
			String username = scanner.nextLine();
			counter--;
		try {
				if (Validator.validUsername(username)) {
					if (users.containsKey(username)) {
						System.out.println("Enter a password: ");
						String password = scanner.nextLine();
						if (users.get(username).getPassword().equals(password)) {
							users.get(username).setLoginStatus(true);					
							users.get(username).setLastLogin(LocalDateTime.now());
							System.out.println("Successful login");
							return;
						} 	
							throw new LoginException("Invalid password. Try again.");	
					} 
						throw new LoginException("Not existing user! ");
				}
				throw new LoginException("Invalid username. Username must be at least 4 characters, without spaces");	
				
		} catch (LoginException e) {
			System.out.println(e.getMessage());
		}
		if (counter == 0) {
			System.out.println("Srry try again later");
		  }
	 }
	}

	public void registrationRequest(User user) {

		try {
			System.out.println("Enter name and lastName: ");
			String name = scanner.nextLine();
			String lastName = scanner.nextLine();
			if (!Validator.validateString(name.toLowerCase()) | !Validator.validateString(lastName)) {
				throw new InvalidFormatInput("Invalid name");
			}
			System.out.println("Enter username:");
			String username = scanner.nextLine();
			if (Validator.validUsername(username)) {
				if (!users.containsKey(username)) {
					System.out.println("Enter email: ");
					String email = scanner.nextLine();
					if (Validator.validEMail(email)) {
						System.out.println("Enter a password - password must be... ");
						String password = scanner.nextLine();
						if (!Validator.validateString(password)) {
							throw new InvalidFormatInput(
									"Invalid password - password must contain at 8 characters, at least 1 lower case letter, at least 1 upper case letter,\\r\\n\" + \r\n"
											+ "								\"at least 1 numeric character, without spaces ");
						}
						System.out.println("Enter a passord again:");
						String password1 = scanner.nextLine();
						if (password.equals(password1)) {
							Customer customer = new Customer(name, lastName, username, password, email);
							users.put(username, customer);
							System.out.println("Successful registration");
							return;
						} 
							throw new InvalidFormatInput("Passwords not equal.");
					} 
						throw new InvalidFormatInput("Invalid email");
				} 
					throw new InvalidFormatInput("The username is already taken. Choose another username.");	
			} 
				throw new InvalidFormatInput("Enter a valid username - al least 4 characters, without spaces.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Unsuccessful registration. Do you want to try again? Y/N");
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("y")) {
				registrationRequest(user);
			}
		}

	}

	public void search(Admin admin) {
		try {
			String product = scanner.nextLine();
			if (Validator.validateString(product)) {
				throw new InvalidFormatInput("Product not found. Try again");
			}
			HashSet<Product> searchedProducts = new HashSet<>();
			for (Map.Entry<Product.TYPES, HashMap<Product, Integer>> key : products.entrySet()) {
				for (Product pr : key.getValue().keySet()) {
					if (pr.getModel().contains(product)) {
						searchedProducts.add(pr);		
					}
				}
			}
			
			System.out.println(searchedProducts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void search(Customer customer) {
		try {
			String product = scanner.nextLine();
			if (Validator.validateString(product)) {
				throw new InvalidFormatInput("Product not found. Try again");
			}
			HashSet<Product> searchedProducts = new HashSet<>();
			for (Map.Entry<Product.TYPES, HashMap<Product, Integer>> key : products.entrySet()) {
				for (Product pr : key.getValue().keySet()) {
					if (pr.getModel().contains(product)) {
						searchedProducts.add(pr);
						customer.recentlyViewedProduct.add(pr);
					}
				}
			}
			
			System.out.println(searchedProducts);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String getName() {
		return name;
	}

	

	public static Map<Product.TYPES, HashMap<Product, Integer>> getProducts() {
		return Collections.unmodifiableMap(products);
	}

	public boolean checkQuantity(Product product, int quantity) {
		if (this.products.containsKey(product.getType())) {

			for (Map.Entry<Product, Integer> entry : products.get(product.getType()).entrySet()) {
				if (entry.getKey().equals(product) && entry.getValue() >= quantity) {
					return true;
				}
			}

		}
		return false;
	}

	public void removeProducts(HashMap<Product, Integer> products) {
		for (Product product : products.keySet()) {
			this.products.get(product.getType()).put(product,
					this.products.get(product.getType()).get(product) - products.get(product));			
			if (!mostWanted.containsKey(product)) {
				mostWanted.put(product, products.get(product));
			} else {
				mostWanted.put(product, products.get(product) + mostWanted.get(product));
			}
		}

	}

	public void printMostWanted() {
		ArrayList<Map.Entry<Product, Integer>> list = new ArrayList<>();
		list.addAll(mostWanted.entrySet());
		list.sort((new Comparator<Entry<Product, Integer>>() {

			@Override
			public int compare(Entry<Product, Integer> o1, Entry<Product, Integer> o2) {				
				return o2.getValue() - o1.getValue();
			}
		}));

		System.out.println(list.toString());
	}

	public boolean validateProductType(TYPES type) {
		for (TYPES s : Market.types) {
			if (type.equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	public void logout(User user) {
		try {
		if(user.isLoginStatus()) {
			user.setLoginStatus(false);
			System.out.println("Successful logout");
		}
		else {
			throw new LoginException("Invalid operation");
		}
		}
		catch (LoginException e) {
			e.getMessage();
		} 
	}
	
	
	public static void generateAdmins(int a) {
		for (int i = 0; i < a; i++) {
			admins.add(new Admin("Admin" + (i + 1), "Adminov", "admin" + (i + 1), "admin40@1A" + (i + 1), "admin@abv.bg"));
		}
		
	}
	
	public static void generateCust(int a) {
		for (int i = 0; i < a; i++) {
			users.put("peshko" + (i + 1),new Customer("Customer" + (i + 1), "Cus", "peshko" + (i + 1), "admin40@1A" + (i + 1), "admin@abv.bg"));
		}		
	}
	
	/*public static Admin getRandomAdmin() {
		ArrayList<Admin> xAdmins = new ArrayList<>();
		xAdmins.addAll(admins);
		return	xAdmins.get(new Random().nextInt(xAdmins.size()));
	}
	
	public static Customer getRandomCust() {
		ArrayList<Customer> custList = new ArrayList<>();
		List xList = (List) users.values();
		
		custList.addAll((Collection<? extends Customer>) xList);
		return	custList.get(new Random().nextInt(custList.size()));
		
	}
	
	*/

	/*
	
	
	
	public static HashSet<Admin> getAdmins() {
		return (HashSet<Admin>) Collections.unmodifiableSet(admins);
	}

	
	public static HashMap<String, User> getUsers() {
		return (HashMap<String, User>) Collections.unmodifiableMap(users);
	}

	public void subscribeUser(Customer customer) {
		Market.subscribers.add(customer);
		
	}

	public void unsubscribe(Customer customer) {
		Market.subscribers.remove(customer);
	}
	
	public void printRecentlyViewedProduct(Customer customer) {
		customer.recentlyViewedProduct.stream()
		.limit(5)
		.forEach(x -> System.out.println(x.getModel() + " " + x.getPrice()));
	}
	
	
	
	
	
	*/
	

	
	
	

}
