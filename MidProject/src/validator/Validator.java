package validator;

import java.util.Random;
import java.util.Scanner;

public class Validator {
	
	private static final String VALIDATE_EMAIL = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}".toLowerCase();


	//username must be at least 4 characters, without spaces
	private static final String VALIDATE_USERNAME = "(\\S){4,}";

	//password must contain at 8 characters, at least 1 lower case letter, at least 1 upper case letter,
	//at least 1 numeric character, without spaces 
	private static final String VALIDATE_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\S)[^\\s]{8,}$";
	
	private static final String VALIDATE_PHONE = "[0-9]{10}";
	
	
	
	public static boolean validateString(String text) {
		return text != null && !text.isEmpty();
		
	}
	
	public static boolean validUsername(String username) {
		return validateString(username) && username.matches(VALIDATE_USERNAME);		
	}
	
	public static boolean validPassword(String password) {
		return validateString(password) && password.matches(VALIDATE_PASSWORD);

	}
	
	public static boolean validEMail(String email) {
		return validateString(email) && email.matches(VALIDATE_EMAIL);
	}

	public static boolean validPhone(String phone) {	
		return validateString(phone) && phone.matches(VALIDATE_PHONE);
	}
	
	public static boolean checkForPositiveNum(int a) {
		if(a < 0) {
			System.out.println("Please enter correct number");
			return false;
		}
		return a > 0;
	}
	
	public static boolean checkForPositiveNum(double a) {
		if(a < 0) {
			System.out.println("Please enter correct number");
			return false;
		}
		return a > 0;
	}
	
	public static int randomInt(int a, int b) {
		return new Random().nextInt(b - a) + a;
	}
	
	
	
	}



