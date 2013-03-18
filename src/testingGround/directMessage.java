package testingGround;

import java.util.ArrayList;
import java.util.List;

import twitter4j.*;

public class directMessage {

		 public static void main(String[] args) {
			 String[] messageArray;
			 
			 Twitter twitter = new TwitterFactory().getInstance();
	   	        try {
	   	        	ArrayList<String> receivedMessages = new ArrayList<String>();
	   	            List<DirectMessage> messages;
	   	                messages = twitter.getDirectMessages();
	   	                
	   	                for (DirectMessage message : messages) {
	   	                    receivedMessages.add("From: @" + message.getSenderScreenName() + " - "
	   	                            + message.getText());
	   	                }
	   	                messageArray = new String[receivedMessages.size()];
	   	                for(int i = receivedMessages.size()-1; i>=0; i--){
	   	                	messageArray[i] = receivedMessages.get(i);
	   	                	System.out.println(messageArray[i]);
	   	                }
	   	            System.out.println("done.");
	   	            System.exit(0);
	   	        } catch (TwitterException te) {
	   	            te.printStackTrace();
	   	            System.out.println("Failed to get messages: " + te.getMessage());
	   	            System.exit(-1);
			   	    }
}
}

