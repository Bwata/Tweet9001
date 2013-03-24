package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import utilities.ButtonType;
import utilities.Listeners;
import utilities.ProgramStyle;
import utilities.TButton;

public class DMessageSendPanel extends JPanel{

	public DMessageSendPanel() {

		setPreferredSize(ProgramStyle.TOP_PANEL_SIZE);

		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setName("void");
		topPanel.setLayout(new BorderLayout());
		
		JTextArea[] textAreas = new JTextArea[2];

		JTextArea reciever = new JTextArea();
		reciever.setName("writeArea");
		textAreas[0] = reciever;
		
		JTextArea message = new JTextArea();
		message.setName("writeArea");
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		message.setEditable(true);
		textAreas[1] = message;
		
		
		//Send Direct message button
		TButton sendButton = new TButton(ButtonType.SEND_DM);
		sendButton.setPassedObject(textAreas);
		sendButton.addActionListener(Listeners.getListener("Button"));
		
		topPanel.add(new JLabel("To: "), BorderLayout.WEST);
		topPanel.add(reciever, BorderLayout.CENTER);
		topPanel.add(sendButton, BorderLayout.EAST);
		
		add(topPanel, BorderLayout.NORTH);
		add(message, BorderLayout.CENTER);

	}


}
