package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import twitter4j.DirectMessage;
import twitter4j.User;
import utilities.Listeners;
import utilities.ProgramStyle;
import view.TrendingList.ListClickListener;

public class DMessageReceivePanel extends JPanel {
	private DirectMessage[] items;
	private JList list;
	private ActionListener listener;
	
	
	public DMessageReceivePanel(DirectMessage[] dmessages){

	        this.items = dmessages;
	        listener = Listeners.getListener("ListListener");

	        //Sets up the JList to display the items
	        list = new JList(dmessages);
	        list.setCellRenderer(ProgramStyle.getDMListRenderer());

	        //Place the JList into a scrollable window
	        JScrollPane scrollPane = new JScrollPane(list,
	        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        scrollPane.setPreferredSize(new Dimension(ProgramStyle.MAIN_ELEMENT_WIDTH, ProgramStyle.MAIN_HEIGHT));

	        //Add all the parts together
	        add(scrollPane);
	    }
}
