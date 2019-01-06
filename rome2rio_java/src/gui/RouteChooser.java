package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import rome2rio.Rome2Rio;

@SuppressWarnings("serial")
public class RouteChooser extends JPanel {
	
	private static final int LAST_Y = 250;
	private static final int LAST_X = 800;
	private static final int INIT_CORD = 100;
	
	
	public RouteChooser(Rome2Rio r2r) {
		
		this.setBounds(INIT_CORD, INIT_CORD, LAST_X, LAST_Y);
		this.setLayout(new GridLayout(2,1));
		this.setBackground(Color.BLACK);
		this.setOpaque(true);
		
		criteriaPanel();
		
		routePanel();
	}
	
	private void criteriaPanel() {
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new EmptyBorder(50, 10, 10, 10));
		panel1.setBackground(Color.BLACK);
		
		JLabel criteria = new JLabel("<html><font color='white'>Order Criteria:</font></html>", JLabel.CENTER);
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

	    JRadioButton noneButton = new JRadioButton("None");
	    noneButton.setMnemonic(KeyEvent.VK_R);
	    noneButton.setFont(new Font("American Typewriter", Font.PLAIN, 15));
	    noneButton.setForeground(Color.WHITE);
	    noneButton.setActionCommand("rabbit");

	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(distanceButton);
	    group.add(durationButton);
	    group.add(priceButton);
	    group.add(noneButton);
	    
	    panel1.add(criteria);
	    panel1.add(distanceButton);
	    panel1.add(durationButton);
	    panel1.add(priceButton);
	    panel1.add(noneButton);
	    
		this.add(panel1);
	}

	private void routePanel() {
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.BLACK);

		
		JFormattedTextField local1 = new JFormattedTextField();
		local1.setColumns(20);
		local1.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		local1.setPreferredSize(new Dimension(local1.getWidth(),60));
		
		JFormattedTextField local2 = new JFormattedTextField();
		local2.setColumns(20);
		local2.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		local2.setPreferredSize(new Dimension(local2.getWidth(),60));

		
		JButton searchRoute = new JButton("Find Transport");
		searchRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		searchRoute.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		searchRoute.setPreferredSize(new Dimension(130,60));

		panel2.add(local1);
		panel2.add(local2);
		panel2.add(searchRoute);
		
		this.add(panel2);

	}



	
}
