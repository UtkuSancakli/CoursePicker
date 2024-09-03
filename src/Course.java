public class Course {
    String name;
    int credit;
    Course prerequisiteCourse;

    public Course(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }

    public Course(String name, int credit, Course prerequisiteCourse) {
        this.name = name;
        this.credit = credit;
        this.prerequisiteCourse = prerequisiteCourse;
    }

    @Override
    public String toString() {
        return name + " (" + credit + " credits)" + (prerequisiteCourse != null ? " (Prerequisite: " + prerequisiteCourse.name + ")" : "");
    }

}
