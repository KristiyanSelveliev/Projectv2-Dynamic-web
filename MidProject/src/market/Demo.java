package market;

import java.sql.Connection;
import java.util.HashSet;

import controller.AdminManager;
import controller.DBManager;
import controller.ProductManager;
import controller.UserManager;
import model.Product;
import model.Product.TYPES;
import validator.Validator;

public class Demo {
	
	public static void main(String[] args) {
		Connection c=DBManager.getInstance().getConnection();
		
		
		//UserManager.getInstance().register("Pesho3","Gosho2","Peshko14","Pesheca001", "pesho3@abv.bg");
		UserManager.getInstance().login("Peshko14","Habalqhubalq1");
		/*Product p=new Product("tvmaika","mnogo qk",200,Product.TYPES.GSM);
		Product p2=new Product("Samsung","mega moshten",1000,Product.TYPES.TV);*/
		Product p3=new Product("Samsung2","mega moshteeen",1000,Product.TYPES.TV);
		//ProductManager.getInstance().addRating(p3, 4);
		AdminManager.getInstance().updateProduct(p3, "mega zle");
		/*try {
			AdminDAO.getInstance().addProduct(p2, 5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//AdminManager.getInstance().addProduct(p3, 20);
		
		//UserPojo up=new UserPojo("Peshko24","pesho","Peshko14","Pesheca001", "nqma znachenie");
		//UserManager.getInstance().changePassword(up, "Habalqhubalq1");
		//UserManager.getInstance().register("Peshko", lastName, username, password, email)
		
		//UserManager.getInstance().addToFavorites(up, p3);
		
		/*try {
			UserDAO.getInstance().checkUsernameAndPass("Goshko", "Peshko");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//UserManager.getInstance().login("Peshko1", "Pesheca00");
		
	
	}
		
	

}
