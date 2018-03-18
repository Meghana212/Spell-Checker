import java.util.Scanner;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
public class Readfile{
    public static HashSet dictionary;
    String inputWord;
     public static void main(String []args){
        System.out.println("starting...");
        setup();
        check();
     }
     
     static void setup()
     {
        int tableSIZE=10;
        dictionary = new HashSet(tableSIZE);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\S.C.R.PESHWA\\Desktop\\mini project\\dictionary.txt"));
            String line = null; 
            while((line = bufferedReader.readLine()) != null) {
                dictionary.add(line);
            }
            bufferedReader.close(); //close file        
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();//print error             
        }
        catch(IOException ex) {
            ex.printStackTrace();//print error
        }
     }
     
     static void check()
     {
    	String inputWord;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter word :");
        inputWord=sc.next();
        if(dictionary.contains(inputWord))
            System.out.println("Exists!");
        else
            System.out.println("Doesn't exist.");
     }
}

