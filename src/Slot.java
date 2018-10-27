import exceptions.EndingTimeBeforeStartingTimeException;


public class Slot implements IDay,ISlot{

	private IDay.Day day;
	private int startingTime=0;//check if it between 7-24
	private int endingTime=0;//check if it between 7-24 and bigger than starting time
	private Room room;
	private Teacher teacher;
	
	public Slot(Day day,int startingTime, int endingTime, int numberOfRoom, String nameOfLect) throws  EndingTimeBeforeStartingTimeException  {
		this.setDay(day);
		this.setStartingTime(startingTime);
		this.setEndingTime(endingTime);
		this.setRoom(new Room(numberOfRoom));
		this.setTeacher(new Teacher(nameOfLect));
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}
	
	public int getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(int startingTime) {
		
		this.startingTime = startingTime;
	
	}

	
	public int getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(int endingTime) throws EndingTimeBeforeStartingTimeException {
				if(endingTime>this.startingTime){
					this.endingTime=endingTime;
				} 
				else throw new EndingTimeBeforeStartingTimeException("The ending hour is before starting hour");
		
		
		
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public boolean matchTeacher(Slot newSlot){
		if(this.getTeacher().getName().equals(newSlot.getTeacher().getName())){
			return true;
		}
		return false;
	}
	public boolean matchRoom(Slot newSlot){
				if(this.getRoom().getRoomNumber()==newSlot.getRoom().getRoomNumber()){
			return true;
		}
		return false;
	}
	public boolean noMatchingHours(Slot newSlot){
		if(newSlot.getDay().equals(this.getDay())){
			if ((newSlot.getEndingTime()>this.getStartingTime()&&newSlot.getEndingTime()<this.getEndingTime())//tempslot start after slot and ending before slot ends
					||(newSlot.getStartingTime()>this.getStartingTime()&&newSlot.getStartingTime()<this.getEndingTime())//slot start before tempslot and ending after tempslot start
					||(newSlot.getStartingTime()<=this.getStartingTime()&&newSlot.getEndingTime()>=this.getEndingTime())){//slot inside tempslot
					return false;
			}
	}
		return true;
	}
	@Override
	public String toString() {
		return "\nSlot [day=" + day + ", startingTime=" + startingTime + ", endingTime=" + endingTime + ", room=" + room
				+ ", teacher=" + teacher + "]";
	}


	
	
}
