/*****************************************************************
This displays the lists passed into the user side of the GUI.

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package xtra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import utilities.*;

import utilities.ProgramStyle;

public class ViewUserList extends JPanel implements View {

	AbstractListModel items;

	JList list;

	ActionListener listener;


	/*****************************************************************

	 *****************************************************************/
	public ViewUserList (AbstractListModel items) {
		this.items = items;
		listener = Listeners.getListener("ListListener");

		//sets the preferences for this JPanel
		this.setMaximumSize(ProgramStyle.actionPanelSize());
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,25,10));
		this.setLayout(new BorderLayout());


		this.add(setUpTitlePanel(), BorderLayout.NORTH);
		this.add(setUpActionPanel(), BorderLayout.CENTER);
	}


	/*****************************************************************

	 *****************************************************************/
	private JPanel setUpTitlePanel () {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);

		JLabel textJLabel;
		if (items instanceof RentalList){
			textJLabel = new JLabel("YOUR CURRENT DIRECTION: " 
					+ ((RentalList) items).getListName() + "...");
		}
		else {
			textJLabel = new JLabel("YOUR CURRENT DIRECTION: " 
					+ ((RentedList) items).getListName() + "...");
		}
		textJLabel.setFont(ProgramStyle.getFont(20));
		textJLabel.setForeground(ProgramStyle.WHITESIGN);
		textJLabel.setOpaque(false);
		panel.add(textJLabel, BorderLayout.SOUTH);

		return panel;

	}



	/*****************************************************************

	 *****************************************************************/
	private JPanel setUpActionPanel () {

		//Sets up the action panel
		JPanel actionPanel = new JPanel();
		actionPanel.setBorder(BorderFactory.createEmptyBorder(20,20,30,27));
		actionPanel.setLayout(new BorderLayout());
		actionPanel.setOpaque(false);

		//Sets up the JList to display the items
		list = new JList(items);
		list.setCellRenderer(ProgramStyle.getUserRenderer());
		list.addMouseListener(new ListClickListener(list));
		list.setOpaque(false);
		list.setBackground(ProgramStyle.GREENSIGN);

		//Place the JList into a scrollable window
		JScrollPane scrollPane = new JScrollPane(list,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

		//Add all the parts together
		actionPanel.add(scrollPane, BorderLayout.CENTER);
		return actionPanel;

	}


	/*****************************************************************

	 *****************************************************************/
	public void paintComponent (Graphics page) {

		ProgramStyle.paintSign(page, 0, 0, 
				ProgramStyle.actionPanelSize().width, 
				ProgramStyle.actionPanelSize().height,
				ProgramStyle.GREENSIGN, false);
	}

	/*****************************************************************

	 *****************************************************************/
	@Override
	public void showList(AbstractListModel list) {
		// TODO this needs to show the rentals to be returned

	}

	@Override
	public void showItem (Object obj) {}


	public class ListClickListener extends MouseAdapter {
		protected JList list;

		public ListClickListener(JList l){
			list = l;
		}

		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				int index = list.locationToIndex(e.getPoint());
				ListModel dlm = list.getModel();
				Object item = dlm.getElementAt(index);;
				list.ensureIndexIsVisible(index);
				System.out.println("Double clicked on " + item);
				listener.actionPerformed(new ActionEvent(item, index, ""));

			}
		}

	}
}