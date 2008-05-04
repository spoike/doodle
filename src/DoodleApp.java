import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ui.SettingsPane;


/**
 * Doodle application class. The main class that creates and starts up Doodle.
 * 
 * @author Mikael Piotrowski
 *
 */
public class DoodleApp {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame application = new SettingsPane();
				application.setVisible(true);
			}
		});
		

	}

}
