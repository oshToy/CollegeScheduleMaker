import java.util.ArrayList;


public class Show {
	private int showCode;
	private ArrayList<Slot> slots;
	public Show(int showCode ){
		this.showCode=showCode;
		this.slots=new ArrayList<Slot>();
	}


	public ArrayList<Slot> getSlots() {
		return slots;
	}


	public int getShowCode() {
		return showCode;
	}


	public int getNumberOfSlots() {
		// TODO Auto-generated method stub
		return slots.size();
	}


}
