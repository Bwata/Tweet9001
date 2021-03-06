/*****************************************************************
Displays the list of direct messages received by the user.

started March 16, 2013
@author Thomas Verstraete, Tyler Hutek
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.DMGroups.DMMessage;

import utilities.Listeners;
import utilities.ProgramStyle;

public class DMessageReceivePanel extends JPanel {

    /**Array of DirectMessages.*/
	private DMMessage[] items;

	/**List displayed.*/
	private JList list;

	/**listener for double clicks.*/
	private ActionListener listener;

	/*****************************************************************
	Constructor. Sets up the list of direct messages for display.

	@param dmessages DirectMessage[] Array of the messages to show.
	 *****************************************************************/
	public DMessageReceivePanel(DMMessage[] dmessages) {

		this.setName("voidPanel");
		
	        this.items = dmessages;
	        listener = Listeners.getListener("ListListener");

	        //Sets up the JList to display the items
	        list = new JList(dmessages);
	        list.setCellRenderer(ProgramStyle.getDMListRenderer());

	        //Place the JList into a scrollable window
	        JScrollPane scrollPane = new JScrollPane(list,
	        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPane.setPreferredSize(ProgramStyle.MAIN_PANEL);

	        //Add all the parts together
	        add(scrollPane);
	    }
}