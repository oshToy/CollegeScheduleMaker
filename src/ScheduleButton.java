import javafx.scene.control.Button;

public class ScheduleButton extends Button implements IHour {	
   private boolean flag;
   private IDay.Day day;
   private int beginingHour;

   public ScheduleButton(IDay.Day day,int beginingHour){
	   super();
	   this.day=day;
	   this.beginingHour=beginingHour;
	   setFlag(true);
   }
public boolean isFlag() {
	return flag;
}

public void setFlag(boolean flag) {
	this.flag = flag;
}
public int getBeginingHour() {
	return beginingHour;
}

public IDay.Day getDay() {
	return day;
}

}

