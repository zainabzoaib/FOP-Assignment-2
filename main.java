import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    // instance variables - replace the example below with your own
    private String unitName;
    private String row;
    private String [] heads;
    private List<String> students;
    private List<Double> stdTotalMarks;
    private HashMap<String, List<String>> map = new HashMap<String, List<String>>();

    /**
     * Constructor for objects of class main
     */
    public Main()
    {
        // initialise instance variables
        //intialising variable
        int lineNumbers=0;
        //making hashmap for student details
        //fetching data from student.csv
        try{
            //making object for file and 
            File studentFile=new File("student.csv");
            //reading data from file
            Scanner myScanner=new Scanner(studentFile);
            //loop condition to read data
            while(myScanner.hasNextLine()){
                //reading data line by line
                row=myScanner.nextLine();
                //checking in lineNumber=0
                if(lineNumbers==0){
                    //separating data by,
                    heads = row.split(",");
                    //setting unitName as heads index 0
                    unitName = heads[0];
                    //printing unit name 
                    System.out.println(unitName);
                }
                //checking if the lineNumber is something except the real data
                else if(lineNumbers>1){
                    //separating data by ,
                    heads = row.split(",");
                    //making ArrayList for data as stdDetails
                    ArrayList<String> stdDetails= new ArrayList<String>();
                    //initialising totalMarks to 0
                    Double totalMarks = 0.0;
                    //
                    stdDetails.add(String.valueOf(heads[2]));
                    //checking if stdDetails have an empty value
                    if (3>heads.length-1 || heads[3].equals("")){
                        stdDetails.add("0.0");
                        totalMarks += 0.0;
                    }
                    else{
                        stdDetails.add(String.valueOf(heads[3]));
                        totalMarks += Double.valueOf(heads[3]);
                    }
                    if (4>heads.length-1 || heads[4].equals("")){
                        stdDetails.add("0.0");
                        totalMarks += 0.0;
                    }
                    else{
                        stdDetails.add(String.valueOf(heads[4]));
                        totalMarks += Double.valueOf(heads[4]);
                    }

                    if (5>heads.length-1 || heads[5].equals("")){
                        stdDetails.add("0.0");
                        totalMarks += 0.0;
                    }
                    else{
                        stdDetails.add(String.valueOf(heads[5]));
                        totalMarks += Double.valueOf(heads[5]);
                    }

                    stdDetails.add(String.valueOf(totalMarks));
                    map.put(heads[0]+" "+ heads[1], stdDetails);
                    students.add(heads[0]+" "+ heads[1]);
                    stdTotalMarks.add(totalMarks);

                }
                lineNumbers++;
            }
            myScanner.close();
            //System.out.println(map);
            //System.out.println(map.size());
            for(String std : map.keySet()){
                List<String> total=map.get(std);
                System.out.println(std + ":" + total.get(4));
            }
            printMarksWithThreshold();
        }catch(FileNotFoundException e){
            System.out.println("The file cannot be found");
            e.printStackTrace();
        } 
    }
    
    public void printMarksWithThreshold(){
        System.out.println("Please provide the threshold for maximum marks");
        //setting user input to class variable unitName
        Double threshold = Double.valueOf((new Scanner(System.in).nextLine()));
        for(String std : map.keySet()){
                Double total=Double.valueOf(map.get(std).get(4));
                if(total<threshold){
                    System.out.println(std + ":" + total);
                }
                
            }
    }
    
    public void minMaxMarks(){
    
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void main(String[] args){
        Main myObj= new Main();

    }
}
