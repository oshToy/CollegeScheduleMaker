import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import exceptions.CourseCodeAlreadyExistException;
import exceptions.CourseNameAlreadyExistException;
import exceptions.EndingTimeBeforeStartingTimeException;
import exceptions.RoomFullException;
import exceptions.TeacherTeachingException;
import exceptions.courseNotExistException;
import javafx.event.EventHandler;

public class Model implements IModel {

	private ArrayList<EventHandler<MyActionEvent>> listeners = new ArrayList<>();
	private AllCourses allCourses = new AllCourses();
	private Schedule schedule = new Schedule();
	private ArrayList<Course> impossibleCoursesByCourseTiming = new ArrayList<>();
	private HashMap<Integer, Course> impossibleCoursesByUserTiming = new HashMap<>();
	private HashSet<Integer> blockedDays = new HashSet<>();// nedd to by finals by
															// number of days
	private HashMap<Integer, HashSet<Integer>> blockedHours = new HashMap<>();// HashMap <days,HashSet<hours>>
	// TODO FOR TESTING ONLY!!!

	@Override
	public Model getModelForTestingOnly() {
		return this;
	}

	// TODO FOR TESTING ONLY!!!
	public AllCourses getAllCoursesForTestingOnly() {
		return allCourses;
	}

	// TODO FOR TESTING ONLY!!!
	public Schedule getScheduleForTestingOnly() {
		return schedule;
	}

	private int ivokingSlotNumber;
	private ISlot[] inokedSlots;
	private boolean createAnotherShow;

