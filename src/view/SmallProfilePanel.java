/*****************************************************************
Panel to display the basic profile information of the user.
Contains the profile image, name, screen name, description and the
numbers of following, followers, and favorites.

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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
        //Sets panel properties
        setPreferredSize(ProgramStyle.POST_PANEL_SIZE);
        setBackground(ProgramStyle.PANEL_COLOR);
        //order of matteborder method (top, left, bottom, right, color)
        setBorder(BorderFactory.createMatteBorder(11, 5, 0, 5,
                ProgramStyle.BACKGROUND_COLOR));
        setForeground(Color.red);

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
            add(setBottomHalf(), BorderLayout.SOUTH);
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
        userName.setFont(ProgramStyle.getFont(20));
        userName.setForeground(ProgramStyle.TEXT_COLOR);
        names.add(userName, BorderLayout.CENTER);

        //screen name
        JLabel screenName = new JLabel("@" + user.getScreenName());
        screenName.setFont(ProgramStyle.getFont(12));
        screenName.setForeground(ProgramStyle.TEXT_COLOR);
        names.add(screenName, BorderLayout.EAST);

        main.add(names, BorderLayout.NORTH);

        //description
        JTextArea description = new JTextArea(user.getDescription());
        //description portion attributes.
        description.setPreferredSize(description.getSize());
        description.setFont(ProgramStyle.getFont(12));
        description.setForeground(ProgramStyle.TEXT_COLOR);
        description.setOpaque(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);

        main.add(description, BorderLayout.CENTER);

        return main;
    }

    /*****************************************************************
    Sets up the bottom half of the Profile Panel. Contains the numbers
    for Following, Followers, and favorites.
     @return JPanel to put into place containing the items
     *****************************************************************/
    private JPanel setBottomHalf() {
        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new GridLayout(1, 3));
        main.setBorder(new EmptyBorder(5, 5, 5, 5));

        main.add(profileNumbers("Following", user.getFriendsCount()));

        main.add(profileNumbers("Followers", user.getFollowersCount()));

        main.add(profileNumbers("Favorite", user.getFavouritesCount()));

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

        main.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
                ProgramStyle.BACKGROUND_COLOR));
        main.setBackground(ProgramStyle.PANEL_COLOR);
        main.setLayout(new GridLayout(2, 1));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(ProgramStyle.TEXT_COLOR);

        JLabel valueLabel = new JLabel("" + value);
        valueLabel.setForeground(ProgramStyle.TEXT_COLOR);

        main.add(titleLabel);
        main.add(valueLabel);

        return main;
    }
}