
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

public class ScheduleJFX implements IView {
	public static final int NUMBER_OF_INPUTS_PER_COURSE=3;
	public static final int NUMBER_OF_INPUTS_PER_SHOW=1;
	public static final int NUMBER_OF_INPUTS_PER_SLOT=5;
	public static final int DEFAULT_SLOTS_AMOUNT=1;
	//course  input
	private TextField courseId;
	private TextField courseName;
	private TextField courseAmountOfShows;
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
	
	private TextField slotsInputFromTextField[][];
	private  BorderPane mainPane;
	
	private ArrayList<ActionListener> listeners = new ArrayList<ActionListener>();
	
	public ScheduleJFX(Stage primaryStage){
		init();
		primaryStage.setTitle("Contacts Manager By Java FX");
		  mainPane=new BorderPane();
			Scene mainScene=new Scene(mainPane,1080,520);
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
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).actionPerformed(new java.awt.event.ActionEvent(this, -1, Controller.DONE_CREATE_COURSE));
		}
	}
	public void makeCourseButtonAction() {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).actionPerformed(new java.awt.event.ActionEvent(this, -1, Controller.CREATE_COURSE));
		}
	}
	public void doneMakingCoursesButtonAction() {
		//mainPane.setCenter(createNewShowPane());
	}
	public void doneMakingShowAction() {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).actionPerformed(new java.awt.event.ActionEvent(this, -1, Controller.DONE_CREATE_SHOW));
		}
	}
	public void doneMakingSlotAction() {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.get(i).actionPerformed(new java.awt.event.ActionEvent(this, -1, Controller.DONE_CREATE_SLOTS));
		}
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
			VBox coursePane=new VBox();
			HBox CourseInputPane = new HBox(5);
			CourseInputPane.setPadding(new Insets(10));
			courseId = new TextField("Course Id... ");
			Label courseIdLab = new Label("Course Id : ");
			courseName = new TextField("Course Name...");
			Label courseNameLab = new Label("Course Name : ");
			courseAmountOfShows = new TextField("Amount of Show... ");
			Label courseAmountOfShowsLab = new Label("Amount of Show :");
			CourseInputPane.getChildren().addAll(courseIdLab,courseId
				,courseNameLab,courseName,courseAmountOfShowsLab,courseAmountOfShows);
			coursePane.getChildren().addAll(CourseInputPane,btnDoneMakingSingalCourse);
			return coursePane;
	}

	@Override
	public Node createNewShowPane() {
		VBox showPane=new VBox(20);
		HBox slotPane=new HBox(7);
		numberOfSlotsComboBox = new ComboBox<>(numberOfSlotsObservableList());
		Label numberOfSlotsLab=new Label("Amount of slots");
		numberOfSlotsComboBox.setValue(DEFAULT_SLOTS_AMOUNT);
		slotPane.getChildren().addAll(numberOfSlotsLab,numberOfSlotsComboBox);
		showPane.getChildren().addAll(slotPane,btnDoneMakingShow);
		return showPane;
	}
	public String[] getShowInput(){
		String []showInput=new String[NUMBER_OF_INPUTS_PER_SHOW];
		showInput[0]=courseId.getText();
		return showInput;
	}
	@Override
	public Node createNewSlotPane(int amountOfSlots) {
		slotsInputFromTextField=new TextField[amountOfSlots][NUMBER_OF_INPUTS_PER_SLOT]; //should be for textfield and check box ;
		VBox slotPane=new VBox(7);
		for (int i = 0; i < amountOfSlots; i++) {
			HBox slotInputPane = new HBox(5);
			dayComboBox = new ComboBox<>(dayObservableList());
			dayComboBox.setPromptText("Choose day");
			startTimeComboBox = new ComboBox<>(TimeObservableList());
			startTimeComboBox.setPromptText("Start time");
			finishTimeComboBox = new ComboBox<>(TimeObservableList());
		    finishTimeComboBox.setPromptText("Finish time");
			roomNumber = new TextField("Room Number...");
			Label roomNumberLab = new Label("Room Number : ");
			lecturerName = new TextField("Lecturer name...");
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
	public void registerListener(ActionListener listener) {
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
		courseInput[2]=courseAmountOfShows.getText();
		return courseInput;
	}
}
