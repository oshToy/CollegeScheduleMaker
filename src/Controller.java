import javafx.event.EventHandler;


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
	public static final String SCHEDULE_BUTTON_UNACTIVE_VIEWER = " schedule button is changed to unactive";
	public static final String SCHEDULE_BUTTON_ACTIVE_VIEWER = "schedule button is changed to active";
	public static final String DAY_CHECKBOX_ACTIVATED_VIEWER = "day checkbox activated viewer";
	public static final String DAY_CHECKBOX_ACTIVATED_MODEL = "day checkbox activated model";
	public static final String DAY_CHECKBOX_DEACTIVATED_VIEWER = "day checkbox deactivated viewer";
	public static final String DAY_CHECKBOX_DEACTIVATED_MODEL = "day checkbox deactivated model";
	public static final String COURSE_CHECKBOX_ACTIVATED = "courseCB activated";
	public static final String COURSE_CHECKBOX_DEACTIVATED = "courseCB deactivated";
	public static final String COURSE_ADDED_TO_SCHEDULE = "course add to schedule";
	public static final String COURSE_REMOVED_FROM_SCHEDULE = "course remove from schedule";
	public static final String CREATE_ANOTHER_SHOW_VIEWER = "create another show viewer";
	public static final String CREATE_ANOTHER_SHOW_MODEL = "create another show model";
	public static final String SCHEDULE_BUTTON_UNACTIVE_MODEL = "schedule button is changed to unactive MODEL";
	public static final String SCHEDULE_BUTTON_ACTIVE_MODEL = "schedule button is changed to active MODEL";


	
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
		else if (e.getMsg().equals(DAY_CHECKBOX_ACTIVATED_VIEWER)){
			model.addPossibleShowsByDay(viewer.getInvokingDayNumber());
		}
		else if (e.getMsg().equals(DAY_CHECKBOX_ACTIVATED_MODEL)){
			viewer.changeColumnToActiveColor(viewer.getInvokingDayNumber());
			viewer.ableCoursesCBByDay(model.getImpossibleCourses(),viewer.getInvokingDayNumber());
		}

		else if (e.getMsg().equals(DAY_CHECKBOX_DEACTIVATED_VIEWER)){
			model.removeShowsByDay(viewer.getInvokingDayNumber());
		}
		else if (e.getMsg().equals(DAY_CHECKBOX_DEACTIVATED_MODEL)){
			viewer.changeColumnToDeactiveColor(viewer.getInvokingDayNumber());
			viewer.disableCoursesCBByDay(model.getImpossibleCourses(),viewer.getInvokingDayNumber());
		}
		else if (e.getMsg().equals(COURSE_CHECKBOX_ACTIVATED)) {
			model.addCourseToSchedule(viewer.getInvokingCourseCheckboxes());

		}
		else if (e.getMsg().equals(COURSE_ADDED_TO_SCHEDULE)) {
			viewer.addSlotTOschedule(model.getInokedSlots());
			viewer.disableAndEnableCoursesCB(model.getImpossibleCourses());
		}
		else if (e.getMsg().equals(COURSE_REMOVED_FROM_SCHEDULE)) {
			viewer.removeSlotFromschedule(model.getInokedSlots());
			viewer.disableAndEnableCoursesCB(model.getImpossibleCourses());
		}
		else if (e.getMsg().equals(COURSE_CHECKBOX_DEACTIVATED)) {
			
			model.removeCourseFromSchedule(viewer.getInvokingCourseCheckboxes());			
		}
		else if (e.getMsg().equals(SCHEDULE_BUTTON_UNACTIVE_VIEWER)) {
			model.removeShowsByHour(viewer.getButtonInvoke());
		}
		else if (e.getMsg().equals(SCHEDULE_BUTTON_UNACTIVE_MODEL)) {
			viewer.disableCoursesCBByHour(model.getImpossibleCourses());
		}
		else if (e.getMsg().equals(SCHEDULE_BUTTON_ACTIVE_VIEWER)) {
			model.addPossibleShowsByHour(viewer.getButtonInvoke());
		}
		
		else if (e.getMsg().equals(SCHEDULE_BUTTON_ACTIVE_MODEL)) {
			viewer.ableCoursesCBByHour(model.getImpossibleCourses());
		}
		else if (e.getMsg().equals(CREATE_ANOTHER_SHOW_VIEWER)) {
			createAnotherShow((IView) e.getSource());
		}
		else if (e.getMsg().equals(CREATE_ANOTHER_SHOW_MODEL)) {
			viewer.setMainPane(viewer.createNewShowPane(Model.toIntFromString(viewer.getCourseInput()[0])));
		}
	}
	

	private void createAnotherShow(IView source) {
		model.createAnotherShow(source.getCreatingCourseCode(),source.getSlotsInput());
		
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
