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
	public String[][] getSlotsInput(int numberOfSlots);
	public void slotTimingException();
	public void roomFullException();
	void teacherTeachingException();
	public void roomInputIsntAint();


}
