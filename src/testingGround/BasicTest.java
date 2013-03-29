package testingGround;

import java.util.ArrayList;
import java.util.List;

import model.DMGroups;
import model.DMGroups.DMMessage;

import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class BasicTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Twitter twitter =  TwitterFactory.getSingleton();
		
		DirectMessage[] rlist = new DirectMessage[1];
		DirectMessage[] slist = new DirectMessage[1];
		
		try {

			List<DirectMessage> messages;
			messages = twitter.getDirectMessages();

			

			rlist =  messages.toArray(rlist);
			messages = twitter.getSentDirectMessages();
			slist = messages.toArray(slist);

		}
		catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get messages: " + te.getMessage());
			System.exit(-1);
		}
		
//		ArrayList<User> Steve2 = new ArrayList<User>();
//		for( DirectMessage msg : slist){
//			if(!Steve2.contains(msg.getRecipient())){
//				Steve2.add(msg.getRecipient());
//			}
//			
//		}
//		for(User ussr : Steve2){
//			System.out.println(Steve2.indexOf(ussr));
//			System.out.println(ussr.getScreenName());
//		}
		
//		
		DMGroups groups = new DMGroups(slist, rlist);
		User[] usr = groups.getUsers();
		for(User steve : usr){
			System.out.println(steve.getScreenName() + "*******************************************");
			DMMessage[] messages=groups.getMessages(steve);
			for( DMMessage msg : messages){
				System.out.println(msg.getMessage().getSenderScreenName()+ " to "+msg.getMessage().getRecipientScreenName()+ "\n" + msg.getMessage().getText());
			}
		}
		
		
		
//		for (DirectMessage mess: rlist) {
//			System.out.println(mess.getRecipientScreenName());
//		}
//		
//		for (DirectMessage mess: slist) {
//			System.out.println(mess.getRecipientScreenName());
//		}

	}

}
