
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataManager {

	int timervalue;
	int degree;
	int jobcounter = 0;
	int addcounter = 0;
	int time = 0;
	int CPUtime = 0;
	int JOBtime = 0;
	
	boolean jobBool = false;
	int bigCounter = 0;
	
	Job[] FCFSarray;
	boolean[] FCFSarraybool;
	boolean[] FCFSarrayoutput;
	
	boolean allJobsDone = false;
	int jobNumber = 0;
	int origcounter = 0;
	

/////////////////////////////////////////////////////////////////////////////////Retrieve Jobs
	public void countJobs() throws FileNotFoundException {
		Scanner scan2 = new Scanner(new File("data.txt"));
		timervalue = scan2.nextInt();
		degree = scan2.nextInt();
		scan2.nextLine();
		
		//Retrieve individual jobs
		while(scan2.hasNextLine()) {
			
			//Job Generics
			String str = scan2.nextLine();
			
			//add to array size
			jobcounter++;
		}
																
		FCFSarray = new Job[jobcounter+1];
		FCFSarraybool = new boolean[jobcounter+1];
		//System.out.println("Job Counter: "+jobcounter);
		scan2.close();
	}
/////////////////////////////////////////////////////////////////////////////////Retrieve File	
	public void retrieveFile() throws FileNotFoundException {
		
		//Get initial values
		Scanner scan = new Scanner(new File("data.txt"));
		String returnValue = "";
		timervalue = scan.nextInt();
		degree = scan.nextInt();
		scan.nextLine();
		
		//Retrieve individual jobs
		while(scan.hasNextLine()) {
			
			//Job Generics
			String str = scan.nextLine();
			StringTokenizer line = new StringTokenizer(str);
			int lineSize = line.countTokens();		
			String name = line.nextToken();
			String priority1 = line.nextToken();
			int priority = Integer.parseInt(priority1);
			returnValue += "\n("+priority + ")Job Name: " + name + "\n";
			int singleJobTime = 0;
			
			//Single Job List
			LinkedList<String> temp = new LinkedList<String>();
			for(int i = 2; i < ((lineSize-1)) ;i=i+2)
			{
				//CPU burst
				String before = line.nextToken();
				returnValue += "CPU Burst Time: " + before + "	";
				temp.add(before);
				singleJobTime += Integer.parseInt(before);
				//Job Type
				String before2 = line.nextToken();
				returnValue += "Units of Data: " + before2 + "\n";
				temp.add(before2);
				int thisDATAint = 0;
				if(before2.equals("T")){
					thisDATAint = 200;
				}
				else{
					thisDATAint = 50;
				}
				singleJobTime += thisDATAint;
			}
			//final CPU burst
			String before3 = line.nextToken();
			returnValue += "CPU Burst Time: " + before3 + "\n";
			temp.add(before3);
			singleJobTime += Integer.parseInt(before3);
			
			//add to array size
													
			addJobs(name,priority,temp, singleJobTime);
		}
																
		scan.close();
	}
/////////////////////////////////////////////////////////////////////////////////Add Jobs
	public void addJobs(String name, int priority,LinkedList<String> lister, int time)
	{
		
		//Add Job to Job List
		Job node = new Job();
		node.jobName = name;
		node.jobPriority = priority;
		node.list = lister;
		node.totalTime = time;
		
		//Add JobList to Array
		FCFSarray[addcounter] = node;
		System.out.println(FCFSarray[addcounter].jobName);
		
		for(int i = 0; i < FCFSarray[addcounter].list.size(); i++){
			System.out.print(FCFSarray[addcounter].list.get(i)+", ");
		}
		System.out.println();
		System.out.println();
		addcounter++;
		
	}
/////////////////////////////////////////////////////////////////////////////////FCFS Round	
	public void FCFSRound()
	{
		while(allJobsDone == false){
			
				for(int i = 0; i < FCFSarray.length-1; i++) {
					
					if(FCFSarraybool[i] == false) {
					int before = -1;
					String currentstr = FCFSarray[i].list.get(FCFSarray[i].index);
					
					//Check if T,I,O and change
					if(currentstr.equals("T")){
							before = 200;
							FCFSarray[i].list.set(FCFSarray[i].index, before +"");
						}
					else if(currentstr.equals("I") || currentstr.equals("O")){
							before = 50;
							FCFSarray[i].list.set(FCFSarray[i].index, before +"");
							
					}else{
							//before = Integer.parseInt(currentstr);
					}
						
					int current = Integer.parseInt(FCFSarray[i].list.get(FCFSarray[i].index));
					
						//Take Away 25 from List Value
						System.out.print("\n("+time+")"+FCFSarray[i].jobName +" running " + current+"\n");
						current = current - 25;
						
						FCFSarray[i].list.set(FCFSarray[i].index, current+"");
						System.out.print("("+time+")"+FCFSarray[i].jobName +" timed out ");
						
						//Increment Index of List
						if(current <= 0) {
							int indexer = FCFSarray[i].index;
							indexer++;
							FCFSarray[i].index = indexer;
							
							//Check  if List is Complete
							if(FCFSarray[i].list.size()-1 <= FCFSarray[i].index){
								FCFSarraybool[i] = true;
								System.out.print("\n"+FCFSarray[i].jobName + " List is FINISHED\n");
							}
						}
						//Counters
						time += 25;
						/*origcounter++;
						if(origcounter >= 300){
							System.out.println("Infinite loop");
							System.exit(0);
						}*/
						//Check End
						int boolcounter = 0;
						for(int p = 0; p < FCFSarraybool.length-1; p++){
							if(FCFSarraybool[p] == true)
								boolcounter++;
						}
						if(boolcounter == FCFSarraybool.length-1){
							allJobsDone = true;
						}
				}else
				{
				//do nothing		
				}
			}
				
		}	
}


	public void finalTime()
	{
		System.out.println("\nCPU Time: " + CPUtime);
		System.out.println("JOB Time: " + JOBtime);
		System.out.println("TOTAL Time: " + time + "\n");
	}

}