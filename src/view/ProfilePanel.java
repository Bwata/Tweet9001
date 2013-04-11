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
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import twitter4j.Status;
import twitter4j.User;
import utilities.ProgramStyle;
import utilities.StatusRenderPanel;

public class ProfilePanel extends JPanel{

	/***/
	private User user;

	/*****************************************************************

	 *****************************************************************/
	public ProfilePanel(User user) {

		this.user = user;
		this.setName("voidPanel");

		//this.setName("borderPanel");
		this.setPreferredSize(ProgramStyle.MAIN_PANEL);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		add(setTopPanel());

		add(setDescription());

		add(setfNumbers());


		//add(new JLabel(user.getScreenName()));
		//add(new JLabel(user.getBiggerProfileImageURL()));
		//JLabel image = new JLabel(new ImageIcon(user.getProfileImageUrlHttps()));
		//add(image);
		//add(new JLabel(user.getDescription()));
		//add(new JLabel("favs: " + user.getFavouritesCount()+""));
		//add(new JLabel("followers: " + user.getFollowersCount()+""));
		//add(new JLabel("following: " + user.getFriendsCount()+""));
		//add(new JLabel(user.getLocation()));
		//add(new JLabel(user.getName()));
		//add(new JLabel(user.getScreenName()));
		//		add(new JLabel("status count: " + user.getStatusesCount()+""));
		//		add(new JLabel(user.isFollowRequestSent()+""));
		add(Box.createVerticalStrut(100));

	}

	/*****************************************************************

	@param user User
	@return JPanel
	 *****************************************************************/
	private JPanel setTopPanel () {		
		JPanel panel = new JPanel();
		panel.setName("borderPanel");
		panel.setLayout(new BorderLayout());
		//panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));

		JLabel image = new JLabel(new ImageIcon(user.getProfileImageUrlHttps()));
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
	private JPanel setDescription ( ) {	
		JPanel panel = new JPanel();
		panel.setName("borderPanel");

		JTextArea desc = new JTextArea(user.getDescription());
		desc.setColumns(30);
		desc.setName("basicTextArea");

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

		main.setLayout(new GridLayout(2, 1));
		JLabel titleLabel = new JLabel(title);

		JLabel valueLabel = new JLabel("" + value);

		main.add(titleLabel);
		main.add(valueLabel);

		return main;
	}
}