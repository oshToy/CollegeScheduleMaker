import java.awt.event.ActionListener;
import java.util.Collection;

import javafx.event.Event;
import javafx.event.EventHandler;

public interface IModel {
	public void registerListener(EventHandler<MyActionEvent> e);
	public void createNewCourse(String[] courseInput);
	public void createNewShow(int courseCode, String[][] strings);
	int getIvokingSlotNumber();
	void setShowNumberPlusOne(int courseCode);
	public Collection<ICourse> getAllCourses();
	public void addCourseToSchedule(ICourse wantedCourse,int showCode);
	ISlot[] getInokedSlots();

}
