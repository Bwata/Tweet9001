/*****************************************************************
The primary class for the model side for the program. Handles all
access to the model.

started February 3, 2013
@author Thomas Verstraete, Tyler Hutek, Rui Takagi, Andrew Jarvis
@version Winter 2013
 *****************************************************************/
package model;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.DMGroups.DMMessage;
import twitter4j.DirectMessage;
import twitter4j.Location;
import twitter4j.Paging;
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
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import utilities.TrendLocations;

/*****************************************************************
The main model of the program. This updates the view.
 *****************************************************************/
public class ModelMain {
	/*****************************************************************
	The user's access token.
	 *****************************************************************/
	AccessToken accessToken;
	/*****************************************************************
	The user's request token.
	 *****************************************************************/
	RequestToken requestToken;
	/*****************************************************************
	The group of direct messages.
	 *****************************************************************/
	DMGroups groups;

	/**The Twitter object to access all the twitter information.*/
	private Twitter twitter;

	/***/
	private TweetGroups tweetGroups;

	/*****************************************************************
    Sets up the needed material for the Model side of the program.
	 *****************************************************************/
	public ModelMain() {
	}

	/*****************************************************************
    Creates a list of statuses for the users to see.
    @return An array of Statuses.
    @throws TwitterException
	 * @throws IOException 
	 * @throws TwitterException
	 * @throws IllegalStateException 
	 *****************************************************************/
	public Status[] getHomeTimeline() throws TwitterException,
		IllegalStateException, IOException {

		//		List<Status> statuses;
		//
		//		Paging paging = new Paging(1, 40);
		//		statuses = twitter.getHomeTimeline(paging);
		//
		//		Status[] list = new Status[1];
		//		
		//		tweetGroups = new TweetGroups(twitter.getScreenName());
		//		
		//		tweetGroups.parseTimeLine(statuses.toArray(list));
		//		
		//		list = tweetGroups.getStati("Group");
		//
		//		
		//		
		//		return list;

		//******************
		List<Status> statuses;

		Paging paging = new Paging(1, 200);
		statuses = twitter.getHomeTimeline(paging);

		Status[] list = new Status[1];

		return statuses.toArray(list);
	}

	/*****************************************************************
    Posts a tweet in the users account.
    @throws TwitterException.
    @param postText The string of text to be posted as a status.
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

		if (image != null) {
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
		return twitter.showUser(twitter.getId());
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
	public TrendLocations getAllTrends() throws TwitterException {

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

		if (!recipient.substring(0, 1).equals("@")) {
			recipient = "@" + recipient;
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
			} catch (TwitterException te) {
			te.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
	/*****************************************************************
	 * Gets a list of the users which have a direct message.
	 * @return a list of users
	 *****************************************************************/
	public User[] getDMUsers() {
		//Sets the parameters of a DM group
		DirectMessage[] rlist = new DirectMessage[1];
		DirectMessage[] slist = new DirectMessage[1];

		try {

			List<DirectMessage> messages;
			//retrieves a list of all direct messages
			messages = twitter.getDirectMessages();

			rlist =  messages.toArray(rlist);
			messages = twitter.getSentDirectMessages();
			slist = messages.toArray(slist);

		}
		catch (TwitterException te) {
			te.printStackTrace();
			System.exit(-1);
		}
		//Creating a new group 
		groups = new DMGroups(slist, rlist);
		return groups.getUsers();

	}
	
	/*****************************************************************
	This returns the messages related to a user.
	@param user the user you're getting messages from
	@return DMMessage[] a list of direct messages
	 *****************************************************************/
	public DMMessage[] getDMMessages(User user) {
		return groups.getMessages(user);
	}

