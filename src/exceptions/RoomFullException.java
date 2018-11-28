package exceptions;

public class RoomFullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomFullException(String errorOutput) {
		super(errorOutput);
	}
}
