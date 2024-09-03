import java.util.ArrayList;

public class Course {
    String name;
    int credit;
    ArrayList <Course> prerequisiteCourse;

    public Course(String name, int credit) {
        this.name = name;
        this.credit = credit;
        this.prerequisiteCourse = new ArrayList<Course>();
    }

    public void addPrerequisiteCourses(Course course) {
        prerequisiteCourse.add(course);
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder(name + " (" + credit + " credits)");

        for (Course course : prerequisiteCourse) {
            result.append(", ").append(course);
        }

        return result.toString();


    }

}
