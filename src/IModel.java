import java.awt.event.ActionListener;

public interface IModel {
	public void registerListener(ActionListener listener);

	public void createNewCourse(String[] courseInput);

}
