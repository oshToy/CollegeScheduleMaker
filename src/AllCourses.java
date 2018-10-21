import java.util.HashMap;
import java.util.Map;

import exceptions.CourseCodeAlreadyExistException;
import exceptions.CourseNameAlreadyExistException;
import exceptions.EndingTimeBeforeStartingTimeException;
import exceptions.RoomFullException;
import exceptions.TeacherTeachingException;
import exceptions.courseNotExistException;

public class AllCourses {
	private Map  <Integer,Course> mapOfCorses;
	
	public AllCourses(){
		this.mapOfCorses=new HashMap<>();
	}
	public void addCourse(int courseId,String courseName) throws CourseCodeAlreadyExistException,CourseNameAlreadyExistException,NumberFormatException{
		for (Course course : mapOfCorses.values()) {
			if(course.getCourseCode()==courseId){
				throw new CourseCodeAlreadyExistException("Id ALREADY EXIST !");
			}
			if(course.getName().equals(courseName)){
				throw new CourseNameAlreadyExistException("NAME ALREADY EXIST !");
			}	
		}
		Course course=new Course(courseId,courseName);
		mapOfCorses.put(courseId,course);
		
	}
	public Course getCourseById(int courseId) throws courseNotExistException{
		Course wantedCourse = null;
		for (Course course : mapOfCorses.values()) {
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
	public Map  <Integer,Course> getArrayOfCourse() {
		return mapOfCorses;
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
	public boolean ligitSlotByTeacher(Slot newSlot) {
		for (Course course : mapOfCorses.values()) {
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
		for (Course course : mapOfCorses.values()) {
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