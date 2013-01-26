/*****************************************************************
This is the enum that contains all the information that is to be 
used for the Login buttons. 

@author Nick Carter, Tyler Hutek, Tyler McCarthy, Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package xtra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import utilities.Listeners;
import utilities.ProgButton;

public enum UserButtonEnum implements ProgButton{
	
	/**This is to show the users account history*/
	HISTORY ("History"), 
	
	/***/
	FAVORITES ("Favorites"),
	
	/***/
	RENTLIST ("Rent List"),
	
	/**This is to display the search dialog box.*/
	SEARCH_LIST ("Search"),
	
	/***/
	SEARCH ("Search"),
	
	/***/
	LIBRARY ("Library"),
	
	/***/
	VIEW_ACCOUNT ("View Account"),

	/***/
	CHECKED_OUT ("Return Items"),
	
	/***/
	LOGOUT ("Logout"),
	
	/***/
	SAVE_ACCOUNT ("Save Changes"),
	
	//these are used in the rental dialog screen
	
	/***/
	RENT ("Rent"),
	
	/***/
	RETURN ("Return"),
	
	/***/
	ADD_FAVORITE ("+ Favorite"),
	
	/***/
	REMOVE_FAVORITE ("- Favorite"),
	
	/***/
	ADD_RENTLIST ("+ RENT LIST"),
	
	/***/
	REMOVE_RENTLIST ("- RENT LIST"),
	
	//These are for the checkout process
	PAY ("Pay");
	
	
	/***/
	private final String title;
	
	/***/
	private final static String className = "UserButton";
	
	/***/
	private final ImageIcon image;
	
	/***/
	public static ActionListener listener;
	
	/***/
	private Object passedObject;
	
	/*****************************************************************
	Constructs the Enum Type based on the chosen enum.
	 *****************************************************************/
	private UserButtonEnum (String title) {
		this.title = title;
		image = new ImageIcon ("Images/ButtonImages/" + this.toString() + ".png"); //this goes and collects the image for the button
		setListener();
	}
	
	/*****************************************************************
	Returns tha title of the button for painting purposes
	
	@return String of the title of the enum
	 *****************************************************************/
	@Override
	public String getTitle () {
		return title;
	}
	
	/*****************************************************************
	Returns that class name of the type of enum this is.
	
	@return String of the class name of the button.
	 *****************************************************************/
	@Override
	public String getClassName() {
		return className;
	}
	
	/*****************************************************************
	Sets the listener for this type of button.
	 *****************************************************************/
	public static void setListener () {
		listener = Listeners.getListener(className);
	}

	/*****************************************************************
	Returns the listener to be used in the buttons.
	
	@return ActionListener for this type of button.
	 *****************************************************************/
	@Override
	public ActionListener getListener() {
		return listener;
	}

	/*****************************************************************
	Returns the Image that is used for this button. It might return
	a null if there is none.
	
	@return ImageIcon of the button to be painted.
	 *****************************************************************/
	@Override
	public ImageIcon getIcon() {
		return image;
	}

	@Override
	public ProgButton getType() {
		return this;
	}

	@Override
	public void setPassedObject(Object obj) {
		this.passedObject = obj;
	}

	@Override
	public Object getPassedObject() {
		return passedObject;
	}



	
 
	
}