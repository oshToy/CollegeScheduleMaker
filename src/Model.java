import java.awt.event.ActionListener;
import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;

public class Model implements IModel{
	
	private ArrayList<EventHandler<MyActionEvent>>listeners = new ArrayList<>();
	private AllCourses allCourses= new AllCourses();
	private Schedule schedule= new Schedule();

	@Override
	public void createNewCourse(String[] courseInput) {
		
		
	}
	
	
	@Override
	public void registerListener(EventHandler<MyActionEvent> e) {
		listeners.add(e);
		
	}

}
