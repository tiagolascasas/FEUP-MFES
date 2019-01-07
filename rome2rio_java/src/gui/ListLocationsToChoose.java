package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import rome2rio.Rome2Rio;

@SuppressWarnings("serial")
public class ListLocationsToChoose extends JPanel implements ActionListener {

	private static String OK = "ok";
	private static String HELP = "help";

	private JFrame controllingFrame; // needed for dialogs
	public JFrame frame;
	public RouteChooser rC;
	public int inputCalling;
	public JList<String> locationsList;
	public JScrollPane listScroller;

	public Rome2Rio r2r;

	public ListLocationsToChoose(JFrame f, Rome2Rio r2r, RouteChooser rC, int inputCalling) {

		this.r2r = r2r;
		this.rC = rC;
		this.inputCalling = inputCalling;

		// Use the default FlowLayout.
		controllingFrame = f;

		// Lay out everything.
		JPanel textPane = new JPanel();
		add(textPane);

		ArrayList<ArrayList<String>> locationsOnSystem = r2r.listLocationsStrings();
		String[] locationsStrings = null;
		if (!locationsOnSystem.isEmpty()) {
			locationsStrings = new String[locationsOnSystem.size()];
			for (int i = 0; i < locationsStrings.length; i++) {
				locationsStrings[i] = locationsOnSystem.get(i).get(0) + ", " + locationsOnSystem.get(i).get(1);
			}
		}

		locationsList = new JList<String>(locationsStrings);

		locationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		locationsList.setSelectedIndex(0);
		locationsList.setLayoutOrientation(JList.VERTICAL);
		locationsList.setVisibleRowCount(15);
		locationsList.setSize(300, 400);

		listScroller = new JScrollPane(locationsList);
		textPane.add(listScroller);

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

		if (OK.equals(cmd)) {

			String[] inputLoc = locationsList.getSelectedValue().split("\\,");
			;

			if (inputCalling == 1)
				rC.setLocationField1(inputLoc[0]);
			else
				rC.setLocationField2(inputLoc[0]);

			resetFocus();
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

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
		listScroller.requestFocusInWindow();
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	public void createAndShowGUI() {

		// Create and set up the window.
		frame = new JFrame("List and Choose Locations from System");

		// Create and set up the content pane.
		final ListLocationsToChoose newContentPane = this;
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