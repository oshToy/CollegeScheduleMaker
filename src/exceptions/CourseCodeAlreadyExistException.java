package exceptions;

public class CourseCodeAlreadyExistException extends Exception {
	
	public CourseCodeAlreadyExistException(String errorOutput) {
		super(errorOutput);
	}
}
