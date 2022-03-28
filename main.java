import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Write a description of class main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class main
{
    // instance variables - replace the example below with your own
    String line;
    Double result;
    String[] tokens;

    /**
     * Constructor for objects of class main
     */
    public main()
    {
        // initialise instance variables
       try{
            File myFile=new File("student.csv");
            Scanner myScanner=new Scanner(myFile);
            while(myScanner.hasNextLine()){
                 line=myScanner.nextLine();
                 tokens = line.split(",");
                 //System.out.println(tokens[0]);
                 System.out.println(line);
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
        main myObj= new main();
        
    }
}
