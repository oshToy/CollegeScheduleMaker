package exceptions;

public class EndingTimeBeforeStartingTimeException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EndingTimeBeforeStartingTimeException(String errorOutput) {
		super(errorOutput);
	}
}
