package Release2TestingGrounds;
import java.io.File;

import javax.swing.JFileChooser;

import twitter4j.*;
public class imageUpload {

	public static void main(String[] args) throws Exception  {

		Twitter twitter = new TwitterFactory().getInstance();
		
	    
	    try{
			String message = "IT WORKS!";
	    	File file = new File("DeskTopIcon.png");
	        StatusUpdate status = new StatusUpdate(message);
	        status.setMedia(file);
	        twitter.updateStatus(status);}
	    catch(TwitterException e){
	        throw e;
	    }
	}
}
