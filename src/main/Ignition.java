/*****************************************************************
The flint to start the whole program.
Then the controller takes over.


@author Thomas Verstraete
@version Fall 2012
 *****************************************************************/
package main;



public class Ignition {

	/**
	 * @param args
	 */
	public static void main(String[] cheese) {
		
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
