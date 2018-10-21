package exceptions;

public class EndingTimeBeforeStartingTimeException extends Exception{
	
	public EndingTimeBeforeStartingTimeException(String errorOutput) {
		super(errorOutput);
	}
}
