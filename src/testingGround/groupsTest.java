package testingGround;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class groupsTest {

	/**
	 * @param args
	 * @throws TwitterException 
	 */
	public static void main(String[] args) throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
	    List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline.");
	    
	    for (Status status : statuses) {
	    	
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }
	}
	    public void createGroup(String groupName) throws IOException{
	    	//Maybe a try catch block with create file for if no file exists yet?
	    	FileWriter fileWriter = new FileWriter("groups.txt");
	    	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    	bufferedWriter.write(groupName+"\n");
	    }

	    public void createFile() throws FileNotFoundException{
	    	FileReader fileReader = new FileReader("groups.txt");
	    }
	    public void addToGroup(String group, String screenName) throws IOException{
	    	FileWriter fileWriter = new FileWriter("groups.txt");
	    	BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	    	FileReader fileReader = new FileReader("groups.txt");
	    	BufferedReader bufferedReader = new BufferedReader(fileReader);
	    	while(bufferedReader.readLine() != group){
	    	//finds the start of the group	
	    	}
	    	while(bufferedReader.readLine() != "\n"){
	    		//finds the next open space
	    	}
	    	bufferedWriter.write(screenName+"\n");
	    }
	    public void filterResults(String groupName, Status[] status) throws IOException{
	    	if(groupName != null){
	    	String userID;
	    	FileReader fileReader = new FileReader("groups.txt");
	    	BufferedReader bufferedReader = new BufferedReader(fileReader);
	    	while(bufferedReader.readLine() != groupName){
	    	//finds the start of the group	
	    	}
	    	
	    	while((userID = bufferedReader.readLine())!= "\n"){
	    		for(int i = 0; i<status.length; i++){
	    			if((int)status[i].getId()!=Integer.parseInt(userID)){
	    				Arrays.asList(status).remove(i);
	    			}
	    		
	    		}
	    	}
	    }
	    }
}


