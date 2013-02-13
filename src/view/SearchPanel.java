/*****************************************************************
Panel that shows a text field and buttons to allow user to search
Twitter for specific items.

started February 10, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyButton;
import utilities.ProgramStyle;
import utilities.SearchButtons;

public class SearchPanel extends JPanel{

	/*****************************************************************
	Generic constructor for the Search Panel
	 *****************************************************************/
	SearchPanel () {
		
		setBackground(ProgramStyle.BACKGROUND_COLOR);
		
		JPanel itemArea = new JPanel();
		itemArea.setLayout(new BorderLayout());
		itemArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		//sets the background color of the panel
		itemArea.setBackground(ProgramStyle.PANEL_COLOR);
		
		JTextField searchWindow = new JTextField(18);
		searchWindow.setBackground(ProgramStyle.BACKGROUND_COLOR);
		searchWindow.setForeground(ProgramStyle.TEXT_COLOR);
		
		//search tweet button
		SearchButtons searchTweet = SearchButtons.SEARCH_TWEET; 
		searchTweet.setPassedObject(searchWindow);
		MyButton tweetButton = new MyButton(searchTweet);
		//tweetButton.setColorBorder(ProgramStyle.SELECT_COLOR);
		//tweetButton.setButtonSize(new Dimension(195, 63));
		
		//search user button
		SearchButtons searchUser = SearchButtons.SEARCH_USER; 
		searchUser.setPassedObject(searchWindow);
		MyButton userButton = new MyButton(searchUser);
		//userButton.setColorBorder(ProgramStyle.SELECT_COLOR);
		//userButton.setButtonSize(new Dimension(195, 63));
		
		//search both tweets and users
		SearchButtons searchAll = SearchButtons.SEARCH_ALL; 
		searchAll.setPassedObject(searchWindow);
		MyButton allButton = new MyButton(searchAll);
		//allButton.setColorBorder(ProgramStyle.SELECT_COLOR);
		//allButton.setButtonSize(new Dimension(195, 63));
		
		itemArea.add(searchWindow, BorderLayout.NORTH);
		
		itemArea.add(tweetButton, BorderLayout.EAST);
		itemArea.add(userButton, BorderLayout.CENTER);
		itemArea.add(allButton, BorderLayout.WEST);
		
		add(itemArea);
	}
}
