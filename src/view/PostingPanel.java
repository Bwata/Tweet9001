/*****************************************************************
Panel containing a text box for the posting of tweets.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import utilities.MainButtons;
import utilities.MyButton;
import utilities.ProgramStyle;

public class PostingPanel extends JPanel {

    /*****************************************************************
    The text area for posting a tweet.
     *****************************************************************/
    JTextArea postArea;

    /*****************************************************************
    Basic Constructor for the panel to write a tweet.
     *****************************************************************/
    PostingPanel() {

        //Sets panel properties
        setPreferredSize(ProgramStyle.POST_PANEL_SIZE);
        setBackground (ProgramStyle.PANEL_COLOR);
        //order of matteborder method (top, left, bottom, right, color)
        this.setBorder(BorderFactory.createMatteBorder(11, 10, 0, 5,
                ProgramStyle.BACKGROUND_COLOR));

        //JTextFields
        postArea = new JTextArea("Write your post here!!!", 6, 30);
        postArea.setBackground(ProgramStyle.PANEL_COLOR);
        postArea.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
                ProgramStyle.BACKGROUND_COLOR));
        postArea.setForeground(ProgramStyle.TEXT_COLOR);
        postArea.setLineWrap(true);
        postArea.setPreferredSize(postArea.getSize());
        add(postArea);
        add(new MyButton(MainButtons.POST_TWEET));
    }

    /*****************************************************************
    Returns the text that is contained in the JTextArea.

    @return String from the JTextArea
     *****************************************************************/
    public String getText() {
        return postArea.getText();
    }

    /*****************************************************************
    Clears out the text in the posting area.
     *****************************************************************/
    public void clearText() {
        postArea.setText("");
    }
}