/*****************************************************************
Panel to display all the buttons in the top right side of the
window.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package main;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utilities.*;

public class ViewButtonPanel extends JPanel {

	
	/*****************************************************************
	Basic Constructor to set up all the main buttons.
	 *****************************************************************/
	ViewButtonPanel () {
		
		//Sets panel properties
		setPreferredSize(ProgramStyle.POST_PANEL_SIZE);
		setBackground (ProgramStyle.PANEL_COLOR);
		//order of matteborder method (top, left, bottom, right, color)
		this.setBorder(BorderFactory.createMatteBorder(11, 5, 0, 11, 
				ProgramStyle.BACKGROUND_COLOR));
		
		//grid layout with three rows and two columns
		this.setLayout(new GridLayout(3,2));
		
		//adds all the buttons based on the enum types
		add(new MyButton(MainButtons.HOMETIMELINE));
		add(new MyButton(MainButtons.POST_TWEET));
		add(new MyButton(MainButtons.SEARCH));
		add(new MyButton(MainButtons.TRENDING));
		add(new MyButton(MainButtons.VIEW_PROFILE));
		add(new MyButton(MainButtons.BLANK));
	}
}
