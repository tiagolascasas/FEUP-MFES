package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import rome2rio.Rome2Rio;

@SuppressWarnings("serial")
public class RouteChooser extends JPanel {
	
	private static final int LAST_Y = 250;
	private static final int LAST_X = 800;
	private static final int INIT_CORD = 100;
	
	private ButtonGroup criteriaChoice;
	private ButtonGroup transportGroup;
	
	private Rome2Rio r2r;
	
	public RouteChooser(Rome2Rio r2r) {
		
		this.setBounds(INIT_CORD, INIT_CORD, LAST_X, LAST_Y);
		this.setLayout(new GridLayout(2,1));
		this.setBackground(Color.BLACK);
		this.setOpaque(true);
		
		this.r2r = r2r;
		
		criteriaPanel();
		createTransportationPanel();
		routePanel();
	}
	
	private void criteriaPanel() {
		
		JPanel panel1 = new JPanel();
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

	    //Group the radio buttons.
	    criteriaChoice = new ButtonGroup();
	    criteriaChoice.add(distanceButton);
	    criteriaChoice.add(durationButton);
	    criteriaChoice.add(priceButton);
	    
	    panel1.add(criteria);
	    panel1.add(distanceButton);
	    panel1.add(durationButton);
	    panel1.add(priceButton);
	    
		this.add(panel1);
	}
	
	protected void createTransportationPanel() {
		JPanel p = new JPanel(new GridLayout(1,1));
		p.setBackground(Color.BLACK);

		JRadioButton busButton = new JRadioButton("Bus");
		busButton.setMnemonic(KeyEvent.VK_R);
		busButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		busButton.setForeground(Color.BLACK);
		busButton.setActionCommand("rabbit");

		JRadioButton carButton = new JRadioButton("Car");
		carButton.setMnemonic(KeyEvent.VK_B);
		carButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		carButton.setForeground(Color.BLACK);
		carButton.setActionCommand("Bird");
		carButton.setSelected(true);

		JRadioButton ferryButton = new JRadioButton("Ferry");
		ferryButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		ferryButton.setForeground(Color.BLACK);
		ferryButton.setMnemonic(KeyEvent.VK_C);
		ferryButton.setActionCommand("cat");

		JRadioButton planeButton = new JRadioButton("Plane");
		planeButton.setMnemonic(KeyEvent.VK_D);
		planeButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		planeButton.setForeground(Color.BLACK);
		planeButton.setActionCommand("dog");

		JRadioButton trainButton = new JRadioButton("Train");
		trainButton.setMnemonic(KeyEvent.VK_R);
		trainButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		trainButton.setForeground(Color.BLACK);
		trainButton.setActionCommand("rabbit");

		//Group the radio buttons.
		transportGroup = new ButtonGroup();    
		transportGroup.add(busButton);
		transportGroup.add(carButton);
		transportGroup.add(ferryButton);
		transportGroup.add(planeButton);
		transportGroup.add(trainButton);

		p.add(busButton);
		p.add(carButton);
		p.add(ferryButton);
		p.add(planeButton);
		p.add(trainButton);

		this.add(p);
	}


	private void routePanel() {
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.BLACK);
		
		JFormattedTextField locationField1 = new JFormattedTextField();
		locationField1.setColumns(20);
		locationField1.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		locationField1.setPreferredSize(new Dimension(locationField1.getWidth(),60));
		
		JFormattedTextField locationField2 = new JFormattedTextField();
		locationField2.setColumns(20);
		locationField2.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		locationField2.setPreferredSize(new Dimension(locationField2.getWidth(),60));
		
		JButton searchRoute = new JButton("Find Transport");
		searchRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String inputLoc1 = locationField1.getText();
				String inputLoc2 =  locationField2.getText();
				
				if(r2r.graph.findNode(inputLoc1) == null) {
					JOptionPane.showMessageDialog(new JFrame("Warning:"), 
							inputLoc1 + " doesn't exist on the Rome2Rio System");
					return;
				}
				if(r2r.graph.findNode(inputLoc2) == null) {
					JOptionPane.showMessageDialog(new JFrame("Warning:"), 
							inputLoc2 + " doesn't exist on the Rome2Rio System");
					return;
				}
				if(r2r.graph.ExistsEdge(inputLoc1,inputLoc2) == false) {
					JOptionPane.showMessageDialog(new JFrame("Warning:"),  
							"Doesn't exist any Route with location " + inputLoc1 
							+ " as source and location " + inputLoc2 
							+ " as target on the Rome2Rio System");
					return;
				}
					
				String criteriaPre = GuiUtils.getSelectedButtonText(criteriaChoice);

				switch(criteriaPre) {
				case "Lowest Distance":
					r2r.getBestRouteForCriterion(inputLoc1,inputLoc2,rome2rio.quotes.DISTANCEQuote.getInstance());
					break;
				case "Lowest Duration":
					r2r.getBestRouteForCriterion(inputLoc1,inputLoc2,rome2rio.quotes.TIMEQuote.getInstance());
					break;
				case "Cheapest Price":
					r2r.getBestRouteForCriterion(inputLoc1,inputLoc2,rome2rio.quotes.PRICEQuote.getInstance());
					break;
				default:
					break;
				}
				
			}
		});
		searchRoute.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		searchRoute.setPreferredSize(new Dimension(130,60));

		panel2.add(locationField1);
		panel2.add(locationField2);
		panel2.add(searchRoute);
		
		this.add(panel2);

	}



	
}
