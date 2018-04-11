package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import controller.DBManager;
import model.Product;

public class AdminDAO implements IAdminDAO{
	
	
	
	private static AdminDAO instance;
	private static Connection connection;

	public static synchronized AdminDAO getInstance() {
		if (instance == null) {
			instance = new AdminDAO();
		}
		return instance;
	}

	private AdminDAO() {
		connection = DBManager.getInstance().getConnection();
	}

	@Override
	public void addProduct(Product product, int quantity) throws Exception {
		PreparedStatement ps=connection.prepareStatement("INSERT INTO products (price,model,description,type,quantity) VALUES (?,?,?,?,?)");
		ps.setDouble(1, product.getPrice());
		ps.setString(2, product.getModel());
		ps.setString(3, product.getDescription());
		ps.setString(4, product.getType().toString());
		ps.setInt(5, quantity);
		ps.executeUpdate();
		
	}

	@Override
	public void removeProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductAdmin(Product product) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createProductAdmin(Product product) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
