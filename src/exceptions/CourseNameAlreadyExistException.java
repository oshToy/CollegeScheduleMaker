package exceptions;

public class CourseNameAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseNameAlreadyExistException(String errorOutput) {
		super(errorOutput);
	}
}
