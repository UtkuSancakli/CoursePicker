import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Matematik", 4));
        courses.add(new Course("Fizik", 3));
        courses.add(new Course("Kimya", 3));
        courses.add(new Course("Biyoloji", 2));
        courses.add(new Course("Edebiyat", 2));
        courses.add(new Course("Tarih", 3));
        courses.add(new Course("Felsefe", 2));

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
            answer.add(new ArrayList<>(tempList));
        } else if (creditRightNow < yourTotalCredit) {
            for (int i = start; i < courses.size(); i++) {
                tempList.add(courses.get(i));
                calculateCombination(courses, tempList, i + 1, yourTotalCredit, answer);
                tempList.removeLast();
            }
        }
    }
}