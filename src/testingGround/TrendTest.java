package testingGround;

import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import utilities.TrendLocations;

public class TrendTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Twitter twitter = TwitterFactory.getSingleton();
		
		ResponseList<Location> abc;
		try {
			abc = twitter.getAvailableTrends();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			abc = null;
			e.printStackTrace();
		}
		
		TrendLocations tl = new TrendLocations(abc);

	}

}
