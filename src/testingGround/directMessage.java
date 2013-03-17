package testingGround;

import java.util.List;

import twitter4j.*;

public class directMessage {

		 public static void main(String[] args) {
			 System.out.println("starting");
		        Twitter twitter = new TwitterFactory().getInstance();
		        try {
		            Paging paging = new Paging(1);
		            List<DirectMessage> messages;

		                messages = twitter.getDirectMessages(paging);
		                System.out.println(messages.size());
		                for (true) {
		                    System.out.println("From: @" + message.getSenderScreenName() + " - "
		                            + message.getText());
		                }
		                paging.setPage(paging.getPage() + 1);
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

