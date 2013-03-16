package utilities;

public enum ButtonType {

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
    SEND_DM ("Send")
    
    
    ;
    
    
    
    
    
    
    
    private String title;
    
    private ButtonType(String title) {
    	this.title = title;
    }
	
	public String getTitle() {
		return title;
	}
}
