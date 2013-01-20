/*****************************************************************
 Interface for the enums to set the JPanel buttons.

 @author Thomas Verstaete
 @version Fall 2012
 *****************************************************************/

package utilities;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public interface ProgButton {

	/*****************************************************************

	 *****************************************************************/
	abstract String getTitle ();
	
	/*****************************************************************

	 *****************************************************************/
	abstract String getClassName();
	
	/*****************************************************************

	 *****************************************************************/
	abstract ActionListener getListener();
	
	/*****************************************************************

	 *****************************************************************/
	abstract ImageIcon getIcon();
	
	/*****************************************************************

	 *****************************************************************/
	abstract ProgButton getType();
	
	/*****************************************************************

	 *****************************************************************/
	abstract void setPassedObject(Object obj);
	
	/*****************************************************************

	 *****************************************************************/
	abstract Object getPassedObject();
}
