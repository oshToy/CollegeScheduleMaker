import java.util.ArrayList;
<<<<<<< HEAD


public class Show {
	private int showCode;
=======
public class Show {
	private int ShowId;
>>>>>>> 13797d4865f7ede8e1f59648fbde7272da06635e
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


	public int getShowId() {
		return ShowId;
	}



	





}
