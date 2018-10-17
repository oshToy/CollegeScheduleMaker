import java.util.Arrays;

public class Course {
	
	private int id;
	private String name;
	private int numOfShows;
	private Show[] shows;
	
	public Course(int id ,String name,int numOfShows){
		this.id=id;
		this.name=name;
		this.numOfShows=numOfShows;
		this.shows=new Show[numOfShows];//create numOfShows shows
	}
	
	public static Course oneShowCourse(int id ,String name){
		return new Course(id, name, 1);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Show[] getShows() {
		return shows;
	}

	@Override
	public String toString() {
		return "\nCourse [id=" + id + ", name=" + name + ", numOfShows=" + numOfShows + ", shows="
				+ Arrays.toString(shows) + "]";
	}


}
