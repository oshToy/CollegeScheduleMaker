import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllCourses {
	private  ArrayList <Course> arrayOfCourse;
	public AllCourses(){
		this.arrayOfCourse=new ArrayList <Course>();
	}
	public void addCourse(int courseId,String courseName,int courseAmountOfShows) throws Exception{
		for (Course course : arrayOfCourse) {
			if(course.getId()==courseId){
				throw new Exception("Id ALREADY EXIST !");
			}
			if(course.getName().equals(courseName)){
				throw new Exception("NAME ALREADY EXIST !");
			}	
		}
		Course course=new Course(courseId,courseName,courseAmountOfShows);
		arrayOfCourse.add(course);
		
	}
	public Course getCourseById(int courseId) throws Exception{
		Course wantedCourse = null;
		for (Course course : arrayOfCourse) {
			if (course.getId()==courseId) {
				wantedCourse=course;
			}
		}
		if(wantedCourse == null){
			throw new Exception("course Id NOT FOUND");
		}
		return wantedCourse;
	}
	public void addShow(int courseId,int numOfShow,int amountOfSlots) throws Exception{
		Show show = new Show(amountOfSlots);
		Course wantedCourse = getCourseById(courseId);
		wantedCourse.getShows()[numOfShow]=show;
	}
	public ArrayList<Course> getArrayOfCourse() {
		return arrayOfCourse;
	}
	public void addSlot(int courseId,int numOfShow,int numOfSlot,IDay.Day day,int startingTime,int endingTime,int numberOfRoom,String nameOfLect) throws Exception{
		Slot newSlot=new Slot(day, startingTime, endingTime, numberOfRoom, nameOfLect);
		if (ligitSlotByroom(newSlot,arrayOfCourse)==false){
			throw new Exception("Room full those hours ");
		}
		else if(ligitSlotByTeacher(newSlot,arrayOfCourse)==false){
			throw new Exception("Teacher teaching those hours");  
		}
		Course wantedCourse = getCourseById(courseId);
		wantedCourse.getShows()[numOfShow].getSlots()[numOfSlot]=newSlot;
	}
	public static boolean ligitSlotByTeacher(Slot newSlot, ArrayList<Course> arrayOfCourse) {
		for (Course course : arrayOfCourse) {
			for (Show shows : course.getShows()){		
				for (Slot slot : shows.getSlots()) {
					if(slot!=null&&newSlot.matchTeacher(slot)==true&&newSlot.noMatchingHours(slot)==false){//TODO clear null condition
						return false;
					}
				}
			}
		}
		return true;
	}
	public static boolean ligitSlotByroom(Slot tempSlot,ArrayList <Course> arrayOfCourse) {
		for (Course course : arrayOfCourse) {
			for (Show shows : course.getShows()){
				for (Slot slot : shows.getSlots()) {
					if(slot!=null&&tempSlot.matchRoom(slot)==true&&tempSlot.noMatchingHours(slot)==false){//TODO clear null condition
						return false;
					}
				}
			}
		}
		return true;
	}

}
