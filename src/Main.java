import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Course math = new Course("Mathematics", 4);
        Course physics = new Course("Physics", 3, math);
        Course chemistry = new Course("Chemistry", 3, math);
        Course biology = new Course("Biology", 2, chemistry);
        Course literature = new Course("Literature", 2);
        Course history = new Course("History", 3);
        Course philosophy = new Course("Philosophy", 2);

        ArrayList<Course> yourCompletedCourses = new ArrayList<>();
        yourCompletedCourses.add(math);

        ArrayList<Course> AllCourses = new ArrayList<>();
        AllCourses.add(math);
        AllCourses.add(physics);
        AllCourses.add(chemistry);
        AllCourses.add(biology);
        AllCourses.add(literature);
        AllCourses.add(history);
        AllCourses.add(philosophy);

        int yourTotalCredit = 6;

        ArrayList<ArrayList<Course>> answer = newCombinations(AllCourses, yourTotalCredit, yourCompletedCourses);

        for (ArrayList<Course> combination : answer) {
            System.out.println("combination:");
            for (Course course : combination) {
                System.out.println("- " + course);
            }
            System.out.println();
        }
    }

    private static ArrayList<ArrayList<Course>> newCombinations(ArrayList<Course> courses, int yourTotalCredit, ArrayList<Course> yourCompletedCourses) {

        ArrayList<ArrayList<Course>> answer = new ArrayList<>();
        calculateCombination(courses, new ArrayList<>(), 0, yourTotalCredit, yourCompletedCourses, answer);
        return answer;
    }

    private static void calculateCombination(ArrayList<Course> courses, ArrayList<Course> tempList, int start, int yourTotalCredit, ArrayList<Course> yourCompletedCourses, ArrayList<ArrayList<Course>> answer) {

        int creditRightNow = tempList.stream().mapToInt(d -> d.credit).sum();

        if (creditRightNow == yourTotalCredit) {
            if (checkPrerequisites(tempList, yourCompletedCourses)) {
               answer.add(new ArrayList<>(tempList));
            }
        } else if (creditRightNow < yourTotalCredit) {
            for (int i = start; i < courses.size(); i++) {
                tempList.add(courses.get(i));
                calculateCombination(courses, tempList, i + 1, yourTotalCredit, yourCompletedCourses, answer);
                tempList.removeLast();
            }
        }
    }

    private static boolean checkPrerequisites(ArrayList<Course> courseList, ArrayList<Course> yourCompletedCourses) {
        for (Course course : courseList) {
            if (course.prerequisiteCourse != null &&
                    !courseList.contains(course.prerequisiteCourse) &&
                    !yourCompletedCourses.contains(course.prerequisiteCourse)) {
                return false;
            }
        }
        return true;
    }
}