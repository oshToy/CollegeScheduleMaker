

import exceptions.EndingTimeBeforeStartingTimeException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	/*
	public static Scanner s = new Scanner(System.in);//static object
	
	public static void addCourse(ArrayList <Course> arrayOfCourse){
		//int id ,String name,int numOfShows
		System.out.println("Making new course...");
		System.out.println("Please enter course id : ");
		int courseId = s.nextInt();
		System.out.println("Please enter course name : ");
		String courseName=s.next();
		System.out.println("Please enter number of shows of the course : ");
		int courseNumOfShows = s.nextInt();
		Course course=new Course(courseId,courseName,courseNumOfShows);
		arrayOfCourse.add(course);
		for (int i = 0; i < courseNumOfShows; i++) {
		addShows(course,i,arrayOfCourse);
		}
		
	}
	public static void addShows(Course course,int numOfShow,ArrayList <Course> arrayOfCourse){
		System.out.println("Making new show...");
		System.out.println("Please enter  number of slots of the show : ");//should be dynamic
		int numOfSlots = s.nextInt();
		Show show = new Show(numOfSlots);
		for (int i = 0; i < numOfSlots; i++) {
			addSlots(show,i,arrayOfCourse);
		}
		course.getShows()[numOfShow]=show;
	}
	
	public static void addSlots(Show show,int numOfSlot,ArrayList <Course> arrayOfCourse){
		//public Slot(Day day,int startingTime, int endingTime, int numberOfRoom, String teacher)
		System.out.println("Making new slot...");
		IDay.Day day=null;
		while(day==null){
		System.out.println("Please enter slot day : 1-7");//should be combo box
		day=IDay.dayByInput(s.nextInt());
		if(day==null){
			System.out.println("error! please enter number 0-7");
		}
		}
		System.out.println("Please enter startingTime : 7-24");//should be combo box
		int startingTime=s.nextInt();
		System.out.println("Please enter endingTime : 7-24");//should be combo box
		int endingTime=s.nextInt();
		while(true){
		try {
		boolean validRoom=false;
		int numberOfRoom=0;
		while(validRoom==false){
		validRoom=true;//init
		System.out.println("Please enter room number :");//should be combo box
		numberOfRoom=s.nextInt();
		Slot tempSlot=new Slot(day, startingTime, endingTime, numberOfRoom, null);
		validRoom=ligitSlotByroom(tempSlot,arrayOfCourse);

		}
		boolean validTeacher=false;
		String nameOfLect="";
		Slot newSlot=null;
		while(validTeacher==false){
		System.out.println("Please enter teacher name :");//should be combo box
		nameOfLect=s.next();
		newSlot=new Slot(day, startingTime, endingTime, numberOfRoom, nameOfLect);
		validTeacher=ligitSlotByTeacher(newSlot,arrayOfCourse);

		}
			show.getSlots()[numOfSlot]=newSlot;
			break;
		} catch (StartingTimeException e) {
			System.out.println(e.toString());
			System.out.println("Please enter startingTime : 7-24");//should be combo box
			startingTime=s.nextInt();
		} catch (EndingTimeException e) {
			System.out.println(e.toString());
			System.out.println("Please enter endingTime : 7-24");//should be combo box
			endingTime=s.nextInt();
		}
		}
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
	*/
	public static void main(String[] args) throws  EndingTimeBeforeStartingTimeException {
		/*
		 ArrayList <Course> arrayOfCourse=new  ArrayList <Course>();
		 
		 
		 boolean anotherCourse=true;
		 while(anotherCourse){
			 addCourse(arrayOfCourse);
			 System.out.println("If you finish add courses , press y");
			 String proccess=s.next();
			 if(proccess.equals("y")){
				 anotherCourse=false;
			
			 }
		 }
		 
		 
		 Course course=new Course(1,"algebra",2);
		 arrayOfCourse.add(course);
		 Show showOne=new Show(2);
		 Show showTwo=new Show(2);
		 course.getShows()[0]=showOne;
		 course.getShows()[1]=showTwo;
		 Slot slotOne=new Slot(IDay.Day.Sunday, 10, 15, 101, "Yossi");
		 Slot slotTwo=new Slot(IDay.Day.Monday, 11, 13, 102, "Yossi");
		 Slot slotOne2=new Slot(IDay.Day.Sunday, 15, 17, 103, "Yossi");
		 Slot slotTwo2=new Slot(IDay.Day.Monday, 12, 15, 104, "ossi");
		 showOne.getSlots()[0]=slotOne;
		 
		 if(ligitSlotByTeacher(slotTwo,arrayOfCourse)&&ligitSlotByroom(slotTwo, arrayOfCourse)){
			 showOne.getSlots()[1]=slotTwo;
		 }
		 if(ligitSlotByTeacher(slotOne2,arrayOfCourse)&&ligitSlotByroom(slotOne2, arrayOfCourse)){
		 showTwo.getSlots()[0]=slotOne2;
		 }
		 if(ligitSlotByTeacher(slotTwo2,arrayOfCourse)&&ligitSlotByroom(slotTwo2, arrayOfCourse)){
			 showTwo.getSlots()[1]=slotTwo2;
		 }
			 
		 Course course1=new Course(2,"math",1);
		 Show showOne1=new Show(1);
		 Slot slotOne1=new Slot(IDay.Day.Sunday, 17, 18, 105, "momo");
		 showOne1.getSlots()[0]=slotOne1;
		 course1.getShows()[0]=showOne1;
		 arrayOfCourse.add(course1);
		 
		 for (Course tempCourse : arrayOfCourse) {
				System.out.println(tempCourse);
				
				
			}
		 System.out.println("schedule !!!!\n");
		 Schedule loz=new Schedule();
		 loz.addCourseToSchedule(arrayOfCourse, 0, 0);
		 loz.addCourseToSchedule(arrayOfCourse, 0, 1);
		 System.out.println(loz.getCourseOfSchedule());
		 System.out.println(loz.timeValidSlots(arrayOfCourse, 1, 0));


		 
		  


		 */

		
		launch(args); 
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		ScheduleJFX app=new ScheduleJFX( primaryStage);
		 
		
	}
	public static Controller createController(ScheduleJFX viewer){
		Model model = new Model();
		Controller controller = new Controller(model);
		controller.addViewer(viewer);
		
		
		
		return controller;
	}
	
}
