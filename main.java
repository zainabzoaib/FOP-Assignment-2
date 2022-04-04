import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<Double> A1;
    private ArrayList<Double> A2;
    private ArrayList<Double> A3;
    private String row;
    private String [] heads;

    /**
     * Constructor for objects of class main
     */
    public Main()
    {
        // initialise instance variables
    int lineNumbers=0;
       try{
            File myFile=new File("student.csv");
            Scanner myScanner=new Scanner(myFile);
            while(myScanner.hasNextLine()){
                 row=myScanner.nextLine();
                 if(lineNumbers==0){
                     heads = row.split(",");
                     unitName = heads[0];
                     System.out.println(unitName);
                    }
                    else if(lineNumbers>1){
                    heads = row.split(",");
                    System.out.printf("%S %S %S %n",heads[0],heads[1],heads[2]);
                    }
                    lineNumbers++;
            }
            myScanner.close();
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
