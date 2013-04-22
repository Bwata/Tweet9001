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

import twitter4j.Trend;
import twitter4j.User;

public class GroupRenderPanel extends JPanel{

	/*****************************************************************
	Constructor for UserRenderPanel. Builds the panel with the appropriate
	items for display.

	@param list JList The list to display in.
	@param value Object The item whose info is being displayed.
	@param index int The index of the item within the list.
	@param isSelected boolean if the item is selected in the GUI
	@param cellHasFocus boolean if the item has focus.
	 *****************************************************************/
	public GroupRenderPanel (
			final JList list,              // the list
			final Object value,            // value to display
			final int index,               // cell index
			final boolean isSelected,      // is the cell selected
			final boolean cellHasFocus)    // does the cell have focus 
	{
		if (isSelected) {
			this.setName("trendSelPanel");
		} else {
			this.setName("trendPanel"); 
		}

		this.setPreferredSize(new Dimension(ProgramStyle.RENDER_WIDTH, 40));
		try {
			String groupName = ((String) value);

			JLabel title = new JLabel(groupName);
			//title.setName("H3");

			add(title);


		} catch (NullPointerException e) {
			//e.printStackTrace();
		}


	}
}