package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;

import net.miginfocom.swing.MigLayout;
import rome2rio.Rome2Rio;

/* PasswordDemo.java requires no other files. */

@SuppressWarnings("serial")
public class AddRoute extends JPanel implements ActionListener {

	public Rome2Rio r2r;
	public JFrame controllingFrame;
	public JFrame frame;
	public JFormattedTextField locationField1;
	public JFormattedTextField locationField2;
	public JFormattedTextField time;
	public JFormattedTextField distance;
	public JFormattedTextField price;
	public ButtonGroup transportGroup;

	private static String OK = "ok";
	private static String HELP = "help";

	public GuiUtils data = new GuiUtils();

	public AddRoute(JFrame f, Rome2Rio r2r) {

		this.r2r = r2r;

		// Use the default FlowLayout.
		controllingFrame = f;

		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		numberFormatter.setValueClass(Long.class); // optional, ensures you will always get a long value
		numberFormatter.setAllowsInvalid(false); // this is the key!!
		numberFormatter.setMinimum(0l); // Optional

		// Create everything.
		locationField1 = new JFormattedTextField();
		locationField1.setColumns(20);

		locationField2 = new JFormattedTextField();
		locationField2.setColumns(20);

		time = new JFormattedTextField(numberFormatter);
		time.setColumns(5);

		distance = new JFormattedTextField(numberFormatter);
		distance.setColumns(5);

		price = new JFormattedTextField(numberFormatter);
		price.setColumns(5);

		JLabel label = new JLabel(
				"Enter the source and target locations' names, duration (min), distance (Km), price (€) and Transport type:");
		label.setLabelFor(locationField1);

		JComponent transpPane = createTransportationPanel();

		// Lay out everything.
		JPanel textPane1 = new JPanel(new GridLayout(1, 0));
		JPanel textPane2 = new JPanel();

		textPane1.add(label);
		textPane2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		textPane2.add(locationField1);
		textPane2.add(locationField2);
		textPane2.add(time);
		textPane2.add(distance);
		textPane2.add(price);
		setLayout(new MigLayout("", "[513px][222px]", "[16px][36px][][][][][][58px]"));

		add(textPane1, "cell 0 0 2 1,alignx center,aligny top");
		add(textPane2, "cell 0 1 2 1,alignx left,aligny top");

		JPanel panel = new JPanel();
		add(panel, "cell 0 2 2 1,grow");
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Transports",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(new MigLayout("", "[513px]", "[]"));

		JRadioButton trainButton = new JRadioButton("Train");
		panel.add(trainButton, "cell 0 0");
		trainButton.setMnemonic(KeyEvent.VK_R);
		trainButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		trainButton.setForeground(Color.BLACK);
		trainButton.setActionCommand("rabbit");
		transportGroup.add(trainButton);

		JRadioButton planeButton = new JRadioButton("Plane");
		panel.add(planeButton, "cell 0 0");
		planeButton.setMnemonic(KeyEvent.VK_D);
		planeButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		planeButton.setForeground(Color.BLACK);
		planeButton.setActionCommand("dog");
		transportGroup.add(planeButton);

		JRadioButton ferryButton = new JRadioButton("Ferry");
		panel.add(ferryButton, "cell 0 0");
		ferryButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		ferryButton.setForeground(Color.BLACK);
		ferryButton.setMnemonic(KeyEvent.VK_C);
		ferryButton.setActionCommand("cat");
		transportGroup.add(ferryButton);

		JRadioButton carButton = new JRadioButton("Car");
		panel.add(carButton, "cell 0 0");
		carButton.setMnemonic(KeyEvent.VK_B);
		carButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		carButton.setForeground(Color.BLACK);
		carButton.setActionCommand("Bird");
		carButton.setSelected(true);
		transportGroup.add(carButton);

		JRadioButton busButton = new JRadioButton("Bus");
		panel.add(busButton, "cell 0 0");
		busButton.setMnemonic(KeyEvent.VK_R);
		busButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		busButton.setForeground(Color.BLACK);
		busButton.setActionCommand("rabbit");
		transportGroup.add(busButton);
		JButton okButton = new JButton("OK");
		add(okButton, "flowx,cell 1 3");

		okButton.setActionCommand(OK);
		okButton.addActionListener(this);
		add(transpPane, "cell 0 7,alignx right,aligny center");
		JButton helpButton = new JButton("Help");
		add(helpButton, "cell 1 3");
		helpButton.setActionCommand(HELP);
		helpButton.addActionListener(this);

	}

	protected JComponent createTransportationPanel() {
		JPanel p = new JPanel(new GridLayout(1, 1));

		// Group the radio buttons.
		transportGroup = new ButtonGroup();

		return p;
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (OK.equals(cmd)) {

			String inputLoc1 = locationField1.getText();
			String inputLoc2 = locationField2.getText();
			Number inputTime = ((Number) time.getValue());
			Number inputDistance = ((Number) distance.getValue());
			Number inputPrice = ((Number) price.getValue());
			String transportTypePre = GuiUtils.getSelectedButtonText(transportGroup);

			Object transportType = null;

			switch (transportTypePre) {
			case "Bus":
				transportType = rome2rio.quotes.BUSQuote.getInstance();
				break;
			case "Car":
				transportType = rome2rio.quotes.CARQuote.getInstance();
				break;
			case "Ferry":
				transportType = rome2rio.quotes.FERRYQuote.getInstance();
				break;
			case "Plane":
				transportType = rome2rio.quotes.PLANEQuote.getInstance();
				break;
			case "Train":
				transportType = rome2rio.quotes.TRAINQuote.getInstance();
				break;
			default:
				break;
			}

			if (r2r.graph.existsEdge(inputLoc1, inputLoc2) == false) {
				if (r2r.addWayBetweenLocations(inputLoc1, inputLoc2, transportType, inputTime, inputDistance,
						inputPrice)) {
					JOptionPane.showMessageDialog(controllingFrame, "Success! The Route between " + inputLoc1 + " and "
							+ inputLoc2 + " is now on the Rome2Rio System.");
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				} else {
					JOptionPane.showMessageDialog(controllingFrame, "Invalid Route.", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				if (r2r.addNewTransportationType(inputLoc1, inputLoc2, transportType, inputTime, inputDistance,
						inputPrice)) {
					JOptionPane.showMessageDialog(controllingFrame, "Success! The Route between " + inputLoc1 + " and "
							+ inputLoc2 + " is now on the Rome2Rio System.");
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				} else {
					JOptionPane.showMessageDialog(controllingFrame, "Invalid Route.", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			locationField1.selectAll();
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
		locationField1.requestFocusInWindow();
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	public void createAndShowGUI() {

		// Create and set up the window.
		frame = new JFrame("Add Location To System");

		// Create and set up the content pane.
		final AddRoute newContentPane = this;
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
		frame.setSize(800, 180);
		frame.setVisible(true);
	}

}