import java.util.ArrayList;

public class Course {
    String name;
    int credit;
    ArrayList <Course> prerequisiteCourses;

    public Course(String name, int credit) {
        this.name = name;
        this.credit = credit;
        this.prerequisiteCourses = new ArrayList<Course>();
    }

    public void addPrerequisiteCourses(Course course) {
        prerequisiteCourses.add(course);
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder(name + " (" + credit + " credits)");

        for (Course course : prerequisiteCourses) {
            result.append(", ").append(course);
        }

        return result.toString();

    }

}
