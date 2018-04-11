package controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.Order;
import model.Product;

public class OrderManager {
	
private static OrderManager instance;

	
	public synchronized static OrderManager getInstance() {
		
		if(instance == null) {
			instance = new OrderManager();
		}
		return instance;
			
	}
	
	private OrderManager() {
		
	}
	
	public void removeProductsFromCart(Order order) {
		order.setListProduct((HashMap<Product, Integer>) Collections.EMPTY_MAP);
	}
	

}
