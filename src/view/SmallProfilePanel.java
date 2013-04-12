/*****************************************************************
Panel to display the basic profile information of the user.
Contains the profile image, name, screen name, description and the
numbers of following, followers, and favorites.

Bigger Profile Image: 73, 73
Profile Banner: 260, 520

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import twitter4j.User;
import utilities.ProgramStyle;

/*****************************************************************
The panel in the top center which displays user information.
 *****************************************************************/
public class SmallProfilePanel extends JPanel {

    /**User object to display aspects of.*/
    private User user;



    /*****************************************************************
    Basic Constructor for the profile information panel.
    @param userPassed User whose profile is displayed.
     *****************************************************************/
    SmallProfilePanel(final User userPassed) {

    	setPreferredSize(ProgramStyle.TOP_PANEL_SIZE);

    	//check if user is valid
      if (userPassed == null) {
          JLabel errorMessage = new JLabel("Twitter is not responding :(");
          JLabel errorDirection = new JLabel("Click to refresh");
          setBackground(Color.red);
          add(errorMessage);
          add(errorDirection);
      } else {
          this.user = userPassed;
          setLayout(new BorderLayout());
          add(setTopHalf(), BorderLayout.CENTER);
          //add(setBottomHalf(), BorderLayout.SOUTH);
      }
    }

    /*****************************************************************
    Sets up the top half of the profile panel.
    @return JPanel to put into place containing the items
     *****************************************************************/
    private JPanel setTopHalf() {
        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new BorderLayout());

        //Profile image
        main.add(setProfileImage(), BorderLayout.WEST);
        //text portion
        main.add(setTopRight(), BorderLayout.CENTER);

        return main;
    }

    /*****************************************************************
    Sets up the profile image in the Profile Panel. Contains only the
    profile image.
    @return JPanel to put into place containing the items
     *****************************************************************/
    private JPanel setProfileImage() {
        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new BorderLayout());

        //gets the profile image
        BufferedImage profilePic;
        try {
            profilePic = ImageIO.read(
                    new URL(user.getBiggerProfileImageURL()));
        } catch (IOException e) {
            profilePic = null;
            //e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(profilePic));

        main.add(picLabel, BorderLayout.NORTH);

        return main;
    }

    /*****************************************************************
    Sets up the text portion of the top half of the Profile Panel.
    Contains the Name, Screen Name and description.
    @return JPanel to put into place containing the items
     *****************************************************************/
    private JPanel setTopRight() {
        JPanel main = new JPanel();

        main.setOpaque(false);
        main.setLayout(new BorderLayout());
        main.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel names = new JPanel();
        names.setLayout(new BorderLayout());
        names.setOpaque(false);

        //user name
        JLabel userName = new JLabel(user.getName());
        userName.setName("H3");
        names.add(userName, BorderLayout.CENTER);

        //screen name
        JLabel screenName = new JLabel("@" + user.getScreenName());
        names.add(screenName, BorderLayout.EAST);

        main.add(names, BorderLayout.NORTH);

        main.add(setBottomHalf(), BorderLayout.CENTER);
        return main;
    }

    /*****************************************************************
    Sets up the bottom half of the Profile Panel. Contains the numbers
    for Following, Followers, and favorites.
     @return JPanel to put into place containing the items
     *****************************************************************/
    private JPanel setBottomHalf() {
        JPanel main = new JPanel();
        main.setName("voidPanel");
        main.setLayout(new GridLayout(1, 3));

        main.add(profileNumbers("Following", user.getFriendsCount()));

        main.add(profileNumbers("Followers", user.getFollowersCount()));

        main.add(profileNumbers("Favorites", user.getFavouritesCount()));

        return main;
    }

    /*****************************************************************
    Builds the profile numbers portion of the Profile Panel.

    @return JPanel to put into place containing the items.
    @param title String section title.
    @param value int to display.
     *****************************************************************/
    private JPanel profileNumbers(String title, int value) {

        JPanel main = new JPanel();

        main.setLayout(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel(title);

        JLabel valueLabel = new JLabel("" + value);

        main.add(titleLabel);
        main.add(valueLabel);

        return main;
    }
}