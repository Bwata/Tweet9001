/*****************************************************************
This class is God. This is the hub where all action passes through.
everything the user does calls on this class to react and make
changes to the Twitter Project.

@author Thomas Verstraete, Tyler Hutek, Rui Takagi, Andrew Jarvis
@version Winter 2013
 *****************************************************************/
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.TwitterException;
import twitter4j.User;

import model.ModelMain;
import utilities.ButtonType;
import utilities.Listeners;
import utilities.TButton;
import utilities.TrendLocations.TrendLocation;
import view.ViewLogin;
import view.ViewMain;

/*****************************************************************
This class controls the program. The controller updates the status
of the model.
 *****************************************************************/
public class Controller {

	/**The frame for the whole project.*/
	private JFrame frame;

	/**MainView of the Whole layout.*/
	private ViewMain mainView;

	/**The access class for the Project Model side.*/
	private ModelMain mainModel;
	
	ViewLogin loginView;

	/**User account info.*/
	//private User user;

	/*****************************************************************
    General constructor sets up all the attributes.
    Note: the eos computers are 1280 X 1024
	 *****************************************************************/
	public Controller() {

		//Sets up the main frame and background of the whole program
		frame = new JFrame("Tweet9001");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);

		//Sets the listeners for the Buttons.
		setListeners();
		
		mainModel = new ModelMain();

		loginView = new ViewLogin();

		frame.getContentPane().add(((JPanel) loginView));

		//show the background
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * @throws TwitterException 
	 * @throws MalformedURLException ***************************************************************


