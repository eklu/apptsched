public class Appt {
	private String date, name,number,start,end,notes,uid;
	private int startMinute, startHour, endMinute, endHour;
	boolean startPM, stopPM;

	public Appt(){
		date="";
		name="";
		number="";
		start="";
		end="";
		notes="";
		uid="";
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Appt(String date, String Aname,String Anumber,String Astart,String Aend,String Anotes,String uid){
		setDate(date);
		setName(Aname);
		setNumber(Anumber);
		setStart(Astart);
		setEnd(Aend);
		setNotes(Anotes);
		setUID(uid);
	}
	
	
	public int getStartMinute() {
		return startMinute;
	}

	public boolean isStartPM() {
		return startPM;
	}

	public void setStartPM(boolean startPM) {
		this.startPM = startPM;
	}

	public boolean isStopPM() {
		return stopPM;
	}

	public void setStopPM(boolean stopPM) {
		this.stopPM = stopPM;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		String[] x = start.split(" ");
		String[] starts = x[0].split(":");
		setStartHour(Integer.parseInt(starts[0])-1);
		setStartMinute(Integer.parseInt(starts[1]));
		setStartPM(x[1].equals("PM"));
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		String[] x = end.split(" ");
		String[] ends = x[0].split(":");
		setEndHour(Integer.parseInt(ends[0])-1);
		setEndMinute(Integer.parseInt(ends[1]));
		setStopPM(x[1].equals("PM"));
		this.end = end;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getUID() {
		return uid;
	}

	public void setUID(String uid) {
		this.uid = uid;
	}
	
	public boolean equals(Object other){
		if(other instanceof Appt){
			Appt o = (Appt)other;
			return getUID().equals(o.getUID()) && getDate().equals(o.getDate()) && getName().equals (o.getName()) && getNumber().equals(o.getNumber()) && getStart().equals(o.getStart()) && getEnd().equals(o.getEnd());
		}
			return false;
		
	}
	
	
}
