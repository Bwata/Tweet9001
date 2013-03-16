package view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import utilities.ButtonType;
import utilities.Listeners;
import utilities.ProgramStyle;
import utilities.TButton;

public class ButtonPanel extends JPanel{

	/*****************************************************************
    Basic Constructor to set up all the main buttons.
     *****************************************************************/
    public ButtonPanel() {

        //Sets panel properties
        setPreferredSize(ProgramStyle.TOP_PANEL_SIZE);
        //setBackground(ProgramStyle.PANEL_COLOR);
        //order of matte border method (top, left, bottom, right, color)
        //this.setBorder(BorderFactory.createMatteBorder(11, 5, 0, 11,
               // ProgramStyle.BACKGROUND_COLOR));

        //this.setName("Test");
        
        //grid layout with three rows and two columns
        this.setLayout(new GridLayout(2,2));

        //adds all the buttons based on the enum types
        JButton ht = new TButton(ButtonType.HOMETIMELINE);
        ht.addActionListener(Listeners.getListener("Button"));
        ht.setName("navButtons");
        add(ht);
        
        JButton se = new TButton(ButtonType.SEARCH);
        se.addActionListener(Listeners.getListener("Button"));
        se.setName("navButtons");
        add(se);
        
        JButton tr = new TButton(ButtonType.TRENDING);
        tr.addActionListener(Listeners.getListener("Button"));
        tr.setName("navButtons");
        add(tr);
        
        JButton dm = new TButton(ButtonType.DIRECT_MESSAGE);
        dm.addActionListener(Listeners.getListener("Button"));
        dm.setName("navButtons");
        add(dm);

    }
	
	
}
