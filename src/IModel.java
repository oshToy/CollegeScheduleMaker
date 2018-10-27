import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javafx.event.Event;
import javafx.event.EventHandler;

public interface IModel {
	public void registerListener(EventHandler<MyActionEvent> e);
	public void createNewCourse(String[] courseInput);
	public void createNewShow(int courseCode, String[][] strings);
	int getIvokingSlotNumber();
	void setShowNumberPlusOne(int courseCode);
	public ICourse[] getAllCoursesForViewer();
	public void addCourseToSchedule(ICourse wantedCourse,int showCode);
	ISlot[] getInokedSlots();
	public void removeCourseFromSchedule(ICourse invokingCourseCheckboxes);

	//TODO FOR TESTING ONLY!!!
	public Model getModelForTestingOnly();
	ArrayList<ICourse> getImpossibleCourses();


}
