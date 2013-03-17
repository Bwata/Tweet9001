package Release2TestingGrounds;

import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.util.Scanner;
public class directMessaging {
	 public static void main(String[] args) {
		 Scanner scanWord = new Scanner(System.in);
		 System.out.println("Enter a recipient: ");
		 String recipient = scanWord.next();
		 System.out.println("Enter a message: ");
		 String words = scanWord.next();

		 if(!recipient.substring(0,1).equals("@")){
			 recipient= "@"+recipient;
		 }
		 
		 System.out.println("END");
		 
	        if (recipient.length() < 2) {
	            System.out.println("Usage: java twitter4j.examples.directmessage.SendDirectMessage [recipient screen name] [message]");
	            System.exit(-1);
	        }
	        Twitter twitter = new TwitterFactory().getInstance();
	        try {
	            DirectMessage message = twitter.sendDirectMessage(recipient, words);
	            System.out.println("Direct message successfully sent to " + message.getRecipientScreenName());
	            System.exit(0);
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to send a direct message: " + te.getMessage());
	            System.exit(-1);
	        }
	    }

}
