package view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utilities.ButtonType;
import utilities.Listeners;
import utilities.TButton;



/*****************************************************************
shows the login view.
 *****************************************************************/
public class ViewLogin extends JPanel{
	/** text field area*/
	private JTextArea[] textFields;
	
//	private JTextArea username;
//
//	private JTextArea password;
//	
//	private JTextArea code;
	/** creates loginPanel. */
	private JPanel loginPanel;
	/** creates pin panel. */
	private JPanel pinPanel;
	/**create view login.*/
	public ViewLogin() {
		
		setName("voidPanel");
		
		JPanel logo = new JPanel();
		logo.setName("logoPanel");
		logo.setPreferredSize(new Dimension(300, 300));
//		JLabel logoImage = new JLabel(new ImageIcon("Tweet9001Logo.png"));
//		logo.add(logoImage);
		
		setLayout(new BorderLayout());
		
		add(logo, BorderLayout.CENTER);
		
		textFields = new JTextArea[3];
		
		JTextArea username = new JTextArea();
		JTextArea password = new JTextArea();
		JTextArea pin = new JTextArea();
		
		textFields[0] = username;
		textFields[1] = password;
		textFields[2] = pin;
		
		//Set text area attributes.
		for (int i = 0; i < textFields.length; i++) {
			textFields[i].setName("writeArea");
			textFields[i].setLineWrap(true);
			textFields[i].setWrapStyleWord(true);
			textFields[i].setEditable(true);
		}
		
		setLogin();
		add(loginPanel, BorderLayout.SOUTH);
		
		
	}
	/**sets login*/
	private void setLogin() {
		
		loginPanel = new JPanel();
		loginPanel.setName("borderPanel");
		loginPanel.setLayout(new GridLayout(0, 1));
		
		TButton loginButton = new TButton(ButtonType.LOGIN);
		loginButton.addActionListener(Listeners.getListener("Login"));
		loginButton.setPassedObject(textFields);
		
		loginPanel.add(new JLabel("Username: "));
		loginPanel.add(textFields[0]);
		loginPanel.add(new JLabel("Password"));
		loginPanel.add(textFields[1]);
		
		loginPanel.add(loginButton);
	}
	/**sets pin request. */
	public void setPinRequest(URL url) {
		
		
		//openWebpage(url);

		this.remove(loginPanel);
		
		pinPanel = new JPanel();
		pinPanel.setName("borderPanel");
		pinPanel.setLayout(new GridLayout(0, 1));
		
		TButton loginButton = new TButton(ButtonType.REGISTER);
		loginButton.addActionListener(Listeners.getListener("Login"));
		loginButton.setPassedObject(textFields);
		
		pinPanel.add(new JLabel("Copy pin here: "));
		pinPanel.add(textFields[2]);
		
		
		pinPanel.add(loginButton);
		add(pinPanel, BorderLayout.SOUTH);
		
		updateUI();
		
		openWebpage(url);
	}

//	public String getCode(URL url) {
//		openWebpage(url);
//
//		this.remove(loginPanel);
//		
//		setPinRequest();
//		add(pinPanel, BorderLayout.SOUTH);
//		
//		
//		return null;
//	}
	/**opens the webpage */
	public static void openWebpage(final URI uri) {
		Desktop desktop = null;
		if (Desktop.isDesktopSupported()) {
			desktop = Desktop.getDesktop();
		}
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/** open webpage. */
	public static void openWebpage(final URL url) {
		try {
			openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	/** shows errors. */
	public void showError() {
		// TODO Auto-generated method stub
		System.out.println("log in failed.");
	}

//	public String[] getInputs() {
//		// TODO Auto-generated method stub
//		
//		
//		
//		return
//		
//		return null;
//	}
	
	//public void
	
	
	
}
