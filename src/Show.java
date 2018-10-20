import java.util.ArrayList;
public class Show {
	private int ShowId;
	private ArrayList<Slot> slots;
	public Show(){
		this.slots=new ArrayList<Slot>();
	}


	public ArrayList<Slot> getSlots() {
		return slots;
	}


	public int getNumberOfSlots() {
		// TODO Auto-generated method stub
		return slots.size();
	}


	public int getShowId() {
		return ShowId;
	}



	





}
