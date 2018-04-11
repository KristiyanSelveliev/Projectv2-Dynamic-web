package controller;

import dao.AdminDAO;
import model.Product;
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
	
	
	

}
