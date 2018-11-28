import java.util.HashSet;

import javafx.scene.control.CheckBox;

public class CourseCheckBox extends CheckBox implements ICourse{
	private int courseCode;
	private String name;
	private HashSet <Integer> set;
	
	public CourseCheckBox(String name,int courseCode,int showCode){
		super(name);
		this.name=name;
		this.courseCode=courseCode;
		set = new HashSet<Integer>() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
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
