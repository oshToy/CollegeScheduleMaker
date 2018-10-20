import java.util.Arrays;

public class Course {
	
	private int courseCode;
	private String name;
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
		return courseCode;
	}

	public String getName() {
		return name;
	}

	public Show[] getShows() {
		return shows;
	}

	
	


}
