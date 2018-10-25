import javafx.scene.control.CheckBox;

public class CourseCheckBox extends CheckBox implements ICourse{
	private int courseCode;
	private String name;
	
	public CourseCheckBox(String name,int courseCode){
		super(name);
		this.name=name;
		this.courseCode=courseCode;
	}
	@Override
	public int getCourseCode() {
		return courseCode;
	}
	@Override
	public String getCourseName() {
		return name;
	}
	
	
	
}
