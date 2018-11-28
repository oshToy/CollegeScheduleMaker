import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SlotInputObjects {
	private ComboBox dayComboBox;
	private ComboBox startTimeComboBox;
	private ComboBox finishTimeComboBox;
	private TextField roomNumber;
	private TextField lecturerName;
	
	public ComboBox getDayComboBox() {
		return dayComboBox;
	}
	public void setDayComboBox(ComboBox dayComboBox) {
		this.dayComboBox = dayComboBox;
	}
	public ComboBox getStartTimeComboBox() {
		return startTimeComboBox;
	}
	public void setStartTimeComboBox(ComboBox startTimeComboBox) {
		this.startTimeComboBox = startTimeComboBox;
	}
	public ComboBox getFinishTimeComboBox() {
		return finishTimeComboBox;
	}
	public void setFinishTimeComboBox(ComboBox finishTimeComboBox) {
		this.finishTimeComboBox = finishTimeComboBox;
	}
	public TextField getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(TextField roomNumber) {
		this.roomNumber = roomNumber;
	}
	public TextField getLecturerName() {
		return lecturerName;
	}
	public void setLecturerName(TextField lecturerName) {
		this.lecturerName = lecturerName;
	}
	
}
