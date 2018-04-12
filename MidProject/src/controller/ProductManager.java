package controller;

import java.util.HashMap;

import dao.ProductDAO;
import market.Market;
import model.Product;

import myExceptions.InvalidFormatInput;

public class ProductManager {
	
	private static ProductManager instance;

	
	public synchronized static ProductManager getInstance() {
		
		if(instance == null) {
			instance = new ProductManager();
		}
		return instance;
			
	}
	
	private ProductManager() {
		
	}
	
	public void addRating(Product product, int rating) {
		if(rating >= Market.MIN_RATING && rating <= Market.MAX_RATING) {
			product.getRatings().add(rating);
			double sum = 0;
			double rate = 0;
			for (Integer r : product.getRatings()) {
				sum += r.intValue();
			}
			rate =  sum/product.getRatings().size();
			try {
				ProductDAO.getInstance().changeRating(product, rate);
				return;

			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		try {
			throw new InvalidFormatInput();
		} catch (InvalidFormatInput e) {
			System.out.println("Unsuccessful operation");
		}
	}
	

}
