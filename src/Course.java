public class Course {
    String name;
    int credit;

    public Course(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }

    @Override
    public String toString() {
        return name + " (" + credit + " credit)";
    }
}
