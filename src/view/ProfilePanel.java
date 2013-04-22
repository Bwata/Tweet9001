/*****************************************************************

started March 28, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import twitter4j.User;
import utilities.ButtonType;
import utilities.Listeners;
import utilities.ProgramStyle;
import utilities.TButton;

public class ProfilePanel extends JPanel{
	
	/**the user*/
	private User user;
	
	/*****************************************************************
	creates a profilePanel
	@param user USer the user
	@param memberGroups String[] the group members.
	@param allGroups String[] all groups.
	 *****************************************************************/
	public ProfilePanel(User user, String[] memberGroups, String[] allGroups) {
		
		this.user = user;
		this.setName("voidPanel");
		
		//this.setName("borderPanel");
		this.setPreferredSize(ProgramStyle.MAIN_PANEL);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(setTopPanel());
		
		add(setDescription());
		
		add(setfNumbers());
		
		add(groupsPanel(memberGroups, allGroups));
		
		add(Box.createVerticalStrut(300));
	}
	
	/*****************************************************************
	@param user User the user
	@return JPanel
	 *****************************************************************/
	private JPanel setTopPanel () {
		JPanel panel = new JPanel();
		panel.setName("borderPanel");
		panel.setLayout(new BorderLayout());
		//panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
		
		JLabel image = new JLabel(new ImageIcon(
				user.getProfileImageUrlHttps()));
		panel.add(image, BorderLayout.WEST);
		
		JPanel sub = new JPanel();
		sub.setName("voidPanel");
		sub.setLayout(new BorderLayout());
		
		JLabel nameLabel = new JLabel(user.getName());
		nameLabel.setName("H3");
		sub.add(nameLabel, BorderLayout.NORTH);
		
		JLabel sNameLabel = new JLabel("@" + user.getScreenName());
		sub.add(sNameLabel, BorderLayout.CENTER);
		
		JLabel locLabel = new JLabel(user.getLocation());
		sub.add(locLabel, BorderLayout.SOUTH);
		
		panel.add(sub, BorderLayout.CENTER);
		
		
		return panel;
		
	}
	
	/*****************************************************************

	@return JPanel
	 *****************************************************************/
	private JPanel setDescription() {
		JPanel panel = new JPanel();
		panel.setName("borderPanel");
		//panel.setLayout(new BorderLayout());
		//panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
		
		JTextArea desc = new JTextArea(user.getDescription());
        desc.setName("borderPanel");
        desc.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
        
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        
        panel.add(desc);
		
		return panel;
		
	}
	
	/*****************************************************************
    Sets up the bottom half of the Profile Panel. Contains the numbers
    for Following, Followers, and favorites.
     @return JPanel to put into place containing the items
     *****************************************************************/
    private JPanel setfNumbers() {
        JPanel panel = new JPanel();
        panel.setName("borderPanel");
        panel.setLayout(new GridLayout(1, 3));
        //panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));

        panel.add(profileNumbers("Following", user.getFriendsCount()));

        panel.add(profileNumbers("Followers", user.getFollowersCount()));

        panel.add(profileNumbers("Favorites", user.getFavouritesCount()));

        return panel;
    }
    
    /*****************************************************************
    Builds the profile numbers portion of the Profile Panel.

    @return JPanel to put into place containing the items.
    @param title String section title.
    @param value int to display.
     *****************************************************************/
    private JPanel profileNumbers(String title, int value) {

        JPanel main = new JPanel();
        main.setName("stdPanel");

        main.setLayout(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel(title);

        JLabel valueLabel = new JLabel("" + value);

        main.add(titleLabel);
        main.add(valueLabel);

        return main;
    }
    /*****************************************************************
	creates the groupsPanel.
	@param memberGroups
	@param allGroups
	@return JPanel the JPanel
     *****************************************************************/
    private JPanel groupsPanel(String[] memberGroups, String[] allGroups) {
    	
    	JPanel panel = new JPanel();
    	panel.setName("borderPanel");
    	panel.setLayout(new GridLayout(0, 2));
    	
    	for (int i = 0; i < allGroups.length; i++) {
    		
    		if (Arrays.asList(memberGroups).contains(allGroups[i])) {
    			
    			TButton minusButton = new TButton(
    					ButtonType.REMOVE_FROM_GROUP, "- " + allGroups[i]);
    			minusButton.setPassedObject(user);
    			minusButton.addActionListener(
    					Listeners.getListener("GroupButton"));
    			panel.add(minusButton);
    		} else {
    			
    			TButton addButton = new TButton(
    					ButtonType.Add_TO_GROUP, "+ " + allGroups[i]);
    			addButton.setPassedObject(user);
    			addButton.addActionListener(
    					Listeners.getListener("GroupButton"));
    			panel.add(addButton);
    			
    			
    		}

    	}

    	return panel;

    }
	
}