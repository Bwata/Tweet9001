package utilities;

import javax.swing.JButton;


public class TButton extends JButton{
	
	ButtonType type;
	
	Object passedObject;

	public TButton(ButtonType type) {
		super(type.getTitle());
		this.type = type;
	}
	
	public TButton(ButtonType type, String title) {
		super(title);
		this.type = type;
	}

	/*****************************************************************

	 *****************************************************************/
	public ButtonType getButtonType() {
		return type;
	}

	public Object getPassedObject() {
		return passedObject;
	}
	
	public void setPassedObject(Object obj) {
		passedObject = obj;
	}
	
}
