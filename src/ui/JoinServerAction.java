package ui;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.swing.AbstractAction;
import javax.swing.text.JTextComponent;

class JoinServerAction extends AbstractAction
{
	private static final long serialVersionUID = 2213023343666797498L;

	private ErrorObserver parent;
	
	private JTextComponent hostNameField;

	private JTextComponent portField;
	
	public JoinServerAction(ErrorObserver parent, JTextComponent hostNameField, JTextComponent portField) {
		this.hostNameField = hostNameField;
		this.portField = portField;
		this.parent = parent;
	}

	public void actionPerformed(ActionEvent e) {
		DoodleClient client;
		try {
			parent.hide();
			int port = Integer.parseInt(portField.getText());
			if (port > 0 && port < 65536) {
				client = new DoodleClient(hostNameField.getText(), port);
				client.start();
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			parent.sendError("Invalid port number.");
		} catch (UnknownHostException ex) {
			parent.sendError("Could not find the server.");
		} catch (ConnectException ex) {
			parent.sendError("Could connect to the server.");
		} catch (IOException ex) {
			ex.printStackTrace();
			parent.sendError("Could not open network connection.");
		}
	}
}

