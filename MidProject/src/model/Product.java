package model;

import java.util.ArrayList;

import market.Market;
import validator.Validator;

public class Product {
	
	public enum TYPES {
		PC, GSM, TV
	}
	
	
	private static int CURRENT_ID = 0;
	
	
	private final int ID;
	private double price;
	private TYPES type;
	private String model;
	private String description;
	private ArrayList<Integer> ratings = new ArrayList<>();
	
	public Product(String model, String description, double price, TYPES type ) {
		this.model = model;
		this.description = description;
		this.price = price;
		this.type = type;
		//this.ID = Product.returnCurrentID();
		this.ID = ++CURRENT_ID;

	}
	
	
	
	/*public double checkRating() {
		double sum = 0;
		for (Integer r : ratings) {
			sum += r.intValue();
		}
		return sum/ratings.size();
	}
	
	public void addRating(int rating) {
		if(rating >= Market.MIN_RATING && rating <= Market.MAX_RATING) {
			this.ratings.add(rating);
			System.out.println("Successful rate");
		}
		else {
			System.out.println("Invalid integer");
		}
	}*/
	
	
	public int getId() {
		return ID;
	}




	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		if(Validator.checkForPositiveNum(price)) {
			this.price = price;
		}
	}



	public TYPES getType() {
		return type;
	}



	public void setType(TYPES type) {
		this.type = type;
	}



	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		if(Validator.validateString(model)) {
			this.model = model;
		}
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		if(Validator.validateString(description)) {
			this.description = description;
		}
	}
	
	public void setRatings(ArrayList<Integer> ratings) {
		this.ratings = ratings;
	}
	
	public ArrayList<Integer> getRatings() {
		return ratings;
	}
	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ID;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (ID != other.ID)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
	
		return this.model;
	}
	
	
	
	

}
