package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utilities.ButtonType;
import utilities.Listeners;
import utilities.TButton;




public class ViewLogin extends JPanel{
	
	private JTextArea[] textFields;
	
//	private JTextArea username;
//
//	private JTextArea password;
//	
//	private JTextArea code;
	
	private JPanel loginPanel;
	
	public ViewLogin () {
		
		setName("voidPanel");
		
		JPanel logo = new JPanel();
		logo.setName("borderPanel");
		
		logo.setPreferredSize(new Dimension(300, 300));
		
		setLayout(new BorderLayout());
		
		add(logo, BorderLayout.CENTER);
		
		textFields = new JTextArea[3];
		
		JTextArea username = new JTextArea();
		JTextArea password = new JTextArea();
		JTextArea code = new JTextArea();
		
		textFields[0] = username;
		textFields[1] = password;
		textFields[2] = code;
		
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
	
	private void setLogin() {
		
		JPanel loginPanel = new JPanel();
		loginPanel.setName("borderPanel");
		loginPanel.setLayout(new GridLayout(0, 1));
		
		TButton loginButton = new TButton(ButtonType.LOGIN);
		loginButton.addActionListener(Listeners.getListener("Login"));
		
		loginPanel.add(new JLabel("Username: "));
		loginPanel.add(textFields[0]);
		loginPanel.add(new JLabel("Password"));
		loginPanel.add(textFields[1]);
		
		loginPanel.add(loginButton);
	}
	
	//public void
	
	
	
}
