

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ScheduleJFX implements IView {
	public static final int NUMBER_OF_INPUTS_PER_COURSE=3;
	public static final int NUMBER_OF_INPUTS_PER_SHOW=1;
	public static final int NUMBER_OF_INPUTS_PER_SLOT=5;
	public static final String DEFAULT_SLOTS_AMOUNT="1";
	private static final double DEFAULT_PADDING = 10;
	
	
	//course  input
	private TextField courseId;
	private TextField courseName;
	private int creatingCourseCode;
	//show  input
	private ComboBox numberOfSlotsComboBox;
	
	//slot input

	private ComboBox dayComboBox;
	private ComboBox startTimeComboBox;
	private ComboBox finishTimeComboBox;
	private TextField roomNumber;
	private TextField lecturerName;

	
	//buttons
	private Button btnMakeCourse=new Button("Create course !");;
	private Button btnDoneMakingCourses=new Button("I'm done create all courses !");
	private Button btnDoneMakingSingalCourse=new Button("I'm done Create course !");
	private Button btnDoneMakingShow=new Button("I'm done create show!");
	private Button btnDoneMakingSlot=new Button("I'm done create slots!");
	
	private String slotsInput[][];
	private  BorderPane mainPane;
	
	private ArrayList<EventHandler<MyActionEvent>> listeners = new ArrayList<>();
	
	public ScheduleJFX(Stage primaryStage){
		init();
		primaryStage.setTitle("Contacts Manager By Java FX");
		  mainPane=new BorderPane();
			Scene mainScene=new Scene(mainPane,1080,520);
			mainScene.getStylesheets().add("MyStyle.css");
			setMainPane(courseMenuPane());
			primaryStage.setScene(mainScene);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.show();
	}
	private void init(){
		registerListener(Main.createController(this));
		btnMakeCourse.setOnAction(e -> makeCourseButtonAction());
		btnDoneMakingCourses.setOnAction(e -> doneMakingCoursesButtonAction());
		btnDoneMakingSingalCourse.setOnAction(e -> doneMakingMakingSingalCourseAction());
		btnDoneMakingShow.setOnAction(e -> doneMakingShowAction());
		btnDoneMakingSlot.setOnAction(e -> doneMakingSlotAction());
	}
	private void doneMakingMakingSingalCourseAction() {
		invokeListeners(Controller.DONE_CREATE_COURSE_VIEWER);

	}
	private void makeCourseButtonAction() {
		invokeListeners(Controller.CREATE_COURSE_VIEWER);
	}
	private void doneMakingCoursesButtonAction() {
		//mainPane.setCenter(createNewShowPane());
	}
	private void doneMakingShowAction() {
		invokeListeners(Controller.DONE_CREATE_SHOW_VIEWER);
	}
	private void doneMakingSlotAction() {
		invokeListeners(Controller.DONE_CREATE_SLOTS_VIEWER);

	}
	@Override
	public String[][] getSlotsInput(int numberOfSlots) {
		slotsInput=new String[numberOfSlots][NUMBER_OF_INPUTS_PER_SLOT]; //should be for textfield and check box ;
		for (int i = 0; i < numberOfSlots; i++) {
		slotsInput[i][0]=(String) dayComboBox.getValue();
		slotsInput[i][1]=(String) startTimeComboBox.getValue();
		slotsInput[i][2]=(String) finishTimeComboBox.getValue();
		slotsInput[i][3]=roomNumber.getText();
		slotsInput[i][4]=lecturerName.getText();
		}
		return slotsInput;
	}
	@Override
	public Node courseMenuPane() {
		HBox topPane = new HBox(60);
		topPane.setPadding(new Insets(120));		
		btnMakeCourse.setPadding(new Insets(20));
		btnDoneMakingCourses.setPadding(new Insets(20));
		topPane.getChildren().addAll(btnMakeCourse,btnDoneMakingCourses);
		
		return topPane;
	}
	@Override
	public Node createNewCoursePane() {
	
			VBox coursePane=new VBox(10);
			HBox CourseInputPane = new HBox(5);
			coursePane.setPadding(new Insets(DEFAULT_PADDING));
			CourseInputPane.setPadding(new Insets(DEFAULT_PADDING));
			Label newCourseLab=new Label("Creating new Course");
			newCourseLab.setFont(new Font(25));
			newCourseLab.setUnderline(true);
			courseId = new TextField();
			Label courseIdLab = new Label("Course Id : ");
			courseName = new TextField();
			Label courseNameLab = new Label("Course Name : ");
			CourseInputPane.getChildren().addAll(courseIdLab,courseId
				,courseNameLab,courseName);
			coursePane.getChildren().addAll(newCourseLab,CourseInputPane,btnDoneMakingSingalCourse);
			return coursePane;
	}

	@Override
	public Node createNewShowPane(int courseCode) {
		this.creatingCourseCode=courseCode;
		VBox showPane=new VBox(20);
		HBox slotPane=new HBox(7);
		showPane.setPadding(new Insets(DEFAULT_PADDING));
		numberOfSlotsComboBox = new ComboBox<>(numberOfSlotsObservableList());
		Label newShowLab=new Label("Creating new Show");
		newShowLab.setFont(new Font(25));
		newShowLab.setUnderline(true);
		Label numberOfSlotsLab=new Label("Amount of slots");
		numberOfSlotsComboBox.setValue(DEFAULT_SLOTS_AMOUNT);
		slotPane.getChildren().addAll(numberOfSlotsLab,numberOfSlotsComboBox);
		showPane.getChildren().addAll(newShowLab,slotPane,btnDoneMakingShow);
		return showPane;
	}
	public int getCreatingCourseCode() {
		return creatingCourseCode;
	}
	@Override
	public String[] getShowInput(){
		String []showInput=new String[NUMBER_OF_INPUTS_PER_SHOW];
		showInput[0]=courseId.getText();
		return showInput;
	}
	@Override
	public Node createNewSlotPane(int amountOfSlots) {
		VBox slotPane=new VBox(20);
		slotPane.setPadding(new Insets(DEFAULT_PADDING));
		Label newShowLab=new Label("Creating new Slots");
		newShowLab.setFont(new Font(25));
		newShowLab.setUnderline(true);
		slotPane.getChildren().add(newShowLab);
		for (int i = 0; i < amountOfSlots; i++) {
			HBox slotInputPane = new HBox(5);
			dayComboBox = new ComboBox<>(dayObservableList());
			dayComboBox.setPromptText("Choose day");
			startTimeComboBox = new ComboBox<>(TimeObservableList());
			startTimeComboBox.setPromptText("Start time");
			finishTimeComboBox = new ComboBox<>(TimeObservableList());
		    finishTimeComboBox.setPromptText("Finish time");
			roomNumber = new TextField();
			Label roomNumberLab = new Label("Room Number : ");
			lecturerName = new TextField();
			Label lecturerNameLab = new Label("Lecturer name : ");
			slotInputPane.getChildren().addAll(dayComboBox,startTimeComboBox,finishTimeComboBox,roomNumberLab,roomNumber,lecturerNameLab,lecturerName);
			slotPane.getChildren().add(slotInputPane);

			
		}
		
		slotPane.getChildren().add(btnDoneMakingSlot);
		return slotPane;
	}

	private ObservableList numberOfSlotsObservableList() {
		ObservableList<String> hourOptions = 
			    FXCollections.observableArrayList(
			        "1","2","3","4","5"
			    );
		return hourOptions;
	}
	private ObservableList TimeObservableList() {
		ObservableList<String> hourOptions = 
			    FXCollections.observableArrayList(
			        "7","8","9","10","11","12","13","14","15","16","17","18"
			        ,"19","20","21","22","23","24"
			    );
		return hourOptions;
	}

	private ObservableList dayObservableList() {
		ObservableList<String> dayOptions = 
			    FXCollections.observableArrayList(
			        "Sunday",
			        "Monday",
			        "Tuesday",
			        "Wednesday",
			        "Thursday",
			        "Friday",
			        "Saturday"
			    );
		return dayOptions;
	}
	public void registerListener(EventHandler<MyActionEvent> listener) {
		listeners.add(listener);
	}

	@Override
	public void setMainPane(Node Pane) {
		mainPane.setCenter(Pane);
		
	}
	@Override
	public int getNumberOfSlots() {
		Integer num = new Integer((String)numberOfSlotsComboBox.getValue());
		return num.intValue();
	}
	public String[] getCourseInput(){
		String [] courseInput=new String[NUMBER_OF_INPUTS_PER_COURSE];
		courseInput[0]=courseId.getText();
		courseInput[1]=courseName.getText();
		return courseInput;
	}
	@Override
	public void courseCodeException() {
		courseId.getStyleClass().add("errorTextField");
		courseId.setText("Course code already exist");
		
	}
	@Override
	public void courseNameException() {
		courseId.getStyleClass().removeAll("errorTextField");
		courseName.getStyleClass().add("errorTextField");
		courseName.setText("Course name already exist");

		
	}
	private void invokeListeners(String command){
		for (EventHandler<MyActionEvent> listener: listeners) {
			listener.handle(new MyActionEvent(this, command));
		}
	}
	@Override
	public void slotTimingException() {
		roomNumber.getStyleClass().removeAll("errorTextField");
		lecturerName.getStyleClass().removeAll("errorTextField");
		startTimeComboBox.getStyleClass().add("errorTextField");
		finishTimeComboBox.getStyleClass().add("errorTextField");
		
	}
	@Override
	public void roomFullException() {
		roomSlotException();
		
	}
	@Override
	public void teacherTeachingException() {
		startTimeComboBox.getStyleClass
		().removeAll("errorTextField");
		finishTimeComboBox.getStyleClass().removeAll("errorTextField");
		roomNumber.getStyleClass().removeAll("errorTextField");
		lecturerName.getStyleClass().add("errorTextField");
		
	}
	@Override
	public void roomInputIsntAint() {
		roomSlotException();
		
	}
	private void roomSlotException(){
		startTimeComboBox.getStyleClass().removeAll("errorTextField");
		finishTimeComboBox.getStyleClass().removeAll("errorTextField");
		lecturerName.getStyleClass().removeAll("errorTextField");
		roomNumber.getStyleClass().add("errorTextField");
	}
}