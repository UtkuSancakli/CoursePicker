import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Course math = new Course("Mathematics", 4);
        Course physics = new Course("Physics", 3, math);
        Course chemistry = new Course("Chemistry", 3, math);
        Course biology = new Course("Biology", 2);
        Course literature = new Course("Literature", 2);
        Course history = new Course("History", 3);
        Course philosophy = new Course("Philosophy", 2);

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(math);
        courses.add(physics);
        courses.add(chemistry);
        courses.add(biology);
        courses.add(literature);
        courses.add(history);
        courses.add(philosophy);

        int yourTotalCredit = 6;

        ArrayList<ArrayList<Course>> answer = newCombinations(courses, yourTotalCredit);

        for (ArrayList<Course> combination : answer) {
            System.out.println("combination:");
            for (Course course : combination) {
                System.out.println("- " + course);
            }
            System.out.println();
        }
    }

    private static ArrayList<ArrayList<Course>> newCombinations(ArrayList<Course> courses, int yourTotalCredit) {

        ArrayList<ArrayList<Course>> answer = new ArrayList<>();
        calculateCombination(courses, new ArrayList<>(), 0, yourTotalCredit, answer);
        return answer;
    }
    private static void calculateCombination(ArrayList<Course> courses, ArrayList<Course> tempList, int start, int yourTotalCredit, ArrayList<ArrayList<Course>> answer) {

        int creditRightNow = tempList.stream().mapToInt(d -> d.credit).sum();

        if (creditRightNow == yourTotalCredit) {
            if (checkPrerequisites(tempList)) {
                answer.add(new ArrayList<>(tempList));
            }
        }
        else if (creditRightNow < yourTotalCredit) {
            for (int i = start; i < courses.size(); i++) {
                tempList.add(courses.get(i));
                calculateCombination(courses, tempList, i + 1, yourTotalCredit, answer);
                tempList.removeLast();
            }
        }
    }
    private static boolean checkPrerequisites(ArrayList<Course> courseList) {
        for (Course course : courseList) {
            if (course.prerequisiteCourse != null && !courseList.contains(course.prerequisiteCourse)) {
                return false;
            }
        }
        return true;
    }
}