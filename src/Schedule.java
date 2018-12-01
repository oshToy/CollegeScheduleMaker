import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Schedule implements IDay {
	

	private Map  <Integer,Course> courseOfSchedule;
	public Schedule(){
		courseOfSchedule=new HashMap();
	}
	public void addCourseToSchedule(Course wantedCourse,int showCode) {
				if(timeValidSlots(wantedCourse,showCode)==true){
					Course course=null;
					if(courseOfSchedule.containsKey(wantedCourse)==false){
					course=new Course(wantedCourse.getCourseCode(),wantedCourse.getCourseName());
					courseOfSchedule.put(wantedCourse.getCourseCode(),course);
					}
					else{
						course=courseOfSchedule.get(wantedCourse);
					}
					course.getShows().put(showCode,wantedCourse.getShowByShowCourse(showCode));
					
					}
	}

	public Map  <Integer,Course> getCourseOfSchedule() {
		return courseOfSchedule;
	}
	public boolean timeValidSlots(Course wantedCourse,int showCode){
		int numOfSlots=wantedCourse.getShowByShowCourse(showCode).getNumberOfSlots();
		for (int  i = 0; i < numOfSlots; i++) {//check all slots of new show
			Slot newSlot=wantedCourse.getShowByShowCourse(showCode).getSlots().get(i);//new slot
			//check all exists courses - with one show only
				for (Course course : courseOfSchedule.values()) {
					for (Slot existSlot : course.getLonleyShow().getSlots()) {
					if(newSlot!=null&&existSlot!=null&&newSlot.noMatchingHours(existSlot)==false){//TODO clear null condition
							return false;
					}
						}
							}
								}
		return true;
	}
	public void removeCourseFromSchedule(Course wantedCourse) {
		courseOfSchedule.remove(wantedCourse.getCourseCode());
		
	}


	
}
