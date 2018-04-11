package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import controller.DBManager;
import model.Customer;
import model.Product;
import model.UserPojo;
import validator.Validator;

public class UserDAO implements IUserDAO {

	private static UserDAO instance;
	private static Connection connection;

	public static synchronized UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	private UserDAO() {
		connection = DBManager.getInstance().getConnection();
	}
	
	
	
	@Override
	public void addUser(UserPojo user) throws SQLException  {
		try (PreparedStatement pStatement = connection.prepareStatement(
				"INSERT INTO users (name, lastName, username, password, email, loginStatus) VALUES (?,?,?,?,?,?)");){
			pStatement.setString(1, user.getName());
			pStatement.setString(2, user.getLastName());
			pStatement.setString(3, user.getUsername());
			pStatement.setString(4, user.getPassword());
			pStatement.setString(5, user.getEmail());
			pStatement.setBoolean(6, true);
			pStatement.executeUpdate();
			
		}
		
		
		
	}
	@Override
	public boolean checkUsernameAndPass(String username, String password) throws SQLException { 
		try(PreparedStatement pStatement = connection
				.prepareStatement("SELECT COUNT(*) FROM users WHERE username = ? AND password = ?");) {
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			try (ResultSet resultSet = pStatement.executeQuery();){
				if (resultSet.getInt(1) == 1) {
					return true;
				
			    }
			
			
		}	
		
			
		}
		return false;
	}
	
	@Override
	public void deleteUser(String username) {
		
		PreparedStatement ps = null;
		try {
			ps=connection.prepareStatement("DELETE FROM users WHERE username=?");
			ps.setString(1, username);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	
	@Override
	public boolean login(String username, String password) throws SQLException {
		if (this.checkUsernameAndPass(username, password)) {
			try(PreparedStatement pStatement = connection
					.prepareStatement("UPDATE users SET loginStatus = " + 1 + " WHERE username = " + username + " ");){
				pStatement.executeUpdate();
				
				return true;
				
			}
			
			
		}
		return false;

	}

	@Override
	public void logout(String username, String password) throws SQLException {
		try (PreparedStatement pStatement = connection
				.prepareStatement("UPDATE users SET loginStatus = " + 0 + " WHERE username = " + username + " ");){
			pStatement.executeUpdate();
			
		}
	
	}

	@Override
	public void changePassword(String username, String password) throws SQLException {
		if (this.checkUsernameAndPass(username, password)) {
			try (PreparedStatement pStatement = connection
					.prepareStatement("UPDATE users SET password = ? WHERE username = ? ");){
				pStatement.setString(1, password);
				pStatement.setString(2, username);
				pStatement.executeUpdate();
				
			}		
			
			
		}

	}

	

	@Override
	public void addProductCustomer(Product product, int quantity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public HashSet<String> searchProduct(String product) throws SQLException {
		HashSet<String> products = new HashSet<>();

		try (PreparedStatement pStatement = connection
				.prepareStatement("SELECT model FROM products WHERE model LIKE \'%"+product+"%\'");){
			try (ResultSet resultSet = pStatement.executeQuery();){
				while(resultSet.next()) {
					products.add(resultSet.getString("model"));
				}
				
			}
			
		}
	
		return products;
		
		

	}

	

	@Override
	public void removeProductCustomer(Product product) throws Exception {
		// TODO Auto-generated method stub

	}

	

	@Override
	public void rateProduct(Product product, int rating) throws SQLException {
		
		//TODO da se mahne ot tuk

	}

	@Override
	public void addToFavorite(UserPojo user, Product product) throws SQLException {
		String sql = "INSERT INTO user_has_favorites (user_id, product_id) VALUES (?,?);";
		PreparedStatement s = connection.prepareStatement(sql);
		//TODO kak da vzema user_id
		s.setInt(2, product.getId());
		s.executeUpdate();
		s.close();

	}
	
	public int returnId(UserPojo user) {
		String sql = "SELECT user_id FROM users WHERE username = "+user.getUsername()+"";
		int id = 0;
		try (PreparedStatement pStatement = connection.prepareStatement(sql);){
			ResultSet resultSet = pStatement.executeQuery();
			id = resultSet.getInt("user_id");
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id;
		
	}
	
	

	@Override
	public void finishOrder() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAdmin(String username) {
		// TODO Auto-generated method stub
		// return false;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT isAdmin FROM users WHERE username=? ");

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int isAdmin = rs.getInt("isAdmin");
			if (isAdmin == 1) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}
