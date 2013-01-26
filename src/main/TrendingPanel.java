/*****************************************************************
Displays all the trends as compiled by twitter.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package main;

import javax.swing.JLabel;
import javax.swing.JPanel;

import twitter4j.Trend;
import utilities.ProgramStyle;

public class TrendingPanel extends JPanel {
	
	
	/*****************************************************************
	Basic Constructor for the trend list
	
	@param trends Trend[] list of the top ten trends.
	 *****************************************************************/
	TrendingPanel (Trend[] trends) {
		
		//sets the background color of the panel
		setBackground(ProgramStyle.BACKGROUND_COLOR);
		
		//for loop to display all trends in array
		for (int i = 0; i < trends.length ; i++) {
			add (new JLabel((i+1) + ":  " + trends[i].getName() + "   "));
		}
	}
}
