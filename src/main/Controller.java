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


    /*****************************************************************
    General constructor sets up all the attributes.
    Note: the eos computers are 1280 X 1024
     *****************************************************************/
    public Controller() {

        //Sets up the model
        mainModel = new ModelMain();
        User user;
        try {
            user = mainModel.getMainUser();
        } catch (TwitterException e) {
            user = null;
            //e.printStackTrace();
        }

        //Sets the listeners for the Buttons.
        setListeners();

        //Sets up the main frame and background of the whole program
        frame = new JFrame("Tweet9001");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //show the main window
        mainView = new ViewMain(user);

        //Show Time line from the beginning
        showHomeTimeline();

        frame.getContentPane().add(((JPanel) mainView));

        //show the background
        frame.pack();
        frame.setVisible(true);
    }

    /*****************************************************************
    When user clicks on the profile panel, the top panel will refresh.
     *****************************************************************/
    private void refreshProfile() {
        User user;
        try {
            user = mainModel.getMainUser();
            System.out.println("refresh method good");
        } catch (TwitterException e) {
            user = null;
            System.out.println("refresh method error");
            //e.printStackTrace();
        }

        mainView.refreshProfile(user);
    }

    /*****************************************************************
    Get the user's home time line and send it to the view.
     *****************************************************************/
    private void showHomeTimeline() {

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
    Process to collect the post text and send it on to twitter then
    update the view's timeline.
     *****************************************************************/
    private void postTweet() {

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
    Gets the top ten trends for the united states - 23424977.

    @throws TwitterException
     *****************************************************************/
    private void getTrends() {

        //sends the trends to display
        try {
            mainView.showTrends(mainModel.getTrends(23424977));
        } catch (TwitterException e) {
            mainView.showError();
            //e.printStackTrace();
        }
    }

    /*****************************************************************
    Tells the view to show the search panel for the user.
     *****************************************************************/
    private void showSearchPanel() {
        mainView.showSearch();
    }

    /*****************************************************************
    Shows the search results if the user wants to search for tweets.
    @param searchText String to find results for.
     *****************************************************************/
    private void showSearchTweets(String searchText) {

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
    Shows the search results if the user wants to search for Users.
    @param searchText String to find results for.
     *****************************************************************/
    private void showSearchUsers(String searchText) {

        try {
            mainView.showUserSearch(mainModel.searchUsers(searchText));
        } catch (TwitterException e) {
            mainView.showError();
            //e.printStackTrace();
        }
    }

    /*****************************************************************
    Adds the listeners from the subclasses to the Static Listeners
    class.
     *****************************************************************/
    private void setListeners() {

        Listeners.addListener(new MainButtonListener());
        Listeners.addListener(new MainListListener());
        Listeners.addListener(new SearchButtonListener());
    }

    //################################################################

    //these are the listeners. WOOOOOHOOOOOO

    /*****************************************************************
    Listener for the main buttons on the main panel.
     *****************************************************************/
    public class MainButtonListener implements ActionListener {

        /**The String determining the type of listener wanted.*/
        //string has to match the enum
        private String listenType = "MainButton";

        /***************************************************************
        The action performed method, like you do.

        @param event ActionEvent containing all the info you will need
         **************************************************************/
        @Override
        public void actionPerformed(ActionEvent event) {

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
    public class MainListListener implements ActionListener {

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

            if (obj instanceof Trend) {
                showSearchTweets(((Trend) obj).getName());
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
    public class SearchButtonListener implements ActionListener {

        /**The String determining the type of listener wanted.*/
        //string has to match the enum
        private String listenType = "SearchButton";

        /***************************************************************
		The action performed method, like you do.

		@param event ActionEvent containing all the info you will need
         **************************************************************/
        @Override
        public void actionPerformed(ActionEvent event) {

            SearchButtons button = (SearchButtons) event.getSource();

            String searchText =
                    ((JTextField) (button.getPassedObject())).getText();

            if (searchText.length() != 0) {

                //Switch to determine what search button was pressed
                switch (button) {

                case SEARCH_TWEET:
                    showSearchTweets(searchText);
                    break;

                case SEARCH_USER:
                    showSearchUsers(searchText);
                    break;

                }
            }
        }

        /***************************************************************
		This gets the determining String.
        @return String of the listener type.
         *************************************************************/
        @Override
        public String toString() {
            return listenType;
        }
    }
}