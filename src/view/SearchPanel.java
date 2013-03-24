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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utilities.ButtonType;
import utilities.Listeners;
import utilities.ProgramStyle;
import utilities.TButton;

/*****************************************************************
This is the panel for tweet searches and user searches.
 *****************************************************************/
public class SearchPanel extends JPanel {

    /*****************************************************************
    Generic constructor for the Search Panel.
     *****************************************************************/
    SearchPanel() {

        setPreferredSize(ProgramStyle.TOP_PANEL_SIZE);
    	
       
        setLayout(new BorderLayout());

        JTextArea searchWindow = new JTextArea();
        searchWindow.setName("writeArea");

        //search tweet button
        TButton tweetButton = new TButton(ButtonType.SEARCH_TWEET);
        tweetButton.setPassedObject(searchWindow);
        tweetButton.addActionListener(Listeners.getListener("Button"));

        //search user button
        TButton userButton = new TButton(ButtonType.SEARCH_USER);
        userButton.setPassedObject(searchWindow);
        userButton.addActionListener(Listeners.getListener("Button"));

        add(searchWindow, BorderLayout.CENTER);
        add(tweetButton, BorderLayout.EAST);
        add(userButton, BorderLayout.WEST);

    }
}