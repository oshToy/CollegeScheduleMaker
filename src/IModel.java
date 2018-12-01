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
	public ICourse[] getAllCoursesForViewer();
	public void addCourseToSchedule(ICourse wantedCourse);
	ISlot[] getInokedSlots();
	public void removeCourseFromSchedule(ICourse invokingCourseCheckboxes);

	//TODO FOR TESTING ONLY!!!
	public Model getModelForTestingOnly();
	ArrayList<ICourse> getImpossibleCourses();
	public void createAnotherShow(int creatingCourseCode, String[][] slotsInput);
	public void removeShowsByDay(int invokingDayNumber);
	void addShowsByDay(int invokingDayNumber);
	public void addPossibleShowsByDay(int invokingDayNumber);
	public void removeShowsByHour(IHour iHour);
	public void addPossibleShowsByHour(IHour iHour);



}
