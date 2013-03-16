/*****************************************************************

started March 3, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import utilities.ButtonType;
import utilities.Listeners;
import utilities.ProgramStyle;
import utilities.TButton;
import utilities.TrendLocations.TrendLocation;
import utilities.WorldTrendButton;

public class WorldTrendsPanel extends JPanel{

	//public WorldTrendsPanel(TreeSet<TrendLocation> locals) {
	public WorldTrendsPanel(TrendLocation[] locals) {

		JPanel main = new JPanel();
		
		main.setLayout(new BoxLayout
				(main, javax.swing.BoxLayout.PAGE_AXIS));
		
		JPanel countryPanel = new JPanel();
		JPanel townPanel = new JPanel();
		
		for (int i = 0; i < locals.length; i++) {
			TrendLocation loc = locals[i]; 
			
			if (loc.getLocation().getPlaceCode() == 19) {
				countryPanel = new JPanel();
				
				TButton button = new TButton(ButtonType.WORLD_TRENDING, loc.getName());
				button.setPassedObject(loc);
				button.addActionListener(Listeners.getListener("Button"));
				
				
				countryPanel.add(button);
				main.add(countryPanel);
			} else if (loc.getLocation().getPlaceCode() == 12) {
				//country
				countryPanel = new JPanel();
				countryPanel.setLayout(new BorderLayout());
				countryPanel.setBorder
					(BorderFactory.createEmptyBorder(20,0,0,0));
				
				TButton button = new TButton(ButtonType.WORLD_TRENDING, loc.getName());
				button = new WorldTrendButton(loc.getName());
				button.setPassedObject(loc);
				button.addActionListener(Listeners.getListener("Button"));
//				button.addActionListener(new ButtonListener
//						(loc.getLocation().getWoeid()));
				
				countryPanel.add(button, BorderLayout.NORTH);
				
				if (locals[i+1].getLocation().getPlaceCode() == 7) {
					townPanel = new JPanel();
					townPanel.setLayout(new GridLayout(0,2));
					countryPanel.add(townPanel, BorderLayout.CENTER);
				}
					
				main.add(countryPanel);
			} else { //town				
				
				TButton button = new TButton(ButtonType.WORLD_TRENDING, loc.getName());
				button = new WorldTrendButton(loc.getTownName());
				button.setPassedObject(loc);
				button.addActionListener(Listeners.getListener("Button"));
//				button.addActionListener(new ButtonListener
//						(loc.getLocation().getWoeid()));
				
				townPanel.add(button);
			}

		}

		JScrollPane scrollPane = new JScrollPane(main,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension
				(ProgramStyle.MAIN_ELEMENT_WIDTH + 50, ProgramStyle.MAIN_HEIGHT));
		add(scrollPane);
		
	}
	
	/*****************************************************************

	 *****************************************************************/
	public class ButtonListener implements ActionListener {

		/***/
		int locID;
		
		/*****************************************************************

		 *****************************************************************/
		public ButtonListener (int id) {
			locID = id;
		}
		
		/*****************************************************************

		 *****************************************************************/
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Twitter twitter = TwitterFactory.getSingleton();
			Trends trends = null;
			try {
				trends = twitter.getPlaceTrends(locID);
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(trends);
		}
	}
}
