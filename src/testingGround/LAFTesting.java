package testingGround;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;

import view.ButtonPanel;
import view.PostingPanel;

public class LAFTesting {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		SynthLookAndFeel laf = new SynthLookAndFeel();
		laf.load(LAFTesting.class.getResourceAsStream("laf.xml"), LAFTesting.class);
		UIManager.setLookAndFeel(laf);
		
		new LAFTesting();
	}
	public LAFTesting() throws Exception  {

		



		JFrame frame = new JFrame("Testing Panel");
//		JPanel panel = new JPanel();
//		panel.setName("Test");
//		JButton button = new JButton("Farts");
//		button.addActionListener(new ButtonListener());
//		JLabel label = new JLabel("Push for Farts!");
//
//		panel.add(label);
//		panel.add(button);


		//Frame Stuff
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.add(new PostingPanel());
		panel.add(new ButtonPanel());
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);

	}
}

class ButtonListener implements ActionListener{
	public ButtonListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Fart");

	}
}

