import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Course math103 = new Course("High Math for Engineers I", 8);

        Course math104 = new Course("High Math for Engineers II", 8);
        math104.addPrerequisiteCourses(math103);

        Course math211 = new Course("Linear Algebra", 6);
        math211.addPrerequisiteCourses(math103);

        Course math212 = new Course("Differential Equations", 6);
        math212.addPrerequisiteCourses(math103);

        Course math217 = new Course("Probability and Statistics", 6);
        math217.addPrerequisiteCourses(math103);

        Course phys101 = new Course("Introduction to Physics I", 8);
        Course phys102 = new Course("Introduction to Physics II", 8);
        phys102.addPrerequisiteCourses(phys101);

        Course eng101 = new Course("English I", 4);
        Course eng102 = new Course("English II", 3);
        Course eng303 = new Course("Technical Communication", 2);
        eng303.addPrerequisiteCourses(eng102);

        Course sec101 = new Course("Introduction to University Life", 1);
        Course sec201 = new Course("Introduction to Sector", 2);
        sec201.addPrerequisiteCourses(sec101);

        Course sec202 = new Course("Competency Management", 2);
        sec202.addPrerequisiteCourses(sec201);

        Course econ210 = new Course("Introduction to Economics", 6);

        Course ee203 = new Course("Digital Systems", 6);

        Course bus101 = new Course("Introduction to Business I", 4);
        Course bus102 = new Course("Introduction to Business II", 4);
        bus102.addPrerequisiteCourses(bus101);

        Course tll101 = new Course("Turkish Literature and Language I", 4);
        Course tll102 = new Course("Turkish Literature and Language II", 4);
        tll102.addPrerequisiteCourses(tll101);

        Course hist201 = new Course("Ataturk's Principles I", 4);
        Course hist202 = new Course("Ataturk's Principles II", 4);
        hist202.addPrerequisiteCourses(hist201);

        Course fe101 = new Course("Introduction to Engineering", 2);
        Course fe2 = new Course("fe2", 2);
        Course fe41 = new Course("fe41", 4);
        Course fe42 = new Course("fe42", 4);
        Course fe61 = new Course("fe61", 6);
        Course fe62 = new Course("fe62", 6);
        Course fe63 = new Course("fe63", 6);
        Course fe64 = new Course("fe64", 6);

        Course bscs1 = new Course("BSCS I", 6);
        Course bscs2 = new Course("BSCS II", 6);

        Course cs101 = new Course("Introduction to Computer Science", 6);
        Course cs102 = new Course("Object Oriented Programming", 6);
        Course cs112 = new Course("Discreet Math for Computer Science", 6);
        Course cs201 = new Course("Datastructures and Algorithms", 6);
        Course cs202 = new Course("Database Management Systems", 6);
        cs202.addPrerequisiteCourses(cs102);

        Course cs240 = new Course("Computer Architecture", 6);
        cs240.addPrerequisiteCourses(ee203);

        Course cs320 = new Course("Programming Engineering", 6);
        cs320.addPrerequisiteCourses(cs102);

        Course cs321 = new Course("Programming Paradigms", 6);
        cs321.addPrerequisiteCourses(cs102);

        Course cs333 = new Course("Algorithm Analysis", 6);
        cs333.addPrerequisiteCourses(cs201);

        Course cs350 = new Course("Operating Systems", 6);
        cs350.addPrerequisiteCourses(cs201);

        Course cs401 = new Course("CS Project I", 4);
        cs401.addPrerequisiteCourses(cs201);
        cs401.addPrerequisiteCourses(cs202);
        cs401.addPrerequisiteCourses(cs240);
        cs401.addPrerequisiteCourses(cs320);
        cs401.addPrerequisiteCourses(cs321);

        Course cs402 = new Course("CS Project II", 4);
        cs402.addPrerequisiteCourses(cs401);

        Course cs410 = new Course("Automata Theory", 6);
        cs410.addPrerequisiteCourses(cs112);
        cs410.addPrerequisiteCourses(cs201);

        Course cs447 = new Course("Communication Networks", 6);
        cs447.addPrerequisiteCourses(cs201);

        ArrayList<Course> yourCompletedCourses = new ArrayList<>();
        quickAdd( yourCompletedCourses, math103, math104, math212, phys101, phys102, eng101, eng102, bus101, tll101, tll102, fe101, ee203, cs101, cs102, cs112, sec201, cs201, cs240, sec101, sec202);

        ArrayList<Course> AllCourses = new ArrayList<>();
        quickAdd(AllCourses, cs101, cs102, cs112, cs201, cs202, cs240, cs320, cs333, cs401, cs402, cs410, cs447, math103, math104, math211, math212, math217, phys101, phys102, sec101, sec201, sec202, eng101, eng102, eng303, tll101, tll102, bus101, bus102, hist201, hist202, ee203, econ210, fe101, fe2, fe41, fe42, fe61, fe62, fe63, fe64, bscs1, bscs2);

        System.out.println(AllCourses.size());

        //----------------------------User Part--------------------------------
        int totalCreditLimit = 30; //Maximum amount of credits you can take.
        int minimumWantedCredit = 28; // Minimum total credits you want to take.
        boolean isRetakingOk = false; // If you want to see the results with retaking the courses, this must be true.
        boolean ableToTakeBSCS = false; //If you decide your specialty this must be true.
        ArrayList <Course> specificCourses = new ArrayList<>(); //If you want any course specifically add to this list
        specificCourses.add(cs202);
        specificCourses.add(math211);
        specificCourses.add(math217);
        //----------------------------User Part--------------------------------


        ArrayList<ArrayList<Course>> result = findCombinations(AllCourses, totalCreditLimit, minimumWantedCredit, yourCompletedCourses, isRetakingOk);
        result.sort(new Comparator<ArrayList<Course>>() {
            @Override
            public int compare(ArrayList<Course> o1, ArrayList<Course> o2) {
                int totalCreditsO1 = o1.stream().mapToInt(c -> c.credit).sum();
                int totalCreditsO2 = o2.stream().mapToInt(c -> c.credit).sum();
                return Integer.compare(totalCreditsO2, totalCreditsO1);
            }
        });

        //print
        for (ArrayList<Course> combination : result) {

            boolean isOk = true;

            if(ableToTakeBSCS){
                for (Course specific : specificCourses){
                    if(!combination.contains(specific)){
                        isOk = false;
                        break;
                    }
                }
            }
            else{
                for (Course specific : specificCourses){
                    if(!combination.contains(specific) || combination.contains(bscs1) || combination.contains(bscs2)){
                        isOk = false;
                        break;
                    }
                }
            }

            if(isOk){
                int usedCredit =0;
                System.out.println("Combination:");

                for (Course course : combination) {
                    System.out.println("- " + course);
                    usedCredit += course.credit;
                }

                System.out.println("Total Credit Taken: " + usedCredit);
                System.out.println();
            }

        }
    }

    public static void quickAdd(ArrayList<Course> courseList, Course... courses) {
        Collections.addAll(courseList, courses);
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

    private static boolean checkPrerequisites(ArrayList<Course> tempList, ArrayList<Course> completedCourses) {
        for (Course course : tempList) {
            // Check if the course has prerequisites
            for (Course prerequisite : course.prerequisiteCourses) {
                // If the prerequisite is not in tempList or completedCourses, the prerequisite is not met
                if (!tempList.contains(prerequisite) && !completedCourses.contains(prerequisite)) {
                    return false;
                }
            }
        }
        return true;
    }
}