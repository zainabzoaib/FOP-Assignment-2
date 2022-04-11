import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.LinkedHashMap;

/**
 * This class is for assignment 02 of FOP. Which includes functions to execute
 * tasks such as:
 * - Read CSV file.
 * - Print Unit name
 * - Get and Set Student Details for CSV file
 * - Print Student details with total marks
 * - Print Student marks with provided total marks threshold.
 * - Sort Student details in the hash map
 * - Print top 10 highest marks scoring student name and marks
 * - Print top 10 lowest marks scoring student name and marks
 * - Create a text based menu to execute operation by user choice
 *
 * @author (Zainab Mirza)
 * @version (1.0.0)
 * @git url https://github.com/zainabzoaib/FOP-Assignment-2
 */
public class Main {
    // instance srting rows and set to blank
    private String rows="";
    // instance hash map for student details
    private HashMap<String, List<String>> mapStdDetails= new HashMap<String, List<String>>();
    // instance hash map for student and total marks
    private HashMap<String, Double> mapStdTotalMarks= new HashMap<String, Double>();
    // instance string list for head
    private String[] heads;
    // instance string list for stdData
    private String[] stdData;

    /**
     * Constructor for objects of class main
     */
    public Main() {
        // call readCSV to call and set student details
        readCSV();
        // initialise menu
        initMenu();
    }

    /**
     * Read provided CSV file in the project directory. Split comma separated
     * content and generate head and data to iterate and fill hash map to use later.
     * Print Unit Name provided in the CSV file. In case of file not found exception
     * will be handled
     *
     * @return read and set student data from csv
     */

    public void readCSV() {
        System.out.println("Please wait while reading provided CSV file....");
        // initialisation of lineNumber to 0
        int lineNumber = 0;
        try {
            // reading file content from student.csv
            File csvFile = new File("student.csv");
            // scanner file content to be iterate
            Scanner fileScanner = new Scanner(csvFile);
            // loop through each content line
            while (fileScanner.hasNextLine()) {
                // setting rows with the current line
                rows = fileScanner.nextLine();
                // check if lineNumber is 0
                if (lineNumber == 0) {
                    // set head with splitting row by comma
                    heads = rows.split(",");
                    // calling printUnitName with head index 0
                    printUnitName(heads[0]);
                }
                // check if lineNumber greater than 1
                if (lineNumber > 1) {
                    // set stdData with splitting row by comma
                    stdData = rows.split(",");
                    // calling setStudentDetails with argument stdData
                    setStudentDetails(stdData);
                }
                // increment lineNumber
                lineNumber++;
            }
            // close Scanner
            fileScanner.close();
        } catch (FileNotFoundException e) {
            // print error if file not found with stack trace
            System.out.println("The file cannot be found");
            e.printStackTrace();
        }
    }

    /**
     * Print unit name provided in the CSV file
     *
     * @param unitName String unit name for printing
     * @return print unit name
     */

    private void printUnitName(String unitName) {
        // print welcome text
        System.out.println("Welcome to Program!\n");
        // print seperator line for beautification
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        // print unit name from the parameter
        System.out.println(unitName);
        // print seperator line for beautification
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }

    /**
     * Print unit name provided in the CSV file
     *
     * @param stdData String list of Student data to iterate and manipulate
     * @return set student details to maps
     */
    public void setStudentDetails(String[] stdData) {
        // initialise array list of string values
        ArrayList<String> stdDetails = new ArrayList<String>();
        // initialise double variable for student total marks
        Double stdTotalMarks = 0.0;
        // add string value of stdDate index 2 for Student ID to array list
        stdDetails.add(String.valueOf(stdData[2]));
        // loop 3 times to get student marks for 3 assignments
        for (int i = 3; i <= 5; i++) {
            // check if index exist in stdData or is null
            if (i > stdData.length - 1 || stdData[i].equals("")) {
                // add 0.0 marks for the assignment in array list
                stdDetails.add("0.0");
                // add 0.0 marks to student total marks
                stdTotalMarks += 0.0;
            } else {
                // else add string value of stdData of index to array list
                stdDetails.add(String.valueOf(stdData[i]));
                // add double value of index in student total marks
                stdTotalMarks += Double.valueOf(stdData[i]);
            }
        }
        // add string value of student total marks to array list
        stdDetails.add(String.valueOf(stdTotalMarks));
        // initialise string variable of stdName to concatenate student last and first
        // name
        String stdName = stdData[0] + " " + stdData[1];
        // put student name as key and list of student details as value in hash map
        mapStdDetails.put(stdName, stdDetails);
        // put student name as key and student total marks as value in hash map
        mapStdTotalMarks.put(stdName, stdTotalMarks);
    }

