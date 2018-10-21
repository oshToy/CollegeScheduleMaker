import javafx.event.Event;
public class MyActionEvent extends Event {
	private static final long serialVersionUID = 1L;
	private String message;

	public MyActionEvent(Object source, String message) {
		super(source, null, null);
		this.message = message;
	
	}
	// get the message
	public String getMsg() {
		return message;
	}
}
