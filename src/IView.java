import com.sun.javafx.scene.paint.GradientUtils.Point;

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
	void courseNameException();
	public int getCreatingCourseCode();
	public String[][] getSlotsInput();
	public void slotTimingException(int slotNumber);
	public void roomFullException(int slotNumber);
	void teacherTeachingException(int slotNumber);
	public void roomInputIsntAint(int slotNumber);
	Node schedulePane();
	void changeSceduleButtonUnactive();


}
