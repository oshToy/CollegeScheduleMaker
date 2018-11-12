import java.util.HashSet;
import java.util.Set;

import javafx.scene.control.CheckBox;

public class CourseCheckBox extends CheckBox implements ICourse{
	private int showCode;
	private int courseCode;
	private String name;
	private HashSet <Integer> set;
	
	public CourseCheckBox(String name,int courseCode,int showCode){
		super(name);
		this.name=name;
		this.courseCode=courseCode;
		this.showCode=showCode;
		set = new HashSet<Integer>() {{
		    add(showCode);
		}};
	}
	@Override
	public int getCourseCode() {
		return  this.courseCode;
	}
	@Override
	public String getCourseName() {
		return  this.name;
	}
	@Override
	public HashSet <Integer> getShowCodes() {
		return set;
	}
	public int getShowCode(){
		return this.courseCode;
	}
	
	
	
}
