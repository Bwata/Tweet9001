/*****************************************************************

started March 13, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import twitter4j.util.CharacterUtil;
import utilities.ButtonType;
import utilities.Listeners;
import utilities.ProgramStyle;
import utilities.TButton;

public class PostingPanel extends JPanel {

	/*****************************************************************
    The text area for posting a tweet.
	 *****************************************************************/
	JTextArea postArea;

	/***/
	private JLabel charCount;

	/*****************************************************************
    Basic Constructor for the panel to write a tweet.
	 *****************************************************************/
	public PostingPanel() {

		//Sets panel properties
		setPreferredSize(ProgramStyle.TOP_PANEL_SIZE);
		setLayout(new BorderLayout());

		//JTextFields
		postArea = new JTextArea("Write your post here!", 4, 30);
		postArea.setLineWrap(true);
		postArea.setWrapStyleWord(true);
		postArea.setEditable(true);

		//sets the listener for counting characters
		postArea.addKeyListener(new KeyboardListener());

		//text character counter
		charCount = new JLabel(" " + (140-CharacterUtil.count(postArea.getText())));
		charCount.setHorizontalTextPosition(JLabel.CENTER);

		JPanel side = new JPanel();
		side.setLayout(new BoxLayout(side, BoxLayout.PAGE_AXIS));
		TButton post = new TButton(ButtonType.POST_TWEET);
		post.setPassedObject(postArea);
		post.addActionListener(Listeners.getListener("Button"));

		TButton image = new TButton(ButtonType.IMAGE);
		image.setPassedObject(postArea);
		image.addActionListener(Listeners.getListener("Button"));

		side.add(image);
		side.add(post);
		side.add(Box.createVerticalGlue());
		side.add(charCount);

		add(postArea, BorderLayout.CENTER);
		add(side, BorderLayout.EAST);

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

	/*****************************************************************
	Listener for the key strokes in the text area.
	 *****************************************************************/
	private class KeyboardListener extends KeyAdapter {

		/*****************************************************************
		sets the counter for the text area.
		
		@param e KeyEvent
		 *****************************************************************/
		public void keyPressed(KeyEvent e) {

			charCount.setText(" " + 
					(140-CharacterUtil.count(postArea.getText())));
		}
	}
}
