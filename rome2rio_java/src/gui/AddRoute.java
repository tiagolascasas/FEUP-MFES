package gui;
 
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import rome2rio.Rome2Rio;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Enumeration;
 
/* PasswordDemo.java requires no other files. */
 
@SuppressWarnings("serial")
public class AddRoute extends JPanel
                          implements ActionListener {
	
	private Rome2Rio r2r;
    private static String OK = "ok";
    private static String HELP = "help";
 
    private JFrame controllingFrame; //needed for dialogs
    public JFrame frame;
    private JFormattedTextField locationField1;
    private JFormattedTextField locationField2;
    private JFormattedTextField time;
    private JFormattedTextField distance;
    private JFormattedTextField price;
    private ButtonGroup transportGroup;
 
    public AddRoute(JFrame f, Rome2Rio r2r) {
    	
    	this.r2r = r2r;
    	
        //Use the default FlowLayout.
        controllingFrame = f;
        
        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
        numberFormatter.setAllowsInvalid(false); //this is the key!!
        numberFormatter.setMinimum(0l); //Optional
 
        //Create everything.
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
        
        JLabel label = new JLabel("Enter the source and target locations' names, duration (min), distance (Km), price (€) and Transport type:");
        label.setLabelFor(locationField1);
 
        JComponent transpPane = createTransportationPanel();
        
        JComponent buttonPane = createButtonPanel();
 
        //Lay out everything.
        JPanel textPane1 = new JPanel(new GridLayout(1,0));  
        JPanel textPane2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        
        textPane1.add(label);
        textPane2.add(locationField1);
        textPane2.add(locationField2);
        textPane2.add(time);
        textPane2.add(distance);
        textPane2.add(price);

 
        add(textPane1);
        add(textPane2);
        add(transpPane);
        add(buttonPane);
        
    }
 
    protected JComponent createTransportationPanel() {
        JPanel p = new JPanel(new GridLayout(1,1));
 
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
 
        return p;
    }
    
    protected JComponent createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
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
    
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
 
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
 
        if (OK.equals(cmd)) { 
        	
            String inputLoc1 = locationField1.getText();
            String inputLoc2 = locationField2.getText();
            Number inputTime = ((Number)time.getValue());
            Number inputDistance = ((Number)distance.getValue());
            Number inputPrice = ((Number)price.getValue());
            String transportTypePre = getSelectedButtonText(transportGroup);
            
            Object transportType = "";
            
            switch(transportTypePre) {
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
            
            
            if (r2r.addWayBetweenLocations(inputLoc1,inputLoc2,transportType,inputTime,inputDistance,inputPrice)) {
            	JOptionPane.showMessageDialog(controllingFrame,
            			"Success! The Route between " + inputLoc1 + " and " + inputLoc2 + " is now on the Rome2Rio System.");
            	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                    "Invalid Route.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }
 
            locationField1.selectAll();
            resetFocus();
        } else { //The user has asked for help.
            JOptionPane.showMessageDialog(controllingFrame,
                "You can get the password by searching this example's\n"
              + "source code for the string \"correctPassword\".\n"
              + "Or look at the section How to Use Password Fields in\n"
              + "the components section of The Java Tutorial.");
        }
    }
 
    //Must be called from the event dispatch thread.
    protected void resetFocus() {
    	locationField1.requestFocusInWindow();
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public void createAndShowGUI() {
    	
        //Create and set up the window.
        frame = new JFrame("Add Location To System");
 
        //Create and set up the content pane.
        final AddRoute newContentPane = this;
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Make sure the focus goes to the right component
        //whenever the frame is initially given the focus.
        frame.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
                newContentPane.resetFocus();
            }
        });
 
        //Display the window.
        frame.setSize(800,180);
        frame.setVisible(true);
    }

}