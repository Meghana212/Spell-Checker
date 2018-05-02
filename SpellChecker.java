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
			tempWord=remove(tempWord);
			tempWord=transformC(tempWord);
			tempWord=transformD(tempWord);
			
			tempWord=transformG(tempWord);
			tempWord=transformH(tempWord);
			tempWord=transform9_10(tempWord);
			tempWord=transformQ(tempWord);
			tempWord=transformS(tempWord);
			tempWord=transformT(tempWord);
			tempWord=transformV(tempWord);
			tempWord=transformW(tempWord);
			tempWord=transformX(tempWord);
			tempWord=transformY(tempWord);
			tempWord=transformZ(tempWord);
			
			tempWord=reduceVowels(tempWord); 
			return tempWord;
		}
		
		//method to remove first letter if "kn" etc. rule 3. and to remove b from end if "nb" etc. rule 2.
		static String remove(String word)
		{
			if(word.length()>2)
				if((word.substring(0,2)=="GN")||(word.substring(0,2)=="KN")||(word.substring(0,2)=="PN")||(word.substring(0,2)=="AE")||(word.substring(0,2)=="WR"))
					word=word.substring(1);
			
			if(word.length()>2)
				if(word.substring(word.length()-3)=="MB")
					word=word.substring(0,word.length()-1);
			
			return word;
		}
		
		//method to simplify Z. rule 18
		static String transformZ(String word)
		{
			for(int curi=0;curi<word.length();curi++)
			{
				if(word.substring(curi,curi+1)=="Z")
					word=word.substring(0,curi)+"S"+word.substring(curi+1);
			}
			return word;
		}
		
		//method to simplify Y. rule 17
		static String transformY(String word)
		{
			for(int curi=0;curi<word.length()-2;curi++)
			{
				for(int curi=0;curi<word.length()-2;curi++)
				{
					if(word.substring(curi,curi+1)=="Y")
						if((word.substring(curi+1,curi+2)!="A")||(word.substring(curi+1,curi+2)!="E")||(word.substring(curi+1,curi+2)!="I")||(word.substring(curi+1,curi+2)!="O")||(word.substring(curi+1,curi+2)!="U"))
							word=word.substring(0,curi)+word.substring(curi+1);
				}
			}
			return word;
		}
		
		//method to simplify X. rule 16
		static String transformX(String word)
		{
			if(word.substring(0,1)=="X")
				word="S"+word.substring(2);
			for(int curi=1;curi<word.length()-1;curi++)
				if(word.substring(curi,curi+1)=="X")
					word=word.substring(0,curi)+"KS"+word.substring(curi+1);
			return word;
		}
		
		//method to simplify W. rule 15
		static String transformW(String word)
		{
			if(word.length()>2)	
			{
				if(word.substring(0,2)=="WH")
					word="W"+word.substring(2);
				for(int curi=0;curi<word.length()-2;curi++)
				{
					if(word.substring(curi,curi+1)=="W")
						if((word.substring(curi+1,curi+2)!="A")||(word.substring(curi+1,curi+2)!="E")||(word.substring(curi+1,curi+2)!="I")||(word.substring(curi+1,curi+2)!="O")||(word.substring(curi+1,curi+2)!="U"))
							word=word.substring(0,curi)+word.substring(curi+1);
				}
			}
			return word;
		}
		
		//method to simplify V. rule 14
		static String transformV(String word)
		{
			for(int curi=0;curi<word.length()-1;curi++)
			{
				if(word.substring(curi,curi+1)=="V")
					word=word.substring(0,curi)+"F"+word.substring(curi+1);
			}
			return word;
		}
		
		//method to simplify S. rule 12
		static String transformS(String word)
		{
			for(int curi=0;curi<word.length()-2;curi++)
			{
				if((word.substring(curi,curi+2)=="SH")||(word.substring(curi,curi+3)=="SIO")||(word.substring(curi,curi+3)=="SIA"))
					word=word.substring(0,curi)+"X"+word.substring(curi+1);
			}
			return word;
		}
		
		//method to simplify T. rule 13
		static String transformT(String word)
		{
			for(int curi=0;curi<word.length()-3;curi++)
			{
				if((word.substring(curi,curi+3)=="TIO")||(word.substring(curi,curi+3)=="TIA"))
					word=word.substring(0,curi)+"X"+word.substring(curi+1);
				if((word.substring(curi,curi+3)=="TCH"))
					word=word.substring(0,curi)+word.substring(curi+1);
			}
			for(int curi=0;curi<word.length()-2;curi++)
				if((word.substring(curi,curi+2)=="TH"))
					word=word.substring(0,curi)+word.substring(curi+1);
			return word;
		}
		
		//method to simplify Q. rule 11
		static String transformQ(String word)
		{
			for(int curi=0;curi<word.length()-1;curi++)
			{
				if(word.substring(curi,curi+1)=="Q")
					word=word.substring(0,curi)+"K"+word.substring(curi+1);
			}
			return word;
		}
		
		//method to simplify CK and PH (rules 9 and 10)
		static String transform9_10(String word)
		{
			for(int curi=0;curi<word.length()-2;curi++)
			{
				if(word.substring(curi,curi+2)=="CK")
					word=word.substring(0,curi)+word.substring(curi+1);
				if(word.substring(curi,curi+2)=="PH")
					word=word.substring(0,curi)+"F"+word.substring(curi+2);
			}
			return word;
		}
		
		//method to simplify H. rule 8
		static String transformH(String word)
		{
			for(int curi=0;curi<word.length()-2;curi++)
			{
				if(word.substring(curi,curi+2)=="AH"||word.substring(curi,curi+2)=="IH"||word.substring(curi,curi+2)=="EH"||word.substring(curi,curi+2)=="OH"||word.substring(curi,curi+2)=="UH")
					if(word.substring(curi+2,curi+3)!="A"||word.substring(curi+2,curi+3)!="E"||word.substring(curi+2,curi+3)!="I"||word.substring(curi+2,curi+3)!="O"||word.substring(curi+2,curi+3)!="U")
					{
						word=word.substring(0,curi)+word.substring(curi+1);
					}
			}
			return word;
		}
		
		//method to simplify G. rules 6 and 7
		static String transformG(String word)
		{
			String tempWord="";
			
			//for rule 6
			if(word.length()>2)	
				if((word.substring(word.length()-2)=="GN"))
					tempWord=word.substring(0,word.length()-3)+word.substring(word.length()-1);
			
			if(word.length()>5)
				if(word.substring(word.length()-5).equals("GNED"))
					tempWord=word.substring(0,word.length()-5)+word.substring(word.length()-3);
			
			if(tempWord!="")
				word=tempWord;
			
			if(word.length()>2)	
				for(int curi=0;curi<word.length()-2;curi++)
				{
					if(word.substring(curi,curi+2).equals("GH"))
						if(word.substring(curi+2,curi+2)=="A"||word.substring(curi+2,curi+2)=="E"||word.substring(curi+2,curi+2)=="I"||word.substring(curi+2,curi+2)=="O"||word.substring(curi+2,curi+2)=="U")
						{
							tempWord=word.substring(0,curi)+word.substring(curi+1);
							word=tempWord;
						}
				}
				
			//for rule 7
			for(int curi=0;curi<word.length()-2;curi++)
			{
				if(word.substring(curi,curi+2)=="GI"||word.substring(curi,curi+2)=="GE"||word.substring(curi,curi+2)=="GY")
					if(word.substring(curi-1,curi+1)!="GG")
						tempWord=word.substring(0,curi)+"J"+word.substring(curi+1);
				else
					tempWord=word.substring(0,curi)+"K"+word.substring(curi+1);	
			}
			if(tempWord.equals(""))
				return word;
			else
				return tempWord;
		}
		
		
		//method to simplify D. rule number 5
		static String transformD(String word) {
			String tempWord="";
			for(int curi=0;curi<word.length()-1;curi++)
			{
				if(word.substring(curi,curi+1).equals("D"))
				{
					if(curi+3<=word.length()) 
					{
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
    	static String dictWord; //to store dictionary word
    	static String dictMet; //to store metaphone of the dictionary word
    	static String suggestions[]=new String[200];
    	static int order[]=new int[200];
    	
    	
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
	        inputWord=sc.nextLine();
	        inputWord=inputWord.toLowerCase();
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
	        			if(d<2)
	        			{	
	        				suggestions[c]=dictWord;
	        				order[c]=d;
	        				c++;
	        			}
	        	}
	        	int temp,index;
	        	String temps;
	        	for(int i=0;i<4;i++)
	        	{
	        		int max=order[i];
	        		index=i;
	        		for(int j=i+1;j<c;j++)
	        		{
	        			if(order[j]<max)
	        			{
	        				max=order[j];
	        				index=j;
	        			}
	        		}
	        		if(i!=index)
	        		{
	        			//swapping in order
	        			temp=order[i];
	        			order[i]=order[index];
	        			order[index]=temp;
	        			//swapping in suggestions
	        			temps=suggestions[i];
	        			suggestions[i]=suggestions[index];
	        			suggestions[index]=temps;
	        		}
	        	}
	        	
	        	for(int i=0;i<4;i++)
	        	{
	        		if(suggestions[i]!=null)
	        			System.out.println(suggestions[i]);
	        	}
	        	
	        }
	        	
	     }     		
	     
	}