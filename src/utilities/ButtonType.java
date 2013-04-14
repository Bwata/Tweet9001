/*****************************************************************
An Enum that lists all the buttons the program uses and contains
the string to be used for the text of the button.

started March 3, 2013
@author Thomas Verstraete
@version Winter 2013
 *****************************************************************/
package utilities;

public enum ButtonType {
	
	//LOGIN ITEMS
	LOGIN ("Login"),
	
	REGISTER ("Register"),
	
	
	//MAIN BUTTONS
	
	GROUPS ("Groups"),
	
	/**Show the basic home timeline.*/
    HOMETIMELINE ("Timeline"),

    /**show the post a tweet panel.*/
    POST_TWEET ("Post"),

    /**Add image to post.*/
    IMAGE ("Image"),

    /**show the searching panel.*/
    SEARCH ("Search"),

    /**show the trending panel.*/
    TRENDING ("Trending"),

    /**show the profile panel.*/
    VIEW_PROFILE ("Profile"),

    /**Quits the program.*/
    QUIT ("Quit"),

    EDIT_PROFILE ("Edit Profile"),

    EDIT_IMAGE ("Edit Image"),

    SAVE_PROFILE ("Save"),

    /**Search for a tweet button.*/
    SEARCH_TWEET("Search Tweet"),

    /**Search for a user button.*/
    SEARCH_USER("Search User"),

    


    /**show the World Trends.*/
    WORLD_TRENDING ("World Trends"),

    /**show the local Trends.*/
    LOCAL_TRENDS ("Local Trends"),

    /**show the DM panel*/
    DIRECT_MESSAGE ("Direct Message"),

    /**Sends the direct message*/
    SEND_DM ("Send"),

    /***/
    BLANK ("");

    private String title;

    private ButtonType(String title) {
    	this.title = title;
    }

	public String getTitle() {
		return title;
	}
}
