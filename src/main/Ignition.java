/*****************************************************************
The flint to start the whole program.
Then the controller takes over.

@author Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package main;

import java.text.ParseException;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;

import testingGround.LAFTesting;
/*****************************************************************
This class is the main class to the program.
 *****************************************************************/
public class Ignition {

     /*****************************************************************
     The main method for the program. This starts the controller.
     @param cheese any command line arguments from the user
     *****************************************************************/
     public static void main(String[] cheese) {

//        this stops the program at the beginning to
//        allow code cover to get more
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter key to continue...");
        scanner.nextLine();

        SynthLookAndFeel laf = new SynthLookAndFeel();
 		try {
			laf.load(LAFTesting.class.getResourceAsStream(
					"../utilities/laf.xml"), LAFTesting.class);
		} catch (ParseException e1) {
			//e1.printStackTrace();
		}
 		try {
			UIManager.setLookAndFeel(laf);
		} catch (UnsupportedLookAndFeelException e1) {
			//e1.printStackTrace();
		}

        Controller control;

        try {

            control = new Controller();

            } catch (Exception e) {
            control = new Controller();
            e.printStackTrace();
        }
    }
}
