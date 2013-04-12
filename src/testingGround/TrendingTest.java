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
	    
	    System.out.println("enum name (String placeType, int placeCode, String countryName, String countryCode, String placeName");
	    
	    int count = 0;
	    
	    /*gets all the areas that have trending lists*/
	    ResponseList<Location> abc = twitter.getAvailableTrends();
	    for (int i = 0; i < abc.size(); i++) {
//	    	System.out.println (abc.get(i).getCountryName());
//	    	System.out.println (abc.get(i).getCountryCode());
//	    	System.out.println (abc.get(i).getPlaceName());
//	    	System.out.println (abc.get(i).getPlaceCode());
//	    	System.out.println (abc.get(i).getName());
	    	//System.out.println (abc.get(i).getURL());
	    	//System.out.println (abc.get(i).getWoeid());
	    	//System.out.println ();
	    	
	    	
	    	//if (abc.get(i).getPlaceCode() == 12) {
	    		count++;
	    		System.out.println(count);
	    	System.out.print (abc.get(i).getName().toUpperCase());
	    	System.out.print ("( ");
	    	System.out.print (abc.get(i).getPlaceCode());
	    	System.out.print (", \"");
	    	System.out.print (abc.get(i).getCountryName());
	    	System.out.print ("\", \"");
	    	System.out.print (abc.get(i).getCountryCode());
	    	System.out.print ("\", \"");
	    	System.out.print (abc.get(i).getName());
	    	System.out.print ("\"),");
	    	System.out.println ();
	    	System.out.println ();
	    	
	   	    //}
	    }
	    //all towns have place code of 7
	    //all countries have place code of 12
	    //worldwide has place code of 19
	    
	    //gets the top ten trends in a given location 
	    //United States = 23424977
	    //Detroit = 2391585
	    //Chicago = 2379574
	    
	    
	    
//	    Trends trends = twitter.getPlaceTrends(23424977);
//	    Trend trend[] = trends.getTrends();
//	    
//	    for (int i = 0; i < trend.length; i++) {
//	    	System.out.println ("Name: " + trend[i].getName());
//	    	System.out.println ("URL: " + trend[i].getURL());
//	    	System.out.println ("Query: " + trend[i].getQuery());
//	    	System.out.println ();
//	    }

	    }
	}
