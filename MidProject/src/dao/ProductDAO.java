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
		try (PreparedStatement pStatement = connection.prepareStatement("UPDATE products SET rating = ? WHERE model = ? ");){
			pStatement.setDouble(1, rating);
			pStatement.setString(2, product.getModel());
			pStatement.executeUpdate();
		}
	}
	
	public int returnIdDB(Product product) {
		int id = 0;
		try (PreparedStatement pStatement = connection.prepareStatement("SELECT id_product FROM products WHERE model = ? ");){
			pStatement.setString(1, product.getModel());
			ResultSet resultSet = pStatement.executeQuery();
			resultSet.next();
			id = resultSet.getInt("id_product");
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id;
	}
	
    

}
