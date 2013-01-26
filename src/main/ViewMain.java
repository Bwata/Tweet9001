/*****************************************************************
Main view contains all gui elements and controls their implementation.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/

package main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import twitter4j.Trend;
import utilities.*;

public class ViewMain extends JPanel{

	/**Top panel containing all semi-permanent panels*/
	JPanel topPanel;
	
	
	/*****************************************************************
	Constructor to set up all the properties of this panel
	 *****************************************************************/
	ViewMain () {
		
		setPreferredSize(ProgramStyle.WINDOW_SIZE);
		
		setBackground(ProgramStyle.BACKGROUND_COLOR);
		
		this.setLayout(new BorderLayout());
		add(setTopPanel(), BorderLayout.NORTH);
		//add(actionPanel, BorderLayout.CENTER);
		
		//this needs to set up the different parts of the panel
		
	}
	
	
	/*****************************************************************
	Sets up the three panel sections at the top of the window and 
	returns it to display.

	@return JPanel
	 *****************************************************************/
	private JPanel setTopPanel () {
		topPanel = new JPanel();
		//set the size of the panel
		topPanel.setPreferredSize(ProgramStyle.TOP_SIZE);
		//sets the color of the background
		topPanel.setBackground(ProgramStyle.BACKGROUND_COLOR);
		
		//sets up the three sections using BorderLayout method
		topPanel.setLayout(new BorderLayout());
		topPanel.add(new ViewPostingPanel(), BorderLayout.WEST);
		topPanel.add(new ViewSmallProfilePanel(), BorderLayout.CENTER);
		topPanel.add(new ViewButtonPanel(), BorderLayout.EAST);
		
		return topPanel;		
	}
	
	
	/*****************************************************************
	Display an array of trends in the main portion of the window.
	and then updates the display.
	
	@param trends Trend[]
	 *****************************************************************/
	public void showTrends (Trend[] trends) {		
		
		//add new trending panel to main section
		add(new TrendingPanel(trends), BorderLayout.CENTER);
		
		//refresh window
		updateUI();
	}
}
