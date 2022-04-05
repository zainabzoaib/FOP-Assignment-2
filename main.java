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
    private ArrayList<String> lastName;
    private ArrayList<String> firstName;
    private ArrayList<Integer> studentId;
    private ArrayList<Double> assignment1;
    private ArrayList<Double> assignment2;
    private ArrayList<Double> assignment3;
    private String row;
    private String [] heads;

    /**
     * Constructor for objects of class main
     */
    public Main()
    {
        // initialise instance variables
        int lineNumbers=0;
        HashMap<String, List<Double>> map = new HashMap<String, List<Double>>();
        try{
            File studentFile=new File("student.csv");
            Scanner myScanner=new Scanner(studentFile);
            while(myScanner.hasNextLine()){
                row=myScanner.nextLine();
                if(lineNumbers==0){
                    heads = row.split(",");
                    unitName = heads[0];
                    System.out.println(unitName);
                }
                else if(lineNumbers>1){
                    heads = row.split(",");
                    ArrayList<Double> stdMarks= new ArrayList<Double>();
                    if (3>heads.length-1 || heads[3].equals("")){
                        stdMarks.add(0.0);
                    }
                    else{
                        stdMarks.add(Double.valueOf(heads[3]));
                    }
                    if (4>heads.length-1 || heads[3].equals("")){
                        stdMarks.add(0.0);
                    }
                    else{
                        stdMarks.add(Double.valueOf(heads[4]));
                    }

                    if (5>heads.length-1 || heads[3].equals("")){
                        stdMarks.add(0.0);
                    }
                    else{
                        stdMarks.add(Double.valueOf(heads[5]));
                    }

                    stdMarks.add(Double.valueOf( heads[2]));
                    map.put(heads[0]+" "+ heads[1], stdMarks);

                }
                lineNumbers++;
            }
            myScanner.close();
            System.out.println(map);
            System.out.println(map.size());
        }catch(FileNotFoundException e){
            System.out.println("The file cannot be found");
            e.printStackTrace();
        }
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
