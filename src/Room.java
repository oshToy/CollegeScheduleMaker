
public class Room {
	private int roomNumber;
	
	public Room(int roomNumber){
		this.roomNumber=roomNumber;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + "]";
	}

	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}	
}
