public class Course {
	private Database db;
	private int id;

    public Course(){

    }

	public Course(Database db, int course_id) {
		this.db = db;
    	this.id = course_id;
    }

    public Boolean isValidForConcession() {
        return true;
    }

	public String getCourseDescription() {
		if (this.id != -1) {
			return this.db.getDescription(this.id);
		} else {
			throw new RuntimeException("Course is not exist");
		}
	}
}
