package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import rome2rio.Rome2Rio;

/* PasswordDemo.java requires no other files. */

@SuppressWarnings("serial")
public class PasswordPanel extends JPanel implements ActionListener {

	private Rome2Rio r2r;
	private static String OK = "ok";
	private static String HELP = "help";

	private JFrame controllingFrame; // needed for dialogs
	public JFrame frame;
	public TopBar menu;
	private JPasswordField passwordField;

	public PasswordPanel(JFrame f, Rome2Rio r2r, TopBar menu) {

		this.r2r = r2r;
		this.menu = menu;

		// Use the default FlowLayout.
		controllingFrame = f;

		// Create everything.
		passwordField = new JPasswordField(10);
		passwordField.setActionCommand(OK);
		passwordField.addActionListener(this);

		JLabel label = new JLabel("Enter the password: ");
		label.setLabelFor(passwordField);

		JComponent buttonPane = createButtonPanel();

		// Lay out everything.
		JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		textPane.add(label);
		textPane.add(passwordField);

		add(textPane);
		add(buttonPane);
	}

	protected JComponent createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(0, 1));
		JButton okButton = new JButton("OK");
		JButton helpButton = new JButton("Help");

		okButton.setActionCommand(OK);
		helpButton.setActionCommand(HELP);
		okButton.addActionListener(this);
		helpButton.addActionListener(this);

		p.add(okButton);
		p.add(helpButton);

		return p;
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (OK.equals(cmd)) { // Process the password.
			char[] input = passwordField.getPassword();
			if (isPasswordCorrect(input) && r2r.changeToAdmin(new String(input))) {
				menu.logoutAsAdministrator(r2r);
				JOptionPane.showMessageDialog(controllingFrame, "Success! You are now logged in as administrator.");
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			} else {
				JOptionPane.showMessageDialog(controllingFrame, "Invalid password. Try again.", "Error Message",
						JOptionPane.ERROR_MESSAGE);
			}

			// Zero out the possible password, for security.
			Arrays.fill(input, '0');

			passwordField.selectAll();
			resetFocus();
		} else { // The user has asked for help.
			JOptionPane.showMessageDialog(controllingFrame,
					"You can get the password by searching this example's\n"
							+ "source code for the string \"correctPassword\".\n"
							+ "Or look at the section How to Use Password Fields in\n"
							+ "the components section of The Java Tutorial.");
		}
	}

	/**
	 * Checks the passed-in array against the correct password. After this method
	 * returns, you should invoke eraseArray on the passed-in array.
	 */
	private boolean isPasswordCorrect(char[] input) {
		boolean isCorrect = true;
		String correctPassword = r2r.adminCode;
		String passInput = new String(input);

		if (input.length != correctPassword.length()) {
			isCorrect = false;
		} else {
			isCorrect = passInput.equals(correctPassword);
		}
		return isCorrect;
	}

	// Must be called from the event dispatch thread.
	protected void resetFocus() {
		passwordField.requestFocusInWindow();
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	public void createAndShowGUI() {

		// Create and set up the window.
		frame = new JFrame("Administrator Password");

		// Create and set up the content pane.
		final PasswordPanel newContentPane = this;
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