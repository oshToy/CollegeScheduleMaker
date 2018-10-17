import java.util.Arrays;

public class Show {
	private Slot[] slots;
	private int numberOfSlots;
	public Show(int numberOfSlots){
		this.numberOfSlots=numberOfSlots;
		this.slots=new Slot[numberOfSlots];
		
	}


	public Slot[] getSlots() {
		return slots;
	}
	@Override
	public String toString() {
		return "\nShow [slots=" + Arrays.toString(slots) + ", numberOfSlots="
				+ numberOfSlots + "]";
	}

	public int getNumberOfSlots() {
		return numberOfSlots;
	}
}
