package testingGround;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import twitter4j.User;

public class TestProfile extends JPanel{
	
	User user;
	
	Image background;
	
	BufferedImage profilePic;
	
	public TestProfile (User user) {
		this.user = user;
		
		this.setLayout(new GridLayout(0,1));

		add(bigProfilePic());
		add(profileBackground());
		add(new JLabel(user.getProfileBackgroundColor()));
		
		
	}
	
	public void paintComponent(Graphics grafica) {
	     grafica.drawImage(profilePic, 0, 0, null); //no need for ImageObserver here
	  }
	
	/*****************************************************************
	Bigger Profile Image
	 *****************************************************************/
	private JLabel bigProfilePic () {
		
		 BufferedImage profilePic;
	        try {
	            profilePic = ImageIO.read(
	                    new URL(user.getBiggerProfileImageURL()));
	            System.out.println(profilePic.getHeight() + "," + profilePic.getWidth());
	        } catch (IOException e) {
	            profilePic = null;
	            //e.printStackTrace();
	        }

	        return new JLabel(new ImageIcon(profilePic));
	}
	
	/*****************************************************************

	@return JLabel
	 *****************************************************************/
	private JLabel profileBackground () {		

		
	        try {
	            profilePic = ImageIO.read(
	                    new URL(user.getProfileBannerURL()));
	            System.out.println(profilePic.getHeight() + "," + profilePic.getWidth());
	        } catch (IOException e) {
	            profilePic = null;
	            //e.printStackTrace();
	        }

	        return new JLabel(new ImageIcon(profilePic));
		
	}

}
