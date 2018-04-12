package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import controller.DBManager;
import model.Order;
import model.Product;

import model.UserPojo;

public class OrderDAO {
	
private static OrderDAO instance;
private static Connection connection;
	
	private OrderDAO() {
		connection= DBManager.getInstance().getConnection();	
	}
	
	public static  synchronized OrderDAO getInstance() {
		
		if(instance == null) {
			instance = new OrderDAO();
		}
		return instance;	
	}
	
	/*public int returnIdDB(Order order) {
		String sql = "SELECT order_id FROM orders WHERE id_number = "+order.getId()+"";
		int id = 0;
		try (PreparedStatement pStatement = connection.prepareStatement(sql);){
			ResultSet resultSet = pStatement.executeQuery();
			id = resultSet.getInt("user_id");
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id;
		
	}*/
	
	/*public void addOrder(Order order) throws SQLException{
		
		String sql = "INSERT INTO orders(id_user, id_number, total_cost, date) VALUES (?,?,?)";
		//PreparedStatement pStatement = null;
		try(PreparedStatement pStatement = connection.prepareStatement(sql);){
			pStatement.setInt(1, order.getUser().getId());
			pStatement.setInt(2, order.getId());
			pStatement.setDouble(3, order.getTotalSum());
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
	        pStatement.setTimestamp(4, date);
	        pStatement.executeUpdate();
		}
		sql = "INSERT INTO order_has_products (id_order, id_product, product_quantity) VALUES (?,?,?)";
		try(PreparedStatement pStatement = connection.prepareStatement(sql);){
			
			for (Map.Entry<Product, Integer> e: order.getListProduct().entrySet()) {
				pStatement.setInt(1, OrderDAO.getInstance().returnIdDB(order));
				pStatement.setInt(2, ProductDAO.getInstance().returnIdDB(e.getKey()));
				pStatement.setInt(3, e.getValue());	
				pStatement.executeUpdate();
			}	
			
			
		}
		
		
        
       //TODO da se dobavi v order_has_products
		
		
		
	}*/

}
