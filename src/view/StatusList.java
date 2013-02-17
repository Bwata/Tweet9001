/*****************************************************************
Panel designed to show off a list of items in scroll panel. And
the list contains a listener of double mouse clickes.
This specific list panel shows off a Twitter4J Status object.

started February 8, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

import twitter4j.Status;
import utilities.Listeners;
import utilities.ProgramStyle;

/*****************************************************************
The panel which displays a list of statuses.
 *****************************************************************/
public class StatusList extends JPanel {

    /**The items in the list that are to be displayed.*/
    private Status[] items;

    /**Swing list object to show list.*/
    private JList list;

    /**List listener when item is clicked.*/
    private ActionListener listener;

    /*****************************************************************
    Constructs the JList and ScrollPane that contains the list of
    Statuses.

    @param itemsPassed Status[] array to show in the panel list.
     *****************************************************************/
   public StatusList(Status[] itemsPassed) {

        this.setBackground(ProgramStyle.BACKGROUND_COLOR);

        this.items = itemsPassed;
        listener = Listeners.getListener("ListListener");

        //Sets up the JList to display the items
		list = new JList(items);
		list.setCellRenderer(ProgramStyle.getStatusListRenderer());
		list.addMouseListener(new ListClickListener(list));
		list.setOpaque(false);
		list.setBackground(ProgramStyle.BACKGROUND_COLOR);

		//Place the JList into a scrollable window
		JScrollPane scrollPane = new JScrollPane(list,
		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(500, 720));
		//hides the vertical scroll bar
		scrollPane.getVerticalScrollBar().setPreferredSize(
		        new Dimension(0, 0));
		scrollPane.setOpaque(false);
		//sets the background color
		scrollPane.setBackground(ProgramStyle.BACKGROUND_COLOR);
		//eliminates the default border
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		//Add all the parts together
		add(scrollPane);
	}

	/*****************************************************************
	Class listener when user clicks on an item in the list.
	 *****************************************************************/
	public class ListClickListener extends MouseAdapter {

	    /**The List to listen to.*/
		protected JList list;

		/***************************************************************
		Listen to List.
		@param l JList to listen to.
		 **************************************************************/
		public ListClickListener(JList l) {
			list = l;
		}

		/***************************************************************
		Mouse event that listens for a double click of an item on
		the list.
		@param e MouseEvent from clicked mouse.
		 **************************************************************/
		public void mouseClicked(MouseEvent e) {
			//checks for two clicks
			if (e.getClickCount() == 2) {

				//Collect clicked item, passes to list listener.
				int index = list.locationToIndex(e.getPoint());
				ListModel dlm = list.getModel();
				Object item = dlm.getElementAt(index);
				list.ensureIndexIsVisible(index);
				listener.actionPerformed(
				        new ActionEvent(item, index, ""));
			}
		}
	}
}