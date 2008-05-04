package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SettingsPane extends JFrame implements ErrorObserver {

	private static final long serialVersionUID = 5094054114823908701L;

	public SettingsPane()
	{
		super("New Doodle");
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 6;
		c.ipady = 6;

		Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		
		c.weightx = 1.0;
		JTextField userNameField = new JTextField("Player One", 20);
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setBorder(emptyBorder);
		userNameLabel.setLabelFor(userNameField);
		addIntoGrid(userNameLabel, gridbag, c);		
		c.weightx = 3.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		addIntoGrid(userNameField, gridbag, c);

		c.weightx = 1.0;
		JTextField hostNameField = new JTextField("localhost", 20);
		JLabel hostNameLabel = new JLabel("Hostname");
		hostNameLabel.setBorder(emptyBorder);
		c.gridwidth = GridBagConstraints.RELATIVE;
		hostNameLabel.setLabelFor(hostNameField);
		addIntoGrid(hostNameLabel, gridbag, c);		
		c.weightx = 3.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		addIntoGrid(hostNameField, gridbag, c);

		JTextField portField = new JTextField("9000", 5);
		JLabel portLabel = new JLabel("Port (1024-65535)");
		portLabel.setBorder(emptyBorder);
		c.weightx = 1.0;
		c.gridwidth = GridBagConstraints.RELATIVE;
		portLabel.setLabelFor(portField);
		addIntoGrid(portLabel, gridbag, c);
		c.weightx = 3.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		addIntoGrid(portField, gridbag, c);
		
		JTextField widthField = new JTextField("400", 4);
		JLabel widthLabel = new JLabel("Width");
		widthLabel.setBorder(emptyBorder);
		widthLabel.setLabelFor(widthField);
		JTextField heightField = new JTextField("400", 4);
		JLabel heightLabel = new JLabel("Height");
		heightLabel.setLabelFor(heightLabel);
		heightLabel.setBorder(emptyBorder);
		JTextField clientField = new JTextField("4", 4);
		JLabel clientLabel = new JLabel("Max clients");
		clientLabel.setBorder(emptyBorder);
		clientLabel.setLabelFor(clientField);
		c.weightx = 1.0;
		c.gridwidth = GridBagConstraints.RELATIVE;
		addIntoGrid(clientLabel, gridbag, c);
		c.gridwidth = GridBagConstraints.REMAINDER;
		addIntoGrid(clientField, gridbag, c);
		c.gridwidth = GridBagConstraints.RELATIVE;
		addIntoGrid(widthLabel, gridbag, c);
		c.gridwidth = GridBagConstraints.REMAINDER;
		addIntoGrid(widthField, gridbag, c);
		c.gridwidth = GridBagConstraints.RELATIVE;
		addIntoGrid(heightLabel, gridbag, c);
		c.gridwidth = GridBagConstraints.REMAINDER;
		addIntoGrid(heightField, gridbag, c);
		
		c.weightx = 0.0;
		JButton startButton = new JButton(
				new StartServerAction(this, portField, widthField, heightField, clientField));
		startButton.setText("Start Doodle Session");
		addIntoGrid(startButton, gridbag, c);
		
		JButton joinButton = new JButton(
				new JoinServerAction(this, hostNameField, portField));
		joinButton.setText("Join Doodle Session");
		addIntoGrid(joinButton, gridbag, c);

		errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.red);
		errorLabel.setBorder(emptyBorder);
		addIntoGrid(errorLabel, gridbag, c);

		setSize(300,300);
		pack();
		setSize(getPreferredSize());
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private JLabel errorLabel;
		
	private void addIntoGrid(Component widget, GridBagLayout gridbag, GridBagConstraints c) {
		gridbag.setConstraints(widget, c);
		add(widget);
	}

	public void sendError(String errorText) {
		errorLabel.setText(errorText);
		setVisible(true);
	}	
	
}
