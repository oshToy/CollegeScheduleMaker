package exceptions;

public class courseNotExistException extends Exception {
	public courseNotExistException(String errorOutput) {
		super(errorOutput);
	}
}
