package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utilities.ButtonType;
import utilities.Listeners;
import utilities.ProgramStyle;
import utilities.TButton;
/*****************************************************************
creats a small group edit panel
 *****************************************************************/
public class SmallGroupsEditPanel extends JPanel {

	/*****************************************************************
	creatse a small group edit panel.
	 *****************************************************************/
	public SmallGroupsEditPanel(String[] groups) {

		setPreferredSize(ProgramStyle.TOP_PANEL_SIZE);
		setName("borderPanel");
		setLayout(new BorderLayout());
		
		
		JPanel topPanel = new JPanel();
		topPanel.setName("stdPanel");
		topPanel.setLayout(new BorderLayout());



		JTextArea newGroupName = new JTextArea();
		newGroupName.setName("writeArea");

		TButton newButton = new TButton(ButtonType.NEW_GROUP);
		newButton.setPassedObject(newGroupName);
		newButton.addActionListener(Listeners.getListener("Button"));

		topPanel.add(new JLabel("New Group: "), BorderLayout.WEST);
		topPanel.add(newGroupName, BorderLayout.CENTER);
		topPanel.add(newButton, BorderLayout.EAST);

		add(topPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setName("stdPanel");
		buttonPanel.setLayout(new GridLayout(0,2));

		for (int i = 0; i < groups.length; i ++) {

			TButton deleteButton = new TButton(ButtonType.DELETE_GROUP, "Delete " + groups[i]);
			deleteButton.setPassedObject(groups[i]);
			deleteButton.addActionListener(Listeners.getListener("Button"));
			buttonPanel.add(deleteButton);

		}
		
		add(buttonPanel, BorderLayout.CENTER);
	}
}
