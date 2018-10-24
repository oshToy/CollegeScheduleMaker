


import java.util.ArrayList;

import com.sun.javafx.scene.layout.region.BorderImageSlices;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ScheduleJFX implements IView {
	public static final int INITIAL_HOUR_OF_SCHEDULE=7;
	public static final int LAST_HOUR_OF_SCHEDULE=24;
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
	private SlotInputObjects slotComboBoxandTextField[];
	private String slotsInput[][];

	
	//buttons
	private Button btnMakeCourse=new Button("Create course !");;
	private Button btnDoneMakingCourses=new Button("I'm done create all courses !");
	private Button btnDoneMakingSingalCourse=new Button("I'm done Create course !");
	private Button btnDoneMakingShow=new Button("I'm done create show!");
	private Button btnDoneMakingSlot=new Button("I'm done create slots!");
	
	
	
	//days checkboxes
	private CheckBox sunday;
	private CheckBox monday;
	private CheckBox tuesday;
	private CheckBox wednesdaye;
	private CheckBox thursday;
	private CheckBox friday;
	
	//days checkboxes
	private Label []hoursCheckBoxes;
	
	//shedule buttons
	private Button[][]scheduleButtons;
	
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
			primaryStage.setFullScreen(true);
			primaryStage.setResizable(false);
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
	
	private void scheduleButtonsUnAvctive() {
		invokeListeners(Controller.SCHEDULE_BUTTON_UN_ACTIVE);

	}
	private void doneMakingMakingSingalCourseAction() {
		invokeListeners(Controller.DONE_CREATE_COURSE_VIEWER);

	}
	private void makeCourseButtonAction() {
		invokeListeners(Controller.CREATE_COURSE_VIEWER);
	}
	private void doneMakingCoursesButtonAction() {
		invokeListeners(Controller.DONE_CREATE_ALL_COURSES_VIEWER);
	}
	private void doneMakingShowAction() {
		invokeListeners(Controller.DONE_CREATE_SHOW_VIEWER);
	}
	private void doneMakingSlotAction() {
		invokeListeners(Controller.DONE_CREATE_SLOTS_VIEWER);

	}
	@Override
	public String[][] getSlotsInput() {
		slotsInput=new String[getNumberOfSlots()][NUMBER_OF_INPUTS_PER_SLOT]; //should be for textfield and check box ;
		for (int i = 0; i < getNumberOfSlots(); i++) {

			slotsInput[i][0]=(String) slotComboBoxandTextField[i].getDayComboBox().getValue();
			slotsInput[i][1]=(String) slotComboBoxandTextField[i].getStartTimeComboBox().getValue();
			slotsInput[i][2]=(String) slotComboBoxandTextField[i].getFinishTimeComboBox().getValue();
			slotsInput[i][3]=slotComboBoxandTextField[i].getRoomNumber().getText();
			slotsInput[i][4]=slotComboBoxandTextField[i].getLecturerName().getText();
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
	public Node schedulePane() {
		GridPane mainPane = new GridPane();
		mainPane.setPadding(new Insets(30,30,10,100));
		mainPane.setMinSize(1080, 720); 
		mainPane.setVgap(7);
		mainPane.setVgap(18);
		sunday=new CheckBox(IDay.Day.Sunday.toString());
		daysTitleUI(sunday);
		mainPane.add(sunday, 1, 0);
		monday=new CheckBox(IDay.Day.Monday.toString());
		daysTitleUI(monday);
		mainPane.add(monday, 2, 0);
		tuesday=new CheckBox(IDay.Day.Tuesday.toString());
		daysTitleUI(tuesday);
		mainPane.add(tuesday, 3, 0);
		wednesdaye=new CheckBox(IDay.Day.Wednesday.toString());
		daysTitleUI(wednesdaye);
		wednesdaye.setPadding(new Insets(0,50,0,0));
		mainPane.add(wednesdaye, 4, 0);
		thursday=new CheckBox(IDay.Day.Thursday.toString());
		daysTitleUI(thursday);	
		mainPane.add(thursday, 5, 0);
		friday=new CheckBox(IDay.Day.Friday.toString());
		daysTitleUI(friday);
		mainPane.add(friday, 6, 0);
		hoursCheckBoxes=new Label[18];
		for (int i = INITIAL_HOUR_OF_SCHEDULE; i < LAST_HOUR_OF_SCHEDULE; i++) {
			Label tempHourBox=new Label(i+" - "+(i+1));
			tempHourBox.setPadding(new Insets(0,20,0,0));
			tempHourBox.getStyleClass().add("scheduleTime");
			hoursCheckBoxes[i-INITIAL_HOUR_OF_SCHEDULE]=tempHourBox;
			mainPane.add(tempHourBox, 0, i-INITIAL_HOUR_OF_SCHEDULE+1,1,1);
		}
		scheduleButtons=new Button[6][LAST_HOUR_OF_SCHEDULE-INITIAL_HOUR_OF_SCHEDULE];
				
		for (int i=0;i<6;i++){
			for(int j=0;j<LAST_HOUR_OF_SCHEDULE-INITIAL_HOUR_OF_SCHEDULE;j++){
			Button tempButton=new Button();
			tempButton.getStyleClass().add("scheduleButtonActive");
			tempButton.setPadding(new Insets(0,15,0,15));
			tempButton.setMinWidth(150);
			tempButton.setMinHeight(40);
			System.out.println(tempButton.getStyleClass().get(1));
			scheduleButtons [i][j].setOnAction(e -> scheduleButtonsUnAvctive());
			scheduleButtons[i][j]=tempButton;
			mainPane.add(tempButton,i+1,j+1);
			}
		}
		
		return mainPane;
	}
	private void daysTitleUI(CheckBox dayCheckBox) {
		dayCheckBox.setPadding(new Insets(0,80,0,0));
		dayCheckBox.setSelected(true);
		dayCheckBox.getStyleClass().add("scheduleDays");
		
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
		slotComboBoxandTextField=new SlotInputObjects [amountOfSlots];
		VBox slotPane=new VBox(20);
		slotPane.setPadding(new Insets(DEFAULT_PADDING));
		Label newShowLab=new Label("Creating new Slots");
		newShowLab.setFont(new Font(25));
		newShowLab.setUnderline(true);
		slotPane.getChildren().add(newShowLab);
		for (int i = 0; i < amountOfSlots; i++) {
			slotComboBoxandTextField[i]=new SlotInputObjects();
			HBox slotInputPane = new HBox(5);
			ComboBox dayComboBox = new ComboBox<>(dayObservableList());
			dayComboBox.setPromptText("Choose day");
			slotComboBoxandTextField[i].setDayComboBox(dayComboBox);
			ComboBox startTimeComboBox = new ComboBox<>(TimeObservableList());
			startTimeComboBox.setPromptText("Start time");
			slotComboBoxandTextField[i].setStartTimeComboBox(startTimeComboBox);
			ComboBox finishTimeComboBox = new ComboBox<>(TimeObservableList());
		    finishTimeComboBox.setPromptText("Finish time");
		    slotComboBoxandTextField[i].setFinishTimeComboBox(finishTimeComboBox);
		    TextField roomNumber = new TextField();
			Label roomNumberLab = new Label("Room Number : ");
			slotComboBoxandTextField[i].setRoomNumber(roomNumber);
			TextField lecturerName = new TextField();
			Label lecturerNameLab = new Label("Lecturer name : ");
			slotComboBoxandTextField[i].setLecturerName(lecturerName);
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
public void slotTimingException(int slotNumber) {
	clearAllSlotStyleExceptOne(slotNumber);
	slotComboBoxandTextField[slotNumber].getRoomNumber().getStyleClass().removeAll("errorTextField");
	slotComboBoxandTextField[slotNumber].getLecturerName().getStyleClass().removeAll("errorTextField");
	slotComboBoxandTextField[slotNumber].getStartTimeComboBox().getStyleClass().add("errorTextField");
	slotComboBoxandTextField[slotNumber].getFinishTimeComboBox().getStyleClass().add("errorTextField");
	
}
@Override
public void roomFullException(int slotNumber) {
	roomSlotException(slotNumber);
	
}
@Override
public void roomInputIsntAint(int slotNumber) {
	roomSlotException(slotNumber);
	
}
@Override
public void teacherTeachingException(int slotNumber) {
	clearAllSlotStyleExceptOne(slotNumber);
	slotComboBoxandTextField[slotNumber].getStartTimeComboBox().getStyleClass().removeAll("errorTextField");
	slotComboBoxandTextField[slotNumber].getFinishTimeComboBox().getStyleClass().removeAll("errorTextField");
	slotComboBoxandTextField[slotNumber].getRoomNumber().getStyleClass().removeAll("errorTextField");
	slotComboBoxandTextField[slotNumber].getLecturerName().getStyleClass().add("errorTextField");
	slotComboBoxandTextField[slotNumber].getLecturerName().setText("Teacher teaching those hours");
}
private void clearAllSlotStyleExceptOne(int slotNumber) {
	for (int i = 0; i < slotComboBoxandTextField.length; i++) {
		if(i!=slotNumber){
		slotComboBoxandTextField[i].getStartTimeComboBox().getStyleClass().removeAll("errorTextField");
		slotComboBoxandTextField[i].getFinishTimeComboBox().getStyleClass().removeAll("errorTextField");
		slotComboBoxandTextField[i].getRoomNumber().getStyleClass().removeAll("errorTextField");
		slotComboBoxandTextField[i].getLecturerName().getStyleClass().removeAll("errorTextField");
		slotComboBoxandTextField[i].getDayComboBox().getStyleClass().removeAll("errorTextField");
		}
	}
	
}
private void roomSlotException(int slotNumber){
	clearAllSlotStyleExceptOne(slotNumber);
	slotComboBoxandTextField[slotNumber].getStartTimeComboBox().getStyleClass().removeAll("errorTextField");
	slotComboBoxandTextField[slotNumber].getFinishTimeComboBox().getStyleClass().removeAll("errorTextField");
	slotComboBoxandTextField[slotNumber].getLecturerName().getStyleClass().removeAll("errorTextField");
	slotComboBoxandTextField[slotNumber].getRoomNumber().getStyleClass().add("errorTextField");
	slotComboBoxandTextField[slotNumber].getLecturerName().setText("Room occupied those hours");

}
public void diactiveCollorButton(Button button) {
	button.getStylesheets().clear();
	button.getStylesheets().add("scheduleButtonUnactive");
}

public void activeCollorButton(Button button) {
	button.getStylesheets().clear();
	button.getStylesheets().add("scheduleButtonActive");
}
public boolean isButoonActive(Button button) {
	if(button.getStylesheets().get(1).equals("scheduleButtonActive")) {
		return true;
	}
	return false;
	}

}