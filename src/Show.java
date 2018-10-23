import java.util.ArrayList;


public class Show {
	
	@Override
	public String toString() {
		return "Show [showCode=" + showCode + ", slots=" + slots + "]";
	}

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
