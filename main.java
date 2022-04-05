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
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
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
                    ArrayList<String> stdMarks= new ArrayList<String>();
                    Double totalMarks = 0.0;
                    stdMarks.add(String.valueOf(heads[2]));
                    if (3>heads.length-1 || heads[3].equals("")){
                        stdMarks.add("0.0");
                        totalMarks += 0.0;
                    }
                    else{
                        stdMarks.add(String.valueOf(heads[3]));
                        totalMarks += Double.valueOf(heads[3]);
                    }
                    if (4>heads.length-1 || heads[4].equals("")){
                        stdMarks.add("0.0");
                        totalMarks += 0.0;
                    }
                    else{
                        stdMarks.add(String.valueOf(heads[4]));
                        totalMarks += Double.valueOf(heads[4]);
                    }

                    if (5>heads.length-1 || heads[5].equals("")){
                        stdMarks.add("0.0");
                        totalMarks += 0.0;
                    }
                    else{
                        stdMarks.add(String.valueOf(heads[5]));
                        totalMarks += Double.valueOf(heads[5]);
                    }

                    stdMarks.add(String.valueOf(totalMarks));
                    map.put(heads[0]+" "+ heads[1], stdMarks);

                }
                lineNumbers++;
            }
            myScanner.close();
            //System.out.println(map);
            //System.out.println(map.size());
            for(String std : map.keySet()){
                System.out.println(std + ":" + map.get(std));
            }
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
