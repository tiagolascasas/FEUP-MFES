package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import net.miginfocom.swing.MigLayout;
import rome2rio.Rome2Rio;

/* PasswordDemo.java requires no other files. */

@SuppressWarnings("serial")
public class AddLocation extends JPanel implements ActionListener {

	private Rome2Rio r2r;
	private static String OK = "ok";
	private static String HELP = "help";

	private JFrame controllingFrame; // needed for dialogs
	public JFrame frame;
	private JFormattedTextField locationField;
	private JFormattedTextField countryField;
	private JFormattedTextField coordNS;
	private JFormattedTextField coordEW;

	public AddLocation(JFrame f, Rome2Rio r2r) {

		this.r2r = r2r;

		// Use the default FlowLayout.
		controllingFrame = f;

		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); // optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); // this is the key!!
		numberFormatter.setMinimum(0l); // Optional

		JLabel label = new JLabel("Enter the new location's name, country, coordinates NS and EW: ");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		setLayout(new MigLayout("", "[397px][397px]", "[311px][][]"));

		// Lay out everything.
		JPanel textPane = new JPanel();
		textPane.setLayout(new MigLayout("", "[527px][5px][167px]", "[16px][60px][][][][]"));

		textPane.add(label, "cell 0 0 3 1,alignx right,aligny top");

		add(textPane, "cell 0 0 2 1,grow");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Name and Country",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textPane.add(panel, "cell 0 2,alignx left,aligny top");

		// Create everything.
		locationField = new JFormattedTextField();
		panel.add(locationField);
		locationField.setColumns(20);
		label.setLabelFor(locationField);

		countryField = new JFormattedTextField();
		panel.add(countryField);
		countryField.setColumns(20);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Coordinates NS and EW",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		textPane.add(panel_1, "cell 2 2,alignx left,aligny top");

		coordNS = new JFormattedTextField(numberFormatter);
		panel_1.add(coordNS);
		coordNS.setColumns(5);

		coordEW = new JFormattedTextField(numberFormatter);
		panel_1.add(coordEW);
		coordEW.setColumns(5);
		JButton okButton = new JButton("OK");
		textPane.add(okButton, "flowx,cell 0 5");

		okButton.setActionCommand(OK);
		JButton helpButton = new JButton("Help");
		textPane.add(helpButton, "cell 0 5");
		helpButton.setActionCommand(HELP);
		helpButton.addActionListener(this);
		okButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (OK.equals(cmd)) { // Process the password.
			String inputLoc = locationField.getText();
			String inputCount = countryField.getText();
			Number inputCNS = ((Number) coordNS.getValue());
			Number inputCEW = ((Number) coordEW.getValue());

			if (r2r.addLocation(inputLoc, inputCount, inputCNS, inputCEW)) {
				JOptionPane.showMessageDialog(controllingFrame,
						"Success! The Location " + inputLoc + " is now on the Rome2Rio System.");
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
		final AddLocation newContentPane = this;
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