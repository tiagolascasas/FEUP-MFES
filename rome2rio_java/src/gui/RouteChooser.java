package gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import rome2rio.Rome2Rio;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class RouteChooser extends JPanel {

	private static final int INIT_CORD = 100;

	private ButtonGroup criteriaChoice;
	private ButtonGroup transportGroup;

	private Rome2Rio r2r;
	private JPanel panel1;
	private JPanel p;

	public RouteChooser(Rome2Rio r2r) {

		this.setBounds(INIT_CORD, INIT_CORD, 813, 277);
		this.setBackground(Color.BLACK);
		this.setOpaque(true);

		this.r2r = r2r;

		criteriaPanel();
		createTransportationPanel();
		routePanel();
	}

	private void criteriaPanel() {

		panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(20, 5, 10, 10));
		panel1.setBackground(Color.BLACK);

		JLabel criteria = new JLabel("<html><font color='white'>Order Criteria:</font></html>", JLabel.LEFT);
		criteria.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		criteria.setForeground(Color.white);

		JRadioButton distanceButton = new JRadioButton("Lowest Distance");
		distanceButton.setMnemonic(KeyEvent.VK_B);
		distanceButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		distanceButton.setForeground(Color.WHITE);
		distanceButton.setActionCommand("Bird");
		distanceButton.setSelected(true);

		JRadioButton durationButton = new JRadioButton("Lowest Duration");
		durationButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		durationButton.setForeground(Color.WHITE);
		durationButton.setMnemonic(KeyEvent.VK_C);
		durationButton.setActionCommand("cat");

		JRadioButton priceButton = new JRadioButton("Cheapest Price");
		priceButton.setMnemonic(KeyEvent.VK_D);
		priceButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		priceButton.setForeground(Color.WHITE);
		priceButton.setActionCommand("dog");

		// Group the radio buttons.
		criteriaChoice = new ButtonGroup();
		criteriaChoice.add(distanceButton);
		criteriaChoice.add(durationButton);
		criteriaChoice.add(priceButton);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel1.add(criteria);
		panel1.add(distanceButton);
		panel1.add(durationButton);
		panel1.add(priceButton);
	}

	protected void createTransportationPanel() {

		p = new JPanel();
		p.setBorder(new EmptyBorder(20, 5, 10, 10));
		p.setBackground(Color.BLACK);

		JLabel criteria = new JLabel("<html><font color='white'>Transport Preference:</font></html>", JLabel.LEFT);
		criteria.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		criteria.setForeground(Color.white);

		JRadioButton busButton = new JRadioButton("Bus");
		busButton.setMnemonic(KeyEvent.VK_R);
		busButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		busButton.setForeground(Color.WHITE);
		busButton.setActionCommand("rabbit");

		JRadioButton carButton = new JRadioButton("Car");
		carButton.setMnemonic(KeyEvent.VK_B);
		carButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		carButton.setForeground(Color.WHITE);
		carButton.setActionCommand("Bird");
		carButton.setSelected(true);

		JRadioButton ferryButton = new JRadioButton("Ferry");
		ferryButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		ferryButton.setForeground(Color.WHITE);
		ferryButton.setMnemonic(KeyEvent.VK_C);
		ferryButton.setActionCommand("cat");

		JRadioButton trainButton = new JRadioButton("Train");
		trainButton.setMnemonic(KeyEvent.VK_R);
		trainButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		trainButton.setForeground(Color.WHITE);
		trainButton.setActionCommand("rabbit");

		// Group the radio buttons.
		transportGroup = new ButtonGroup();
		transportGroup.add(busButton);
		transportGroup.add(carButton);
		transportGroup.add(ferryButton);
		transportGroup.add(trainButton);
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		p.add(criteria);
		p.add(busButton);
		p.add(carButton);
		p.add(ferryButton);
		p.add(trainButton);

		JRadioButton planeButton = new JRadioButton("Plane");
		planeButton.setMnemonic(KeyEvent.VK_D);
		planeButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		planeButton.setForeground(Color.WHITE);
		planeButton.setActionCommand("dog");
		transportGroup.add(planeButton);
		p.add(planeButton);

		JRadioButton anyButton = new JRadioButton("Any");
		anyButton.setMnemonic(KeyEvent.VK_R);
		anyButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		anyButton.setForeground(Color.WHITE);
		anyButton.setActionCommand("rabbit");
		transportGroup.add(anyButton);
		p.add(anyButton);
	}

	private void routePanel() {

		JPanel panel2 = new JPanel();
		panel2.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel2.setBackground(Color.BLACK);

		panel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		//panel.setPreferredSize(new Dimension(550, panel.getHeight()));
		panel2.add(panel);
				panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				Image img = null;
				
				JPanel panel_2 = new JPanel();
				panel_2.setBackground(Color.BLACK);
				panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Source", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
				panel.add(panel_2);
				
				JFormattedTextField locationField1 = new JFormattedTextField();
				panel_2.add(locationField1);
				locationField1.setColumns(15);
				locationField1.setFont(new Font("American Typewriter", Font.PLAIN, 15));
				locationField1.setPreferredSize(new Dimension(locationField1.getWidth(), 60));
				
				JButton searchLocation1 = new JButton("");
				searchLocation1.setPreferredSize(new Dimension(60, 60));
				panel_2.add(searchLocation1);
				searchLocation1.setIcon(new ImageIcon("/Users/nadiacarvalho/Documents/Github/MFES-trabalho/rome2rio_java/images/search.png"));
				searchLocation1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				searchLocation1.setFont(new Font("American Typewriter", Font.PLAIN, 15));
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.BLACK);
				panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Target", TitledBorder.LEADING, TitledBorder.TOP, null, Color.LIGHT_GRAY));
				panel.add(panel_1);
				//searchLocation1.setPreferredSize(new Dimension(130, 60));

				JFormattedTextField locationField2 = new JFormattedTextField();
				panel_1.add(locationField2);
				locationField2.setColumns(15);
				locationField2.setFont(new Font("American Typewriter", Font.PLAIN, 15));
				locationField2.setPreferredSize(new Dimension(locationField2.getWidth(), 60));
				
				JButton searchLocation2 = new JButton("");
				searchLocation2.setPreferredSize(new Dimension(60, 60));
				panel_1.add(searchLocation2);
				searchLocation2.setIcon(new ImageIcon("/Users/nadiacarvalho/Documents/Github/MFES-trabalho/rome2rio_java/images/search.png"));
				searchLocation2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				searchLocation2.setFont(new Font("American Typewriter", Font.PLAIN, 15));
				//searchLocation1.setPreferredSize(new Dimension(130, 60));

		JButton searchRoute = new JButton("Find Transport");
		searchRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String inputLoc1 = (String) locationField1.getText();
				String inputLoc2 = (String) locationField2.getText();

				if (r2r.graph.findNode(inputLoc1) == null) {
					JOptionPane.showMessageDialog(new JFrame("Warning:"),
							inputLoc1 + " doesn't exist on the Rome2Rio System");
					return;
				}
				if (r2r.graph.findNode(inputLoc2) == null) {
					JOptionPane.showMessageDialog(new JFrame("Warning:"),
							inputLoc2 + " doesn't exist on the Rome2Rio System");
					return;
				}
				if (r2r.graph.existsEdge(inputLoc1, inputLoc2) == false) {
					JOptionPane.showMessageDialog(new JFrame("Warning:"), "Doesn't exist any Route with location "
							+ inputLoc1 + " as source and location " + inputLoc2 + " as target on the Rome2Rio System");
					return;
				}

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
				case "Any":
				default:
					transportType = rome2rio.quotes.ANYQuote.getInstance();
					break;
				}

				String criteriaPre = GuiUtils.getSelectedButtonText(criteriaChoice);

				switch (criteriaPre) {
				case "Lowest Distance":
					r2r.getRouteWithCriteria(inputLoc1, inputLoc2, rome2rio.quotes.DISTANCEQuote.getInstance(),
							transportType);
					break;
				case "Lowest Duration":
					r2r.getRouteWithCriteria(inputLoc1, inputLoc2, rome2rio.quotes.TIMEQuote.getInstance(),
							transportType);
					break;
				case "Cheapest Price":
					r2r.getRouteWithCriteria(inputLoc1, inputLoc2, rome2rio.quotes.PRICEQuote.getInstance(),
							transportType);
					break;
				default:
					break;
				}

			}
		});
		searchRoute.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		searchRoute.setPreferredSize(new Dimension(130, 60));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
				
		panel2.add(searchRoute);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel2, GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(p, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 784, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE))
							.addGap(0))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 59, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(p, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
		);
		setLayout(groupLayout);
	}

}
