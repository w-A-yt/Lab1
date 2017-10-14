package lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class ReadFromFile {
    public  static List<String >  getLowerCaseWords(File file) {
    	System.out.println("hello1");
        Scanner scanner = null;
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        String text = "";
        List<String > words = new ArrayList<>();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(scanner!=null){
        while(scanner.hasNextLine()){
            text+=scanner.nextLine();
        }
            scanner.close();
        }
        Matcher matcher = pattern.matcher(text);
        int number6=0;
        while (matcher.find()){
        	String s=matcher.group().toLowerCase();
        	//int flag=exist(words,s);
        	//words.
        	/*if(flag==1)
        	{
        		
        	}
        	else
        	{
        		number++;
        		g.
        	}*/
            words.add(s);
        }
        
        
     
        return words;
    }
    
    public static int exist(List<String> words,String s)
    {
    	int flag=0;
    	if(words.contains(s))
    	{
    		flag=1;
    	}
    	return flag;
    }
     
    public static void WriteToFile(List<String> words ,File file){
        
        FileWriter writer=null;
        try {
            writer = new FileWriter(file);
            for (String word : words) {
                writer.write(word+" ");
            }
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(writer!=null){
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }  
            }
        }
    }
    /*static class InnerTest{
        public static void main(String[] args) {
        	File file = new File("C:/lab/input.txt");
            List<String> words=getLowerCaseWords(file);
            WriteToFile(words,new File("C:/lab/output.txt"));
        }
    }*/
     
}

