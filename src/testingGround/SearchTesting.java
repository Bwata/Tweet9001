package testingGround;

import java.util.List;

import twitter4j.*;

public class SearchTesting {
	


	    public static void main(String[] args) {
	        
	        Twitter twitter = new TwitterFactory().getInstance();
	        try {
	            Query query = new Query("octagoncow");
	            QueryResult result;
	            do {
	                result = twitter.search(query);
	                List<Status> tweets = result.getTweets();
	                for (Status tweet : tweets) {
	                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	               System.out.println("TWEET LIST SIZE :" + tweets.size());
	                }
	            } while ((query = result.nextQuery()) != null);
	            System.exit(0);
	        } catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to search tweets: " + te.getMessage());
	            System.exit(-1);
	        }
	    }
	
}
