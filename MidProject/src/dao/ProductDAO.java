package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		String sql = "SELECT id_product FROM products WHERE model = "+product.getModel()+"";
		int id = 0;
		try (PreparedStatement pStatement = connection.prepareStatement(sql);){
			ResultSet resultSet = pStatement.executeQuery();
			id = resultSet.getInt("id_product");
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
	
    

}
