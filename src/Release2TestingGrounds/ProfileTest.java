package Release2TestingGrounds;

import java.io.File;

import javax.swing.JFileChooser;

import view.SmallProfilePanel;

public class ProfileTest {
	
	public static void main(String[] cheese){
		
		SmallProfilePanel.update("test9002", null, "BEHIND YOU", "stuff");
		
		JFileChooser chooser = new JFileChooser();
		
		chooser.showOpenDialog(null);
				
		File file = chooser.getSelectedFile();
		
		SmallProfilePanel.updatePic(file);
	}

}
