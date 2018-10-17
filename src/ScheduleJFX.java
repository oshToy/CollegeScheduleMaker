import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScheduleJFX {
	final int NUMBER_OF_INPUTS_PER_SLOT=5;
	private TextField slotsInputFromTextField[][];
	public ScheduleJFX(Stage primaryStage){
		primaryStage.setTitle("Contacts Manager By Java FX");
		 BorderPane mainPane=new BorderPane();
			Scene mainScene=new Scene(mainPane,1080,520);
			mainPane.setCenter(createNewSlotPane(5));
			primaryStage.setScene(mainScene);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.show();
	}

	private Node courseMenuPane() {
		HBox topPane = new HBox(60);
		topPane.setPadding(new Insets(120));		
		Button btnMakeCourse=new Button("Create course !");
		btnMakeCourse.setPadding(new Insets(20));
		Button btnDoneMakingCourses=new Button("I'm done !");
		btnDoneMakingCourses.setPadding(new Insets(20));
		topPane.getChildren().addAll(btnMakeCourse,btnDoneMakingCourses);
		
		return topPane;
	}
	private Node createNewCoursePane() {
			VBox coursePane=new VBox();
			HBox CourseInputPane = new HBox(5);
			CourseInputPane.setPadding(new Insets(10));
			TextField courseId = new TextField("Course Id... ");
			Label courseIdLab = new Label("Course Id : ");
			TextField courseName = new TextField("Course Name...");
			Label courseNameLab = new Label("Course Name : ");
			TextField courseAmountOfShows = new TextField("Amount of Show... ");
			Label courseAmountOfShowsLab = new Label("Amount of Show :");
			CourseInputPane.getChildren().addAll(courseIdLab,courseId
				,courseNameLab,courseName,courseAmountOfShowsLab,courseAmountOfShows);
			Button btnDoneMakingCourses=new Button("I'm done !");
			coursePane.getChildren().addAll(CourseInputPane,btnDoneMakingCourses);
			return coursePane;
	}
	private Node createNewShowPane() {
		VBox showPane=new VBox();
		HBox showInputPane = new HBox(5);
		TextField courseAmountOfSlots = new TextField("Amount of slots...");
		Label courseAmountOfSlotsLab = new Label("Amount of slots : ");
		showInputPane.getChildren().addAll(courseAmountOfSlotsLab,courseAmountOfSlots);
		Button btnDoneMakingShow=new Button("I'm done !");
		showPane.getChildren().addAll(showInputPane,btnDoneMakingShow);
		return showPane;
	}
	private Node createNewSlotPane(int amountOfSlots) {
		slotsInputFromTextField=new TextField[amountOfSlots][NUMBER_OF_INPUTS_PER_SLOT]; //should be for textfield and check box ;
		VBox slotPane=new VBox();
		for (int i = 0; i < amountOfSlots; i++) {
			HBox slotInputPane = new HBox(5);
			TextField courseAmountOfSlots = new TextField("Amount of slots...");
			Label courseAmountOfSlotsLab = new Label("Amount of slots : ");
			slotInputPane.getChildren().addAll(courseAmountOfSlotsLab,courseAmountOfSlots);
			slotPane.getChildren().add(slotInputPane);

		}
		
		Button btnDoneMakingShow=new Button("I'm done !");
		slotPane.getChildren().add(btnDoneMakingShow);
		return slotPane;
	}
	
}
