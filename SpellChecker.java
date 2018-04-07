import java.util.*;
//import java.awt.List;
import java.io.*;

/*class to find metaphones. this is based on the metaphone rules given in Wikipedia page of Metaphone*/
	class Metaphone{
  
		/*method to simplify the given word using metaphone rules*/	
		static String find_Metaphones(String word) //main method where we use the rules to find metaphones
		{
			String tempWord="" ;
			word=word.toUpperCase();
			tempWord=word;
			tempWord=reduceDouble(tempWord);
			if(tempWord.substring(0,2).equals("KN")||tempWord.substring(0,2).equals("GN")||tempWord.substring(0,2).equals("PN")||tempWord.substring(0,2).equals("AE")||tempWord.substring(0,2).equals("WR"))
				tempWord=tempWord.substring(1);
			if(tempWord.substring(tempWord.length()-2).equals("MB"))
				tempWord=tempWord.substring(0,tempWord.length()-1);
			tempWord=transformC(tempWord);
			tempWord=transformD(tempWord);
			tempWord=reduceVowels(tempWord); //this has to be last step. do all other reductions based on rule before this.
			return tempWord;
		}
		
		
		//method to simplify D. rule number 5
		static String transformD(String word) {
			String tempWord="";
			for(int curi=0;curi<word.length();curi++)
			{
				if(word.substring(curi,curi+1).equals("d"))
				{
					if(curi+3<=word.length()) {
					if(word.substring(curi+1,curi+3).equals("GE")||word.substring(curi+1,curi+3).equals("GY")||word.substring(curi+1,curi+3).equals("GI"))
						tempWord=word.substring(0,curi)+"J"+word.substring(curi+1);
					}
					else
						tempWord=word.substring(0,curi)+"T"+word.substring(curi+1);
				}
			}
			if(tempWord.equals(""))
				return word;
			else
				return tempWord;
		}
		
		//method to simplify according to rules if C is present. (rule number 4)
		static String transformC(String word) {
			String tempWord="";
			for(int curi=0;curi<word.length();curi++)
			{
				if(word.substring(curi,curi+1).equals("C"))
				{
					if(curi+3<=word.length()) {
					if(word.substring(curi+1,curi+3).equals("IA"))
						tempWord=word.substring(0,curi)+"X"+word.substring(curi+1);
					}
					else if(word.substring(curi,curi+1).equals("I")||word.substring(curi,curi+1).equals("E")||word.substring(curi,curi+1).equals("Y"))
						tempWord=word.substring(0,curi)+"S"+word.substring(curi+1);
					else if(word.substring(curi,curi+1).equals("H"))
					{
						if(curi+3<=word.length()) {
						if(word.substring(curi-1,curi+1).equals("SCH"))
							tempWord=word.substring(0,curi)+"K"+word.substring(curi+1);
						}
						else
							tempWord=word.substring(0,curi)+"X"+word.substring(curi+1);
					}
					else
						tempWord=word.substring(0,curi)+"K"+word.substring(curi+1);
				}
			}
			if(tempWord.equals(""))
				return word;
			else
				return tempWord;
		}
		
		//method to remove duplicate letters except CC
		static String reduceDouble(String word) {
			String tempWord="";
			for(int curi=0;curi<word.length()-1;curi++)
			{
				tempWord+=word.substring(curi,curi+1);
				if(word.substring(curi,curi+1).equals(word.substring(curi+1,curi+2)))
					if(word.substring(curi,curi+1).equals("C")) 
						continue;
					else
						curi++;
			}
			tempWord+=word.substring(word.length()-1);
			return tempWord;
		}
		
		//method to reduce vowels. we have to eliminate all the vowels except if they are the first letter.
		static String reduceVowels(String word) {
			String letter;
			String tempWord=word.substring(0,1);
			for(int curi=1;curi<word.length();curi++) //curi is current letter index
			{
				letter=word.substring(curi,curi+1);
				//System.out.println(letter);
				if(letter.equals("A")||letter.equals("E")||letter.equals("I")||letter.equals("O")||letter.equals("U"))
				{	tempWord=tempWord.concat("A"); //don't add the vowel into new word 
				}
				else
					tempWord+=letter;
			}
			return tempWord;
		}
	}
  
  /*main class where the input is taken and checked initially*/
  public class SpellChecker extends Metaphone{
	  	public static Set<String> dictionary;
	  	static int c; //to count number of suggestions printed
	    static String inputWord;
	    static String newMetaphone;
    	static String dictWord=new String(); //to store dictionary word
    	static String dictMet=new String(); //to store metaphone of the dictionary word
    	static String suggestions[]=new String[30];
    	static int order[]=new int[30];
    	
    	
	     public static void main(String []args){
	        System.out.println("starting...");
	        setup();
	        check();
	     }
	     
	     static void setup()
	     {
	    	 int tableSIZE=13;
	    	 dictionary= new HashSet<String>(tableSIZE);
	        try {
	            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\ADE152\\Documents\\dictionary.txt"));
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
	        Scanner sc=new Scanner(System.in);
	        System.out.println("Enter word :");
	        inputWord=sc.next();
	        //long t1=System.nanoTime();
	        int flag=0;
	        //IfWrong obj=new IfWrong();
			Iterator<String> itr=dictionary.iterator();
			//System.out.println(itr.next());
        	while(itr.hasNext())
        	{
        		dictWord=itr.next();
        		if(dictWord.equals(inputWord))
        		{
        			System.out.println("Exists!");
        			flag=1;
        			break;
        		}
        	}
	        if(flag!=1)
	        {
	        	c=0;
	            System.out.println("Doesn't exist.");
	        	newMetaphone=find_Metaphones(inputWord);
	        	//System.out.println(newMetaphone);
	        	System.out.println("The correct word may be:");
	        	/*while(itr.hasNext())
	        		System.out.println(itr.next());*/
	        	Edit_Distance dist=new Edit_Distance();
	        	itr=dictionary.iterator();
	        	while(itr.hasNext())
	        	{
	        		dictWord=itr.next();
	        		dictMet=find_Metaphones(dictWord);
	        		int d=dist.editDistDP(newMetaphone,dictMet,newMetaphone.length(),dictMet.length());
	        		//System.out.println(newMetaphone+" "+dictSets[0]+" "+dictSets[1]+" "+d);
	        		if(d<4)
	        		{	
	        			suggestions[c]=dictWord;
	        			order[c]=d;
	        			c++;
	        		}
	        	}
	        	int count=0;
	        	for(int i=0;i<4;i++)
	        	{
	        		for(int j=0;j<c;j++)
	        		{	if(order[j]==i)
	        			{
	        				System.out.println(suggestions[j]);
	        				count++;
	        			}
	        		if(count==4)
	        			break;
	        		}
	        		if(count==4)
	        			break;
	        	}
	        }
	        	
	     }     		
	}