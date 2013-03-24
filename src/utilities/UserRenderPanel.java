/*****************************************************************
List Rendering Panel for Statuses.

started March 3, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package utilities;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import twitter4j.User;

public class UserRenderPanel extends JPanel{

	/*****************************************************************
	Constructor for UserRenderPanel. Builds the panel with the appropriate
	items for display.

	@param list JList The list to display in.
	@param value Object The item whose info is being displayed.
	@param index int The index of the item within the list.
	@param isSelected boolean if the item is selected in the GUI
	@param cellHasFocus boolean if the item has focus.
	 *****************************************************************/
	public UserRenderPanel (
            final JList list,              // the list
            final Object value,            // value to display
            final int index,               // cell index
            final boolean isSelected,      // is the cell selected
            final boolean cellHasFocus)    // does the cell have focus 
	{
		/* Items to show from user
        *
        * Image           .getMiniProfileImageURL()
        * Name            .getName();
        * handle          .getScreenName()
        * description     .getDescription()
        * location        .getLocation()
        * */
<<<<<<< HEAD
		
		this.setName("statusPanel");
		
=======

>>>>>>> 05002386c1621d5746d08e4c1d81cb9623dfa10a
		 try {
           User user = ((User) value);

           this.setLayout(new BorderLayout());
<<<<<<< HEAD
			this.setPreferredSize(new Dimension(ProgramStyle.RENDER_WIDTH, 90));
           
=======
			this.setPreferredSize(new Dimension(ProgramStyle.RENDER_WIDTH, 100));

>>>>>>> 05002386c1621d5746d08e4c1d81cb9623dfa10a
         //checks if selected
			if (isSelected) {
				setName("selectedPanel");
			} else {
				//nothing?
			}

           JLabel image = new JLabel(new ImageIcon(user.getProfileImageUrlHttps()));
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

           JLabel timeStamp = new JLabel(user.getLocation());
           timeStamp.setName("H6");
           userInfo.add(timeStamp);

           textPanel.add(userInfo, BorderLayout.NORTH);

           JTextArea postArea = new JTextArea(user.getDescription());
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