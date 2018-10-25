import java.util.Collection;

import javafx.scene.Node;

public interface IView {

	public  Node createNewShowPane(int courseCode);
	public  Node createNewCoursePane();
	public Node createNewSlotPane(int amountOfSlots);
	public  void setMainPane(Node p);
	public int getNumberOfSlots();
	public Node courseMenuPane();
	public String[] getCourseInput();
	public String[] getShowInput();
	public void courseCodeException();
	public void courseNameException();
	public int getCreatingCourseCode();
	public String[][] getSlotsInput();
	public void slotTimingException(int slotNumber);
	public void roomFullException(int slotNumber);
	public void teacherTeachingException(int slotNumber);
	public void roomInputIsntAint(int slotNumber);
	public void scheduleMakerPane(Collection<ICourse> coursesName);
	void changeColumnToDeactiveColor(int coulmn);
	int getInvokingDayNumber();
	void changeColumnToActiveColor(int coulmn);
	CourseCheckBox getInvokingCourseCheckboxes();
	public void addSlotTOschedule(ISlot[] iSlots);


}
