/*****************************************************************
This class is God. This is the hub where all action passes through.
everything the user does calls on this class to react and make
changes to the accounts and rentals.

@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.TwitterException;
import twitter4j.User;

import model.ModelMain;
import utilities.Listeners;
import utilities.MainButtons;
import utilities.SearchButtons;
import view.ViewMain;


public class Controller {

	/**The frame for the whole project.*/
	private JFrame frame;

	/**MainView of the Whole layout*/
	private ViewMain mainView;

	/**The access class for the Project Model side*/
	private ModelMain mainModel;


	/*****************************************************************
	General constructor sets up all the attributes.
	Note: the eos computers are 1280 X 1024
	 *****************************************************************/
	public Controller () {

		//Instantiate the twitter object
		mainModel = new ModelMain();
		User user;
        try {
            user = mainModel.getMainUser();
        } catch (TwitterException e) {
            user = null;
            e.printStackTrace();
        }

		//This sets the listeners for the Buttons.
		setListeners();

		//sets up the main frame and background of the whole program
		frame = new JFrame ("Tweet9001");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		//show the main window
		mainView = new ViewMain(user);
		frame.getContentPane().add(((JPanel) mainView));

		//show the background
		frame.pack();
		frame.setVisible(true);
	}







	//Methods go in here

	


	/*****************************************************************


	 *****************************************************************/
	private void showHomeTimeline () {		

		
		Status[] stati;
		try {
			stati = mainModel.getHomeTimeline();
			mainView.showSearchResults(stati);
		} catch (TwitterException e) {
			stati = null;
			mainView.showError();
			//e.printStackTrace();
		}
		
	}
	
	/*****************************************************************


	 *****************************************************************/
	private void postTweet () {		
		
		String postText = mainView.getPost();
		try {
			mainModel.postTweet(postText);
			mainView.clearPost();
			showHomeTimeline();
		} catch (TwitterException e) {
			mainView.showError();
			//e.printStackTrace();
		}
		
	}

	
	/*****************************************************************
	Gets the top ten trends for the united states - 23424977

	@throws TwitterException 
	 *****************************************************************/
	private void getTrends () {

		//sends the trends to display
		try {
			mainView.addTrends(mainModel.getTrends(23424977));
			//mainView.addTrends(mainModel.getTrends(23424977));
			//mainView.addTrends(mainModel.getTrends(23424977));
            
		} catch (TwitterException e) {
			mainView.showError();
			//e.printStackTrace();
		}
	}

	
	/*****************************************************************


	 *****************************************************************/
	private void showSearchPanel () {	
		mainView.showSearch();
	}
	
	
	/*****************************************************************


	 *****************************************************************/
	private void showSearchResults (String searchText) {		

		Status[] stati;
		try {
			stati = mainModel.searchTweets(searchText);
			mainView.showSearchResults(stati);
		} catch (TwitterException e) {
			stati = null;
			mainView.showError();
			//e.printStackTrace();
		}
		
	}

	
	/*****************************************************************


	 *****************************************************************/
	private void showSearchTweets (String searchText) {		

		Status[] stati;
		try {
			stati = mainModel.searchTweets(searchText);
			mainView.showSearchResults(stati);
		} catch (TwitterException e) {
			stati = null;
			mainView.showError();
			//e.printStackTrace();
		}
		
	}
	
	
	/*****************************************************************


	 *****************************************************************/
	private void showSearchUsers (String searchText) {	
	    
	    try {
            mainView.showUserSearch(mainModel.searchUsers(searchText));
        } catch (TwitterException e) {
            mainView.showError();
            //e.printStackTrace();
        }

	}
	
	/*****************************************************************


	 *****************************************************************/
	private void showSearchBoth (String searchText) {		

	}




	/*****************************************************************
	Adds the listeners from the subclasses to the Static Listeners 
	class. 
	 *****************************************************************/
	private void setListeners () {

		Listeners.addListener(new MainButtonListener());
		Listeners.addListener(new MainListListener());
		Listeners.addListener(new SearchButtonListener());
	}

	//################################################################

	//these are the listeners. WOOOOOHOOOOOO

	/*****************************************************************
	Listener for the main buttons on the main panel
	 *****************************************************************/
	public class MainButtonListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		private String listenType = "MainButton"; //string has to match the enum

		/*****************************************************************
		The action performed method, like you do.

		@param event ActionEvent containing all the info you will need
		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			//Action to perform when the method is called.
			//Switch to Determine Pressed Button
			switch (((MainButtons) event.getSource())) {

			case HOMETIMELINE: 
				showHomeTimeline();
				break;

			case POST_TWEET: 
				postTweet();
				break;

			case SEARCH: 
				showSearchPanel();
				break;

			case TRENDING: 
					getTrends();
				break;

			case VIEW_PROFILE: 
				System.out.println ("Profile");
				break;

			default: 
				System.out.println ("Default");
				break;
			}
		}

		/*****************************************************************
		This gets the determining String
		 *****************************************************************/

		@Override
		public String toString () {
			return listenType;
		}
	}

	/*****************************************************************
	Listener for the list objects
	 *****************************************************************/
	public class MainListListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		private String listenType = "ListListener"; //string has to match the enum

		/*****************************************************************
		The action performed method, like you do.

		@param event ActionEvent containing all the info you will need
		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			Object obj = event.getSource();

			if (obj instanceof Trend) {
				showSearchTweets(((Trend) obj).getName());
				//System.out.println("Controller: " + ((Trend) obj).getName());
			}
			
			if (obj instanceof Status) {
				System.out.println("Controller: status");
			}




			//Action to perform when the method is called.
			//Switch to Determine Pressed Button

		}

		/*****************************************************************
		This gets the determining String
		 *****************************************************************/

		@Override
		public String toString () {
			return listenType;
		}
	}

	/*****************************************************************
	Listener for the main buttons on the main panel
	 *****************************************************************/
	public class SearchButtonListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		private String listenType = "SearchButton"; //string has to match the enum

		/*****************************************************************
		The action performed method, like you do.

		@param event ActionEvent containing all the info you will need
		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {
			
			SearchButtons button = (SearchButtons) event.getSource();
			System.out.println(button);
			JTextField field = (JTextField) button.getPassedObject();
			System.out.println (field);
			
			String searchText = ((JTextField) (button.getPassedObject())).getText();
			
			//Action to perform when the method is called.
			//Switch to Determine Pressed Button

			//Switch to determine what search button was pressed
			switch (button) {

			case SEARCH_TWEET: 
				//System.out.println ("search tweet");
				showSearchTweets(searchText);
				//mainModel.searchTweets(searchText);
				break;

			case SEARCH_USER: 
				showSearchUsers(searchText);
				//System.out.println ("search user");
				break;

			case SEARCH_ALL: 
				showSearchBoth(searchText);
				//System.out.println ("search all");
				break;

			default: 
				System.out.println ("search button default");
				break;
			}
		}

		/*****************************************************************
		This gets the determining String
		 *****************************************************************/

		@Override
		public String toString () {
			return listenType;
		}
	}
}