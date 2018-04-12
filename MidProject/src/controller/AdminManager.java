package controller;

import dao.AdminDAO;

import model.Product;
import myExceptions.InvalidFormatInput;
import validator.Validator;

public class AdminManager {
	
    private static int MAX_QUANTITY=50;
	private static AdminManager instance;

	public synchronized static AdminManager getInstance() {

		if (instance == null) {
			instance = new AdminManager();
		}
		return instance;
		
		

	}

	private AdminManager() {

	}

	public boolean addProduct(Product product, int quantity) {
		if (quantity>0  && quantity<MAX_QUANTITY ) {
            try {
				AdminDAO.getInstance().addProduct(product, quantity);
			    return true;
			} catch (Exception e) {
			    System.out.println("Srry product cant be added da se addva");
				e.printStackTrace();
			}
		}
           return false; 
	}
	
	
	
	
	public boolean updateProduct(Product product,String description) {
		if(product == null || !Validator.validateString(description)) {
			System.out.println("Sorry cant update. Invalid data");
			return false;
		}
		product.setDescription(description);
		try {
			AdminDAO.getInstance().updateProductAdmin(product);
			return true;
		} catch (Exception e) {
			System.out.println("Sorry cant update");
			e.printStackTrace();
		}
		return false;
	}
	
	public  boolean createProduct(Product product,int quantity) {
		try {
			if(Validator.validateString(product.getModel()) && Validator.validateString(product.getDescription()) &&
					Validator.checkForPositiveNum(product.getPrice()) && Validator.checkForPositiveNum(quantity)) {
				AdminDAO.getInstance().createProductAdmin(product, quantity);
				return true;				
			}
			throw new InvalidFormatInput("Invalid data format.");
		}catch(Exception e) {
			System.out.println("Sorry cant create" + e.getMessage());
			
		}
		return false;
	}
	
	public boolean deleteProduct(Product product) {
		if(product == null) {
			System.out.println("invalid data");
			return false;
		}
		try {
			AdminDAO.getInstance().removeProduct(product);
			return true;
		}catch(Exception e) {
			System.out.println("Srry cant delete");
		}
		return false;
		
	}
	
	
	
	

}
