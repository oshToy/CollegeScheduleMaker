import java.util.HashMap;
import java.util.Map;

import exceptions.CourseCodeAlreadyExistException;
import exceptions.CourseNameAlreadyExistException;
import exceptions.EndingTimeBeforeStartingTimeException;
import exceptions.RoomFullException;
import exceptions.TeacherTeachingException;
import exceptions.courseNotExistException;

public class AllCourses {
	private Map  <Integer,ICourse> mapOfCourses;
	
	public AllCourses(){
		this.mapOfCourses=new HashMap<>();
	}
	public void addCourse(int courseId,String courseName) throws CourseCodeAlreadyExistException,CourseNameAlreadyExistException,NumberFormatException{
		for (ICourse icourse : mapOfCourses.values()) {
			Course course=(Course) icourse;
			if(course.getCourseCode()==courseId){
				throw new CourseCodeAlreadyExistException("Id ALREADY EXIST !");
			}
			if(course.getCourseName().equals(courseName)){
				throw new CourseNameAlreadyExistException("NAME ALREADY EXIST !");
			}	
		}
		Course course=new Course(courseId,courseName);
		mapOfCourses.put(courseId,course);
		
	}
	public Course getCourseById(int courseId) throws courseNotExistException{
		Course wantedCourse = null;
		for (ICourse icourse : mapOfCourses.values()) {
			Course course=(Course) icourse;
			if (course.getCourseCode()==courseId) {
				wantedCourse=course;
			}
		}
		if(wantedCourse == null){
			throw new courseNotExistException("course Id NOT FOUND");
		}
		return wantedCourse;
	}
	
	public void addShow(int courseId,int showCode) throws courseNotExistException {
		Show show = new Show(showCode);
		Course wantedCourse = getCourseById(courseId);
		wantedCourse.getShows().put(showCode, show);
	}
	public Map  <Integer,ICourse> getMapOfCourse() {
		return mapOfCourses;
	}
	public void addSlot(int courseId,int numOfShow,int numOfSlot,IDay.Day day,int startingTime,int endingTime,int numberOfRoom,String nameOfLect) throws RoomFullException, TeacherTeachingException, EndingTimeBeforeStartingTimeException, courseNotExistException {
		Slot newSlot=new Slot(day, startingTime, endingTime, numberOfRoom, nameOfLect);
		if (ligitSlotByroom(newSlot)==false){
			throw new RoomFullException("Room full those hours ");
		}
		else if(ligitSlotByTeacher(newSlot)==false){
			throw new TeacherTeachingException("Teacher teaching those hours");  
		}
		Course wantedCourse = getCourseById(courseId);
		//wantedCourse.getShows()[numOfShow].getSlots().add(newSlot);
		wantedCourse.getShows().get(numOfShow).getSlots().add(newSlot);
	}
	@Override
	public String toString() {
		return "AllCourses [mapOfCorses=" + mapOfCourses + "]";
	}
	public boolean ligitSlotByTeacher(Slot newSlot) {
		for (ICourse icourse : mapOfCourses.values()) {
			Course course=(Course) icourse;
			for (Show shows : course.getShows().values()){	
				for (Slot slot : shows.getSlots()) {
					if(slot!=null&&newSlot.matchTeacher(slot)==true&&newSlot.noMatchingHours(slot)==false){//TODO clear null condition
						return false;
					}
				}
			}
		}
		return true;
	}
	public boolean ligitSlotByroom(Slot tempSlot) {
		for (ICourse icourse : mapOfCourses.values()) {
			Course course=(Course) icourse;
			//for (Show shows : course.getShows()){
			for (Show shows : course.getShows().values()){
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