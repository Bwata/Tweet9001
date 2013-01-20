/*****************************************************************
This class takes care of all the buttons, from drawing them to 
getting their own listeners based on the enum passed. and if the
user wants a picture it will get it itself.

@author Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class MyButton extends JPanel {

	/**The possible image to display*/
	ImageIcon image;

	/**The title of the button and also the displayed label**/
	String title;

	/**when clicked call this listener*/
	ActionListener listener;

	/**The enum that passes */
	ProgButton type;

	/*These are the "sign style button" options*/
	/**The size of the button*/
	private Dimension buttonSize;

	/**The Size of the font for the button*/
	private int fontSize;

	/**The background color of the button*/
	private Color color;

	/**This fits the button to the title String size*/
	private boolean fitButton;

	/**This is the passed string for the action listener*/
	private String passedString;

	/*****************************************************************
	Constructor for a sign button basic and simple.
	 *****************************************************************/
	public MyButton (ProgButton buttonType) {
		
		//sets the variables
		type = buttonType;
		title = type.getTitle().toUpperCase();
		passedString = title;

		//gets the lister based on the type
		listener = type.getListener();

		//Sets the default settings for the button
		setDefaultStyle();

		//adds the click listener for the button
		addMouseListener(new ClickListener());

		//sets the default size of the button
		setPreferredSize(buttonSize);
	}

	/*****************************************************************
	sets the default style choices for the sign style buttons
	 *****************************************************************/
	public void setDefaultStyle () {

		//styling for the button here

	}

	/*****************************************************************
	Paints the image used for the "button" on the JPanel.

	@param page Graphics, like you do.
	 *****************************************************************/
	public void paintComponent (Graphics page) {

		try {   // if there is an image for this to paint
			image.paintIcon(this, page, 0, 0);
		}
		catch (NullPointerException e) { // if no image 

			//if the button is to fit the text size, sets the size
			if (fitButton){
				Dimension dim = ProgramStyle.getStringSize(page,
						title, fontSize);
				buttonSize = new Dimension (dim.width + 20, 
						buttonSize.height);
			}

			//most fo the painting of the button to be done here.
	
		}

	}
	/*****************************************************************
	Figures out where to put the String title to center it in the box.
	
	@param page Graphics, like you do.
	 *****************************************************************/
	private Point getTitleLocation (Graphics page) {

		//get the size of the string to be printed.
		FontMetrics fm = page.getFontMetrics
				(ProgramStyle.getFont(fontSize));
		Dimension stringSize = new Dimension(fm.stringWidth(title), 
				fm.getHeight());

		//Finds the center of the image
		Point center = new Point (buttonSize.width / 2, 
				buttonSize.height / 2);

		//Finds the point bottom left of the string to make it 
			//centered on the image
		Point string = new Point(center.x-(stringSize.width/2), 
				center.y + (stringSize.height/2)-3);

		return string;
	}

	/*****************************************************************
	Tells the button to use an image for the button. gets the image'
	from the enum type
	 *****************************************************************/
	public void useImageForButton () {
		image = type.getIcon();
		buttonSize = new Dimension (image.getIconWidth(), image.getIconHeight());
		setPreferredSize(buttonSize);
	}

	/*****************************************************************
	Changes the default String passed in the ActionEvent Call.
	 *****************************************************************/
	public void setPassedString(String str) {
		this.passedString = str;
	}

	/*****************************************************************
	Collects the String used to passed thru the ActionEvent call.
	
	@return Object String type
	 *****************************************************************/
	public Object getPassedString() {
		return passedString;
	}


	/*****************************************************************
	Gets the size of the button
	 *****************************************************************/
	public Dimension getButtonSize() {
		return buttonSize;
	}

	/*****************************************************************
	Sets the size of the button
	 *****************************************************************/
	public void setButtonSize(Dimension buttonSize) {
		this.buttonSize = buttonSize;
	}

	/*****************************************************************
	makes the button fit to the size of the text
	 *****************************************************************/
	public void fitButtonToText() {
		fitButton = true;
	}

	/*****************************************************************
	Gets the font size used in the button.
	 *****************************************************************/
	public int getFontSize() {
		return fontSize;
	}

	/*****************************************************************
	sets the font size used in the button
	 *****************************************************************/
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/*****************************************************************
	gets the background color
	
	@return Color of the background
	 *****************************************************************/
	public Color getColor() {
		return color;
	}

	/*****************************************************************
	sets the background color
	 *****************************************************************/
	public void setColor(Color color) {
		this.color = color;
	}

	/*****************************************************************
	Private Listener for the mouse to click the panel to activate the
	"button"
	 *****************************************************************/
	public class ClickListener implements MouseListener {

		/*****************************************************************
		detects the Mouse click and passes to the "button" listener the
		button type, enum.
		 *****************************************************************/
		@Override
		public void mouseClicked (MouseEvent event) {
			listener.actionPerformed(new ActionEvent (type, 0,
					passedString));
		}

		/*****************************************************************
		Unused interface methods
		 *****************************************************************/
		@Override
		public void mousePressed (MouseEvent event) {}

		@Override
		public void mouseReleased (MouseEvent event) {}

		@Override
		public void mouseEntered (MouseEvent event) {}

		@Override
		public void mouseExited (MouseEvent event) {}
	}

}
