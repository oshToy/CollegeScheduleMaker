
public interface IDay {
	public enum Day{Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday};
	public static  Day dayByInput(int dayByNum){
		if(dayByNum == 1){
			return IDay.Day.Sunday;
		}
		else if(dayByNum == 2){
			return IDay.Day.Monday;
		}
		else if(dayByNum == 3){
			return IDay.Day.Tuesday;
		}
		else if(dayByNum == 4){
			return IDay.Day.Wednesday;
		}
		else if(dayByNum == 5){
			return IDay.Day.Thursday;
		}
		else if(dayByNum == 6){
			return IDay.Day.Friday;
		}
		else if(dayByNum == 7){
			return IDay.Day.Saturday;
		}
		
		return null;
	}
}
