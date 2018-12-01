package exceptions;

public class RoomFullException extends Exception {

	public RoomFullException(String errorOutput) {
		super(errorOutput);
	}
}
