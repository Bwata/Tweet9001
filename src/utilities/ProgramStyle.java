/*****************************************************************
This class is used to maintain consistency in the style of
 the GUI.

@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package utilities;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


public class ProgramStyle {
	
	//COLORS-------------------------------------

	/**Main background color of the window*/
	public static final Color BACKGROUND_COLOR = new Color (131, 185, 214);
	
	/**Color of the panels*/
	public static final Color PANEL_COLOR = new Color (247, 215, 178);
	
	/**Color of the panels*/
	public static final Color BUTTON_COLOR = new Color (252, 114, 114);
	
	
	
	
	//DIMENSIONS----------------------------------

	/**The Size of the programs window*/
	public static final Dimension WINDOW_SIZE = new Dimension(1200, 1000);
	
	/**The Size of the programs top panel*/
	public static final Dimension TOP_SIZE = 
			new Dimension(WINDOW_SIZE.width,200);
	
	/**The Size of the programs top panel*/
	public static final Dimension BUTTON_PANEL_SIZE = 
			new Dimension(TOP_SIZE.width/3, TOP_SIZE.height);
	
	/**The Size of the programs top panel*/
	public static final Dimension PROFILE_PANEL_SIZE = 
			new Dimension(TOP_SIZE.width/3, TOP_SIZE.height);
	
	/**The Size of the programs top panel*/
	public static final Dimension POST_PANEL_SIZE = 
			new Dimension(TOP_SIZE.width/3, TOP_SIZE.height);
	
	//need to add window size dimensions of just smaller then eos lab size
	
	//from screen size figure the four sections of the UI
	
	
	
	
	//fill in all the colors, sizes, strings, and other design and style variables

	/*****************************************************************
	Access the screen size of the computer program is on
 	*****************************************************************/
	public static Dimension windowSize () {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		return new Dimension (dim.width, dim.height - 45);
	}


	/*****************************************************************
	This sends back the font style that is used for the GUI at the 
	requested size.

	@param size int font size desired.
	 *****************************************************************/
	public static Font getFont (int size) {
		return new Font ("Halvetica", Font.BOLD, size);
	}

    /*****************************************************************
	Paints the background image to use behind all the View windows
	 *****************************************************************/
	public static void paintBackground (Component c, Graphics page) {
		ImageIcon backGround = new ImageIcon("Images/CloudBackground.png");
		backGround.paintIcon(c, page, 0, 0);
	}

	/*****************************************************************
     Determines the size of a string to be painted with the given
     string and fontsize.
     
     @param page Graphics object string will be painted on.
     @param title String to get size for.
     @param fontSize int font size sting will be painted at.
     @return Dimension x, and y size of the string to be painted.
	 *****************************************************************/
	public static Dimension getStringSize (Graphics page, String title,
                                           int fontSize) {

		FontMetrics fm = page.getFontMetrics(ProgramStyle.getFont(fontSize));
		Dimension stringSize = new Dimension(fm.stringWidth(title), 
				fm.getHeight());

		return stringSize;
	}


	/*****************************************************************
	Accesses the renderer to paint the list items.

	@return ListCellRenderer used to paint the list items.
	 *****************************************************************/
	public static ListCellRenderer getListRenderer () {
		ProgramStyle temp = new ProgramStyle();
		return temp.new ListRenderer();
	}

	/* Ideas for different types of lists
	 * 
	 * Time lines
	 * trend lists
	 * 
	 */

	/*****************************************************************
	The ListRenderer object that is used to paint items in a list
	 *****************************************************************/
	class ListRenderer extends JPanel implements ListCellRenderer {

		/*****************************************************************
		One of two required methods to use to Paint list items.
		 *****************************************************************/
		@Override
		public Component getListCellRendererComponent(
				final JList list,              // the list
				final Object value,            // value to display
				final int index,               // cell index
				final boolean isSelected,      // is the cell selected
				final boolean cellHasFocus)    // does the cell have focus
		{
			return new JPanel() {

				public void paintComponent(Graphics g) {
					super.paintComponent(g);

					/*this is where the styling needs to be built.
						you need to use things as if you are painting
						them. You can not use them like putting objects in
						a jpanel.
						
						examples: 
						g.setColor(isSelected ? ProgramStyle.GREENSIGN : 
						ProgramStyle.GREENSIGN);
						
						image = new ImageIcon("IMAGE NAME");
						image.paintIcon(this, g, 5, 5);
						
						g.setColor(WHITESIGN);
						g.setFont(ProgramStyle.getFont(20));
						g.drawString("Words to display);
						*/
				}
				
				public Dimension getPreferredSize() {
					return new Dimension(100,100);
				}
			};
		}
	}
}