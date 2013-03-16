package testingGround;

import java.awt.ScrollPane;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;

import twitter4j.Location;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import utilities.ProgramStyle;
import utilities.TrendLocations;
import view.ButtonPanel;
import view.ViewMain;
import view.WorldTrendsPanel;

public class TestFrame {

	/**
	 * @param args
	 * @throws TwitterException 
	 */
	public static void main(String[] args) throws TwitterException {
		
		SynthLookAndFeel laf = new SynthLookAndFeel();
		try {
			laf.load(LAFTesting.class.getResourceAsStream("laf.xml"), LAFTesting.class);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			UIManager.setLookAndFeel(laf);
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//Sets up the main frame and background of the whole program
        JFrame frame = new JFrame("Tweet9001");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setPreferredSize(ProgramStyle.windowSize());

		Twitter twitter = TwitterFactory.getSingleton();
		
		ResponseList<Location> abc;
		try {
			abc = twitter.getAvailableTrends();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			abc = null;
			e.printStackTrace();
		}
        
		TrendLocations tl = new TrendLocations(abc);
		//tl.printTrendLocations();
        
		
        frame.getContentPane().add(new WorldTrendsPanel(tl.getArray()));

 
        frame.pack();
        frame.setVisible(true);

	}

}
