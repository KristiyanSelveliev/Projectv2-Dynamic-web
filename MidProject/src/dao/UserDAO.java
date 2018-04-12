package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.TreeSet;

import controller.DBManager;
import model.Product;
import model.UserPojo;


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
				"INSERT INTO users (name, last_name, username, password, email,isAdmin,registrationDate) VALUES (?,?,?,?,?,?,?)");){
			pStatement.setString(1, user.getName());
			pStatement.setString(2, user.getLastName());
			pStatement.setString(3, user.getUsername());
			pStatement.setString(4, user.getPassword());
			pStatement.setString(5, user.getEmail());
			pStatement.setBoolean(6,false);
			pStatement.setDate(7, Date.valueOf(LocalDate.now()));
			pStatement.executeUpdate();
			
		}
		
		
		
		
	}
	@Override
	public boolean checkUsernameAndPass(String username, String password) throws SQLException { 
		try(PreparedStatement pStatement = connection
				.prepareStatement("SELECT username FROM users WHERE username = ? AND password = ?");) {
			pStatement.setString(1, username);
			pStatement.setString(2, password);
			try (ResultSet resultSet = pStatement.executeQuery();){
				int count=0;
				
				while (resultSet.next()) {
					System.out.println(resultSet.getString("username"));
					count++;
			    }	
				if(count>0) {
					return false;
				}
			
		     }		
		}
		return true;	
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
	public void changePassword(String username, String password) throws SQLException {		
		try (PreparedStatement pStatement = connection
					.prepareStatement("UPDATE users SET password = ? WHERE username = ? ");){
				pStatement.setString(1, password);
				pStatement.setString(2, username);
				pStatement.executeUpdate();
				
		}	
	}

	

	/*@Override
	public void addProductCustomer(Product product, int quantity) throws Exception {
		// TODO shte se mahne

	}
/*
	@Override
	public TreeSet<Product> searchProduct(String product) throws SQLException {
		TreeSet<Product> products = new TreeSet<>((Product p1, Product p2) -> p1.getModel().compareTo(p2.getModel()));

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

	*/

	/*@Override
	public void removeProductCustomer(Product product) throws Exception {
		// TODO shte se mahne

	}

	

	@Override
	public void rateProduct(Product product, int rating) throws SQLException {
		
		//TODO da se mahne ot tuk

	}*/

	@Override
	public void addToFavorite(UserPojo user, Product product) throws SQLException {
		String sql = "INSERT INTO user_has_favorites (user_id, product_id) VALUES (?,?);";
		try (PreparedStatement s = connection.prepareStatement(sql);){
			s.setInt(1, UserDAO.getInstance().returnId(user));
			s.setInt(2, ProductDAO.getInstance().returnIdDB(product));
			s.executeUpdate();
		}

	}
	
	public int returnId(UserPojo user) {
		
          int id = 0;
		
		try (PreparedStatement ps=connection.prepareStatement("SELECT id_user FROM users WHERE username = ?");)
				{
			ps.setString(1, user.getUsername());
			ResultSet rs=ps.executeQuery();
			rs.next();
			id=rs.getInt("id_user");
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println(id);
		return id;
		
	}
	
	

	/*@Override
	public void finishOrder() throws Exception {
		// TODO tozi metod trqbva da se mahne ? 

	}*/

	@Override
	public boolean isAdmin(String username) {
	
		// return false;
	
		try ( PreparedStatement ps = connection.prepareStatement("SELECT isAdmin FROM users WHERE username=? ");)
		{
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int isAdmin = rs.getInt("isAdmin");
		if (isAdmin == 1) {
			return true;
		}

	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;

}
		
		
		

	}


