package utilities;

import javax.swing.JButton;

/*****************************************************************
Button that is able to capture.

started March 14, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
public class TButton extends JButton {

	/**The ButtonType enum of this button.*/
	ButtonType type;

	/**The object attached for passing to the listener.*/
	Object passedObject;

	/*****************************************************************
	Constructor. Define variables from the Enum parameter.

	@param type ButtonType of this button.
	 *****************************************************************/
	public TButton(ButtonType type) {
		super(type.getTitle());
		this.type = type;
	}

	/*****************************************************************
	Constructor. Define the title and type seperately to allow for 
	different text in the button.

	@param type ButtonType of this button.
	@param title String to be placed in the button GUI.
	 *****************************************************************/
	public TButton(ButtonType type, String title) {
		super(title);
		this.type = type;
	}

	/*****************************************************************
	Returns the type of button.

	@return ButtonType of the button.
	 *****************************************************************/
	public ButtonType getButtonType() {
		return type;
	}

	/*****************************************************************
	Return the object to be passed with the button.

	@return Object that is passed.
	 *****************************************************************/
	public Object getPassedObject() {
		return passedObject;
	}

	/*****************************************************************
	Sets the object to be passed with the button.

	@param obj Object to be passed.
	 *****************************************************************/
	public void setPassedObject(Object obj) {
		passedObject = obj;
	}
}