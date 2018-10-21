import java.awt.event.ActionListener;

import javafx.event.Event;
import javafx.event.EventHandler;

public interface IModel {
	public void registerListener(EventHandler<MyActionEvent> e);

	public void createNewCourse(String[] courseInput);

}
