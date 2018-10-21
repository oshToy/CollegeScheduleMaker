import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Course {
	
	private int courseCode;
	private String name;
	//private Show[] mapOfShows;
	private Map  <Integer,Show> mapOfShows;
	
	public Course(int courseCode ,String name){
		this.courseCode=courseCode;
		this.name=name;
		mapOfShows=new HashMap();

	}
	
	public int getCourseCode() {
		return courseCode;
	}

	public String getName() {
		return name;
	}

	public Map  <Integer,Show> getShows() {
		return mapOfShows;
	}
	public Show getShowByShowCourse(int showCode) {
		return this.getShows().get(showCode);
	}
	public Show getLonleyShow() {
		if(getShows().size()==1){
			for (Show show : mapOfShows.values()) {
				return show;
			}
		}
		return null;
	}



}