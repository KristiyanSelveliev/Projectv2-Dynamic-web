package dao;



import java.util.HashSet;
import java.util.TreeSet;

import model.Product;
import model.UserPojo;

public interface IUserDAO {
	
	
	 void addUser(UserPojo user) throws Exception;
	 
	 boolean checkUsernameAndPass(String username,String password) throws Exception;
	
	 void deleteUser(String username);
	
	
	 void changePassword(String username, String password) throws Exception;
	
	// void addProductCustomer(Product product, int quantity) throws Exception;
	
	// TreeSet<Product> searchProduct(String product) throws Exception;
	
	// void removeProductCustomer(Product product) throws Exception;
	
	// void rateProduct(Product product, int rating) throws Exception;
	
	 void addToFavorite(UserPojo user, Product product) throws Exception;
	
     //void finishOrder() throws Exception;
	
	 boolean isAdmin(String username);
	
	
	
	

}
