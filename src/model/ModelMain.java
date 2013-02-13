/*****************************************************************
The primary class for the model side for the program. Handles all
access to the model.

started February 3, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package model;

import java.util.List;

import javax.swing.AbstractListModel;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class ModelMain {
	
	/**The Twitter object to access all the twitter information*/
	private Twitter twitter;
	
	/*****************************************************************
	Sets up the needed material for the Model side of the program.
	 *****************************************************************/
	public ModelMain () {
		
		twitter =  TwitterFactory.getSingleton();
	}
	
	
	/*****************************************************************

	@throws TwitterException 
	 *****************************************************************/
	public Status[] getHomeTimeline () throws TwitterException {		

		List<Status> statuses;
	
			statuses = twitter.getHomeTimeline();
			
			Status[] list = new Status[1];

            return statuses.toArray(list);
	}
	
	/*****************************************************************

	@throws TwitterException
	 *****************************************************************/
	public void postTweet (String postText) throws TwitterException {		

		Status status;
			status = twitter.updateStatus(postText);
	}
	
	/*****************************************************************
	
	@throws TwitterException
	 *****************************************************************/
	public Status[] searchTweets (String searchWord) throws TwitterException {		
		
            Query query = new Query(searchWord);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                
                Status[] list = new Status[1];

                return tweets.toArray(list);
                
            } while ((query = result.nextQuery()) != null);
	}
	
	
	/*****************************************************************
    
    
    @throws TwitterException
     *****************************************************************/
    public User[] searchUsers (String searchWord) throws TwitterException {     
        
            String user = searchWord;
            int pageNumber = 1;
            do {
                List<User> users = twitter.searchUsers(user, pageNumber);

                
                User[] list = new User[1];
                pageNumber++;
                return users.toArray(list);
                
            } while (pageNumber<=1);
       
    }
    
    /*****************************************************************

    @return User
    @throws TwitterException 
     *****************************************************************/
    public User getMainUser () throws TwitterException {       
        return twitter.showUser("CIS350");
    }

	/*****************************************************************
	Gets the top ten trends for the united states

	@throws TwitterException 
	 *****************************************************************/
	public Trend[] getTrends (int location) throws TwitterException {		

		Trends trends = twitter.getPlaceTrends(location);
		return trends.getTrends();
	}
}