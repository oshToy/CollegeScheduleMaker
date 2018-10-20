import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Schedule implements IDay {
	
	private ArrayList <Course> courseOfSchedule;//only one show courses 
	
	public Schedule(){
		courseOfSchedule=new ArrayList <Course>();
		
	}
	//public void addCourseToSchedule(int id ,String name,int numOfShows,int numberOfSlots,Day day,int startingTime, int endingTime, int numberOfRoom, String nameOfLect) throws Exception{
	public void addCourseToSchedule(Course wantedCourse,int wantedShow) {
		
				
			//Course course =new Course(id,name,numOfShows) ;
			//CHECKING TIMING VALIDTION of slots

				if(timeValidSlots(wantedCourse,wantedShow)==true){
				Course course=Course.oneShowCourse(wantedCourse.getId(),wantedCourse.getName());//create course with one show !
				course.getShows()[0]=wantedCourse.getShows()[wantedShow];
				courseOfSchedule.add(course);
				}
	}
	//public removeCourseFromSchedule(c)//TODO
	public ArrayList<Course> getCourseOfSchedule() {
		return courseOfSchedule;
	}
	public boolean timeValidSlots(Course wantedCourse,int wantedShow){
		int numOfSlots=wantedCourse.getShows()[wantedShow].getNumberOfSlots();
		for (int  i = 0; i < numOfSlots; i++) {//check all slots of new show
			Slot newSlot=wantedCourse.getShows()[wantedShow].getSlots().get(i);//new slot
			for (int  j = 0; j < courseOfSchedule.size(); j++) {//check all exists courses - with one show only
				for (int  k = 0; k < courseOfSchedule.get(j).getShows()[0].getNumberOfSlots(); k++) {//chack all exists slots 
					Slot existSlot=courseOfSchedule.get(j).getShows()[0].getSlots().get(k);
					if(newSlot!=null&&existSlot!=null&&newSlot.noMatchingHours(existSlot)==false){//TODO clear null condition
							return false;
					}
						}
							}
								}
		return true;
	}


	
}
