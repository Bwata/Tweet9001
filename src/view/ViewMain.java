package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.DMGroups.DMMessage;

import twitter4j.DirectMessage;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.User;
import utilities.ProgramStyle;
import utilities.TrendLocations;

public class ViewMain extends JPanel {

	/**Top panel containing all semi-permanent panels.*/
	private JPanel topPanel;

	/**Top Center Panel for displaying different items.*/
	private JPanel topCenter;

	/**The posting panel.*/
	//private PostingPanel postPanel;

	/**The main Panel.*/
	private JPanel middlePanel;

	/***/
	private JPanel focusPanel;

	//JScrollPane mainScrollPane;

	/***/
	private JPanel accessPanel;
	/*****************************************************************
	states of timeline.
	 *****************************************************************/
	private enum State {TIMELINE, TRENDING, PROFILE,
		DIRECTMESSAGE, SEARCH, SEARCH_RESULT, GROUPS};

	/***/
	private State state;

	/*****************************************************************
    Constructor to set up all the properties of this panel.

    @param user the main user.
	 *****************************************************************/
	public ViewMain(User user, Status[] stati) {

		state = State.TIMELINE;

		setPreferredSize(ProgramStyle.WINDOW_SIZE);
		setName("backgroundPanel");

		this.setLayout(new BorderLayout());
		add(setTopPanel(user), BorderLayout.NORTH);

		setMiddlePanel(new StatusList(stati));
		removeAccessPanel();
		removeFocusPanel();

		updateUI();
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
		topPanel.setName("voidPanel");

		//sets up the three sections using BorderLayout method
		topPanel.setLayout(new BorderLayout());

		//sets up the panels
		JPanel postPanel = new PostingPanel();
		topPanel.add(postPanel, BorderLayout.WEST);

		topCenter = new SmallProfilePanel(user);
		topPanel.add(topCenter, BorderLayout.CENTER);

		JPanel buttonPanel = new ButtonPanel();
		topPanel.add(buttonPanel, BorderLayout.EAST);

		return topPanel;
	}

	/*****************************************************************

	@return JPanel
	 *****************************************************************/
	private JPanel placeHolderPanel() {
		JPanel panel = new JPanel();
		panel.setName("voidPanel");
		panel.setPreferredSize(ProgramStyle.MAIN_PANEL);
		return panel;

	}

	/*****************************************************************
	sets middle panel.
	@param panel JPanel JPanel.

	 *****************************************************************/
	private void setMiddlePanel(JPanel panel) {

		if (middlePanel != null) {
			remove(middlePanel);
		}

		middlePanel = panel;

		add(middlePanel, BorderLayout.CENTER);
	}

	/*****************************************************************
	Removes middle panel.
	 *****************************************************************/
	private void removeMiddlePanel() {
		if (middlePanel != null) {
			remove(middlePanel);
		} 
		middlePanel = placeHolderPanel();
		add(middlePanel, BorderLayout.CENTER);
	}


	/*****************************************************************
	sets access panel.
	@param panel JPanel.
	 *****************************************************************/
	private void setAccessPanel (JPanel panel) {

		if (accessPanel != null) {
			remove(accessPanel);
		}
		accessPanel = panel;

		add(accessPanel, BorderLayout.WEST);
	}

	/*****************************************************************
	removes the access panel.

	 *****************************************************************/
	private void removeAccessPanel () {
		if (accessPanel != null) {
			remove(accessPanel);
		} 
		accessPanel = placeHolderPanel();
		add(accessPanel, BorderLayout.WEST);
	}

	/*****************************************************************
	 sets the focus panel.
	 *****************************************************************/
	private void setFocusPanel (JPanel panel) {
		if (focusPanel != null) {
			remove(focusPanel);
		}

		focusPanel = panel;

		add(focusPanel, BorderLayout.EAST);
	}

