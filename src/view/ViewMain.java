/*****************************************************************
Main view contains all GUI elements and controls their implementation.

started January 26, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.User;
import utilities.*;

/*****************************************************************
The main view which contains all GUI elements.
 *****************************************************************/
public class ViewMain extends JPanel {

    /**Top panel containing all semi-permanent panels.*/
    JPanel topPanel;

    /**The posting panel.*/
    private PostingPanel postPanel;

    /**The main Panel.*/
    JPanel mainPanel;


    /*****************************************************************
    Constructor to set up all the properties of this panel.

    @param user the main user.
     *****************************************************************/
    public ViewMain(User user) {

        setPreferredSize(ProgramStyle.WINDOW_SIZE);

       setBackground(ProgramStyle.BACKGROUND_COLOR);

        mainPanel = new JPanel();
        mainPanel.setOpaque(false);

        this.setLayout(new BorderLayout());
        add(setTopPanel(user), BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        //this needs to set up the different parts of the panel

    }


    /*****************************************************************
    Sets up the three panel sections at the top of the window and
    returns it to display.

    @return JPanel
     *****************************************************************/
    private JPanel setTopPanel (User user) {
        topPanel = new JPanel();
        //set the size of the panel
        topPanel.setPreferredSize(ProgramStyle.TOP_SIZE);
        //sets the color of the background
        topPanel.setBackground(ProgramStyle.BACKGROUND_COLOR);

        //sets up the three sections using BorderLayout method
        topPanel.setLayout(new BorderLayout());
        postPanel = new PostingPanel();
        topPanel.add(postPanel, BorderLayout.WEST);
        topPanel.add(new SmallProfilePanel(user), BorderLayout.CENTER);
        topPanel.add(new ButtonPanel(), BorderLayout.EAST);

        return topPanel;
    }


    /*****************************************************************
    Display an array of trends in the main portion of the window.
    and then updates the display.

    @param trends Trend[]
     *****************************************************************/
    public void showTrends(Trend[] trends) {



        remove(mainPanel);
        mainPanel = new TrendingList(trends);

        //add new trending panel to main section
        add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    For future use.
    @param trends Trend[]
     *****************************************************************/
//  public void addTrends (Trend[] trends) {
//
//      if (mainPanel instanceof TrendingList) {
//          ((TrendingList) mainPanel).addTrendList(trends);
//      }
//     else {
//
//      remove(mainPanel);
//        mainPanel = new TrendingList(trends);
//
//        //add new trending panel to main section
//        add(mainPanel, BorderLayout.CENTER);
//      }
//        //refresh window
//        updateUI();
//  }

    /*****************************************************************
    Show the search panel in the main section.
     *****************************************************************/
    public void showSearch() {

        remove(mainPanel);
        mainPanel = new SearchPanel();

        add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    Show the results of a tweet search in the main section.
    @param stati Status[]
     *****************************************************************/
    public void showSearchResults(Status[] stati) {

        remove(mainPanel);
        mainPanel = new StatusList(stati);

        add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    Show the search results of a user search in main panel.
    @param userSearch User[]
     *****************************************************************/
    public void showUserSearch(User[] userSearch) {

        remove(mainPanel);
        mainPanel = new UserList(userSearch);

        add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    Collects the text in the tweet posting panel.

    @return String text in text field
     *****************************************************************/
    public String getPost() {
        return postPanel.getText();
    }

    /*****************************************************************
    Clears out the text that is written in the post text field.

     *****************************************************************/
    public void clearPost() {
        postPanel.clearText();
    }

    /*****************************************************************
    Displays an error message.
     *****************************************************************/
    public void showError() {

        remove(mainPanel);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.RED);
        mainPanel.setBorder(BorderFactory.createMatteBorder(20, 20, 20, 20,
               ProgramStyle.BACKGROUND_COLOR));

        JLabel errorLabel = new JLabel("Twitter is Not Responding :(");
        errorLabel.setFont(ProgramStyle.getFont(70));
        mainPanel.add(errorLabel);

        JLabel errorDesc = new JLabel("Please check your internet access.");
        errorDesc.setFont(ProgramStyle.getFont(15));
        mainPanel.add(errorDesc);

        add(mainPanel, BorderLayout.CENTER);

        //refresh window
        updateUI();
    }

    /*****************************************************************
    Allows user to refresh the profile by clicking on the Profile Panel.
    @param user User to show in Profile Panel.
     *****************************************************************/
    public void refreshProfile(User user) {
        setTopPanel(user);
    }
}