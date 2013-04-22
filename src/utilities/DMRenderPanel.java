package utilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.DMGroups.DMMessage;

import twitter4j.DirectMessage;
import twitter4j.User;

/*****************************************************************
List Rendering Panel for Direct Messages.

started March 16, 2013
@author Thomas Verstraete, Tyler Hutek
@version Winter 2013
 *****************************************************************/
public class DMRenderPanel extends JPanel{

	/*****************************************************************
	Constructor for DMRenderPanel. Builds the panel with the appropriate
	items for display.

	@param list JList The list to display in.
	@param value Object The item whose info is being displayed.
	@param index int The index of the item within the list.
	@param isSelected boolean if the item is selected in the GUI
	@param cellHasFocus boolean if the item has focus.
	 *****************************************************************/
	public DMRenderPanel(
			final JList list,              // the list
			final Object value,            // value to display
			final int index,               // cell index
			final boolean isSelected,      // is the cell selected
			final boolean cellHasFocus)    // does the cell have focus 
	{

		this.setName("statusPanel");
		
		try {
			DirectMessage directMessage = ((DMMessage) value).getMessage();
			User user = directMessage.getSender();

			//checks if selected
			if (isSelected) {
				setName("selectedPanel");
			}

			this.setLayout(new BorderLayout());
			this.setPreferredSize(new Dimension(
					ProgramStyle.RENDER_WIDTH, 100));

			JLabel image = new JLabel(new ImageIcon(
					user.getProfileImageUrlHttps()));
			add(image, BorderLayout.WEST);

			JPanel textPanel = new JPanel();
			textPanel.setName("voidPanel");
			textPanel.setLayout(new BorderLayout());

			JPanel userInfo = new JPanel();
			userInfo.setName("voidPanel");
			userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.LINE_AXIS));
			JLabel userName = new JLabel(user.getName());
			userName.setName("H5");
			userInfo.add(userName);

			JLabel userScreenName = new JLabel("@" + user.getScreenName());
			userScreenName.setName("H6");
			userInfo.add(userScreenName);

			userInfo.add(Box.createHorizontalGlue());

			Date date = directMessage.getCreatedAt();

			JLabel timeStamp = new JLabel(((date.getMonth() + 1) + "/" 
					 + date.getDate() + "/" + (date.getYear() - 100) + " "
					 + (date.getHours() % 12) + ":" + date.getMinutes()));
			timeStamp.setName("H6");
			userInfo.add(timeStamp);

			textPanel.add(userInfo, BorderLayout.NORTH);

			JTextArea postArea = new JTextArea(directMessage.getText());
			postArea.setName("voidPanel");

			postArea.setLineWrap(true);
			postArea.setWrapStyleWord(true);
			postArea.setEditable(false);

			textPanel.add(postArea, BorderLayout.CENTER);

			add(textPanel, BorderLayout.CENTER);

		} catch (NullPointerException e) {
			//e.printStackTrace();
		}
	}
}