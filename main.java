import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Comparator;
import java.util.LinkedHashMap;

/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    // instance variables - replace the example below with your own
    private String rows;
    private String [] heads;
    private HashMap<String, Double> mapStdTotalMarks;
    private HashMap<String, List<String>> mapStdDetails;
    private String[] stdData;

    /**
     * Constructor for objects of class main
     */
    public Main(){
        rows ="";
        mapStdDetails = new HashMap<String, List<String>>();
        mapStdTotalMarks = new HashMap<String, Double>();
    }

    public void readCSV() {
        System.out.println("Please wait while reading provided CSV file....");
        int lineNumber = 0;
        try {
            File csvFile = new File("student.csv");
            Scanner fileScanner = new Scanner(csvFile);
            while (fileScanner.hasNextLine()) {
                rows = fileScanner.nextLine();
                // Getting and Setting Unit Name
                if (lineNumber == 0) {
                    heads = rows.split(",");
                    printUnitName(heads[0]);
                }
                // Getting Students Data
                if (lineNumber > 1) {
                    stdData = rows.split(",");
                    setStudentDetails(stdData);
                }
                lineNumber++;
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file cannot be found");
            e.printStackTrace();
        }
    }

    private void printUnitName(String unitName) {
        System.out.println("Welcome to our Program!\n");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(unitName);
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }

    public void setStudentDetails(String[] stdData) {
        ArrayList<String> stdDetails = new ArrayList<String>();
        Double stdTotalMarks = 0.0;
        stdDetails.add(String.valueOf(stdData[2]));
        for (int i = 3; i <= 5; i++) {
            if (i > stdData.length - 1 || stdData[i].equals("")) {
                stdDetails.add("0.0");
                stdTotalMarks += 0.0;
            } else {
                stdDetails.add(String.valueOf(stdData[i]));
                stdTotalMarks += Double.valueOf(stdData[i]);
            }
        }
        stdDetails.add(String.valueOf(stdTotalMarks));
        String stdName = stdData[0] + " " + stdData[1];
        mapStdDetails.put(stdName, stdDetails);
        mapStdTotalMarks.put(stdName, stdTotalMarks);
    }

    public void printStudentDetailsWithTotalMarks() {
        System.out.println("Student names and their total marks");
        for (String std : mapStdDetails.keySet()) {
            List<String> total = mapStdDetails.get(std);
            System.out.println(std + ":" + total.get(4));
        }
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        initMenu();
    }

    public void printMarksWithThreshold(){
        System.out.println("Please enter your threshold");
        Scanner inputScanner = new Scanner(System.in);
        Double threshold = Double.valueOf(inputScanner.nextLine());
        for (String std : mapStdDetails.keySet()) {
            Double total = Double.valueOf(mapStdDetails.get(std).get(4));
            if (total < threshold) {
                // System.out.println(std + ": " + total);
                System.out.printf("%s: %-10s \n", std, total);
            }
        }
        inputScanner.close();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        initMenu();
    }

    public Map<String, Double> sortHashMapByOrder(Map<String, Double> mapStdTotal, Boolean order) {
        List<Map.Entry<String, Double>> sortingList = new LinkedList<Map.Entry<String, Double>>(mapStdTotal.entrySet());
        // Sorting the list based on values
        Collections.sort(sortingList, new Comparator<Map.Entry<String, Double>>() {
                public int compare(Map.Entry<String, Double> o1,
                Map.Entry<String, Double> o2) {
                    if (order) {
                        return o1.getValue().compareTo(o2.getValue());
                    } else {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                }
            });
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        for (Map.Entry<String, Double> entry : sortingList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public void printTopHighestMarks() {
        System.out.println("Total 10 Highest Marks acheiving Students are:");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        Map<String, Double> sortedStdMap = sortHashMapByOrder(mapStdTotalMarks, false);
        int limit = 1;
        for (String key : sortedStdMap.keySet()) {
            if (limit <= 10) {
                System.out.println(key + ":" + sortedStdMap.get(key));
            }
            limit++;
        }
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        initMenu();
    }

    public void printTopLowestMarks() {
        System.out.println("Total 10 Lowest Marks acheiving Students are:");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        Map<String, Double> sortedStdMap = sortHashMapByOrder(mapStdTotalMarks, true);
        int limit = 1;
        for (String key : sortedStdMap.keySet()) {
            if (limit <= 10) {
                System.out.println(key + ":" + sortedStdMap.get(key));
            }
            limit++;
        }
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        initMenu();
    }

    public void initMenu() {
        System.out.println("Choose from the following to perfom action");
        System.out.println("*********************************************\n");
        System.out.println("1 - Select to see Student Details with Total Marks");
        System.out.println("2 - Select to see list of students with the total marks less than a certain threshold.");
        System.out.println("3 - Select to see top 10 Highest Marks scoring students");
        System.out.println("4 - Select to see top 10 Lowest Marks scoring students");
        System.out.println("5 - Exit Application");
        try {
            System.out.println("Enter your desired number:");
            Scanner menuScanner = new Scanner(System.in);
            int choice = menuScanner.nextInt();

            switch (choice) {
                case 1:
                    printStudentDetailsWithTotalMarks();
                    break;
                case 2:
                    printMarksWithThreshold();
                    break;
                case 3:
                    printTopHighestMarks();
                    break;
                case 4:
                    printTopLowestMarks();
                    break;
                case 5:
                    quitApp();
                    break;
                default:
                    // The user input an unexpected choice.
            }
            menuScanner.close();
        } catch (Exception ex) {
            System.out.println("The file cannot be found");
            ex.printStackTrace();
        }

    }

    public void quitApp() {
        System.out.println("Program exit!");
        System.out.println("Thank you for using!");
        System.exit(0);
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void main(String[] args){
        Main myObj= new Main();
        myObj.readCSV();
        myObj.initMenu();
        //myObj.printStudentDetailsWithTotalMarks();
        //myObj.printMarksWithThreshold();
        //myObj.printTopHighestMarks();
        //myObj.printTopLowestMarks();

    }
}
