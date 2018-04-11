package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.DBManager;
import market.Market;
import model.Product;

public class ProductDAO {
	
private static ProductDAO instance;
private static Connection connection;
	
	private ProductDAO() {
		connection = DBManager.getInstance().getConnection();	
	}
	
	public static  synchronized ProductDAO getInstance() {
		
		if(instance == null) {
			instance = new ProductDAO();
		}
		return instance;	
	}

	public void changeRating(Product product, double rating) throws SQLException{
		try (PreparedStatement pStatement = connection.prepareStatement("UPDATE products SET rating = ? WHERE product_model = "+product.getModel()+" ");){
			pStatement.setDouble(1, rating);
			pStatement.executeUpdate();
		}catch (Exception e) {
			System.out.println("Invalid operation");
		}		
	}
	
	public int returnIdDB(Product product) {
		//TODO must fill
		return 0;
	}
	
    

}
