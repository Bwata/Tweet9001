/*****************************************************************
Panel to display the basic profile information of the user.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import twitter4j.User;
import utilities.ProgramStyle;

public class SmallProfilePanel extends JPanel {

    /***/
    private User user;

	/*****************************************************************
	Basic Constructor for the profile information panel.
	 *****************************************************************/
	SmallProfilePanel (User user) {
		//Sets panel properties
		setPreferredSize(ProgramStyle.POST_PANEL_SIZE);
		setBackground (ProgramStyle.PANEL_COLOR);
		//order of matteborder method (top, left, bottom, right, color)
		this.setBorder(BorderFactory.createMatteBorder(11, 5, 0, 5, 
				ProgramStyle.BACKGROUND_COLOR));
		UIManager.put("ToolTip.background", ProgramStyle.BACKGROUND_COLOR);
		
		this.setToolTipText("<html>C<br>D<br>E</html>");
		
		
		if (user == null) {
		    JLabel errorMessage = new JLabel("Twitter is not responding :(");
		    setBackground (Color.red);
		    
		}
		else {
		    this.user = user;
		   this.add(setTopHalf());
		   this.add(setBottomHalf());
		}
		
		//String sName= "@";
        //sName += user.getScreenName();
        
//        String userDescription = user.getDescription();
//        int friendCount = user.getFriendsCount();
//        int favCount = user.getFavouritesCount();
//        int followerCount = user.getFollowersCount();
        
//        name = new JLabel("Name: " + user.getName());
//        screenName = new JLabel("Screen name: " + user.getScreenName());
//        description = new JLabel("Description: " + user.getDescription());
//        followers = new JLabel("Followers: " + user.getFriendsCount()); //Friends = followers?
//        followers = new JLabel("Following: " + user.getFollowersCount()); //Is followersCount your "friends" or people following you?
//        favs = new JLabel("Favorites: " + user.getFavouritesCount());
//        
//
//        top = new JPanel();
//        bottom = new JPanel();
//
//        top.add(name);
//        top.add(screenName);
//        top.add(description);
//        
//        bottom.add(following);
//        bottom.add(followers);
//        bottom.add(favs);
//        
//        add(BorderLayout.NORTH, top);
//        add(BorderLayout.CENTER, bottom);
//        

	}
	
	private JPanel setTopHalf() {
	    JPanel main = new JPanel();
	    main.setOpaque(false);
	    main.setLayout(new BorderLayout());
	    main.setPreferredSize(new Dimension 
	            (ProgramStyle.PROFILE_PANEL_SIZE.width, 
	            (ProgramStyle.PROFILE_PANEL_SIZE.height - 80)));
	    //main.setBorder(BorderFactory.createMatteBorder(1,1,1,1, 
                //Color.RED));
	    
	    //gets the profile image
        BufferedImage profilePic;
        try {
            profilePic = ImageIO.read(new URL(user.getBiggerProfileImageURL()));
            System.out.println (profilePic.getWidth() + ", " + profilePic.getHeight());
        } catch (IOException e) {
            profilePic = null;
            //e.printStackTrace();
        }
        
       
        JLabel picLabel = new JLabel (new ImageIcon(profilePic));
       // 
        main.add (picLabel, BorderLayout.WEST);
	    
	    JPanel east = new JPanel();
	    east.setOpaque(false);
	    east.setLayout(new BorderLayout());
	    east.setBorder(new EmptyBorder(5,5,5,5));
	    
	    JPanel names = new JPanel();
	    names.setLayout(new BorderLayout());
	    names.setOpaque(false);
	    
	    
	    //user name
	    JLabel userName = new JLabel(user.getName());
	    userName.setFont(ProgramStyle.getFont(20));
	    names.add(userName, BorderLayout.CENTER);
	    
	    //screen name
        JLabel screenName = new JLabel("@" + user.getScreenName());
        screenName.setFont(ProgramStyle.getFont(12));
        names.add(screenName, BorderLayout.EAST);
        
        east.add(names, BorderLayout.NORTH);
        
        //description
        JTextArea description = new JTextArea(user.getDescription());
        
        //description.setBorder(BorderFactory.createMatteBorder(1,1,1,1, 
                //Color.RED));
        
        description.setPreferredSize(description.getSize());
        description.setFont(ProgramStyle.getFont(10));
        description.setOpaque(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        east.add(description, BorderLayout.CENTER);
	    
        main.add(east, BorderLayout.CENTER);
	    
	    return main;
	}
	
	/*****************************************************************

	 *****************************************************************/
	private JPanel setBottomHalf() {
	    JPanel main = new JPanel();
	    main.setOpaque(false);
        main.setLayout(new GridLayout(1,3));
        main.setPreferredSize(new Dimension 
                (ProgramStyle.PROFILE_PANEL_SIZE.width, 
                (ProgramStyle.PROFILE_PANEL_SIZE.height - 140)));
        main.setBorder(new EmptyBorder(5,5,5,5));
	    
	    JPanel followPanel = new JPanel();
	    followPanel.setBackground (ProgramStyle.BACKGROUND_COLOR);
	    followPanel.setBorder(BorderFactory.createMatteBorder(5,5,5,5, 
                ProgramStyle.PANEL_COLOR));
	    followPanel.setLayout(new GridLayout(2,1));
	    followPanel.add(new JLabel("Following"));
	    followPanel.add(new JLabel("" + user.getFriendsCount()));
	    main.add(followPanel);
	    
	    JPanel followerPanel = new JPanel();
	    followerPanel.setBackground (ProgramStyle.BACKGROUND_COLOR);
	    followerPanel.setBorder(BorderFactory.createMatteBorder(5,5,5,5, 
                ProgramStyle.PANEL_COLOR));
	    followerPanel.setLayout(new GridLayout(2,1));
	    followerPanel.add(new JLabel("Followers"));
	    followerPanel.add(new JLabel("" + user.getFollowersCount()));
        main.add(followerPanel);
	    
        JPanel favoritePanel = new JPanel();
        favoritePanel.setBorder(BorderFactory.createMatteBorder(5,5,5,5, 
                ProgramStyle.PANEL_COLOR));
        favoritePanel.setBackground (ProgramStyle.BACKGROUND_COLOR);
        favoritePanel.setLayout(new GridLayout(2,1));
        favoritePanel.add(new JLabel("Favorite"));
        favoritePanel.add(new JLabel("" + user.getFavouritesCount()));
        main.add(favoritePanel);
	    
	    return main;
	}
}
