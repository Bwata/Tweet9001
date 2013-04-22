/*****************************************************************
Panel of Buttons on the top right side of the screen.

started March 8, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import utilities.ButtonType;
import utilities.Listeners;
import utilities.ProgramStyle;
import utilities.TButton;

public class ButtonPanel extends JPanel {

    /*****************************************************************
    Basic Constructor to set up all the main buttons.
     *****************************************************************/
    public ButtonPanel() {

        //Sets panel properties
        setPreferredSize(ProgramStyle.TOP_PANEL_SIZE);
        setName("borderPanel");

        //grid layout with three rows and two columns
        this.setLayout(new GridLayout(3,2));

        //adds all the buttons based on the enum types
        TButton ht = new TButton(ButtonType.HOMETIMELINE);
        ht.addActionListener(Listeners.getListener("Button"));
        ht.setName("navButtons");
        add(ht);

        TButton se = new TButton(ButtonType.SEARCH);
        se.addActionListener(Listeners.getListener("Button"));
        se.setName("navButtons");
        add(se);

        TButton tr = new TButton(ButtonType.TRENDING);
        tr.addActionListener(Listeners.getListener("Button"));
        tr.setName("navButtons");
        add(tr);

        TButton dm = new TButton(ButtonType.DIRECT_MESSAGE);
        dm.addActionListener(Listeners.getListener("Button"));
        dm.setName("navButtons");
        add(dm);

        TButton ep = new TButton(ButtonType.EDIT_PROFILE);
        ep.addActionListener(Listeners.getListener("Button"));
        ep.setName("navButtons");
        add(ep);

        TButton gp = new TButton(ButtonType.GROUPS);
        gp.addActionListener(Listeners.getListener("Button"));
        gp.setName("navButtons");
        add(gp);
    }
}