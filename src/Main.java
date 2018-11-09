
import exceptions.EndingTimeBeforeStartingTimeException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) throws EndingTimeBeforeStartingTimeException {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ScheduleJFX app = new ScheduleJFX(primaryStage);

	}

	public static Controller createController(ScheduleJFX viewer) {
		Model model = new Model();
		Controller controller = new Controller(model);
		controller.addViewer(viewer);

		return controller;
	}

}
