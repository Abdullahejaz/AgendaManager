import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.*;


/**
 * @author Abdullah Ejaz
 */
public class AgendaManager {
	public static int cycle =1;
	public static int left;
	public static int right;
	public static int largest;
	public static Integer showMax = 0;
	public static String maxRule = "";
	public static int lineno =1;
	
	static ArrayList<String> namesList = new ArrayList<String>();
    static ArrayList<Integer> priorityList = new ArrayList<Integer>();
    static Map<Integer, String> map = new HashMap<Integer, String>();
	
    public static int n = priorityList.size();
    public static long startTime = System.currentTimeMillis();
    
	
	public static void readFile() throws IOException {
		
		
		String lines = "";
		
		
	//Creating Scanner inFile to read input file
		Scanner inFile = new Scanner(new File("C:\\Users\\abdul\\Desktop\\test2.txt"));
		
	if (cycle <= 30) {
		
		
		while (inFile.hasNext() && cycle<= 30)
		{
			
			
			//Finding the next Line
			
			lines = inFile.nextLine();
			
			
				if (lines.length()!=0) {
					
					
					String[] rulesArr = lines.trim().split("(\\)\\,) |\\)");
					
					for(String s : rulesArr) {
						String[] singleRuleArr = s.split("(, )");
						
						
					singleRuleArr[0] = singleRuleArr[0].substring(1);	
					
					if (singleRuleArr[0].length() > 5) {
						System.out.println("Invalid rule format");
						continue;
					}
					
						namesList.add(singleRuleArr[0]);
						
					//singleRuleArr[1] = singleRuleArr[1];//.trim();//.substring(0, singleRuleArr[1].length()-0);
					//System.out.println(singleRuleArr[1]);
					
				
						priorityList.add(Integer.parseInt(singleRuleArr[1]));
						
						map.put(Integer.parseInt(singleRuleArr[1]), singleRuleArr[0]);
					
						
						buildHeap(priorityList);
						
						
						
				}
					
					
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CYCLE " +cycle+  " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("Activated Rules---------------------------------" +namesList);
					System.out.println("Corresponding Proiority List--------------------" + priorityList);
					
					 heapExtractMax(priorityList);
					 deleteMax(priorityList);
					 //map.put(priorityList, namesList);
					 
					 cycle++;
					lineno++;
					
				}
                    
				
				
		}
		
            
		}
		while(n !=0 && cycle<=30) {
			 buildHeap(priorityList);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ CYCLE " +cycle+  " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Activated Rules---------------------------------" +namesList);
            System.out.println("Priority List after deleting max----------------" + priorityList);
           
            //heapify(priorityList, 0);
			heapExtractMax(priorityList);
			 deleteMax(priorityList);
			 cycle++;
			
		}
	
		  
		
		inFile.close();
		
		
		
}

	
	public static  void buildHeap(ArrayList<Integer> priorityList) {
		int n = priorityList.size(); 
		 for(int i=n/2; i>=0; i--) {
			 
			 heapify(priorityList, i);	 
		}
		 
		 
		}
	 
	 
	 public static void heapify(ArrayList<Integer> priorityList, int i) {
		n = priorityList.size();
		left = (2*i)+1;
		right = (2*i)+2;
		if(left < n && priorityList.get(left) > priorityList.get(i)) {
			 largest = left;
		}
			 else
			 {
				 largest = i;
				 }
		if(right < n && priorityList.get(right)> priorityList.get(largest)) {
			largest= right;
			
			
		}
		if (largest != i) {
			
			
			int temp = priorityList.get(i);
			priorityList.set(i, priorityList.get(largest));
			priorityList.set(largest, temp);
			
			String tempNames = namesList.get(i);
			namesList.set(i, namesList.get(largest));
			namesList.set(largest, tempNames);
			
			
			heapify(priorityList, largest);
			
			
		}
		
	}
	
	private static void heapExtractMax(ArrayList<Integer> priorityList) {
		int n = priorityList.size();
		if(n > 0) {
		showMax = priorityList.get(0);
		String ExecutedRule = map.get(showMax);
		System.out.println("Executed Rule-----------------------------------[" + ExecutedRule+ "," +showMax+ "]");

		//System.out.println("Rule deleted in cycle " +cycle+ " with its priority is-------------[" +ExecutedRule+ "," +showMax+"]");
		System.out.println("                                                                             ");
		}
	}
	 private static void deleteMax(ArrayList<Integer> priorityList) {
		 if(priorityList.size() > 0) {
		 priorityList.remove(0);
		 namesList.remove(0);
		 }
		
	}
	
	public static void main(String[] args) {
		
		
		
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The file is not compatible");
			
		}
		long endTime = System.currentTimeMillis();
        System.out.println("\nThe running time is " + (endTime - startTime) + " milliseconds");

	}

}
