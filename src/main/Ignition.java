/*****************************************************************
The flint to start the whole program.
Then the controller takes over.


@author Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package main;

import java.util.Scanner;



public class Ignition {

	/**
	 * @param cheese
	 */
	public static void main(String[] cheese) {
		
		//this stops the program at the beginning to allow code cover to get more
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Press Enter key to continue...");
//		scanner.nextLine();
		
		Controller control;
		
		try{
			
			control = new Controller();
		
		}
		catch (Exception e) {
			
			control = new Controller();
			e.printStackTrace();
		
		}
	}
}
