/*****************************************************************
The primary class for the model side for the program. Handles all
access to the model.

started February 3, 2013
@author Thomas Verstraete, Tyler Hutek, Rui Takagi, Andrew Jarvis
@version Winter 2013
 *****************************************************************/
package model;

import java.io.File;
import java.util.List;
import twitter4j.DirectMessage;
import twitter4j.Location;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RelatedResults;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import utilities.TrendLocations;

/*****************************************************************
The main model of the program. This updates the view.
 *****************************************************************/
public class ModelMain {

    /**The Twitter object to access all the twitter information.*/
	private Twitter twitter;

	/*****************************************************************
    Sets up the needed material for the Model side of the program.
	 *****************************************************************/
	public ModelMain() {

		twitter =  TwitterFactory.getSingleton();
	}

	/*****************************************************************
    Creates a list of statuses for the users to see.
    @return An array of Statuses.
    @throws TwitterException
	 *****************************************************************/
	public Status[] getHomeTimeline() throws TwitterException {

		List<Status> statuses;

		statuses = twitter.getHomeTimeline();

		Status[] list = new Status[1];

		return statuses.toArray(list);
	}

	/*****************************************************************
    Posts a tweet in the users account.
    @param postText The string of text to be posted as a status.
    @throws TwitterException
	 *****************************************************************/
	public void postTweet(String postText) throws TwitterException {

		twitter.updateStatus(postText);
	}

	/*****************************************************************
    Posts an image with a message.
    @param message the message to upload the photo with
    @param image the picture to be posted
    @throws TwitterException
	 *****************************************************************/
    public void postTweet(String message, File image)
			throws TwitterException {

		if(image != null){
			StatusUpdate status = new StatusUpdate(message);
			status.setMedia(image);
			twitter.updateStatus(status);
		}
	}

	/*****************************************************************
    Searches for user tweets based on a given string.
    @param searchWord the string to be searched for.
    @return an array of tweets containing the searched phrase.
    @throws TwitterException
	 *****************************************************************/
	public Status[] searchTweets(String searchWord) throws TwitterException {

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
    Search for users based on a given string.

    @param searchWord the string to be searched for.
    @return an array of users
    @throws TwitterException
	 *****************************************************************/
	public User[] searchUsers(String searchWord) throws TwitterException {

		String user = searchWord;
		int pageNumber = 1;
		do {
			List<User> users = twitter.searchUsers(user, pageNumber);


			User[] list = new User[1];
			pageNumber++;
			return users.toArray(list);

		} while (pageNumber <= 1);
	}

	/*****************************************************************
     Creates a user based on the main user.
    @return User
    @throws TwitterException
	 *****************************************************************/
	public User getMainUser() throws TwitterException {
		return twitter.showUser("CIS350");
	}

	/*****************************************************************
    Gets the top ten trends for the united states.

    @param location the location of trends that the user wants.
    @throws TwitterException
    @return the trends found based on location.
	 *****************************************************************/
	public Trend[] getTrends(int location) throws TwitterException {

		Trends trends = twitter.getPlaceTrends(location);
		return trends.getTrends();
	}

	/*****************************************************************
	Collects all possible trending locations.

	@return TrendLocations Collection of All trend locations.
	@throws TwitterException
	 *****************************************************************/
	public TrendLocations getAllTrends () throws TwitterException {

		ResponseList<Location> locations = twitter.getAvailableTrends();
		return new TrendLocations(locations);
	}

	/*****************************************************************
	Sends a Direct Message to the parameter recipient with the message
	words.

	@param recipient String User to recieve DM.
	@param words String message to be sent.
	@return boolean If the message was successfully sent.
	 *****************************************************************/
	public boolean directMessaging(String recipient, String words) {

		if(!recipient.substring(0,1).equals("@")){
			recipient= "@"+recipient;
		}

		if (recipient.length() < 2) {
			return false;
		}

		try {
			DirectMessage message = twitter.sendDirectMessage(recipient, words);
			return true;
		} catch (TwitterException te) {
			te.printStackTrace();
			return false;
		}
	}

	/*****************************************************************
    Gets a list of received direct messages and converts them to an
    arrayList.

    @return Direct Message[] List of messages sent to this user.
	 *****************************************************************/
	public DirectMessage[] receiveDirectMessage() {

		Twitter twitter = new TwitterFactory().getInstance();
		try {

			List<DirectMessage> messages;
			messages = twitter.getDirectMessages();

			DirectMessage[] list = new DirectMessage[1];

			return messages.toArray(list);

		}
		catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get messages: " + te.getMessage());
			System.exit(-1);
		}
		return null;
	}

	/*****************************************************************
	used to update profile information.
	 *****************************************************************/
    public void update(String nam, String url, String loc, String des){
    	    	
    	try {
			twitter.updateProfile(nam, url, loc, des);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /*****************************************************************
	updates profile picture
     *****************************************************************/
    public void updatePic(File img){
    	System.out.println("MM 232");
    	try {
			twitter.updateProfileImage(img);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /*****************************************************************
     * method for getting the replies to a status.
     * @param statusID
     * @return Status[] an array of statuses/replies
     * @throws TwitterException
     *****************************************************************/
    public Status[] getConversations(long statusID) throws TwitterException{

    	RelatedResults results = twitter.getRelatedResults(statusID);
    	List<Status> conversations = results.getTweetsWithConversation();

    	Status[] list = new Status[1];

    	Status originalStatus = twitter.showStatus(statusID);
//    	if (conversations.isEmpty()) {
//    		System.out.println("MM 262");
//    	    conversations = results.getTweetsWithReply();
//    	}
//
//    	if (conversations.isEmpty()) {
//    		System.out.println("mm 267");
//    	    conversations = new ArrayList<Status>();
//    	    Status status = originalStatus;
//    	    while (status.getInReplyToStatusId() > 0) {
//    	        status = twitter.showStatus(status.getInReplyToStatusId());
//    	        conversations.add(status);
//    	    }
//    	}

    	if (!conversations.isEmpty()) {
    		System.out.println("mm 277");
    	    conversations.add(originalStatus);
    	}

        return conversations.toArray(list);
    }
}