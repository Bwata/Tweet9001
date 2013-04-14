package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;
import utilities.ButtonType;
import utilities.Listeners;
import utilities.TButton;
import view.ViewLogin;

public class LoginController {
	
	AccessToken accessToken;
	
	private Twitter twitter;
	
	ConfigurationBuilder cb;
	TwitterFactory tf;
	
	ViewLogin loginView;

	public LoginController () {
		
		JFrame frame = new JFrame();
		
		Listeners.addListener(new loginButtonListener());

		loginView = new ViewLogin();
		
		frame.getContentPane().add(((JPanel) loginView));

		//show the background
		frame.pack();
		frame.setVisible(true);
		
	}
	
	/**
	 * @throws TwitterException 
	 * @throws MalformedURLException ***************************************************************


	 *****************************************************************/
	private void login(String username, String password){	
		
		boolean logedIn = mainModel.login(username, password);
		
		if (logedIn) {
			setUpMain();
		} else {
			URL url = null;
			try {
				url = mainModel.getRequestURL();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loginView.setPinRequest(url);
		}
		//String [] inputs = loginView.getInputs();

	}
	
	/*****************************************************************


	 *****************************************************************/
	private void registerLogin (JTextArea[] inputs) {		

		boolean logedIn = false;
		String[] inputStrings = new String[inputs.length];
		
		for (int i = 0; i < inputs.length; i++) {
			inputStrings[i] = inputs[i].getText();
		}
		
		try {
			logedIn = mainModel.setOAuthCode(inputStrings);
			
			if (logedIn) {
				
				
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (logedIn) {
			setUpMain();
			
		} else {
			loginView.showError();
		}
		
	}
	
	/*****************************************************************

	 *****************************************************************/
	public boolean authenticate(String[] inputs) 
			throws IllegalStateException, TwitterException, IOException {

		 
		

		User user; 

		cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("UP8vf0xlwUkPHvikkEBXQ")
		.setOAuthConsumerSecret("62H0idR3HypsRitEUQI3j2ugqTINXybjeBSLr4QH78");

		tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();

		//String username = JOptionPane.showInputDialog(frame, "User Name");

		File file = new File("loginInformation.txt");

		Scanner scanner;
		try{
			scanner = new Scanner(file);
		}
		catch(java.io.FileNotFoundException e){
			file = new File("loginInformation.txt");
			file.createNewFile();
			scanner = new Scanner(file);
		}
		while (scanner.hasNextLine()) {

			String line = scanner.nextLine();

			String[] s = line.split(", ");
			//[0] == username
			//[1] == password
			//[2] == token
			//[3] == secret

			if (s[0].equals(inputs[0]) && s[1].equals(inputs[1])) {
				System.out.println("username is recognized");
				accessToken = new AccessToken(s[2], s[3]);
				twitter.setOAuthAccessToken(accessToken);
				break;
			}

		}
		//favorites.loadFavorites(username);			
		if (accessToken != null) {
			scanner.close();
			return true;
		} else {
			scanner.close();
			return false;
		}
	}
	
	public URL getRequestURL() throws TwitterException, MalformedURLException {
		RequestToken requestToken = twitter.getOAuthRequestToken();
		return new URL(requestToken.getAuthenticationURL());
		
	}
	
	

	public boolean setOAuthCode (String[] inputs) throws TwitterException, IOException   {
		
		//RequestToken requestToken;
		User user;
		try {
			try {
				// get request token.
				// this will throw IllegalStateException if access token is already available
				//requestToken = twitter.getOAuthRequestToken();

				accessToken = null;

				RequestToken requestToken = twitter.getOAuthRequestToken();

				while (null == accessToken) {

					//URL url = new URL(requestToken.getAuthenticationURL());

					//openWebpage(url);

					//String pin = ""; // = JOptionPane.showInputDialog(frame, 
					//"Enter the PIN(if available) and hit ok.");

					try {
						if (inputs[2].length() > 0) {
							accessToken = twitter
									.getOAuthAccessToken(requestToken, inputs[2]);
						} else {
							accessToken = twitter
									.getOAuthAccessToken(requestToken);
						}
					} catch (TwitterException te) {
						if (401 == te.getStatusCode()) {

							//								JOptionPane.showMessageDialog(frame,
							//										"Unable to get the access token.",
							//										"Inane error",
							//										JOptionPane.ERROR_MESSAGE);
							te.printStackTrace();

							// System.out.println("Unable to get the access token.");
						} else {
							te.printStackTrace();
						}
					}
				}

			} catch (IllegalStateException ie) {
				// access token is already available, or consumer key/secret is not set.
				if (!twitter.getAuthorization().isEnabled()) {
					System.out.println("OAuth consumer key/secret is not set.");
					System.exit(-1);
				}
			}

		} catch (NullPointerException np) {
			System.out.println("Exiting");
			System.exit(0);
		}

		//user = twitter.showUser(twitter.getId());

		PrintWriter out = new PrintWriter(
				new FileWriter("./loginInformation.txt", true));
		String saveFile = "";

		//user = twitter.showUser(twitter.getId());

		
		saveFile += inputs[0] + ", " + inputs[1] + ", " 
				+ accessToken.getToken() + ", " + accessToken.getTokenSecret();
		//scanner.close();
		out.println();
		out.print(saveFile);
		out.close();
	
	return true;
}
	
	
	
	/*****************************************************************
    Listener for the main buttons on the main panel.
	 *****************************************************************/
	public class loginButtonListener implements ActionListener {

		/**The String determining the type of listener wanted.*/
		//string has to match the enum
		private String listenType = "Login";

		/***************************************************************
        The action performed method, like you do.

        @param event ActionEvent containing all the info you will need
		 **************************************************************/
		@Override
		public void actionPerformed(ActionEvent event) {

			TButton button = (TButton) event.getSource();
			ButtonType type = ((TButton) event.getSource())
					.getButtonType();

			//Switch to Determine Pressed Button
			switch (type) {

			case LOGIN:
				
				JTextArea[] areas = ((JTextArea[])
						(button.getPassedObject()));
				login(areas[0].getText(), areas[1].getText());
				
				break;

			case REGISTER:

				JTextArea[] areas1 = ((JTextArea[])
						(button.getPassedObject()));
				
				registerLogin(areas1);
				
				break;

			default:
				break;
			}
		}

		/***************************************************************
        This gets the determining String.
        @return String of the listener type.
		 **************************************************************/
		@Override
		public String toString() {
			return listenType;
		}
	}

	
}
