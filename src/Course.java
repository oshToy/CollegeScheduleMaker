import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Course implements ICourse {

	private int courseCode;
	private String name;

	@Override
	public String toString() {
		return "Course [courseCode=" + courseCode + ", name=" + name + ", mapOfShows=" + mapOfShows + "]";
	}

	// private Show[] mapOfShows;
	private Map<Integer, Show> mapOfShows;

	public Course(int courseCode, String name) {
		this.courseCode = courseCode;
		this.name = name;
		mapOfShows = new HashMap<>();

	}

	@Override
	public HashSet<Integer> getShowCodes() {
		HashSet<Integer> ret = new HashSet<>(mapOfShows.keySet());
		return ret;
	}

	@Override
	public int getCourseCode() {
		return courseCode;
	}

	@Override
	public String getCourseName() {
		return name;
	}

	public Map<Integer, Show> getShows() {
		return mapOfShows;
	}

	public Show getShowByShowCourse(int showCode) {
		return mapOfShows.get(showCode);
	}

	public Show getLonleyShow() {
		if (getShows().size() == 1) {
			for (Show show : mapOfShows.values()) {
				return show;
			}
		}
		return null;
	}

}