	 *****************************************************************/
	private void login(String username, String password){	
		
		boolean logedIn = false;
		System.out.println("controller 90: first login : " + logedIn);
		try {
			logedIn = mainModel.authenticate(username, password);
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (logedIn) {
			setUpMain();
		} else {
			URL url = null;
			try {
				url = mainModel.getRequestURL();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loginView.setPinRequest(url);
		}
		//String [] inputs = loginView.getInputs();

	}
	
	/*****************************************************************


	 *****************************************************************/
	private void registerLogin (JTextArea[] inputs) {		

		boolean logedIn = false;
		String[] inputStrings = new String[inputs.length];
		
		for (int i = 0; i < inputs.length; i++) {
			inputStrings[i] = inputs[i].getText();
		}
		
		try {
			logedIn = mainModel.setOAuthCode(inputStrings);
			
			if (logedIn) {
				
				
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (logedIn) {
			setUpMain();
			
		} else {
			loginView.showError();
		}
		
	}

	/*****************************************************************

	 *****************************************************************/
	private void setUpMain() {

		//Sets up the model
		User user;
		Status[] stati;
		mainModel = new ModelMain();
		try {
			user = mainModel.getMainUser();
			stati = mainModel.getHomeTimeline();
		} catch (TwitterException e) {
			user = null;
			stati = null;
			//e.printStackTrace();
		}

		//show the main window
		mainView = new ViewMain(user, stati);

		//Show Time line from the beginning
		//showHomeTimeline();

		frame.remove(loginView);
		
		frame.getContentPane().add(((JPanel) mainView));

		//show the background
		frame.pack();
		frame.setVisible(true);
	}

	/*****************************************************************
    Redisplay the small profile information.
	 *****************************************************************/
	//    private void refreshProfile() {
	//        mainView.resetSmallProfile(user);
	//    }

	/*****************************************************************
    Get the user's home time line and send it to the view.
	 *****************************************************************/
	private void showHomeTimeline() {

		Status[] stati;
		try {

			mainView.switchToTimeline(mainModel.getHomeTimeline(),
					mainModel.getMainUser());

		} catch (TwitterException e) {
			stati = null;
			mainView.showError();
			//e.printStackTrace();
		}
	}

	/*****************************************************************
    Process to collect the post text and send it on to twitter then
    update the view's timeline.

    @param text String message to post.
	 *****************************************************************/
	private void postTweet(String text) {

		try {
			mainModel.postTweet(text);

			showHomeTimeline();
		} catch (TwitterException e) {
			mainView.showError();
			//e.printStackTrace();
		}
	}

	/*****************************************************************
    Take the String parameter, collect the image with a filechooser
    and post both to the twitters.

    @param text String message to post.
	 *****************************************************************/
	private void postTweetAndImage(String text) {

		try {
			mainModel.postTweet(text, mainView.imageChooser());
			//mainView.clearPost();
			showHomeTimeline();
		} catch (TwitterException e) {
			mainView.showError();
			//e.printStackTrace();
		}
	}

	/*****************************************************************
    Get the Top Ten trends of a hard coded location and also display
    all the possible trending locaitons of Twitter.
    Local examples:
    united states - 23424977.
	Detroit - 2391585
	Chicago - 2379574
	 *****************************************************************/
	private void getTrends() {

		//sends the trends to display
		try {

			mainView.switchToTrending(mainModel.getAllTrends());
			mainView.addList(mainModel.getTrends(23424977), "USA");
		} catch (TwitterException e) {
			mainView.showError();
			//e.printStackTrace();
		}
	}

	/*****************************************************************
    Gets the top ten trends for a parameter location.
    @throws TwitterException
	 *****************************************************************/
	private void getTrends(TrendLocation loc) {

		//sends the trends to display
		try {
			//mainView.resetMainPanel();


			mainView.addList(mainModel.getTrends(
					loc.getLocation().getWoeid()),
					loc.getTownName());
		} catch (TwitterException e) {
			mainView.showError();
			//e.printStackTrace();
		}
	}

	/*****************************************************************
    Tells the view to show the search panel for the user.
	 *****************************************************************/
	private void showSearchPanel() {
		mainView.switchToSearch();
	}

	/*****************************************************************
    Tells the view to show the Direct Message panel for the user.
    And displays the DM's sent to the user.
	 *****************************************************************/
	private void showDMSendPanel() {

		mainView.switchToDM(mainModel.getDMUsers());

	}

	/*****************************************************************
	Sends a direct message to the parameter user with the parameter
	message.
	@param recipient String user to receive DM from program user
	@param words String message to send recipient.
	 *****************************************************************/
	private void sendDM(String recipient, String words) {
		mainModel.directMessaging(recipient, words);
	}

	/*****************************************************************


	 *****************************************************************/
	private void showDMMessages (User user) {		

		mainView.addList(mainModel.getDMMessages(user), "");

	}

	/*****************************************************************
    Shows the search results if the user wants to search for tweets.
    @param searchText String to find results for.
	 *****************************************************************/
	private void showSearchTweets(String searchText) {

		try {

			mainView.switchToSearchResult(mainModel.searchTweets(searchText), 
					mainModel.getMainUser());

		} catch (TwitterException e) {
			mainView.showError();
			//e.printStackTrace();
		}
	}


	/*****************************************************************
    Shows the search results if the user wants to search for tweets.
    @param searchText String to find results for.
	 *****************************************************************/
	private void showTrendTweets(String searchText) {

		try {

			mainView.addList(mainModel.searchTweets(searchText), searchText);

		} catch (TwitterException e) {
			mainView.showError();
			//e.printStackTrace();
		}
	}
	/*****************************************************************
    Shows the search results if the user wants to search for Users.
    @param searchText String to find results for.
	 *****************************************************************/
	private void showSearchUsers(String searchText) {

		try {

			mainView.switchToSearchResult(mainModel.searchUsers(searchText), 
					mainModel.getMainUser());

		} catch (TwitterException e) {
			mainView.showError();
			//e.printStackTrace();
		}
	}

	/*****************************************************************


	 *****************************************************************/
	private void showProfileEdit() {
		//mainView.showProfileEdit(user);
	}

	/*****************************************************************


	 *****************************************************************/
	private void showProfile(User user) {
		mainView.showProfile(user);
	}

	/*****************************************************************


	 *****************************************************************/
	private void clearSides() {		
		mainView.clearSides();
	}

	/*****************************************************************
	Gives user a file chooser to edit the profile image.
	 *****************************************************************/
	private void imageEdit () {		
		mainModel.updatePic(mainView.imageChooser());
	}

	/*****************************************************************
	Takes the info to edit the profile.
	@param areas an array of JText areas used for editing profile info.
	 *****************************************************************/
	private void editProfile(JTextArea[] areas) {
		mainModel.update(areas[0].getText(), areas[1].getText(),
				areas[2].getText(), areas[3].getText());
	}

	/*****************************************************************
	 * Gets the conversations/replies from a status.
	 * @param statusID
	 * @throws TwitterException
	 *****************************************************************/
	private void getConversations(long statusID) throws TwitterException{


		Status[] convo = mainModel.getConversations(statusID);

		if (convo.length > 1) {
			mainView.addList(convo, "");
		}

	}

	/*****************************************************************
    Adds the listeners from the subclasses to the Static Listeners
    class.
	 *****************************************************************/
	private void setListeners() {

		Listeners.addListener(new ButtonListener());
		Listeners.addListener(new ListListener());
		Listeners.addListener(new loginButtonListener());
	}

	//################################################################

	//these are the listeners. WOOOOOHOOOOOO

	/*****************************************************************
    Listener for the main buttons on the main panel.
	 *****************************************************************/
	public class ButtonListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		//string has to match the enum
		private String listenType = "Button";

		/***************************************************************
        The action performed method, like you do.

        @param event ActionEvent containing all the info you will need
		 **************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			TButton button = (TButton) event.getSource();
			ButtonType type = ((TButton) event.getSource())
					.getButtonType();

			//Switch to Determine Pressed Button
			switch (type) {

			case HOMETIMELINE:
				showHomeTimeline();
				//refreshProfile();
				break;

			case SEARCH:
				showSearchPanel();
				break;

			case TRENDING:
				getTrends();
				break;

			case DIRECT_MESSAGE:
				showDMSendPanel();
				break;

			case POST_TWEET:
				String tweetText =
				((JTextArea) (button.getPassedObject())).getText();
				((JTextArea) (button.getPassedObject())).setText("");
				postTweet(tweetText);
				break;

			case IMAGE:
				String message =
				((JTextArea) (button.getPassedObject())).getText();
				((JTextArea) (button.getPassedObject())).setText("");
				postTweetAndImage(message);
				break;



			case SEARCH_TWEET:
				String searchTweetText =
				((JTextArea) (button.getPassedObject())).getText();
				showSearchTweets(searchTweetText);

				break;

			case SEARCH_USER:
				String searchUserText =
				((JTextArea) (button.getPassedObject())).getText();
				showSearchUsers(searchUserText);

				break;

			case WORLD_TRENDING:

				TrendLocation loc = ((TrendLocation)
						(button.getPassedObject()));
				getTrends(loc);

				break;



			case SEND_DM:
				JTextArea[] areas = ((JTextArea[])
						(button.getPassedObject()));
				sendDM(areas[0].getText(), areas[1].getText());
				break;

			case EDIT_IMAGE:
				System.out.println("Controller 357");
				//show file chooser and update it then
				imageEdit();
				break;

			case EDIT_PROFILE:
				//show profile panel
				showProfileEdit();
				break;

			case SAVE_PROFILE:
				//get text info from edit panel
				JTextArea[] profileAreas = ((JTextArea[])
						(button.getPassedObject()));
				editProfile(profileAreas);
				showHomeTimeline();
				break;

			default:
				break;
			}
		}

		/***************************************************************
        This gets the determining String.
        @return String of the listener type.
		 **************************************************************/
		@Override
		public String toString() {
			return listenType;
		}
	}

	/*****************************************************************
    Listener for the list objects.
	 *****************************************************************/
	public class ListListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		//string has to match the enum
		private String listenType = "ListListener";

		/***************************************************************
		The action performed method, like you do.

		@param event ActionEvent containing all the info you will need.
		 **************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			Object obj = event.getSource();
			String text = event.getActionCommand();
			int id = event.getID();

			if (id == -1) {
				clearSides();

			} else if (id == -2) {
				showDMMessages((User) obj);
				//System.out.println("cont listlistener DMMessage");

			} else if (obj instanceof Trend) {
				showTrendTweets(((Trend) obj).getName());

			} else if (obj instanceof Status) {

				try {
					getConversations(((Status) obj).getId());
					showProfile(((Status) obj).getUser());
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (obj instanceof User) {
				showProfile((User) obj);

			}



			//            if (obj instanceof Status) {
			//                //For future use.
			//            }
		}

		/***************************************************************
		This gets the determining String.
		@return String of the listener type.
		 **************************************************************/
		@Override
		public String toString() {
			return listenType;
		}
	}

	/*****************************************************************
    Listener for the main buttons on the main panel.
	 *****************************************************************/
	public class FButtonListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		//string has to match the enum
		private String listenType = "FButton";

		/***************************************************************
        The action performed method, like you do.

        @param event ActionEvent containing all the info you will need
		 **************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			TButton button = (TButton) event.getSource();
			ButtonType type = ((TButton) event.getSource())
					.getButtonType();

			//Switch to Determine Pressed Button
			switch (type) {


			default:
				break;
			}
		}

		/***************************************************************
        This gets the determining String.
        @return String of the listener type.
		 **************************************************************/
		@Override
		public String toString() {
			return listenType;
		}
	}

	/*****************************************************************
    Listener for the main buttons on the main panel.
	 *****************************************************************/
	public class loginButtonListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		//string has to match the enum
		private String listenType = "Login";

		/***************************************************************
        The action performed method, like you do.

        @param event ActionEvent containing all the info you will need
		 **************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			TButton button = (TButton) event.getSource();
			ButtonType type = ((TButton) event.getSource())
					.getButtonType();

			//Switch to Determine Pressed Button
			switch (type) {

			case LOGIN:
				
				JTextArea[] areas = ((JTextArea[])
						(button.getPassedObject()));
				login(areas[0].getText(), areas[1].getText());
				
				break;

			case REGISTER:

				JTextArea[] areas1 = ((JTextArea[])
						(button.getPassedObject()));
				
				registerLogin(areas1);
				
				break;

			default:
				break;
			}
		}

		/***************************************************************
        This gets the determining String.
        @return String of the listener type.
		 **************************************************************/
		@Override
		public String toString() {
			return listenType;
		}
	}

}
