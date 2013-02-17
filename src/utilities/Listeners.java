/*****************************************************************
This is the listener holding tank for all the actions that need to
be performed.

@author Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package utilities;

import java.awt.event.ActionListener;
import java.util.ArrayList;
/*****************************************************************
Sets up the needed material for the listeners of the program.
 *****************************************************************/
public class Listeners {

    /**Array of Listeners for view side to access.*/
    private static ArrayList<ActionListener> listeners =
        new ArrayList<ActionListener>();

    /*****************************************************************
    Access the specific listener for the Action by passing it the
    string for the listener.

    @param type String variable to match with a specific listener.
    @return ActionListener for the action desired
     *****************************************************************/
    public static ActionListener getListener(String type) {

        for (int index = 0; index < listeners.size(); index++) {
            if (listeners.get(index).toString() == (type)) {
                return listeners.get(index);
            }
        }

            return null;
    }

    /*****************************************************************
    Adds an action listener to this holding tank.

    @param listener ActionListener to add to the tank.
     *****************************************************************/
    public static void addListener(ActionListener listener) {
        listeners.add(listener);
    }
}
