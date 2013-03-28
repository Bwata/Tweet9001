package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import twitter4j.User;
import utilities.ProgramStyle;

public class ProfilePanel extends JPanel{
	
	/*****************************************************************

	 *****************************************************************/
	public ProfilePanel(User user) {
		
		this.setName("borderPanel");
		this.setPreferredSize(ProgramStyle.MAIN_PANEL);
		
		add(new JLabel(user.getScreenName()));
		
		
	}

}
