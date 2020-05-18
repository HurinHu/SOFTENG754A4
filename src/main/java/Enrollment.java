public class Enrollment {

  private Database db;
  private User user;

  public Enrollment(Database db) {
	  this.db = db;
  }

  public Enrollment(Database db, User user) {
	  this.db = db;
	  this.user = user;
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

  public int getEnrolledStudentNum(Course course) {
    int course_id = -1;
    int enrolledStudent = -1;
    course_id = course.getCourseId();
    if (course_id != -1) {
      enrolledStudent = this.db.getEnrolled(course_id);
    } else {
      throw new RuntimeException("Course is not exist");
    }
    return enrolledStudent;
  }

  public int getRemainingSeats(Course course) {
    int capacity = this.getCourseCapacity(course);
    int enrolledStudent = this.getEnrolledStudentNum(course);
    int remaining = capacity - enrolledStudent;
    if (remaining >= 0) {
      return remaining;
    } else {
      throw new RuntimeException("Internal Error");
    }
  }

  public String getCourseStatus(Course course) {
    int course_id = -1;
    int user_id = -1;
    String status = "";
    course_id = course.getCourseId();
    user_id = this.user.getId();
    if (course_id == -1) {
      throw new RuntimeException("Course is not exist");
    } else if (user_id == -1) {
      throw new RuntimeException("Student is not exist");
    } else {
      status = this.db.getStatus(course_id, user_id);
    }
    return status;
  }

  public boolean setCourseConfirmed(Course course) {
    return false;
  }
}