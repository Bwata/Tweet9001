/*****************************************************************
Panel to display all the buttons in the top right side of the
window.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import utilities.MainButtons;
import utilities.MyButton;
import utilities.ProgramStyle;

/*****************************************************************
The panel that displays the main group of buttons in the top right.
 *****************************************************************/
public class ButtonPanel extends JPanel {

    /*****************************************************************
    Basic Constructor to set up all the main buttons.
     *****************************************************************/
    ButtonPanel() {

        //Sets panel properties
        setPreferredSize(ProgramStyle.POST_PANEL_SIZE);
        setBackground(ProgramStyle.PANEL_COLOR);
        //order of matte border method (top, left, bottom, right, color)
        this.setBorder(BorderFactory.createMatteBorder(11, 5, 0, 11,
                ProgramStyle.BACKGROUND_COLOR));

        //grid layout with three rows and two columns
        this.setLayout(new GridLayout(3,1));

        //adds all the buttons based on the enum types
        add(new MyButton(MainButtons.HOMETIMELINE));
        add(new MyButton(MainButtons.SEARCH));
        add(new MyButton(MainButtons.TRENDING));

    }
}
