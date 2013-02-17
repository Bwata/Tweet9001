/*****************************************************************
Panel that shows a text field and buttons to allow user to search
Twitter for specific items.

started February 10, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.MyButton;
import utilities.ProgramStyle;
import utilities.SearchButtons;

/*****************************************************************
This is the panel for tweet searches and user searches.
 *****************************************************************/
public class SearchPanel extends JPanel {

    /*****************************************************************
    Generic constructor for the Search Panel.
     *****************************************************************/
    SearchPanel() {

        setBackground(ProgramStyle.BACKGROUND_COLOR);

        JPanel itemArea = new JPanel();
        itemArea.setLayout(new BorderLayout());
        itemArea.setBorder(BorderFactory.
                createEmptyBorder(20, 20, 20, 20));

        //sets the background color of the panel
        itemArea.setBackground(ProgramStyle.PANEL_COLOR);

        JTextField searchWindow = new JTextField(18);
        searchWindow.setBorder(BorderFactory.createMatteBorder(
                5, 5, 5, 5, ProgramStyle.BACKGROUND_COLOR));
        searchWindow.setBackground(ProgramStyle.PANEL_COLOR);
        searchWindow.setForeground(ProgramStyle.TEXT_COLOR);

        //search tweet button
        SearchButtons searchTweet = SearchButtons.SEARCH_TWEET;
        searchTweet.setPassedObject(searchWindow);
        MyButton tweetButton = new MyButton(searchTweet);

        //search user button
        SearchButtons searchUser = SearchButtons.SEARCH_USER;
        searchUser.setPassedObject(searchWindow);
        MyButton userButton = new MyButton(searchUser);

        itemArea.add(searchWindow, BorderLayout.NORTH);
        itemArea.add(tweetButton, BorderLayout.EAST);
        itemArea.add(userButton, BorderLayout.WEST);

        add(itemArea);
    }
}