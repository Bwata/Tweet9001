package testingGround;

import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


public class TrendingTest {
	public static void main(String[] args) throws TwitterException{
		 // The factory instance is re-useable and thread safe.
	    Twitter twitter = TwitterFactory.getSingleton();
	    
	    /*gets all the areas that have trending lists*/
//	    ResponseList<Location> abc = twitter.getAvailableTrends();
//	    for (int i = 0; i < abc.size(); i++) {
//	    	System.out.println (abc.get(i).getCountryName());
//	    	System.out.println (abc.get(i).getCountryCode());
//	    	System.out.println (abc.get(i).getPlaceName());
//	    	System.out.println (abc.get(i).getPlaceCode());
//	    	System.out.println (abc.get(i).getName());
//	    	System.out.println (abc.get(i).getURL());
//	    	System.out.println (abc.get(i).getWoeid());
//	    	System.out.println ();
//	    }
	    
	    //gets the top ten trends in a given location 
	    //United States = 23424977
	    //Detroit = 2391585
	    //Chicago = 2379574
	    Trends trends = twitter.getPlaceTrends(23424977);
	    Trend trend[] = trends.getTrends();
	    
	    for (int i = 0; i < trend.length; i++) {
	    	System.out.println ("Name: " + trend[i].getName());
	    	System.out.println ("URL: " + trend[i].getURL());
	    	System.out.println ("Query: " + trend[i].getQuery());
	    	System.out.println ();
	    }

	    }
	}
