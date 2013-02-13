/*****************************************************************
Displays all the trends as compiled by twitter.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package xtra;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

import twitter4j.Trend;
import utilities.ProgramStyle;
import view.TrendingList;

public class TrendingPanel extends JPanel {
	
	
	/*****************************************************************
	Basic Constructor for the trend list
	
	@param trends Trend[] list of the top ten trends.
	 *****************************************************************/
	TrendingPanel (Trend[] trends) {
		
		//sets the background color of the panel
		setBackground(ProgramStyle.BACKGROUND_COLOR);
		
		add(new TrendingList(trends));
	}
}