	/*****************************************************************
	removes the focus panel.
	 *****************************************************************/
	private void removeFocusPanel() {
		if (focusPanel != null) {
			remove(focusPanel);
		} 
		focusPanel = placeHolderPanel();
		add(focusPanel, BorderLayout.EAST);
	}

	/*****************************************************************
	Redisplays the profile information in the top center panel.

	@param user User to display the profile infor for.
	 *****************************************************************/
	private void resetSmallProfile(User user) {

		topPanel.remove(topCenter);
		topCenter = new SmallProfilePanel(user);
		topCenter.setName("stdPanel");
		topPanel.add(topCenter, BorderLayout.CENTER);
	}

	/*****************************************************************
    Show the search panel in the main section.
	 *****************************************************************/
	private void showSearch() {

		state = State.SEARCH;

		topPanel.remove(topCenter);
		topCenter = new SearchPanel();
		topCenter.setName("stdPanel");
		topPanel.add(topCenter, BorderLayout.CENTER);
	}

	/*****************************************************************
	Show the search panel in the main section.
	 *****************************************************************/
	private void showDM() {

		topPanel.remove(topCenter);
		topCenter = new DMessageSendPanel();
		topPanel.add(topCenter, BorderLayout.CENTER);
	}

	/*****************************************************************
	This clears the side panels or focus panel and access panel.
	 *****************************************************************/
	public void clearSides() {
		if (state == State.TIMELINE || state == State.SEARCH_RESULT) {
		removeFocusPanel();
		removeAccessPanel();

		//refresh window
		updateUI();
		}
	}

	/*****************************************************************
	Switches the view state to Timeline.

	@param stati Status[] the status
	@param user User the user
	 *****************************************************************/
	public void switchToTimeline (Status[] stati, User user) {		

		if (state != State.TIMELINE) {
			state = State.TIMELINE;

			setMiddlePanel(new StatusList(stati));
			resetSmallProfile(user);

			removeAccessPanel();
			removeFocusPanel();

			//refresh window
			updateUI();
		}
	}

	/*****************************************************************
    Display an array of trends in the main portion of the window.
    and then updates the display.
    @param locals TrendLocations locations.
	 *****************************************************************/
	public void switchToTrending(TrendLocations locals) {

		if (state != State.TRENDING) {
			state = State.TRENDING;

			removeMiddlePanel();
			removeFocusPanel();

			setAccessPanel(new WorldTrendsPanel(locals.getArray()));

			//refresh window
			updateUI();
		}
	}

	/*****************************************************************
	switch to DM
	@param users User[] the users.
	 *****************************************************************/
	public void switchToDM (User[] users) {	

		if (state != State.DIRECTMESSAGE) {
			state = State.DIRECTMESSAGE;

			topPanel.remove(topCenter);
			topCenter = new DMessageSendPanel();
			topPanel.add(topCenter, BorderLayout.CENTER);

			setAccessPanel(new DMUserList(users));
			removeMiddlePanel();
			removeFocusPanel();

			showDM();
		}
		
		//refresh window
		updateUI();
	}

	/*****************************************************************
	switches to search.
	 *****************************************************************/
	public void switchToSearch() {	

		if (state != State.SEARCH) {
			state = State.SEARCH;

			showSearch();

		}
		//refresh window
		updateUI();
	}

	/*****************************************************************
	switches to search result.
	@param obj the object
	@param user the user
	 *****************************************************************/
	public void switchToSearchResult (Object[] obj, User user) {	

		if (state != State.SEARCH_RESULT) {
			state = State.SEARCH_RESULT;

			if (obj instanceof Status[]) {

				setMiddlePanel(new StatusList((Status[]) obj));

			} else {

				setMiddlePanel(new UserList((User[]) obj));

			}

			resetSmallProfile(user);

			removeAccessPanel();
			removeFocusPanel();

		}
		//refresh window
		updateUI();
	}
	
