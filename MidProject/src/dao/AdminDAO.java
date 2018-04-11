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
		try(PreparedStatement ps=connection.prepareStatement("DELETE FROM products WHERE model=? AND description=? ");){
			ps.setString(1, product.getModel());
			ps.setString(2, product.getDescription());
			ps.executeUpdate();
		}
		
	}

	@Override
	public void updateProductAdmin(Product product) throws Exception {
		try(PreparedStatement ps=connection.prepareStatement("UPDATE products SET description=? where model=?");){
			ps.setString(1, product.getDescription());
			ps.setString(2, product.getModel());
			ps.executeUpdate();
		}
		
	}

	@Override
	public void createProductAdmin(Product product,int quantity) throws Exception {
		 String sql = "INSERT INTO products (model, description, price, type, quantity) VALUES (?,?,?,?,?)";
		  try (PreparedStatement pStatement = connection.prepareStatement(sql);){
		   pStatement.setString(1, product.getModel());
		   pStatement.setString(2, product.getDescription());
		   pStatement.setDouble(3, product.getPrice());
		   pStatement.setString(4, product.getType().toString());
		   pStatement.setInt(5, quantity);
		   pStatement.executeUpdate();
		  }
	}
	
	
	

}
