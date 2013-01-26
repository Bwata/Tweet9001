/*****************************************************************
Panel containing a text box for the posting of tweets.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package main;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utilities.ProgramStyle;

public class ViewPostingPanel extends JPanel {

	
	/*****************************************************************
	Basic Constructor for the panel to write a tweet
	 *****************************************************************/
	ViewPostingPanel () {
		
		//Sets panel properties
		setPreferredSize(ProgramStyle.POST_PANEL_SIZE);
		setBackground (ProgramStyle.PANEL_COLOR);
		//order of matteborder method (top, left, bottom, right, color)
		this.setBorder(BorderFactory.createMatteBorder(11, 10, 0, 5, 
				ProgramStyle.BACKGROUND_COLOR));
	}
}
