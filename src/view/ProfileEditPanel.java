/*****************************************************************
Panel for editing profile.

started March 17, 2013
@author Thomas Verstraete, Andrew Jarvis
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import twitter4j.User;
import utilities.ButtonType;
import utilities.Listeners;
import utilities.TButton;

public class ProfileEditPanel extends JPanel {

	/*****************************************************************
	Constructor.
	 *****************************************************************/
	public ProfileEditPanel (User user) {

		this.setName("voidPanel");

		JTextArea[] areas = new JTextArea[4];

		//this preferences
		setPreferredSize(new Dimension(500, 175));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel top = new JPanel();
		top.setName("borderPanel");
		top.setLayout(new GridLayout(6, 1));

		JPanel bottom = new JPanel();
		bottom.setName("borderPanel");
		bottom.setLayout(new GridLayout(2, 1));

		JPanel buttons = new JPanel();
		buttons.setName("borderPanel");
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));

		JLabel usernameLabel = new JLabel("UserName:");
		JTextArea username = new JTextArea(user.getName());
		username.setName("writeArea");
		areas[0] = username;
		JLabel urlLabel = new JLabel("URL:");
		JTextArea url = new JTextArea(user.getURL());
		url.setName("writeArea");
		areas[1] = url;
		JLabel locLabel = new JLabel("Location:");
		JTextArea loc = new JTextArea(user.getLocation());
		loc.setName("writeArea");
		areas[2] = loc;

		top.add(usernameLabel);
		top.add(username);
		top.add(urlLabel);
		top.add(url);
		top.add(locLabel);
		top.add(loc);

		add(top);

		//Description side
		JLabel descLabel = new JLabel("Description:");
		JTextArea description = new JTextArea(user.getDescription());
		description.setName("writeArea");
		
		areas[3] = description;

		bottom.add(descLabel);
		bottom.add(description);

		add(bottom);

		//Buttons section
		TButton imageEdit = new TButton(ButtonType.EDIT_IMAGE);
		imageEdit.addActionListener(Listeners.getListener("Button"));

		TButton saveEdit = new TButton(ButtonType.SAVE_PROFILE);
		saveEdit.addActionListener(Listeners.getListener("Button"));

		//collects the text fields and passes them along
		saveEdit.setPassedObject(areas);

		buttons.add(Box.createHorizontalGlue());
		buttons.add(imageEdit);
		buttons.add(saveEdit);
		buttons.add(Box.createHorizontalGlue());

		add(buttons);
		
		add(Box.createVerticalStrut(300));
	}
}