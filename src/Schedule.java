import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Schedule implements IDay {
	

	private Map  <Integer,Course> courseOfSchedule;
	public Schedule(){
		courseOfSchedule=new HashMap();

		
	}
	//public void addCourseToSchedule(int id ,String name,int numOfShows,int numberOfSlots,Day day,int startingTime, int endingTime, int numberOfRoom, String nameOfLect) throws Exception{
	public void addCourseToSchedule(Course wantedCourse,int showCode) {
		
			//CHECKING TIMING VALIDTION of slots

				if(timeValidSlots(wantedCourse,showCode)==true){
					Course course=new Course(wantedCourse.getCourseCode(),wantedCourse.getName());
				//Course course=Course.oneShowCourse(wantedCourse.getId(),wantedCourse.getName());//create course with one show !
					course.getShows().put(showCode,course.getShowByShowCourse(showCode));
					courseOfSchedule.put(wantedCourse.getCourseCode(),course);
					}
	}
	//public removeCourseFromSchedule(c)//TODO
	public Map  <Integer,Course> getCourseOfSchedule() {
		return courseOfSchedule;
	}
	public boolean timeValidSlots(Course wantedCourse,int showCode){
		int numOfSlots=wantedCourse.getShowByShowCourse(showCode).getNumberOfSlots();
		for (int  i = 0; i < numOfSlots; i++) {//check all slots of new show
			Slot newSlot=wantedCourse.getShowByShowCourse(showCode).getSlots().get(i);//new slot
			for (int  j = 0; j < courseOfSchedule.size(); j++) {//check all exists courses - with one show only
				for (int  k = 0; k < courseOfSchedule.get(j).getLonleyShow().getNumberOfSlots(); k++) {//chack all exists slots 
					Slot existSlot=courseOfSchedule.get(j).getLonleyShow().getSlots().get(k);
					if(newSlot!=null&&existSlot!=null&&newSlot.noMatchingHours(existSlot)==false){//TODO clear null condition
							return false;
					}
						}
							}
								}
		return true;
	}


	
}
