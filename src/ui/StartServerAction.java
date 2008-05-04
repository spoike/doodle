package ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

class StartServerAction extends AbstractAction
{
	private static final long serialVersionUID = 1785179074849552469L;

	private Component parent;
	
	private JTextComponent portField;

	private JTextComponent widthField;

	private JTextComponent heightField;

	private JTextField clientField;
	
	public StartServerAction(SettingsPane parent, JTextField portField, JTextField widthField, JTextField heightField, JTextField clientField) {
		this.portField = portField;
		this.parent = parent;
		this.heightField = heightField;
		this.widthField = widthField;
		this.clientField = clientField;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			parent.setVisible(false);
			new DoodleSender(
					Integer.parseInt(portField.getText()),
					Integer.parseInt(widthField.getText()),
					Integer.parseInt(heightField.getText()),
					Integer.parseInt(clientField.getText())
					);
		} catch (IOException ex) {
			ex.printStackTrace();
			parent.setVisible(true);
		}
	}	
}
