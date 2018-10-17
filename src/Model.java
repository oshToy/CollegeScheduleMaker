import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Model implements IModel{
	
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
	private AllCourses allCourses= new AllCourses();
	private Schedule schedule= new Schedule();

	@Override
	public void createNewCourse(String[] courseInput) {
		
		
	}
	@Override
	public void registerListener(ActionListener listener) {
		listeners.add(listener);
	}

}
