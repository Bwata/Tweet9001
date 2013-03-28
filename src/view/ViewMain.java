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

	private enum State {TIMELINE, TRENDING, PROFILE,  DIRECTMESSAGE, SEARCH};

	/***/
	private State state;

	/*****************************************************************
    Constructor to set up all the properties of this panel.

    @param user the main user.
	 *****************************************************************/
	public ViewMain(User user) {

		state = State.TIMELINE;

		setPreferredSize(ProgramStyle.WINDOW_SIZE);
		setName("backgroundPanel");

		setMainPanel();
		//setAccessPanel(new JPanel());

		this.setLayout(new BorderLayout());
		add(setTopPanel(user), BorderLayout.NORTH);
	}

	/*****************************************************************


	 *****************************************************************/
	private void setMainPanel () {	
		
		System.out.println("VM 71");

		if (middlePanel != null) {
			remove(middlePanel);
			System.out.println("vm 75");
		}
		
		//updateUI();

		middlePanel = new JPanel();
		middlePanel.setName("backgroundPanel");
		//mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
		//mainPanel.setPreferredSize(new Dimension(0, ProgramStyle.MAIN_HEIGHT));


//		mainScrollPane = new JScrollPane(mainPanel,
//				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
//				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		mainScrollPane.setPreferredSize(new Dimension(
//				ProgramStyle.RENDER_WIDTH*2,
//				ProgramStyle.MAIN_HEIGHT));
//		mainScrollPane.setOpaque(false);
//		//eliminates the default border
//		mainScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


		add(middlePanel, BorderLayout.CENTER);

		//refresh window
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
		topPanel.setName("borderPanel");

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


	 *****************************************************************/
	private void setAccessPanel (JPanel panel) {

		if (accessPanel != null) {
			System.out.println("vm 136");
			remove(accessPanel);
		}
		System.out.println("vm 139");
		accessPanel = panel;
		accessPanel.setName("borderPanel");
		accessPanel.setPreferredSize(new Dimension(
				ProgramStyle.RENDER_WIDTH, ProgramStyle.MAIN_HEIGHT));	

		add(accessPanel, BorderLayout.WEST);
	}

	/*****************************************************************


	 *****************************************************************/
	private void removeAccessPanel () {		
		if (accessPanel != null) {
			remove(accessPanel);
			System.out.println("vm 155");
		} 
		System.out.println("vm 157");
		accessPanel = null;

		//refresh window
		updateUI();
	}
	
	/*****************************************************************


	 *****************************************************************/
	private void setFocusPanel () {		
		if (focusPanel != null) {
			remove(focusPanel);
			System.out.println("vm 75");
		}
		
		//updateUI();

		focusPanel = new JPanel();
		focusPanel.setName("backgroundPanel");

		add(focusPanel, BorderLayout.EAST);

		//refresh window
		updateUI();
	}

	/*****************************************************************
	Redisplays the profile information in the top center panel.

	@param user User to display the profile infor for.
	 *****************************************************************/
	public void resetSmallProfile (User user) {

		topPanel.remove(topCenter);
		topCenter = new SmallProfilePanel(user);
		topCenter.setName("voidPanel");
		topPanel.add(topCenter, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}

	/*****************************************************************
    Show the search panel in the main section.
	 *****************************************************************/
	public void showSearch() {
		
		state = State.SEARCH;

		topPanel.remove(topCenter);
		topCenter = new SearchPanel();
		topCenter.setName("voidPanel");
		topPanel.add(topCenter, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}

	/*****************************************************************
	

	 *****************************************************************/
	public void switchToTimeline (Status[] stati) {		

		if (state != State.TIMELINE) {
			state = State.TIMELINE;

			setMainPanel();
			setFocusPanel();
			middlePanel.add(new StatusList(stati));
			removeAccessPanel();


			//refresh window
			updateUI();
		}
	}

	/*****************************************************************
    Display an array of trends in the main portion of the window.
    and then updates the display.
    @param place the location for trends
    @param trends Trend[]
	 *****************************************************************/
	public void switchToTrending(TrendLocations locals) {

		if(state != State.TRENDING) {
			state = State.TRENDING;

			setMainPanel();

			setAccessPanel(new WorldTrendsPanel(locals.getArray()));

			//add new trending panel to main section
			//add(mainPanel, BorderLayout.CENTER);

			//refresh window
			
			updateUI();
		}
	}

	/*****************************************************************


	 *****************************************************************/
	public void switchToDM (DirectMessage[] messages) {	

		if (state != State.DIRECTMESSAGE) {
			state = State.DIRECTMESSAGE;

			topPanel.remove(topCenter);
			topCenter = new DMessageSendPanel();
			topPanel.add(topCenter, BorderLayout.CENTER);
			
			removeAccessPanel();
			setMainPanel();
			
			middlePanel.add(new DMessageReceivePanel(messages));

			showDM();

		}

	}
	
	/*****************************************************************


	 *****************************************************************/
	public void switchToSearch () {	

		if (state != State.SEARCH) {
			state = State.SEARCH;

			removeAccessPanel();
			setMainPanel();

			showDM();

		}

	}


	/*****************************************************************
    Show the search panel in the main section.
	 *****************************************************************/
	private void showDM() {

		topPanel.remove(topCenter);
		topCenter = new DMessageSendPanel();
		topPanel.add(topCenter, BorderLayout.CENTER);

		//refresh window
		updateUI();
	}
	
	/*****************************************************************


	 *****************************************************************/
	public void showProfile (User user) {	
		
		//show the user in the access panel with fancy styling.

	}

	/*****************************************************************


	 *****************************************************************/
	public void showProfileEdit (User user) {		

		//		if (state != State.PROFILE_EDIT) {
		//			state = State.PROFILE_EDIT;

		removeAccessPanel();

		setAccessPanel(new ProfileEditPanel(user));

		//		}

	}

	/*****************************************************************

	 *****************************************************************/
	public void addList(Object[] obj, String name) {


		if (obj instanceof Status[]) {
			if (state == State.TIMELINE) {
				setFocusPanel();
				focusPanel.add(new StatusList((Status[]) obj));
			} else {
				middlePanel.add(new StatusList((Status[]) obj));
			}

		} else if (obj instanceof User[]) {

			middlePanel.add(new UserList((User[]) obj));

		} else if (obj instanceof DirectMessage[]) {

			middlePanel.add(new DMessageReceivePanel((DirectMessage[]) obj));

		} else if (obj instanceof Trend[]) {

			middlePanel.add(new TrendingList((Trend[]) obj, name));

		}

		//refresh window
		middlePanel.updateUI();
		updateUI();
	}

	/*****************************************************************
	Displays the file chooser for image uploading.

	 *****************************************************************/
	public File imageChooser(){

		//System.out.println("VM 283");
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
