/*****************************************************************
Panel to display the basic profile information of the user.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package main;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utilities.ProgramStyle;

public class ViewSmallProfilePanel extends JPanel {


	/*****************************************************************
	Basic Constructor for the profile information panel.
	 *****************************************************************/
	ViewSmallProfilePanel () {
		//Sets panel properties
		setPreferredSize(ProgramStyle.POST_PANEL_SIZE);
		setBackground (ProgramStyle.PANEL_COLOR);
		//order of matteborder method (top, left, bottom, right, color)
		this.setBorder(BorderFactory.createMatteBorder(11, 5, 0, 5, 
				ProgramStyle.BACKGROUND_COLOR));
	}
}
