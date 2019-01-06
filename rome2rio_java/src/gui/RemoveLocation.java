package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import rome2rio.Rome2Rio;

/* PasswordDemo.java requires no other files. */

@SuppressWarnings("serial")
public class RemoveLocation extends JPanel implements ActionListener {

	private Rome2Rio r2r;
	private static String OK = "ok";
	private static String HELP = "help";

	private JFrame controllingFrame; // needed for dialogs
	public JFrame frame;
	private JFormattedTextField locationField;

	public RemoveLocation(JFrame f, Rome2Rio r2r) {

		this.r2r = r2r;

		// Use the default FlowLayout.
		controllingFrame = f;

		// Create everything.
		locationField = new JFormattedTextField();
		locationField.setColumns(20);

		JLabel label = new JLabel("Enter the location's name to remove: ");
		label.setLabelFor(locationField);

		JComponent buttonPane = createButtonPanel();
		setLayout(new MigLayout("", "[499px]", "[36px][58px]"));

		// Lay out everything.
		JPanel textPane = new JPanel();
		textPane.setLayout(new GridLayout(0, 1, 0, 0));

		textPane.add(label);
		textPane.add(locationField);

		add(textPane, "flowx,cell 0 0,alignx left,aligny top");
		add(buttonPane, "cell 0 1,alignx center,aligny top");
		JButton okButton = new JButton("OK");
		add(okButton, "cell 0 0");

		okButton.setActionCommand(OK);
		okButton.addActionListener(this);
		JButton helpButton = new JButton("Help");
		add(helpButton, "cell 0 0");
		helpButton.setActionCommand(HELP);
		helpButton.addActionListener(this);
	}

	protected JComponent createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));

		return p;
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (OK.equals(cmd)) { // Process the password.
			String inputLoc = locationField.getText();

			if (r2r.removeLocation(inputLoc)) {
				JOptionPane.showMessageDialog(controllingFrame,
						"Success! The Location " + inputLoc + " was removed from the Rome2Rio System.");
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			} else {
				JOptionPane.showMessageDialog(controllingFrame,
						"Invalid City. Try to change the Name or the coordinates.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}

			locationField.selectAll();
			resetFocus();
		} else { // The user has asked for help.
			JOptionPane.showMessageDialog(controllingFrame,
					"You can get the password by searching this example's\n"
							+ "source code for the string \"correctPassword\".\n"
							+ "Or look at the section How to Use Password Fields in\n"
							+ "the components section of The Java Tutorial.");
		}
	}

	// Must be called from the event dispatch thread.
	protected void resetFocus() {
		locationField.requestFocusInWindow();
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	public void createAndShowGUI() {

		// Create and set up the window.
		frame = new JFrame("Add Location To System");

		// Create and set up the content pane.
		final RemoveLocation newContentPane = this;
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Make sure the focus goes to the right component
		// whenever the frame is initially given the focus.
		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				newContentPane.resetFocus();
			}
		});

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}