package exceptions;

public class CourseNameAlreadyExistException extends Exception {

	public CourseNameAlreadyExistException(String errorOutput) {
		super(errorOutput);
	}
}
