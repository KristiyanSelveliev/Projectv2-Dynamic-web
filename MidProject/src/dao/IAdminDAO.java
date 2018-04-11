package dao;

import model.Product;

public interface IAdminDAO {
	
	 void addProduct(Product product, int quantity) throws Exception;
	 
	 void removeProduct(Product product) throws Exception;
	 
	 void updateProductAdmin(Product product) throws Exception;
	 
	 void createProductAdmin(Product product,int quantity) throws Exception;
	 

}
