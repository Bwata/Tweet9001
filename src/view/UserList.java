/*****************************************************************
Panel designed to show off a list of items in scroll panel. And
the list contains a listener of double mouse clickes.
This specific list panel shows off a Twitter4J User object.

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

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import twitter4j.User;
import utilities.Listeners;
import utilities.ProgramStyle;

public class UserList extends JPanel{

    /**The items in the list that are to be displayed.*/
    User[] items;

    /**Swing list object to show list.*/
    JList list;

    /**List listener when item is clicked.*/
    ActionListener listener;

    /*****************************************************************
    Constructs the JList and ScrollPane that contains the list of
    Statuses.

    @param items Status[] array to show in the panel list.
     *****************************************************************/
    public UserList(User[] items) {

    	this.setName("voidPanel");
    	
        this.items = items;
        listener = Listeners.getListener("ListListener");

        //Sets up the JList to display the items
        list = new JList(items);
        list.setCellRenderer(ProgramStyle.getUserListRenderer());
        list.addMouseListener(new ListClickListener(list));
        list.setOpaque(false);

        //Place the JList into a scrollable window
        JScrollPane scrollPane = new JScrollPane(list,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(ProgramStyle.MAIN_PANEL);
        scrollPane.setOpaque(false);

        //Add all the parts together
        add(scrollPane);
    }

    /*****************************************************************
    Class listener when user clicks on an item in the list.
     *****************************************************************/
    public class ListClickListener extends MouseAdapter {
        protected JList list;

        public ListClickListener(JList l){
            list = l;
        }

        /*****************************************************************
        Mouse event that listens for a double click of an item on the list.

        @param e The mouse event.
         *****************************************************************/
        public void mouseClicked(MouseEvent e) {
            //checks for two clicks
            if (e.getClickCount() == 2) {

                //Collects the clicked item and passes it to the list listener
                int index = list.locationToIndex(e.getPoint());
                ListModel dlm = list.getModel();
                Object item = dlm.getElementAt(index);
                list.ensureIndexIsVisible(index);
                listener.actionPerformed(new ActionEvent(item, index, ""));
            } else {
				listener.actionPerformed(
				        new ActionEvent(new Object(), -1, "clear"));
			}
        }
    }
}