	/*****************************************************************
	used to update profile information.
	@param nam the name of the string
	@param url the url of the string
	@param loc the location of the string
	@param des the description
	 *****************************************************************/
	public void update(String nam, String url, String loc, String des) {

		try {
			twitter.updateProfile(nam, url, loc, des);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*****************************************************************
	updates profile picture.
	@param img the file of the image
	 *****************************************************************/
	public void updatePic(File img) {
		try {
			twitter.updateProfileImage(img);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*****************************************************************
	 * method for getting the replies to a status.
	 * @param statusID long the ID of the status
	 * @return Status[] an array of statuses/replies
	 * @throws TwitterException
	 *****************************************************************/
	public Status[] getConversations(long statusID) throws TwitterException {

		RelatedResults results = twitter.getRelatedResults(statusID);
		List<Status> conversations = results.getTweetsWithConversation();

		Status[] list = new Status[1];

		Status originalStatus = twitter.showStatus(statusID);
		//    	if (conversations.isEmpty()) {
		//    	    conversations = results.getTweetsWithReply();
		//    	}
		//
		//    	if (conversations.isEmpty()) {
		//    	    conversations = new ArrayList<Status>();
		//    	    Status status = originalStatus;
		//    	    while (status.getInReplyToStatusId() > 0) {
		//    	        status = twitter.showStatus(status.getInReplyToStatusId());
		//    	        conversations.add(status);
		//    	    }
		//    	}

		if (!conversations.isEmpty()) {
			conversations.add(originalStatus);
		}

		Collections.reverse(conversations);
		return conversations.toArray(list);
	}

	/*****************************************************************
	Authenticates the user.
	@param username String the username of the user.
	@param password String the password of the user.
	@return true if the user is authenticated.
	 *****************************************************************/
	public boolean authenticate(String username, String password) 
			throws IllegalStateException, TwitterException, IOException {

		ConfigurationBuilder cb;
		TwitterFactory tf; 
		//JFrame frame = new JFrame();


		User user; 

		cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("UP8vf0xlwUkPHvikkEBXQ")
		.setOAuthConsumerSecret("62H0idR3HypsRitEUQI3j2ugqTINXybjeBSLr4QH78");

		tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();

		//String username = JOptionPane.showInputDialog(frame, "User Name");

		File file = new File("loginInformation.txt");

		Scanner scanner;
		try {
			scanner = new Scanner(file); 
			} catch (java.io.FileNotFoundException e) {
			file = new File("loginInformation.txt");
			file.createNewFile();
			scanner = new Scanner(file);
		}
		while (scanner.hasNextLine()) {

			String line = scanner.nextLine();


			String[] s = line.split(", ");
			//[0] == username
			//[1] == password
			//[2] == token
			//[3] == secret

			if (s[0].equals(username) && s[1].equals(password)) {
				accessToken = new AccessToken(s[2], s[3]);
				twitter.setOAuthAccessToken(accessToken);
				break;
			}

		}
		//favorites.loadFavorites(username);			
		if (accessToken != null) {
			scanner.close();
			return true;
		} else {
			scanner.close();
			return false;
		}
	}

	/*****************************************************************
	requests a URL to be given.
	@return URL the url to be given.
	@throws MalformedURLException MalformedURLException 
	@throws TwitterException TwitterException
	 *****************************************************************/
	public URL getRequestURL() throws TwitterException, MalformedURLException {
		requestToken = twitter.getOAuthRequestToken();
		return new URL(requestToken.getAuthenticationURL());

	}


	/*****************************************************************
	Sets the authentication code.
	@param inputs String[] the string of inputs
	@return true if the authentication code was set.
	@throws TwitterException TwitterException
	@throws IOException IOException
	 *****************************************************************/
	public boolean setOAuthCode(String[] inputs)
			throws TwitterException, IOException   {

		//RequestToken requestToken;
		User user;
		try {
			try {
				// get request token.
				// this will throw IllegalStateException
				     //if access token is already available
				//requestToken = twitter.getOAuthRequestToken();

				accessToken = null;

				//RequestToken requestToken = twitter.getOAuthRequestToken();

				while (null == accessToken) {

					//URL url = new URL(requestToken.getAuthenticationURL());

					//openWebpage(url);

					//String pin = ""; // = JOptionPane.showInputDialog(frame, 
					//"Enter the PIN(if available) and hit ok.");

					try {
						if (inputs[2].length() > 0) {
							accessToken = twitter
								 .getOAuthAccessToken(requestToken, inputs[2]);
						} else {
							accessToken = twitter
									.getOAuthAccessToken(requestToken);
						}
					} catch (TwitterException te) {
						if (401 == te.getStatusCode()) {

							//JOptionPane.showMessageDialog(frame,
							//"Unable to get the access token.",
							//"Inane error",
							//JOptionPane.ERROR_MESSAGE);
							te.printStackTrace();


						} else {
							te.printStackTrace();
						}
					}
				}

			} catch (IllegalStateException ie) {
				// access token is already available,
				//or consumer key/secret is not set.
				if (!twitter.getAuthorization().isEnabled()) {
					System.exit(-1);
				}
			}

		} catch (NullPointerException np) {

			System.exit(0);
		}

		//user = twitter.showUser(twitter.getId());

		PrintWriter out = new PrintWriter(
				new FileWriter("./loginInformation.txt", true));
		String saveFile = "";

		//user = twitter.showUser(twitter.getId());


		saveFile += inputs[0] + ", " + inputs[1] + ", " 
				+ accessToken.getToken() + ", " + accessToken.getTokenSecret();
		//scanner.close();
		out.println();
		out.print(saveFile);
		out.close();

		return true;
	}



	/*****************************************************************
	Logs the user out.
	 *****************************************************************/
	public final void logout() {
		twitter.setOAuthAccessToken(null);
		//favorites.saveFavorites();
		accessToken = null;
	}

	/*****************************************************************
	Opens the webpage given a uri.
	@param uri the url to open
	 *****************************************************************/
	public static void openWebpage(final URI uri) {
		Desktop desktop = null;
		if (Desktop.isDesktopSupported()) {
			desktop = Desktop.getDesktop();
		}
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*****************************************************************
	Opens the webpage given a url.
	@param url the url to be opened.
	 *****************************************************************/
	public static void openWebpage(final URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	/*****************************************************************
	Gets a group of statuses.
	@param group String the group to get statuses from.
	@return Status[] the statuses from a group.
	 *****************************************************************/
	public Status[] getGroupStati(String group) {
		//gets the statuses
		//convert status to array
		try {
			tweetGroups.parseTimeLine(getHomeTimeline());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tweetGroups.getStati(group);

	}

	/*****************************************************************
	Gets all statuses from the group.
	@return Status[][]
	 *****************************************************************/
	public Status[][] getAllGroupStati() {	
		
		try {
			tweetGroups.parseTimeLine(getHomeTimeline());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String [] groupNames = tweetGroups.getGroupNames();
		Status[][] rVal= new Status[groupNames.length][1];
		
		for (int i = 0; i < groupNames.length; i++) {
			rVal[i] = tweetGroups.getStati(groupNames[i]);
		}

		return rVal;
	}

	/*****************************************************************
	sets the groups.
	 *****************************************************************/
	public void setGroups() {		
		try {
			tweetGroups = new TweetGroups(twitter.getScreenName());
			tweetGroups.parseTimeLine(getHomeTimeline());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	/*****************************************************************
	Gets the group names.
	@return String[]
	 *****************************************************************/
	public String[] getGroupNames() {		
		return tweetGroups.getGroupNames();
	}

	/*****************************************************************
	 Gets the users in a group.
	 @param group String the group you want users from
	 @return User[] the array of users in a group.
	 @throws TwitterException TwitterException
	 *****************************************************************/
	public User[] getGroupUsers(String group) throws TwitterException {
		//returns the users in group
		String[] userStrings = tweetGroups.getUsers(group);
		User[] users = new User[userStrings.length];

		for (int i = 0; i < userStrings.length; i++) {
			users[i] = twitter.showUser(userStrings[i]);
		}

		//TODO: finish this
		return users;
	}
	
	/*****************************************************************
	 Gets the user groups.
	 @return String[] the list of groups the user is in.
	 @param userScreenName String the user screen name.	 
	 *****************************************************************/
	public String[] getUserGroups(String userSceenName) {
		return tweetGroups.checkUser(userSceenName);
	}
	
	
	/*****************************************************************
	Removes the user from a group.
	@param group String the group to remove from.
	@param user String the user to remove.
	 *****************************************************************/
	public void removeFromGroup(String group, String user) {
		tweetGroups.removeFromGroup(group, user);
		
	}
	
	/*****************************************************************
	adds a user to a group.
	@param group String the group to add to
	@param user the user to add to a group.
	 *****************************************************************/
	public void addToGroup(String group, String user) {
		tweetGroups.addToGroup(group, user);
	}
	
	/*****************************************************************
	Creates a new group.
	@param group String the name of the group.
	 *****************************************************************/
	public void newGroup(String group) {
		tweetGroups.createGroup(group);
	}
	
	/*****************************************************************
	Deletes the group.
	@param group String the name of the group.
	 *****************************************************************/
	public void deleteGroup(String group) {
		tweetGroups.destroyGroup(group);
	}
	
	//public boolean login(String username, String password) {
	//	// TODO Auto-generated method stub
	//	return false;
	//}

	//TODO: create get user favorites, get user followers, get user followings

}