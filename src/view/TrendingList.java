/*****************************************************************
Panel designed to show off a list of items in scroll panel. And
the list contains a listener of double mouse clickes.
This specific list panel shows off a Twitter4J Trend objects.

started February 8, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import twitter4j.Trend;
import utilities.Listeners;
import utilities.ProgramStyle;

/*****************************************************************
The panel which displays all trending topics.
 *****************************************************************/
public class TrendingList extends JPanel {

    /**Swing list object to show list.*/
    JList list;

    /**List listener when item is clicked.*/
    ActionListener listener;

    /*****************************************************************
    Constructs the JList and ScrollPane that contains the list of
    Trends.

    @param items Trends[] array to show in the panel list.
     *****************************************************************/
    public TrendingList(Trend[] items, String place) {

    	this.setName("voidPanel");
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(ProgramStyle.RENDER_WIDTH, ProgramStyle.MAIN_HEIGHT));

    	JLabel title = new JLabel(place);
    	title.setName("H3");

        listener = Listeners.getListener("ListListener");

        //Sets up the JList to display the items
        list = new JList(items);
        list.setCellRenderer(ProgramStyle.getTrendListRenderer());
        list.addMouseListener(new ListClickListener());
        list.setOpaque(false);
        list.setVisibleRowCount(10);

        //Add all the parts together
        add(title, BorderLayout.NORTH);
        add(list, BorderLayout.CENTER);
    }

    /*****************************************************************
    This is for future use.
    @param items Trend[]
     *****************************************************************/
//    public void addTrendList (Trend[] items, String place) {
//
//    	JPanel listItem = new JPanel();
//    	listItem.setLayout(new BorderLayout());
//
//    	JLabel title = new JLabel(place);
//    	title.setName("H3");
//    	System.out.println("Break");
//
//        listener = Listeners.getListener("ListListener");
//
//        //Sets up the JList to display the items
//        list = new JList(items);
//        list.setCellRenderer(ProgramStyle.getTrendListRenderer());
//        list.addMouseListener(new ListClickListener(list));
//        list.setVisibleRowCount(10);
//
//        //Add all the parts together
//        listItem.add(title, BorderLayout.NORTH);
//        listItem.add(list, BorderLayout.CENTER);
//        add(listItem);
//    }

    /*****************************************************************
    Class listener when user clicks on an item in the list.
     *****************************************************************/
    public class ListClickListener extends MouseAdapter {

        /*****************************************************************
        Mouse event that listens for a double click of an item on the list.

        @param e the mouse event.
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
            }
        }
    }
}