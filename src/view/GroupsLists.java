/*****************************************************************

started April 14, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import twitter4j.Status;
import utilities.ProgramStyle;

public class GroupsLists extends JPanel {

	public GroupsLists(String[] groupNames, Status[][] groupStati) {


		this.setName("voidPanel");

		//this.setName("borderPanel");
		//this.setPreferredSize(ProgramStyle.MAIN_PANEL);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setName("voidPanel");
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));


		//for loop to do something
		for (int i = 0; i < groupNames.length ; i++) {
			
			JPanel panel = new JPanel();
			panel.setName("voidPanel");
			panel.setLayout(new BorderLayout());
			
			JLabel groupNm = new JLabel(groupNames[i]);
			groupNm.setName("H3");
			
			panel.add(groupNm, BorderLayout.NORTH);
			
			System.out.println("GroupLists: Number of Stati in list: " + groupStati[i].length);
			
			JPanel statusPanel = new StatusList(groupStati[i]);
			Dimension dim = ProgramStyle.MAIN_PANEL;
			statusPanel.setPreferredSize(new Dimension (dim.width, dim.height - 50));
			
			panel.add(statusPanel, BorderLayout.CENTER);
			
			mainPanel.add(panel);
			
		}
		
		JScrollPane scrollPane = new JScrollPane(mainPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				Dimension dim = ProgramStyle.WINDOW_SIZE;
				
				dim = new Dimension(dim.width, (dim.height - ProgramStyle.TOP_SIZE.height));
				
				scrollPane.setPreferredSize(dim);
				scrollPane.setOpaque(false);
				//eliminates the default border
				//scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		
				this.add(scrollPane);

	}


}
