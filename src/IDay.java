
public interface IDay {
	public enum Day{Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday};
	public static  Day dayByInt(int dayByNum){
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
	public static  int intByDay(String name){
		if(name.equals(IDay.Day.Sunday.toString())){
			return 1;
		}
		else if(name.equals(IDay.Day.Monday.toString())){
			return 2;
		}
		else if(name.equals(IDay.Day.Tuesday.toString())){
			return  3 ;
		}
		else if(name.equals(IDay.Day.Wednesday.toString())){
			return 4;
		}
		else if(name.equals(IDay.Day.Thursday.toString())){
			return 5;
		}
		else if(name.equals(IDay.Day.Friday.toString()) ){
			return 6;
		}
		else if(name.equals(IDay.Day.Saturday.toString())){
			return 7;
		}
		
		return 0;
	}
	public static  Day dayByString(String dayByString){
		if(dayByString.equals("Sunday")){
			return IDay.Day.Sunday;
		}
		else if(dayByString.equals("Monday")){
			return IDay.Day.Monday;
		}
		else if(dayByString.equals("Tuesday")){
			return IDay.Day.Tuesday;
		}
		else if(dayByString.equals("Wednesday")){
			return IDay.Day.Wednesday;
		}
		else if(dayByString.equals("Thursday")){
			return IDay.Day.Thursday;
		}
		else if(dayByString.equals("Friday")){
			return IDay.Day.Friday;
		}
		else if(dayByString.equals("Saturday")){
			return IDay.Day.Saturday;
		}
		
		
		return null;
	}
}
