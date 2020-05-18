public class Enrollment {

  private Database db;

  public Enrollment(Database db) {
	  this.db = db;
  }

  public int getCourseCapacity(Course course) {
    int course_id = -1;
    int capacity = -1;
    course_id = course.getCourseId();
    if (course_id != -1) {
      capacity = this.db.getCapacity(course_id);
    } else {
      throw new RuntimeException("Course is not exist");
    }
    return capacity;
  }

}