    /**
     * Print student details with total marks
     * by iterating over the hash map of student details.
     * 
     * @return print student detail and total marks
     */

    public void printStudentDetailsWithTotalMarks() {
        // loop for each key in the hash map
        for (String std : mapStdDetails.keySet()) {
            // initialise list of string and set value of hash map by key
            List<String> total = mapStdDetails.get(std);
            // print student name, student id and total marks
            System.out.println("(" + total.get(0) + ")" + std + ":" + total.get(4));
        }
        // print seperator line for beautification
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        // calling initMenu to reinitialise menu after execution complete
        initMenu();
    }

    /**
     * Print student marks by provided input threshold
     * 
     * @return printing student name and marks according to the entered threshold
     */
    public void printMarksWithThreshold() {
        // print imstructions to get input from user
        System.out.println("Please provide the threshold for maximum marks:");
        // print seperator line for beautification
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        // getting user input
        Scanner inputScanner = new Scanner(System.in);
        // initialise double variable and set value of user input
        Double threshold = Double.valueOf(inputScanner.nextLine());
        // loop for each key in hash map
        for (String std : mapStdDetails.keySet()) {
            // initialise list of string and get student details from hash map
            List<String> stdDetails = mapStdDetails.get(std);
            // initialise double total and set value of hash map of index 4
            Double total = Double.valueOf(mapStdDetails.get(std).get(4));
            // check if total is less than provided threshold
            if (total < threshold) {
                // print student name and total marks of student
                System.out.println("(" + stdDetails.get(0) + ")" + std + ": " + total);
            }
        }
        // close scanner
        inputScanner.close();
        // print seperator line for beautification
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        // calling initMenu to reinitialise menu after execution complete
        initMenu();
    }

