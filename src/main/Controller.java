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
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

    /**User account info*/
	private User user;

    /*****************************************************************
    General constructor sets up all the attributes.
    Note: the eos computers are 1280 X 1024
     *****************************************************************/
    public Controller() {

        //Sets up the model
        mainModel = new ModelMain();
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
    Redisplay the small profile information.
     *****************************************************************/
    private void refreshProfile() {
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
    
    @param text String message to post.
     *****************************************************************/
    private void postTweet(String text) {

        try {
            mainModel.postTweet(text);
            mainView.clearPost();
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
            mainView.clearPost();
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
        	mainView.resetMainPanel();
        	
            mainView.showTrends(mainModel.getTrends(23424977), "USA", 
            		mainModel.getAllTrends());
        } catch (TwitterException e) {
            mainView.showError();
            //e.printStackTrace();
        }
    }
    
    /*****************************************************************
    Gets the top ten trends for a parameter location
    @throws TwitterException
	 *****************************************************************/
	private void getTrends(TrendLocation loc) {

		//sends the trends to display
		try {
			mainView.resetMainPanel();

			mainView.addTrends(mainModel.getTrends
					(loc.getLocation().getWoeid()), loc.getTownName());
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
    Tells the view to show the Direct Message panel for the user.
    And displays the DM's sent to the user.
	 *****************************************************************/
	private void showDMSendPanel() {
		DirectMessage[] Dmessages = mainModel.receiveDirectMessage();
		mainView.showDM(Dmessages);
	}

	/*****************************************************************
	Sends a direct message to the parameter user with the parameter 
	message.
	@param recipient String user to receive DM from program user
	@param words String message to send recipient.
	 *****************************************************************/
	private void sendDM (String recipient, String words) {	
		mainModel.directMessaging(recipient, words);
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


     *****************************************************************/
    private void showProfileEdit () {		
    	mainView.showProfileEdit(user);
    }
    
    /*****************************************************************
	Gives user a file chooser to edit the profile image.
     *****************************************************************/
    private void imageEdit () {		
    	mainModel.updatePic(mainView.imageChooser());
    }
    
    /*****************************************************************
	Takes the info to edit the profile.
     *****************************************************************/
    private void editProfile (JTextArea[] areas) {		
    	mainModel.update(areas[0].getText(), areas[1].getText(), 
    			areas[2].getText(), areas[3].getText());
    }
    
    /*****************************************************************
     * Gets the conversations/replies from a status
     * @param statusID
     * @return Status[] an array of statuses/replies
     * @throws TwitterException
     *****************************************************************/
    private void getConversations(long statusID) throws TwitterException{

    	
    	Status[] convo = mainModel.getConversations(statusID);
    	
    	if (convo.length > 1) {
    		mainView.addConversations(convo);
    	}
    	
    }

    /*****************************************************************
    Adds the listeners from the subclasses to the Static Listeners
    class.
     *****************************************************************/
    private void setListeners() {

    	Listeners.addListener(new ButtonListener());
        Listeners.addListener(new ListListener());
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
            	refreshProfile();
                break;

            case POST_TWEET:
            	String tweetText =
                ((JTextArea) (button.getPassedObject())).getText();
                postTweet(tweetText);
                break;
                
            case IMAGE:
            	String message = 
            	((JTextArea) (button.getPassedObject())).getText();
            	postTweetAndImage(message);
            	break;

            case SEARCH:
                showSearchPanel();
                break;

            case TRENDING:
                getTrends();
                break;
            	
            case SEARCH_TWEET:
            	String searchTweetText =
                ((JTextArea) (button.getPassedObject())).getText();
            	showSearchTweets(searchTweetText);
            	refreshProfile();
            	break;
                
            case SEARCH_USER:
            	String searchUserText =
                ((JTextArea) (button.getPassedObject())).getText();
            	showSearchUsers(searchUserText);
            	refreshProfile();
            	break;
            	
            case WORLD_TRENDING:

				TrendLocation loc = ((TrendLocation) (button.getPassedObject()));
				getTrends(loc);   
            	
            	break;
            	
			case DIRECT_MESSAGE:
				showDMSendPanel();
				break;

			case SEND_DM:
				JTextArea[] areas = ((JTextArea[]) (button.getPassedObject()));
				sendDM(areas[0].getText(), areas[1].getText());
				refreshProfile();
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
				JTextArea[] profileAreas = ((JTextArea[]) (button.getPassedObject()));
				editProfile(profileAreas);
				showHomeTimeline();
            	refreshProfile();
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

            if (obj instanceof Trend) {
                showSearchTweets(((Trend) obj).getName());
            }
            
            if (obj instanceof Status) {
//                if(((Status) obj).isRetweet() == true){ //TODO
//                	System.out.println("faaaaat");
//                	showIfRetweet();	       	
//                }
                try {
					getConversations(((Status) obj).getId());
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
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
}
