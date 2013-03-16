/*****************************************************************
This class is used to maintain consistency in the style of
the GUI.

@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package utilities;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import twitter4j.Trend;
import twitter4j.Status;
import twitter4j.User;

/*****************************************************************
Sets up consistency for the GUI in the project.
 *****************************************************************/
public class ProgramStyle {

    //COLORS-------------------------------------
    //colors come from: 925377
    //http://www.colorhunter.com/tag/industrial/6

    /**Main background color of the window.*/
    public static final Color BACKGROUND_COLOR = new Color(38, 35, 37);

    /**Color of the panels.*/
    public static final Color PANEL_COLOR = new Color(192, 197, 203);

    /**Color of the panels.*/
    public static final Color SELECT_COLOR = new Color(156, 159, 165);

    /**Color of the text.*/
    public static final Color TEXT_COLOR = new Color(81, 77, 81);


    //DIMENSIONS----------------------------------

    /**The Size of the programs window.*/
    //Note: the eos computers are 1280 X 1024
    public static final Dimension WINDOW_SIZE =
            new Dimension(1200, 740);

    /**The Size of the programs top panel.*/
    public static final Dimension TOP_SIZE =
            new Dimension(WINDOW_SIZE.width, 120);

    /**The Size of the programs top panel.*/
    public static final Dimension TOP_PANEL_SIZE =
            new Dimension(TOP_SIZE.width / 3, TOP_SIZE.height);
    
    /**The Height of the main panel elements.*/
    public static final int MAIN_HEIGHT = ((int) windowSize().getHeight()-170);
    
    /**The width of the main panel elements*/
    public static final int MAIN_ELEMENT_WIDTH = (int) windowSize().getWidth()/3;

    //fill in all other colors, sizes, strings, and other design
    //and style variables

