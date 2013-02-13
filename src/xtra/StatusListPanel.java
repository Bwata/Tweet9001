package xtra;

import javax.swing.JPanel;

import twitter4j.Status;
import utilities.ProgramStyle;
import view.StatusList;

public class StatusListPanel extends JPanel {

	
	public StatusListPanel (Status[] stati) {
	
	//sets the background color of the panel
	setBackground(ProgramStyle.BACKGROUND_COLOR);
	
	add(new StatusList(stati));
	
		
	}
	
	
}
