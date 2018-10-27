

import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class Controller implements EventHandler<MyActionEvent> {
	// all finals that will references to comands from model and viewers
	public static final String CREATE_COURSE_VIEWER = "button create course invoked";
	public static final String DONE_CREATE_ALL_COURSES_VIEWER = "button Done create all course invoked";
	public static final String DONE_CREATE_COURSE_VIEWER = "button Done create course invoked";
	public static final String DONE_CREATE_SHOW_VIEWER = "button Done create show invoked";
	public static final String DONE_CREATE_SLOTS_VIEWER = "buttonDone create slots invoked";
	public static final String DONE_CREATE_COURSE_MODEL = "model done create course";
	public static final String DONE_CREATE_SLOTS_MODEL = "model done create slots";
	public static final String COURSE_CODE_ALREADY_EXIST_ERROR = "Course code error";
	public static final String COURSE_NAME_ALREADY_EXIST_ERROR = "Course name error";
	public static final String TIMING_ERROR = "The ending hour is before starting hour";
	public static final String ROOM_FULL_EROOR = "ROOM_FULL_EROOR";
	public static final String TEACHER_ALREADY_TEACHING_ERROR = "TEACHER_ALREADY_TEACHING_ERROR";
	public static final String ROOM_INPUT_ISNT_INTEGER = "room input isnt a int";
	public static final String DAY_CHECKBOX_ACTIVATED = "day checkbox activated";
	public static final String DAY_CHECKBOX_DEACTIVATED = "day checkbox deactivated";
	public static final String SCHEDULE_BUTTON_UN_ACTIVE = " schedule button is changed from/to active";
	public static final String COURSE_CHECKBOX_ACTIVATED = "courseCB activated";
	public static final String COURSE_CHECKBOX_DEACTIVATED = "courseCB deactivated";
	public static final String COURSE_ADDED_TO_SCHEDULE = "course add to schedule";
	public static final String COURSE_REMOVED_FROM_SCHEDULE = "course remove from schedule";

	
	private IView viewer;
	private IModel model;
	private boolean testing=true;	//TODO FOR TESTING ONLY!!!
	public Controller(IModel model) {
		this.model = model;
		model.registerListener(this);
	}
	@Override
	public void handle(MyActionEvent e) {
		
		//TODO FOR TESTING ONLY!!!
		if(testing==true){
		TestScheduleInsert.testSchedule(model.getModelForTestingOnly());
		testing=false;
		}
		if (e.getMsg().equals(DONE_CREATE_COURSE_VIEWER)) {
			createNewCourse((IView) e.getSource());
		}
		else if (e.getMsg().equals(DONE_CREATE_SHOW_VIEWER)) {
			int numberOfSlots = viewer.getNumberOfSlots();
			(viewer).setMainPane(viewer.createNewSlotPane(numberOfSlots));
			model.setShowNumberPlusOne(viewer.getCreatingCourseCode());
		}
		else if (e.getMsg().equals(DONE_CREATE_SLOTS_VIEWER)) {
			createNewShow((IView) e.getSource());
		}
		else if (e.getMsg().equals(DONE_CREATE_COURSE_MODEL)) {

			viewer.setMainPane(viewer.createNewShowPane(Model.toIntFromString(viewer.getCourseInput()[0])));

		} else if (e.getMsg().equals(CREATE_COURSE_VIEWER)) {

			(viewer).setMainPane(((IView) e.getSource()).createNewCoursePane());

		}  else if (e.getMsg().equals(DONE_CREATE_SLOTS_MODEL)) {
			(viewer).setMainPane(viewer.courseMenuPane());

		} else if (e.getMsg().equals(COURSE_CODE_ALREADY_EXIST_ERROR)) {

			viewer.courseCodeException();

		} else if (e.getMsg().equals(COURSE_NAME_ALREADY_EXIST_ERROR)) {

			viewer.courseNameException();
			
		}
		else if (e.getMsg().equals(TIMING_ERROR)){
			viewer.slotTimingException(model.getIvokingSlotNumber());
		}
		else if (e.getMsg().equals(ROOM_FULL_EROOR)){
			viewer.roomFullException(model.getIvokingSlotNumber());
		}
		else if (e.getMsg().equals(TEACHER_ALREADY_TEACHING_ERROR)){
			viewer.teacherTeachingException(model.getIvokingSlotNumber());
		}
		else if (e.getMsg().equals(ROOM_INPUT_ISNT_INTEGER)){
			viewer.roomInputIsntAint(model.getIvokingSlotNumber());
		}
		else if (e.getMsg().equals(DONE_CREATE_ALL_COURSES_VIEWER)){
			//ONLY FOR TEST !!
			viewer.scheduleMakerPane(model.getAllCoursesForViewer());
		}

		else if (e.getMsg().equals(DAY_CHECKBOX_ACTIVATED)){
			//ONLY FOR TEST !! changeColumnToActiveColor
			viewer.changeColumnToActiveColor(viewer.getInvokingDayNumber());
		}
		else if (e.getMsg().equals(DAY_CHECKBOX_DEACTIVATED)){
			//ONLY FOR TEST !!
			viewer.changeColumnToDeactiveColor(viewer.getInvokingDayNumber());
		}
		else if (e.getMsg().equals(COURSE_CHECKBOX_ACTIVATED)) {
			model.addCourseToSchedule(viewer.getInvokingCourseCheckboxes(),0);
		}
		else if (e.getMsg().equals(COURSE_ADDED_TO_SCHEDULE)) {
			viewer.addSlotTOschedule(model.getInokedSlots());
			viewer.disableAndEnableCoursesCB(model.getImpossibleCourses());
		}
		else if (e.getMsg().equals(COURSE_CHECKBOX_DEACTIVATED)) {
			model.removeCourseFromSchedule(viewer.getInvokingCourseCheckboxes());
		}
		else if (e.getMsg().equals(COURSE_REMOVED_FROM_SCHEDULE)) {
			viewer.removeSlotFromschedule(model.getInokedSlots());
		}
	}

	private void createNewShow(IView source) {
		model.createNewShow(source.getCreatingCourseCode(),source.getSlotsInput());
	}

	private void createNewCourse(IView source) {
		model.createNewCourse(source.getCourseInput());
	}

	public void addViewer(ScheduleJFX viewer) {
		this.viewer = viewer;

	}

    
	

}
