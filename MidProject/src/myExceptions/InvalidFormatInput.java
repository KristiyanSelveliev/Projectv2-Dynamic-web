package myExceptions;

public class InvalidFormatInput extends Exception{
	

	public InvalidFormatInput(String message, Throwable cause) {
		super(message, cause);		
	}

	public InvalidFormatInput(String message) {
		super(message);
	}

	public InvalidFormatInput(Throwable cause) {
		super(cause);
	}

	public InvalidFormatInput() {
		super();
	}
	
	

}
