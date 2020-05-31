package web;

public class TimeSlot {
	int _id;
	String _time;
	int _courseId;
	int _capacity;

	public TimeSlot(int id, String time, int courseId, int capacity) {
		this._id = id;
    	this._time = time;
    	this._courseId = courseId;
    	this._capacity = capacity;
    }
}
