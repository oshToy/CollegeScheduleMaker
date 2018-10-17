import javafx.scene.Node;

public interface IView {

	public  Node createNewShowPane();
	public  Node createNewCoursePane();
	public Node createNewSlotPane(int amountOfSlots);
	public  void setMainPane(Node p);
	public int getNumberOfSlots();
	public Node courseMenuPane();
	public String[] getCourseInput();

}