	/*****************************************************************
	switches to group.
	@param groupNames String[] groupnames

	 *****************************************************************/
	public void switchToGroups (String[] groupNames) {

		if (state != State.GROUPS) {
			state = State.GROUPS;

			removeMiddlePanel();
			removeAccessPanel();
			removeFocusPanel();
			
			topPanel.remove(topCenter);
			topCenter = new SmallGroupsEditPanel(groupNames);
			topPanel.add(topCenter, BorderLayout.CENTER);
			
			setAccessPanel(new GroupList(groupNames));
			

			//Add all the parts together
			//add(scrollPane, BorderLayout.CENTER);
			
			

		}
		//refresh window
		updateUI();
	}
		
	/*****************************************************************

	resets the groups.
	@param groupnames String[] thegroupnames
	@param groupStati Status[][] the stati.
	 *****************************************************************/	
		public void resetGroups(String[] groupNames, Status[][] groupStati) {
			
			if (state == State.GROUPS) {
				state = State.TIMELINE;
			
				switchToGroups(groupNames);
			
			}
		}
		
	


	/*****************************************************************
	show profile.
	@param user the user
	@param memberGroups groupMembers
	@param allGroups all groups.
	 *****************************************************************/
	public void showProfile(User user, String[] memberGroups, 
			String[] allGroups) {
		if (state == State.GROUPS) {
			setFocusPanel(new ProfilePanel(user, memberGroups, allGroups));
		} else if (state != State.TRENDING) {
			//show the user in the access panel with fancy styling.
			setAccessPanel(new ProfilePanel(user, memberGroups, allGroups));
		}

		//refresh window
		updateUI();
	}

	/*****************************************************************
	shows the profile edit.
	@param user User
	 *****************************************************************/
	public void showProfileEdit(User user) {

		removeAccessPanel();

		setMiddlePanel(new ProfileEditPanel(user));

		//refresh window
		updateUI();

	}

	/*****************************************************************
	Adds to list.
	@param obj objet.
	@param name String name.
	 *****************************************************************/
	public void addList(Object[] obj, String name) {


		if (obj instanceof Status[]) {
			if (state == State.TIMELINE) {
				setFocusPanel(new StatusList((Status[]) obj));

			} else if (state == State.TRENDING) {
				setFocusPanel(new StatusList((Status[]) obj));

			} else if (state == State.SEARCH_RESULT) {
				setFocusPanel(new StatusList((Status[]) obj));
				
			} else if (state == State.GROUPS) {
				setMiddlePanel(new StatusList((Status[]) obj));
				
			} else {
				setMiddlePanel(new StatusList((Status[]) obj));
			}

		} else if (obj instanceof User[]) {

			if (state == State.SEARCH) {
				setMiddlePanel(new UserList((User[]) obj));
			} 


		} else if (obj instanceof DMMessage[]) {

			setMiddlePanel(new DMessageReceivePanel((DMMessage[]) obj));

		} else if (obj instanceof Trend[]) {

			setMiddlePanel(new TrendingList((Trend[]) obj, name));

		}

		//refresh window
		updateUI();
	}

	/*****************************************************************
	Displays the file chooser for image uploading.
	@return File files.
	 *****************************************************************/
	public File imageChooser() {

		int option;
		JFileChooser imageChooser = new JFileChooser();

		option = imageChooser.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			File picture = imageChooser.getSelectedFile();
			return picture;
		} else {
			return null;
		}
	}

	/*****************************************************************
    Displays an error message.

    TODO change clean this shit up.
	 *****************************************************************/
	public void showError() {

		remove(middlePanel);

		middlePanel = new JPanel();
		middlePanel.setBackground(Color.RED);
		middlePanel.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20,
				ProgramStyle.BACKGROUND_COLOR));

		JLabel errorLabel = new JLabel("Twitter is Not Responding :(");
		errorLabel.setFont(ProgramStyle.getFont(70));
		middlePanel.add(errorLabel);

		JLabel errorDesc = new JLabel("Please check your internet access.");
		errorDesc.setFont(ProgramStyle.getFont(15));
		middlePanel.add(errorDesc);

		add(middlePanel, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}
}
