package utilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import twitter4j.User;



public class UserRenderPanel extends JPanel{
	
	public UserRenderPanel (
            final JList list,              // the list
            final Object value,            // value to display
            final int index,               // cell index
            final boolean isSelected,      // is the cell selected
            final boolean cellHasFocus)    // does the cell have focus 
	{
		
		
		/* Items to show from user
        *
        * Image           .getMiniProfileImageURL()
        * Name            .getName();
        * handle          .getScreenName()
        * description     .getDescription()
        * location        .getLocation()
        * */
		
		 try {
           User user = ((User) value);
           
           
           
           this.setLayout(new BorderLayout());
           this.setPreferredSize(new Dimension(500, 100));
           
         //checks if selected
			if (isSelected) {
				setName("selectedPanel");
			} else {
				//nothing?
			}
           
           JLabel image = new JLabel(new ImageIcon(user.getProfileImageUrlHttps()));
           add(image, BorderLayout.WEST);
           
           JPanel textPanel = new JPanel();
           textPanel.setName("voidPanel");
           textPanel.setLayout(new BorderLayout());
           
           JPanel userInfo = new JPanel();
           userInfo.setName("voidPanel");
           userInfo.setLayout(new BoxLayout(userInfo, BoxLayout.LINE_AXIS));
           JLabel userName = new JLabel(user.getName());
           userName.setName("H5");
           userInfo.add(userName);
           
           JLabel userScreenName = new JLabel("@" + user.getScreenName());
           userScreenName.setName("H6");
           userInfo.add(userScreenName);
           
           userInfo.add(Box.createHorizontalGlue());
           
           JLabel timeStamp = new JLabel(user.getLocation());
           timeStamp.setName("H6");
           userInfo.add(timeStamp);
           
           textPanel.add(userInfo, BorderLayout.NORTH);
           
           
           JTextArea postArea = new JTextArea(user.getDescription());
           postArea.setName("voidPanel");

           postArea.setLineWrap(true);
           postArea.setWrapStyleWord(true);
           postArea.setEditable(false);
           
           textPanel.add(postArea, BorderLayout.CENTER);
           
           add(textPanel, BorderLayout.CENTER);
           
		 } catch (NullPointerException e) {
           //e.printStackTrace();
       }

//       //set the size of the panel
//       setPreferredSize(new Dimension(500, 80));
//
//       g.setColor(BACKGROUND_COLOR);
//       g.fillRect(0, 0, getWidth(), getHeight());
//
//       //paint the background if selected or not
//       if (isSelected) {
//           g.setColor(SELECT_COLOR);
//       }
//       else {
//           g.setColor(PANEL_COLOR);
//       }
//       g.fillRect(7, 7, getWidth() - 14, getHeight() - 14);
//
//       try {
//           User user = ((User) value);
//
//           //set the font style and size
//           g.setFont(ProgramStyle.getFont(12));
//
//           //set the color of the string and draw it
//           g.setColor(ProgramStyle.TEXT_COLOR);
//
//           //paints the tweeters profile image
//           ImageIcon image = new ImageIcon
//                   (user.getProfileImageUrlHttps());
//           image.paintIcon(this, g, 0, 0);
//
//           //set the font style and size for the User Name
//           g.setFont(ProgramStyle.getFont(15));
//
//           //paints the user name
//           g.drawString(user.getName(), 50, 22);
//
//           Dimension nameSize = getStringSize(
//                   g, user.getName(), 15);
//
//           //set the font style and size for the User Name
//           g.setFont(ProgramStyle.getFont(9));
//
//           //paints the user's twitter handle name
//           g.drawString("@" + user.getScreenName(),
//                   (50 + nameSize.width + 5), 22);
//
//           //paints the creation time
//           String local = user.getLocation();
//           if (local != null) {
//               g.drawString(user.getLocation(), 425, 22);
//           }
//
//           //set the font style and size for the tweet
//           g.setFont(ProgramStyle.getFont(12));
//
//           //paints the post string
//           String post = user.getDescription();
//           ArrayList<String> postLines = new ArrayList<String>();
//
//           while (post.length() > 50) {
//
//               postLines.add(post.substring(0, 51));
//               post = post.substring(51);
//
//           }
//           postLines.add(post);
//
//           for (int i = 0; i < postLines.size(); i++) {
//
//               g.drawString(postLines.get(i), 50, (40 + 15 * i));
//
//           }
//       } catch (NullPointerException e) {
//           //e.printStackTrace();
//       }
		
		
	}

}
