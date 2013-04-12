/*****************************************************************
 Interface for the enums to set the JPanel buttons.

 @author Thomas Verstaete
 @version Fall 2012
 *****************************************************************/

package zOldCode;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

/*****************************************************************
The interface for the enums in the JPanel buttons.
 *****************************************************************/
public interface ProgButton {

    /*****************************************************************
    Returns the title of the button for painting purposes.

    @return String of the title of the enum.
     *****************************************************************/
    abstract String getTitle();

    /*****************************************************************
    Returns that class name of the type of enum this is.

    @return String of the class name of the button.
     *****************************************************************/
    abstract String getClassName();

    /*****************************************************************
    Returns the listener to be used in the buttons.

    @return ActionListener for this type of button.
     *****************************************************************/
    abstract ActionListener getListener();

    /*****************************************************************
    Returns the Image that is used for this button. It might return
    a null if there is none.

    @return ImageIcon of the button to be painted.
     *****************************************************************/
    abstract ImageIcon getIcon();

    /*****************************************************************
    Returns the enum this consists of. Don't know if this is needed.

    @return ProgButton enum instance of the created type
     *****************************************************************/
    abstract ProgButton getType();

    /*****************************************************************
    Object that can be passed with this enum.

    @param obj Object to pass with enum.
     *****************************************************************/
    abstract void setPassedObject(Object obj);

    /*****************************************************************
    Getting the object that is attached to enum if set to pass.

    @return Object that is attached to enum.
     *****************************************************************/
    abstract Object getPassedObject();
}