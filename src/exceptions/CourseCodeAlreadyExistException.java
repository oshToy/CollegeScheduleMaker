package exceptions;

public class CourseCodeAlreadyExistException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseCodeAlreadyExistException(String errorOutput) {
		super(errorOutput);
	}
}
