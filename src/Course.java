import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Course {
	
	private int courseCode;
	private String name;
<<<<<<< HEAD
	//private Show[] mapOfShows;
	private Map  <Integer,Show> mapOfShows;
	
	public Course(int courseCode ,String name){
		this.courseCode=courseCode;
		this.name=name;
		mapOfShows=new HashMap();

	}
	
	public int getCourseCode() {
=======
		private Show[] shows;
	
	public Course(int id ,String name,int numOfShows){
		this.courseCode=id;
		this.name=name;
		this.shows=new Show[numOfShows];//create numOfShows shows
	}
	
	public static Course oneShowCourse(int id ,String name){
		return new Course(id, name, 1);
	}

	public int getId() {
>>>>>>> 13797d4865f7ede8e1f59648fbde7272da06635e
		return courseCode;
	}

	public String getName() {
		return name;
	}

	public Map  <Integer,Show> getShows() {
		return mapOfShows;
	}
<<<<<<< HEAD
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
=======

	
	
>>>>>>> 13797d4865f7ede8e1f59648fbde7272da06635e



}
