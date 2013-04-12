package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import utilities.ProgramStyle;

public abstract class MainPanel extends JPanel{
	
	/***/
	protected JPanel mainPanel;
	
	/***/
	protected JPanel accessPanel;
	
	/*****************************************************************

	 *****************************************************************/
	public MainPanel () {
		
		//Panel preferences
		this.setName("voidPanel");
		this.setLayout(new BorderLayout());
		
		mainPanel = new JPanel();
		mainPanel.setName("voidPanel");
		mainPanel.setLayout(new GridLayout(0,1));
		
		accessPanel = new JPanel();
		accessPanel.setName("borderPanel");

	}
	
	public abstract void showList(Object[] obj);
	
	public abstract void addList(Object[] obj);
	
	public abstract void reset();
	
	public abstract void showAccessPanel(Object obj);

}
