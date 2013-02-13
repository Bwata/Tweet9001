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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import twitter4j.Trend;
import utilities.Listeners;
import utilities.ProgramStyle;

public class TrendingList extends JPanel{

    /**The items in the list that are to be displayed*/
    ArrayList<Trend[]> itemsList;

    /**Swing list object to show list*/
    JList list;

    /**List listener when item is clicked*/
    ActionListener listener;

    /*****************************************************************
	Constructs the JList and ScrollPane that contains the list of 
	Trends.

	@param items Trends[] array to show in the panel list.
     *****************************************************************/
    public TrendingList (Trend[] items) {

        this.setBackground(ProgramStyle.BACKGROUND_COLOR);
        itemsList = new ArrayList<Trend[]>();
        
        itemsList.add(items);
        listener = Listeners.getListener("ListListener");

        //Sets up the JList to display the items
        list = new JList(items);
        list.setCellRenderer(ProgramStyle.getTrendListRenderer());
        list.addMouseListener(new ListClickListener(list));
        list.setOpaque(false);
        list.setBackground(ProgramStyle.BACKGROUND_COLOR);
        list.setVisibleRowCount(10);

        //Add all the parts together
        add(list);
    }

    /*****************************************************************

	@param items Trend[]
     *****************************************************************/
    public void addTrendList (Trend[] items) {     
        
        itemsList.add(items);
        
        listener = Listeners.getListener("ListListener");

        //Sets up the JList to display the items
        list = new JList(items);
        list.setCellRenderer(ProgramStyle.getTrendListRenderer());
        list.addMouseListener(new ListClickListener(list));
        list.setOpaque(false);
        list.setBackground(ProgramStyle.BACKGROUND_COLOR);
        list.setVisibleRowCount(10);

        //Add all the parts together
        add(list);
    }

    /*****************************************************************
	Class listener when user clicks on an item in the list
     *****************************************************************/
    public class ListClickListener extends MouseAdapter {
        protected JList list;

        public ListClickListener(JList l){
            list = l;
        }

        /*****************************************************************
		Mouse event that listens for a double click of an item on the list.
         *****************************************************************/
        public void mouseClicked(MouseEvent e){
            //checks for two clicks
            if(e.getClickCount() == 2){

                //Collects the clicked item and passes it to the list listener
                int index = list.locationToIndex(e.getPoint());
                ListModel dlm = list.getModel();
                Object item = dlm.getElementAt(index);;
                list.ensureIndexIsVisible(index);
                listener.actionPerformed(new ActionEvent(item, index, ""));
            }
        }
    }
}
