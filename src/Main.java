import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Course math101 = new Course("Mathematics 101", 4);
        Course phys101 = new Course("Physics 101", 3);
        Course chem101 = new Course("Chemistry 101", 3);
        Course math102 = new Course("Mathematics 102", 2, math101);
        Course phys102 = new Course("Physics 102", 4, phys101);
        Course history = new Course("History", 2);
        Course philosophy = new Course("Philosophy", 1);

        ArrayList<Course> yourCompletedCourses = new ArrayList<>(); //All the courses you've already completed must be added to this list.
        yourCompletedCourses.add(math101);

        ArrayList<Course> AllCourses = new ArrayList<>(); //All the courses must be added to this list.
        AllCourses.add(math101);
        AllCourses.add(phys101);
        AllCourses.add(chem101);
        AllCourses.add(math102);
        AllCourses.add(phys102);
        AllCourses.add(history);
        AllCourses.add(philosophy);

        int totalCreditLimit = 6; //Maximum amount of credits you can take.
        int minimumWantedCredit = 4; // Minimum total credits you want to take.
        boolean isRetakingOk = false; // If you want to see the results with retaking the courses, this must be true.

        ArrayList<ArrayList<Course>> result = findCombinations(AllCourses, totalCreditLimit, minimumWantedCredit, yourCompletedCourses, isRetakingOk);

        for (ArrayList<Course> combination : result) {

            //int creditTaken = 0;
            System.out.println("Combination:");

            for (Course course : combination) {
                System.out.println("- " + course);
                //creditTaken += course.credit;
            }

            //System.out.println("Total Credit Taken: " + creditTaken);
            System.out.println();
        }
    }

    public static ArrayList<ArrayList<Course>> findCombinations(ArrayList<Course> courses, int totalCreditLimit, int minimumWantedCredit, ArrayList<Course> completedCourses, boolean isRetakingOk) {

        ArrayList<ArrayList<Course>> result = new ArrayList<>();
        calculateCombinations(courses, new ArrayList<>(), 0, totalCreditLimit, minimumWantedCredit, completedCourses, isRetakingOk, result);
        return result;
    }

    private static void calculateCombinations(ArrayList<Course> courses, ArrayList<Course> tempList, int start, int totalCreditLimit, int minimumWantedCredit, ArrayList<Course> completedCourses, boolean isRetakingOk, ArrayList<ArrayList<Course>> resultArray) {

        int currentTotalCredits = tempList.stream().mapToInt(c -> c.credit).sum();

        if (currentTotalCredits >= minimumWantedCredit && currentTotalCredits <= totalCreditLimit) {
            if (checkPrerequisites(tempList, completedCourses)) {
                resultArray.add(new ArrayList<>(tempList));
            }
        }

        if (currentTotalCredits < totalCreditLimit) {
            for (int i = start; i < courses.size(); i++) {
                Course course = courses.get(i);
                if (isRetakingOk || !completedCourses.contains(course)) {
                    tempList.add(course);
                    calculateCombinations(courses, tempList, i + 1, totalCreditLimit, minimumWantedCredit, completedCourses, isRetakingOk, resultArray);
                    tempList.removeLast();
                }
            }
        }
    }

    private static boolean checkPrerequisites(ArrayList<Course> courseList, ArrayList<Course> completedCourses) {
        for (Course course : courseList) {
            if (course.prerequisiteCourse != null &&
                    !courseList.contains(course.prerequisiteCourse) &&
                    !completedCourses.contains(course.prerequisiteCourse)) {
                return false;
            }
        }
        return true;
    }
}