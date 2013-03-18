/*****************************************************************
Main view contains all GUI elements and controls their implementation.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.*;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.User;
import utilities.*;

/*****************************************************************
The main view which contains all GUI elements.
 *****************************************************************/
public class ViewMain extends JPanel {

	/**Top panel containing all semi-permanent panels.*/
	JPanel topPanel;

	/**Top Center Panel for displaying different items.*/
	private JPanel topCenter;

	/**The posting panel.*/
	private PostingPanel postPanel;

	/**The main Panel.*/
	JPanel mainPanel;

	/*****************************************************************
    Constructor to set up all the properties of this panel.

    @param user the main user.
	 *****************************************************************/
	public ViewMain(User user) {

		setPreferredSize(ProgramStyle.windowSize());
		setName("backgroundPanel");

		mainPanel = new JPanel();
		mainPanel.setName("backgroundPanel");

		this.setLayout(new BorderLayout());
		add(setTopPanel(user), BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
	}


	/*****************************************************************
    Sets up the three panel sections at the top of the window and
    returns it to display.

    @return JPanel
	 *****************************************************************/
	private JPanel setTopPanel (User user) {

		topPanel = new JPanel();
		//set the size of the panel
		topPanel.setPreferredSize(ProgramStyle.TOP_SIZE);
		topPanel.setName("backgroundPanel");

		//sets up the three sections using BorderLayout method
		topPanel.setLayout(new BorderLayout());

		//sets up the panels
		postPanel = new PostingPanel();
		topPanel.add(postPanel, BorderLayout.WEST);
		topCenter = new SmallProfilePanel(user);
		topPanel.add(topCenter, BorderLayout.CENTER);
		topPanel.add(new ButtonPanel(), BorderLayout.EAST);

		return topPanel;
	}

	/*****************************************************************
	Redisplays the profile information in the top center panel.

	@param user User to display the profile infor for.
	 *****************************************************************/
	public void resetSmallProfile (User user) {

		topPanel.remove(topCenter);
		//resetMainPanel();
		topCenter = new SmallProfilePanel(user);
		topPanel.add(topCenter, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}

	/*****************************************************************
	Resets the mainPanel to clear out the old cruft.
	 *****************************************************************/
	public void resetMainPanel() {
		remove(mainPanel);
		mainPanel = new JPanel();
		mainPanel.setName("backgroundPanel");
		add(mainPanel, BorderLayout.CENTER);
		//refresh window
		updateUI();
	}

	/*****************************************************************
    Display an array of trends in the main portion of the window.
    and then updates the display.
    @param place the location for trends
    @param trends Trend[]
	 *****************************************************************/
	public void showTrends(Trend[] trends, String place,
			TrendLocations locals) {

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new WorldTrendsPanel(locals.getArray()), BorderLayout.WEST);

		//remove(mainPanel);
		mainPanel.add(new TrendingList(trends, place), BorderLayout.CENTER);

		//add new trending panel to main section
		add(mainPanel, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}

	/*****************************************************************
    For future use.
    @param trends Trend[]
	 *****************************************************************/
	public void addTrends (Trend[] trends, String place) {

        mainPanel.add(new TrendingList(trends, place), BorderLayout.EAST);

		updateUI();
	}

	/*****************************************************************
    Show the search panel in the main section.
	 *****************************************************************/
	public void showSearch() {

		topPanel.remove(topCenter);
		topCenter = new SearchPanel();
		topPanel.add(topCenter, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}

	/*****************************************************************
    Show the search panel in the main section.
	 *****************************************************************/
	public void showDM() {

		topPanel.remove(topCenter);
		topCenter = new DMessageSendPanel();
		topPanel.add(topCenter, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}

	/*****************************************************************
    Show the Direct Message panel in the main section.
	 *****************************************************************/
	public void showDM(DirectMessage[] dmessages) {


		topPanel.remove(topCenter);
		topCenter = new DMessageSendPanel();

		topPanel.add(topCenter, BorderLayout.CENTER);

		resetMainPanel();

		mainPanel.add(new DMessageReceivePanel(dmessages));


		updateUI();

	}

	/*****************************************************************
    Show the results of a tweet search in the main section.
    @param stati Status[]
	 *****************************************************************/
	public void showSearchResults(Status[] stati) {

		resetMainPanel();
		mainPanel.add(new StatusList(stati));

		add(mainPanel, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}

	/*****************************************************************
	 * This method adds the status list conversation to the gui.
	 * @param Status[] convo the list of status conversations.
	 *****************************************************************/
	public void addConversations (Status[] convo) {		
		mainPanel.add(new StatusList(convo));

		//refresh window
		updateUI();
	}

	/*****************************************************************
    Show the search results of a user search in main panel.
    @param userSearch User[]
	 *****************************************************************/
	public void showUserSearch(User[] userSearch) {

		resetMainPanel();
		mainPanel.add(new UserList(userSearch));

		add(mainPanel, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}

	/*****************************************************************
    Collects the text in the tweet posting panel.

    @return String text in text field
	 *****************************************************************/
	public String getPost() {
		return postPanel.getText();
	}

	/*****************************************************************
    Clears out the text that is written in the post text field.

	 *****************************************************************/
	public void clearPost() {
		postPanel.clearText();
	}

	/*****************************************************************
    Displays an error message.
	 *****************************************************************/
	public void showError() {

		remove(mainPanel);

		mainPanel = new JPanel();
		mainPanel.setBackground(Color.RED);
		mainPanel.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20,
				ProgramStyle.BACKGROUND_COLOR));

		JLabel errorLabel = new JLabel("Twitter is Not Responding :(");
		errorLabel.setFont(ProgramStyle.getFont(70));
		mainPanel.add(errorLabel);

		JLabel errorDesc = new JLabel("Please check your internet access.");
		errorDesc.setFont(ProgramStyle.getFont(15));
		mainPanel.add(errorDesc);

		add(mainPanel, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}

	/*****************************************************************
    Allows user to refresh the profile by clicking on the Profile Panel.
    @param user User to show in Profile Panel.
	 *****************************************************************/
	public void refreshProfile(User user) {

		remove(topPanel);
		add(setTopPanel(user), BorderLayout.NORTH);
		updateUI();
	}

	/*****************************************************************
	Displays the file chooser for image uploading.

	 *****************************************************************/
	public File imageChooser(){

		System.out.println("VM 283");
		int option;
		JFileChooser imageChooser = new JFileChooser();

		option = imageChooser.showOpenDialog(this);
		if(option == JFileChooser.APPROVE_OPTION){
			File picture = imageChooser.getSelectedFile();
			return picture;
		}
		else{
			return null;
		}
	}

	/*****************************************************************

	@param user User
	 *****************************************************************/
	public void showProfileEdit (User user) {

		resetMainPanel();
		mainPanel.add(new ProfileEditPanel(user));

		add(mainPanel, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}
}