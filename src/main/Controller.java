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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import utilities.Listeners;
import utilities.MainButtons;


public class Controller {


	/**The frame for the whole project*/
	private JFrame frame;

	/**MainView of the Whole layout*/
	ViewMain mainView;

	/**The Twitter object to access all the twitter information*/
	private Twitter twitter;


	/*****************************************************************
	General constructor sets up all the attributes and opens up the 
	log in screen
	Note: the eos computers are 1280 X 1024
	 *****************************************************************/
	public Controller () {

		//Instantiate the twitter object
		twitter =  TwitterFactory.getSingleton();

		//This sets the listeners for the Buttons.
		setListeners();

		//sets up the main frame and background of the whole program
		frame = new JFrame ("Tweet9001");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);

		//show the main window
		mainView = new ViewMain();
		frame.getContentPane().add(((JPanel) mainView));

		//show the background
		frame.pack();
		frame.setVisible(true);
	}







	//Methods go in here









	/*****************************************************************
	Gets the top ten trends for the united states

	@throws TwitterException 
	 *****************************************************************/
	private void getTrends () throws TwitterException {		

		Trends trends = twitter.getPlaceTrends(23424977);
		Trend trend[] = trends.getTrends();

		//sends the trends to display
		mainView.showTrends(trend);
	}








	/*****************************************************************
	Adds the listeners from the subclasses to the Static Listeners 
	class. 
	 *****************************************************************/
	public void setListeners () {

		Listeners.addListener(new MainButtonListener());

	}

	//################################################################

	//these are the listeners. WOOOOOHOOOOOO

	/*****************************************************************
	Listener description
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
				System.out.println ("HomeTimeline");
				break;
				
			case POST_TWEET: 
				System.out.println ("Post tweet");
				break;

			case SEARCH: 
				System.out.println ("Search");
				break;
				
			case TRENDING: 
				//System.out.println ("Trending");
				try {
					getTrends();
				} catch (TwitterException e) {
					// @TODO SHOW TWITTER IS UNACCESSABLE MESSAGE
					e.printStackTrace();
				}
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
}