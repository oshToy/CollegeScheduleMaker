import java.awt.event.ActionListener;
import java.util.ArrayList;

import exceptions.CourseCodeAlreadyExistException;
import exceptions.CourseNameAlreadyExistException;
import exceptions.EndingTimeBeforeStartingTimeException;
import exceptions.RoomFullException;
import exceptions.TeacherTeachingException;
import exceptions.courseNotExistException;
import javafx.event.Event;
import javafx.event.EventHandler;

public class Model implements IModel {

	private ArrayList<EventHandler<MyActionEvent>> listeners = new ArrayList<>();
	private AllCourses allCourses = new AllCourses();
	private Schedule schedule = new Schedule();

	@Override
	public void createNewCourse(String[] courseInput) {
		try {
			allCourses.addCourse(toIntFromString(courseInput[0]), courseInput[1]);
			invokeListeners(Controller.DONE_CREATE_COURSE_MODEL);
		} catch (CourseCodeAlreadyExistException | NumberFormatException e) {
			invokeListeners(Controller.COURSE_CODE_ALREADY_EXIST_ERROR);
		} catch (CourseNameAlreadyExistException e) {
			invokeListeners(Controller.COURSE_NAME_ALREADY_EXIST_ERROR);
		}
	}

	@Override
	public void registerListener(EventHandler<MyActionEvent> e) {
		listeners.add(e);

	}

	@Override
	public void createNewShow(int courseCode, String[][] slotsInput) {
		int showId;
		Course course;

			try {

			showId = allCourses.getCourseById(courseCode).getShows().size();
			allCourses.addShow(courseCode, showId);
			for (int i = 0; i < slotsInput.length; i++) {
				IDay.Day day = IDay.dayByString(slotsInput[i][0]);

				int startingTime = toIntFromString(slotsInput[i][1]);

				int endingTime = toIntFromString(slotsInput[i][2]);
				int numberOfRoom=0;
				try{
				numberOfRoom = toIntFromString(slotsInput[i][3]);
				} catch (NumberFormatException e) {
					invokeListeners(Controller.ROOM_INPUT_ISNT_INTEGER);
				}
				String nameOfLect = slotsInput[i][4];
				try {
					try {
						allCourses.addShow(courseCode, showId);
						allCourses.addSlot(courseCode, showId, i, day, startingTime, endingTime, numberOfRoom, nameOfLect);
						invokeListeners(Controller.DONE_CREATE_SLOTS_MODEL);
					} catch (RoomFullException e) {
						invokeListeners(Controller.ROOM_FULL_EROOR);
					} catch (TeacherTeachingException e) {
						invokeListeners(Controller.TEACHER_ALREADY_TEACHING_ERROR);
					}
				} catch (EndingTimeBeforeStartingTimeException e) {
					invokeListeners(Controller.TIMING_ERROR);
				}
			}
			} catch (courseNotExistException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		

	}

	static public int toIntFromString(String intAsString) throws NumberFormatException{
		return Integer.parseInt(intAsString);
	}

	private void invokeListeners(String command) {
		for (EventHandler<MyActionEvent> listener : listeners) {
			listener.handle(new MyActionEvent(this, command));
		}
	}

}