    /*****************************************************************
    Access the screen size of the computer program is on.
    
    @return Dimension size of the window of the monitor.
     *****************************************************************/
    public static Dimension windowSize() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension(dim.width, dim.height - 45);
    }

    /*****************************************************************
    This sends back the font style that is used for the GUI at the
    requested size.

    @param size int font size desired.
    @return Font object used to paint strings.
     *****************************************************************/
    public static Font getFont(int size) {
        return new Font("Halvetica", Font.BOLD, size);
    }


    /*****************************************************************
    Paints the background image to use behind all the View windows.

    @param c Component object to paint the background image for.
    @param page Graphics object used to do the painting.
     *****************************************************************/
    public static void paintBackground(Component c, Graphics page) {
        ImageIcon backGround = new ImageIcon("Images/??????.png");
        backGround.paintIcon(c, page, 0, 0);
    }


    /*****************************************************************
     Determines the size of a string to be painted with the given
     string and fontsize.

     @param page Graphics object string will be painted on.
     @param title String to get size for.
     @param fontSize int font size sting will be painted at.
     @return Dimension x, and y size of the string to be painted.
     *****************************************************************/
    public static Dimension getStringSize(Graphics page, String title,
            int fontSize) {

        FontMetrics fm = page.getFontMetrics(ProgramStyle.
                getFont(fontSize));
        Dimension stringSize = new Dimension(fm.stringWidth(title),
                fm.getHeight());

        return stringSize;
    }


    /*****************************************************************
    Accesses the Trend List Renderer to paint the list of trend items.

    @return ListCellRenderer used to paint the list items.
     *****************************************************************/
    public static ListCellRenderer getTrendListRenderer() {
        ProgramStyle temp = new ProgramStyle();
        return temp.new TrendListRenderer();
    }


    /*****************************************************************
    The ListRenderer object that is used to paint items in a list.
    For this one it is the trending topics in twitter.
     *****************************************************************/
    class TrendListRenderer extends JPanel implements ListCellRenderer {

        /*****************************************************************
        One of two required methods to use to Paint list items.

        @return Returns the ListCellRendererComponent.
         *****************************************************************/
        @Override
        public Component getListCellRendererComponent(
                final JList list,              // the list
                final Object value,            // value to display
                final int index,               // cell index
                final boolean isSelected,      // is the cell selected
                final boolean cellHasFocus)    // does the cell have focus
        {
            return new JPanel() {

                public void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    //set the size of the panel
                    setPreferredSize(new Dimension(350, 50));

                    //paint the background if selected or not
                    if (isSelected) {
                        g.setColor(SELECT_COLOR);
                    }
                    else {
                        g.setColor(PANEL_COLOR);
                    }
                    g.fillRect(0, 0, getWidth(), getHeight());

                    Trend trend = ((Trend) value);

                    //set the font style and size
                    g.setFont(ProgramStyle.getFont(20));

                    //set the color of the string and draw it
                    g.setColor(ProgramStyle.TEXT_COLOR);
                    g.drawString(trend.getName(), 5, 30);

                }

                public Dimension getPreferredSize() {
                    return new Dimension(350, 50);
                }
            };
        }
    }

    /*****************************************************************
    Accesses the Status List Renderer to paint the list of Status items.

    @return ListCellRenderer used to paint the list items.
     *****************************************************************/
    public static StatusListRenderer getStatusListRenderer() {
        ProgramStyle temp = new ProgramStyle();
        return temp.new StatusListRenderer();
    }


    /*****************************************************************
    The ListRenderer object that is used to paint items in a list.
    For this one it is the Statuses in twitter.
     *****************************************************************/
    class StatusListRenderer extends JPanel implements ListCellRenderer {

        /*****************************************************************
        One of two required methods to use to Paint list items.

        @return the ListCellRendererComponent.
         *****************************************************************/
        @Override
        public Component getListCellRendererComponent(
                final JList list,              // the list
                final Object value,            // value to display
                final int index,               // cell index
                final boolean isSelected,      // is the cell selected
                final boolean cellHasFocus)    // does the cell have focus
        {
        	
        	return new StatusRenderPanel (list, value, index, isSelected, cellHasFocus);
//            return new JPanel() {
//
//                public void paintComponent(Graphics g) {
//                    super.paintComponent(g);
//
//                    //set the size of the panel
//                    setPreferredSize(new Dimension(500, 80));
//
//                    g.setColor(BACKGROUND_COLOR);
//                    g.fillRect(0, 0, getWidth(), getHeight());
//
//                    //paint the background if selected or not
//                    if (isSelected) {
//                        g.setColor(SELECT_COLOR);
//                    }
//                    else {
//                        g.setColor(PANEL_COLOR);
//                    }
//                    g.fillRect(7, 7, getWidth() - 14, getHeight() - 14);
//
//
//                    Status status = ((Status) value);
//
//                    try {
//                        //set the font style and size
//                        g.setFont(ProgramStyle.getFont(12));
//
//                        //set the color of the string and draw it
//                        g.setColor(ProgramStyle.TEXT_COLOR);
//
//
//                        //paints the tweeters profile image
//                        ImageIcon image = new ImageIcon(
//                                status.getUser().getProfileImageUrlHttps());
//                        image.paintIcon(this, g, 0, 0);
//
//
//                        //set the font style and size for the User Name
//                        g.setFont(ProgramStyle.getFont(15));
//
//                        //paints the user name
//                        g.drawString(status.getUser().getName(), 50, 22);
//
//                        Dimension nameSize = getStringSize(
//                                g, status.getUser().getName(), 15);
//
//                        //set the font style and size for the User Name
//                        g.setFont(ProgramStyle.getFont(9));
//
//                        //paints the user's twitter handle name
//                        g.drawString("@" + status.getUser().getScreenName(),
//                                (50 + nameSize.width + 5), 22);
//
//                        //paints the creation time
//                        Date date = status.getCreatedAt();
//                        g.drawString(((date.getMonth() + 1) + "/" + date.getDate()
//                                + "/" + (date.getYear() - 100)), 450, 22);
//
//                        //set the font style and size for the tweet
//                        g.setFont(ProgramStyle.getFont(12));
//
//                        //paints the post string
//                        String post = status.getText();
//                        ArrayList<String> postLines = new ArrayList<String>();
//
//                        while (post.length() > 50){
//
//                            postLines.add(post.substring(0, 51));
//                            post = post.substring(51);
//
//                        }
//                        postLines.add(post);
//
//                        for (int i = 0; i < postLines.size(); i++) {
//
//                            g.drawString(postLines.get(i), 50, (40 + 15 * i));
//
//                        }
//                    } catch (NullPointerException e) {
//                        //e.printStackTrace();
//                    }
//                }
//              /*****************************************************************
//              Sets the Size of the individual panel in the list.
//              @return Dimension size of the panel to display.
//              *****************************************************************/
//                public Dimension getPreferredSize() {
//                    return new Dimension(500, 80);
//                }
//            };
        }
    }

    /*****************************************************************
    Accesses the renderer to paint the list items.

    @return ListCellRenderer used to paint the list items.
     *****************************************************************/
    public static ListCellRenderer getUserListRenderer() {
        ProgramStyle temp = new ProgramStyle();
        return temp.new UserListRenderer();
    }

    /*****************************************************************
    The ListRenderer object that is used to paint items in a list.
     *****************************************************************/
    class UserListRenderer extends JPanel implements ListCellRenderer {

        /*****************************************************************
        One of two required methods to use to Paint list items.
         *****************************************************************/
        @Override
        public Component getListCellRendererComponent(
                final JList list,              // the list
                final Object value,            // value to display
                final int index,               // cell index
                final boolean isSelected,      // is the cell selected
                final boolean cellHasFocus)    // does the cell have focus
        {
        	return new UserRenderPanel(list, value, index, isSelected, cellHasFocus);
        	
//            return new JPanel() {
//
//                public void paintComponent(Graphics g) {
//                    super.paintComponent(g);
//
//                    /* Items to show from user
//                     *
//                     * Image           .getMiniProfileImageURL()
//                     * Name            .getName();
//                     * handle          .getScreenName()
//                     * description     .getDescription()
//                     * location        .getLocation()
//                     * */
//
//                    //set the size of the panel
//                    setPreferredSize(new Dimension(500, 80));
//
//                    g.setColor(BACKGROUND_COLOR);
//                    g.fillRect(0, 0, getWidth(), getHeight());
//
//                    //paint the background if selected or not
//                    if (isSelected) {
//                        g.setColor(SELECT_COLOR);
//                    }
//                    else {
//                        g.setColor(PANEL_COLOR);
//                    }
//                    g.fillRect(7, 7, getWidth() - 14, getHeight() - 14);
//
//                    try {
//                        User user = ((User) value);
//
//                        //set the font style and size
//                        g.setFont(ProgramStyle.getFont(12));
//
//                        //set the color of the string and draw it
//                        g.setColor(ProgramStyle.TEXT_COLOR);
//
//                        //paints the tweeters profile image
//                        ImageIcon image = new ImageIcon
//                                (user.getProfileImageUrlHttps());
//                        image.paintIcon(this, g, 0, 0);
//
//                        //set the font style and size for the User Name
//                        g.setFont(ProgramStyle.getFont(15));
//
//                        //paints the user name
//                        g.drawString(user.getName(), 50, 22);
//
//                        Dimension nameSize = getStringSize(
//                                g, user.getName(), 15);
//
//                        //set the font style and size for the User Name
//                        g.setFont(ProgramStyle.getFont(9));
//
//                        //paints the user's twitter handle name
//                        g.drawString("@" + user.getScreenName(),
//                                (50 + nameSize.width + 5), 22);
//
//                        //paints the creation time
//                        String local = user.getLocation();
//                        if (local != null) {
//                            g.drawString(user.getLocation(), 425, 22);
//                        }
//
//                        //set the font style and size for the tweet
//                        g.setFont(ProgramStyle.getFont(12));
//
//                        //paints the post string
//                        String post = user.getDescription();
//                        ArrayList<String> postLines = new ArrayList<String>();
//
//                        while (post.length() > 50) {
//
//                            postLines.add(post.substring(0, 51));
//                            post = post.substring(51);
//
//                        }
//                        postLines.add(post);
//
//                        for (int i = 0; i < postLines.size(); i++) {
//
//                            g.drawString(postLines.get(i), 50, (40 + 15 * i));
//
//                        }
//                    } catch (NullPointerException e) {
//                        //e.printStackTrace();
//                    }
//                }
//
//                public Dimension getPreferredSize() {
//                    return new Dimension(500, 80);
//                }
//            };
        }
    }
}