	public int setShowNumberPlusOne(int courseCode) {
		int showNumber = 0;
		try {
			showNumber = allCourses.getCourseById(courseCode).getShows().size();
		} catch (courseNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return showNumber;
	}

	@Override
	public void createNewCourse(String[] courseInput) {
		try {
			allCourses.addCourse(toIntFromString(courseInput[0]), courseInput[1]);
			invokeListeners(Controller.DONE_CREATE_COURSE_MODEL);
			return;
		} catch (CourseCodeAlreadyExistException | NumberFormatException e) {
			invokeListeners(Controller.COURSE_CODE_ALREADY_EXIST_ERROR);
			return;
		} catch (CourseNameAlreadyExistException e) {
			invokeListeners(Controller.COURSE_NAME_ALREADY_EXIST_ERROR);
			return;
		}
	}

	@Override
	public int getIvokingSlotNumber() {
		return ivokingSlotNumber;
	}

	private void setIvokingSlotNumber(int ivokingSlotNumber) {
		this.ivokingSlotNumber = ivokingSlotNumber;
	}

	@Override
	public void registerListener(EventHandler<MyActionEvent> e) {
		listeners.add(e);

	}

	@Override
	public void createNewShow(int courseCode, String[][] slotsInput) {
		try {

			int showNumber = setShowNumberPlusOne(courseCode);
			// avoiding create same show after return from error input
			if (allCourses.getCourseById(courseCode).getShowByShowCourse(showNumber) == null) {// IS
																								// the
																								// show
																								// already
																								// created
																								// ?
				allCourses.addShow(courseCode, showNumber);
			} else {
				allCourses.getCourseById(courseCode).getShowByShowCourse(showNumber).getSlots().clear();// override
																										// data
																										// from
																										// the
																										// slots
																										// input

			}
			for (int i = 0; i < slotsInput.length; i++) {
				IDay.Day day = IDay.dayByString(slotsInput[i][0]);
				int startingTime = toIntFromString(slotsInput[i][1]);
				int endingTime = toIntFromString(slotsInput[i][2]);
				int numberOfRoom = 0;
				try {
					numberOfRoom = toIntFromString(slotsInput[i][3]);
				} catch (NumberFormatException e) {
					setIvokingSlotNumber(i);
					invokeListeners(Controller.ROOM_INPUT_ISNT_INTEGER);
					return;
				}
				String nameOfLect = slotsInput[i][4];
				try {
					try {
						allCourses.addSlot(courseCode, showNumber, i, day, startingTime, endingTime, numberOfRoom,
								nameOfLect);
					} catch (RoomFullException e) {
						setIvokingSlotNumber(i);
						invokeListeners(Controller.ROOM_FULL_EROOR);
						return;
					} catch (TeacherTeachingException e) {
						setIvokingSlotNumber(i);
						invokeListeners(Controller.TEACHER_ALREADY_TEACHING_ERROR);
						return;
					}
				} catch (EndingTimeBeforeStartingTimeException e) {
					setIvokingSlotNumber(i);
					invokeListeners(Controller.TIMING_ERROR);
					return;
				}
			}
			if (createAnotherShow == false) {
				invokeListeners(Controller.DONE_CREATE_SLOTS_MODEL);
			} else {
				createAnotherShow = false;
				invokeListeners(Controller.CREATE_ANOTHER_SHOW_MODEL);
			}
			return;
		} catch (courseNotExistException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}

	}

	static public int toIntFromString(String intAsString) throws NumberFormatException {
		return Integer.parseInt(intAsString);
	}

	private void invokeListeners(String command) {
		for (EventHandler<MyActionEvent> listener : listeners) {
			listener.handle(new MyActionEvent(this, command));
		}
	}

	@Override
	public ICourse[] getAllCoursesForViewer() {
		ICourse[] collection = new ICourse[allCourses.getMapOfCourse().size()];
		allCourses.getMapOfCourse().values().toArray(collection);
		return collection;
	}

	@Override
	public void addCourseToSchedule(ICourse wantedCourse) {// if you get here ,
															// its posiible to
															// add that coourse
		try {

			schedule.addCourseToSchedule(allCourses.getCourseById(wantedCourse.getCourseCode()),
					(int) wantedCourse.getShowCodes().toArray()[0]);
			inokedSlots = new ISlot[allCourses.getCourseById(wantedCourse.getCourseCode())
					.getShowByShowCourse((int) wantedCourse.getShowCodes().toArray()[0]).getSlots().size()];
			allCourses.getCourseById(wantedCourse.getCourseCode())
					.getShowByShowCourse((int) wantedCourse.getShowCodes().toArray()[0]).getSlots()
					.toArray(inokedSlots);
			impossibleCourses();
			invokeListeners(Controller.COURSE_ADDED_TO_SCHEDULE);
		} catch (courseNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ISlot[] getInokedSlots() {
		return inokedSlots;
	}

	@Override
	public void removeCourseFromSchedule(ICourse wantedCourse) {
		try {
			inokedSlots = new ISlot[allCourses.getCourseById(wantedCourse.getCourseCode())
					.getShowByShowCourse((int) wantedCourse.getShowCodes().toArray()[0]).getSlots().size()];
			allCourses.getCourseById(wantedCourse.getCourseCode())
					.getShowByShowCourse((int) wantedCourse.getShowCodes().toArray()[0]).getSlots()
					.toArray(inokedSlots);
			schedule.removeCourseFromSchedule(allCourses.getCourseById(wantedCourse.getCourseCode()));
			impossibleCourses();
			invokeListeners(Controller.COURSE_REMOVED_FROM_SCHEDULE);
		} catch (courseNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void impossibleCourses() {
		ArrayList<Course> impossibleCoursesNew = new ArrayList<>();
		for (Course checkCourse : allCourses.getMapOfCourse().values()) {
			for (int i = 0; i < checkCourse.getShowCodes().size(); i++) {
				if (schedule.timeValidSlots(checkCourse, (int) checkCourse.getShowCodes().toArray()[i]) == false) {
					Course course = new Course(checkCourse.getCourseCode(), checkCourse.getCourseName());
					course.getShows().put(i, checkCourse.getShows().get(i));
					impossibleCoursesNew.add(course);
				}
			}
		}
		this.impossibleCoursesByCourseTiming = impossibleCoursesNew;
	}

	@Override
	public void createAnotherShow(int creatingCourseCode, String[][] slotsInput) {
		createAnotherShow = true;
		createNewShow(creatingCourseCode, slotsInput);

	}

	@Override
	public void addShowsByDay(int invokingDayNumber) {
		for (Course course : allCourses.getMapOfCourse().values()) {
			for (Show show : course.getShows().values()) {
				for (Slot iSlot : show.getSlots()) {
					int slotDay = IDay.intByDay(iSlot.getDay().toString());
					if (invokingDayNumber == slotDay) {
						schedule.removeCourseFromSchedule(course);
						Course impossibleCourse = new Course(course.getCourseCode(), course.getCourseName());
						course.getShows().put(show.getShowCode(), show);
						// impossibleCoursesByUserTiming.add(course);
					}
				}
			}
		}
		invokeListeners(Controller.DAY_CHECKBOX_DEACTIVATED_MODEL);
	}

	@Override
	public void removeShowsByDay(int invokingDayNumber) {
		for (Course course : allCourses.getMapOfCourse().values()) {
			for (Show show : course.getShows().values()) {
				for (Slot iSlot : show.getSlots()) {
					int slotDay = IDay.intByDay(iSlot.getDay().toString());
					if (invokingDayNumber == slotDay) {
						schedule.removeCourseFromSchedule(course);
						if (impossibleCoursesByUserTiming.get(course.getCourseCode()) == null) {
							Course impossibleCourse = new Course(course.getCourseCode(), course.getCourseName());
							impossibleCourse.getShows().put(show.getShowCode(), show);
							impossibleCoursesByUserTiming.put(course.getCourseCode(), impossibleCourse);
						} else {

							impossibleCoursesByUserTiming.get(course.getCourseCode()).getShows().put(show.getShowCode(),
									show);

						}
					}
				}
			}
		}

		impossibleCourses();
		blockedDays.add(invokingDayNumber);
		blockedHours.remove(invokingDayNumber);
		invokeListeners(Controller.DAY_CHECKBOX_DEACTIVATED_MODEL);
	}

	@Override
	public ArrayList<ICourse> getImpossibleCourses() {// union of two array list
		Map<Integer, Course> impossibleCoursesNew = new HashMap<>();
		for (Course iCourse : impossibleCoursesByCourseTiming) {
			impossibleCoursesNew.put(iCourse.getCourseCode(), iCourse);
		}
		for (Course iCourse : impossibleCoursesByUserTiming.values()) {
			if (impossibleCoursesNew.get(iCourse.getCourseCode()) == null) {
				impossibleCoursesNew.put(iCourse.getCourseCode(), iCourse);
			} else {
				for (Integer integer : iCourse.getShowCodes()) {
					impossibleCoursesNew.get(iCourse.getCourseCode()).getShows().put(integer,
							iCourse.getShows().get(integer));
				}

			}
		}
		ArrayList<ICourse> ret = new ArrayList<>();
		for (ICourse iCourse : impossibleCoursesNew.values()) {
			ret.add(iCourse);
		}
		// System.out.println("schedule");
		
		// System.out.println();
		// System.out.println(impossibleCoursesByCourseTiming);
		// System.out.println();
		// System.out.println(impossibleCoursesByUserTiming);
		return ret;
	}

	@Override
	public void addPossibleShowsByDay(int invokingDayNumber) {
		blockedDays.remove(invokingDayNumber);
		HashMap<Course, HashSet<Integer>> map = new HashMap<>();// craete map of imposiible course ,
		// for not changing length of arraylist while runing over it
		for (Course course : impossibleCoursesByUserTiming.values()) {
			for (Show show : course.getShows().values()) {
				for (Slot iSlot : show.getSlots()) {
					int slotDay = IDay.intByDay(iSlot.getDay().toString());
					if (invokingDayNumber == slotDay) {
						boolean flag = false;
						// check if course blocking by another day before
						// removing
						for (Slot iSlotblock : show.getSlots()) {
							int slotblockDay = IDay.intByDay(iSlotblock.getDay().toString());
							if (blockedDays.contains(slotblockDay)) {
								flag = true;
							} else if (blockedHours.get(slotblockDay) != null) {
								HashSet<Integer> set = blockedHours.get(slotblockDay);
								int slotBeginingHour = iSlotblock.getStartingTime();
								int slotEndiningHour = iSlotblock.getEndingTime();
								for (Integer integer : set) {
									if ((slotBeginingHour <= integer && slotEndiningHour > integer)) {
										flag = true;
										break;
									}
								}
							}
						}
						if (flag == false) {
							if (map.get(course) == null) {
								map.put(course, new HashSet<Integer>());
							}

							map.get(course).add(show.getShowCode());
						}

					}
				}
			}
		}
		for (Entry<Course, HashSet<Integer>> entry : map.entrySet()) {
			if (impossibleCoursesByUserTiming.get(entry.getKey().getCourseCode()).getShowCodes().size() == 1) {
				impossibleCoursesByUserTiming.remove(entry.getKey().getCourseCode());
			} else {
				for (Integer integer : entry.getValue()) {
					impossibleCoursesByUserTiming.get(entry.getKey().getCourseCode()).getShows().remove(integer);
				}
			}
		}
		impossibleCourses();

		invokeListeners(Controller.DAY_CHECKBOX_ACTIVATED_MODEL);

	}

	@Override
	public void removeShowsByHour(IHour buttonInvoke) {
		int dayNumber = IDay.intByDay(buttonInvoke.getDay().toString());
		int beginingHour = buttonInvoke.getBeginingHour();

		for (Course course : allCourses.getMapOfCourse().values()) {
			for (Show show : course.getShows().values()) {
				for (Slot iSlot : show.getSlots()) {
					int slotDay = IDay.intByDay(iSlot.getDay().toString());
					int slotBeginingHour = iSlot.getStartingTime();
					int slotEndiningHour = iSlot.getEndingTime();
					if (dayNumber == slotDay && (slotBeginingHour <= beginingHour && slotEndiningHour > beginingHour)) {
						schedule.removeCourseFromSchedule(course);
						if (impossibleCoursesByUserTiming.get(course.getCourseCode()) == null) {
							Course impossibleCourse = new Course(course.getCourseCode(), course.getCourseName());
							impossibleCourse.getShows().put(show.getShowCode(), show);
							impossibleCoursesByUserTiming.put(course.getCourseCode(), impossibleCourse);
						} else {

							impossibleCoursesByUserTiming.get(course.getCourseCode()).getShows().put(show.getShowCode(),
									show);

						}
					}
				}
			}
		}

		impossibleCourses();
		if (blockedHours.get(dayNumber) == null) {
			blockedHours.put(dayNumber, new HashSet<Integer>());
		}
		blockedHours.get(dayNumber).add(beginingHour);
		System.out.println(blockedHours);
		invokeListeners(Controller.SCHEDULE_BUTTON_UNACTIVE_MODEL);

	}

	@Override
	public void addPossibleShowsByHour(IHour buttonInvoke) {
		int dayNumber = IDay.intByDay(buttonInvoke.getDay().toString());
		int beginingHour = buttonInvoke.getBeginingHour();
		System.out.println(blockedHours);
		if (blockedHours.get(dayNumber) != null) {// if hour become active where day was blocked
			blockedHours.get(dayNumber).remove(beginingHour);// deleting blocked hours
			if (blockedHours.get(dayNumber).size() == 0) {// deleting blocked day if there are not any blocking hours
				blockedHours.remove(dayNumber);
			}
		}
		HashMap<Course, HashSet<Integer>> map = new HashMap();// craete map of imposiible course ,
		// for not changing length of arraylist while runing over it
		int possibleCourseID = -1;
		int possibleShowID = -1;
		for (Course course : impossibleCoursesByUserTiming.values()) {
			for (Show show : course.getShows().values()) {
				for (Slot iSlot : show.getSlots()) {
					int slotDay = IDay.intByDay(iSlot.getDay().toString());
					int slotBeginingHour = iSlot.getStartingTime();
					int slotEndiningHour = iSlot.getEndingTime();
					if (dayNumber == slotDay && (slotBeginingHour <= beginingHour && slotEndiningHour > beginingHour)) {
						boolean flag = false;
						// check if course blocking by another day before
						// removing
						for (Slot iSlotblock : show.getSlots()) {

							int slotblockDay = IDay.intByDay(iSlotblock.getDay().toString());
							if (blockedDays.contains(slotblockDay)) {
								flag = true;
							} else if (blockedHours.get(slotblockDay) != null) {
								HashSet<Integer> set = blockedHours.get(slotblockDay);
								int islotBeginingHour = iSlotblock.getStartingTime();
								int islotEndiningHour = iSlotblock.getEndingTime();
								for (Integer integer : set) {
									if ((islotBeginingHour <= integer && islotEndiningHour > integer)) {
										flag = true;
										break;
									}
								}
							}
						}
						if (flag == false) {
							if (map.get(course) == null) {
								map.put(course, new HashSet<Integer>());
							}

							map.get(course).add(show.getShowCode());
						}

					}
				}
			}
		}
		for (Entry<Course, HashSet<Integer>> entry : map.entrySet()) {
			if (impossibleCoursesByUserTiming.get(entry.getKey().getCourseCode()).getShowCodes().size() == 1) {
				impossibleCoursesByUserTiming.remove(entry.getKey().getCourseCode());
			} else {
				for (Integer integer : entry.getValue()) {
					impossibleCoursesByUserTiming.get(entry.getKey().getCourseCode()).getShows().remove(integer);
				}
			}
		}
		impossibleCourses();

		invokeListeners(Controller.SCHEDULE_BUTTON_ACTIVE_MODEL);

	}

}
