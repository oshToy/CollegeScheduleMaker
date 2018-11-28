package exceptions;

public class courseNotExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public courseNotExistException(String errorOutput) {
		super(errorOutput);
	}
}