    /**
     * Separate function to sort Hash map preserving key and values
     *
     * @param mapStdTotal hash map of string key and double value
     * @param order       boolean order for sorting order. true = ascending and
     *                    false = descending
     * @return sortedMap sorted map filled with sorted linked list by collection
     *         sort
     * @see Map
     */
    public Map<String, Double> sortHashMapByOrder(Map<String, Double> mapStdTotal, Boolean order) {
        // initialise list of hash map with string key and double value and set value of
        // linkedlist generated by hash map entry set
        List<Map.Entry<String, Double>> sortingList = new LinkedList<Map.Entry<String, Double>>(mapStdTotal.entrySet());
        // sort list by collection sort with comparator to sort by values instead of
        // keys
        Collections.sort(sortingList, new Comparator<Map.Entry<String, Double>>() {
                // init integer compare call back to sort by comparing values
                public int compare(Map.Entry<String, Double> value1, Map.Entry<String, Double> value2) {
                    // if order is true
                    if (order) {
                        // return value1 entry element compared to value2 entry element
                        return value1.getValue().compareTo(value2.getValue());
                    } else {
                        // return value2 entry element compared to value1 entry element
                        return value2.getValue().compareTo(value1.getValue());
                    }
                }
            });

        // maintaining insertion order with the help of LinkedList
        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();
        // loop for each entry of sorted linked hashmap to refill sorted map
        for (Map.Entry<String, Double> entry : sortingList) {
            // put key of linked hashmap as key and value as hashmap value
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        // return filled sortedMap
        return sortedMap;
    }

    /**
     * Print top 10 Higher Marks from the data provided
     * 
     * @return print result with top 10 highest marks on the screen
     *
     */
    public void printTopHighestMarks() {
        // print instructions
        System.out.println("Total 10 Highest Marks acheiving Students are:");
        // print seperator line for beautification
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        // initialise hash map and call sortHashMapByOrder with arguments as hash map of
        // student with total marks and order as false
        Map<String, Double> sortedStdMap = sortHashMapByOrder(mapStdTotalMarks, false);
        // initialise limit as 1
        Integer limit = 1;
        // loop for each key in sorted student map keyset
        for (String key : sortedStdMap.keySet()) {
            // check if limit is less than 10 as we need only top 10 records
            if (limit <= 10) {
                // print student name and total marks
                System.out.println(key + ":" + sortedStdMap.get(key));
            }
            // increment limit
            limit++;
        }
        // print seperator line for beautification
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        // calling initMenu to reinitialise menu after execution complete
        initMenu();
    }

    /**
     * Print top 10 Lowest Marks from the data provided
     * 
     * @return print result with top 10 lowest marks on the screen
     *
     */
    public void printTopLowestMarks() {
        // print instructions
        System.out.println("Total 10 Lowest Marks acheiving Students are:");
        // print seperator line for beautification
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        // initialise hash map and call sortHashMapByOrder with arguments as hash map of
        // student with total marks and order as true
        Map<String, Double> sortedStdMap = sortHashMapByOrder(mapStdTotalMarks, true);
        // initialise limit as 1
        Integer limit = 1;
        // loop for each key in sorted student map keyset
        for (String key : sortedStdMap.keySet()) {
            // check if limit is less than 10 as we need only top 10 records
            if (limit <= 10) {
                // print student name and total marks
                System.out.println(key + ":" + sortedStdMap.get(key));
            }
            // increment limit
            limit++;
        }
        // print seperator line for beautification
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        // calling initMenu to reinitialise menu after execution complete
        initMenu();
    }

    /**
     * Initialise screen with menu to get user input and execute desired operation
     * 
     * @return print menu options and call function of selection
     *
     */
    public void initMenu() {
        // print instructions for user to input value
        System.out.println("Choose from these choices to perfom action");
        // print seperator line for beautification
        System.out.println("*********************************************\n");
        // print menu option to execution operation
        System.out.println("1 - Print Student Details with Total Marks");
        // print menu option to execution operation
        System.out.println("2 - Print marks by maximum threshold");
        // print menu option to execution operation
        System.out.println("3 - Print top 10 Higher Marks with Student name");
        // print menu option to execution operation
        System.out.println("4 - Print top 10 Lowest Marks with Student name");
        // print menu option to execution operation
        System.out.println("5 - Exit Application");
        try {
            // print instruction to get input
            System.out.println("Enter your desired number:");
            // get input from user to scanner
            Scanner menuScanner = new Scanner(System.in);
            // initialise choice and set scanner value
            Integer choice = menuScanner.nextInt();
            // swith case to check and execute required operation
            switch (choice) {
                    // if value is 1
                case 1:
                    // call printStudentDetailsWithTotalMarks
                    printStudentDetailsWithTotalMarks();
                    // break case
                    break;
                case 2:
                    // call printMarksWithThreshold
                    printMarksWithThreshold();
                    // break case
                    break;
                case 3:
                    // call printTopHighestMarks
                    printTopHighestMarks();
                    // break case
                    break;
                case 4:
                    // call printTopLowestMarks
                    printTopLowestMarks();
                    // break case
                    break;
                case 5:
                    // call quitApp
                    quitApp();
                    // break case
                    break;
                default:
                    // if the user input an unexpected choice print
                    System.out.println("Please select value from the options only");
            }
            // close scanner
            menuScanner.close();
        } catch (Exception ex) {
            // print error message
            System.out.println("An error occured");
            // print stack trace
            ex.printStackTrace();
        }
    }

    /**
     * Print exit message and terminate application
     * 
     * @return exit application
     *
     */
    public void quitApp() {
        // print exit message before termination
        System.out.println("Program exit!");
        System.out.println("Thank you for using!");
        // terminate application with exit code
        System.exit(0);
    }

    /**
     * Application start function
     * 
     * @return initiate class and run
     *
     */

    public static void main(String[] args) {
        // create instance of class
        Main myObj = new Main();

    }
}
