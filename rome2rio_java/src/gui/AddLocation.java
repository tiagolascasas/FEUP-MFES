package gui;
 
import javax.swing.*;
import javax.swing.text.NumberFormatter;

import rome2rio.Rome2Rio;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
 
/* PasswordDemo.java requires no other files. */
 
@SuppressWarnings("serial")
public class AddLocation extends JPanel
                          implements ActionListener {
	
	private Rome2Rio r2r;
    private static String OK = "ok";
    private static String HELP = "help";
 
    private JFrame controllingFrame; //needed for dialogs
    public JFrame frame;
    private JFormattedTextField locationField;
    private JFormattedTextField coordNS;
    private JFormattedTextField coordEW;
 
    public AddLocation(JFrame f, Rome2Rio r2r) {
    	
    	this.r2r = r2r;
    	
        //Use the default FlowLayout.
        controllingFrame = f;
        
        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
        numberFormatter.setAllowsInvalid(false); //this is the key!!
        numberFormatter.setMinimum(0l); //Optional
 
        //Create everything.
        locationField = new JFormattedTextField();
        locationField.setColumns(20);
        
        coordNS = new JFormattedTextField(numberFormatter);
        coordNS.setColumns(5);
        
        coordEW = new JFormattedTextField(numberFormatter);
        coordEW.setColumns(5);

        JLabel label = new JLabel("Enter the new location's name, coordinates NS and EW: ");
        label.setLabelFor(locationField);
 
        JComponent buttonPane = createButtonPanel();
 
        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        
        textPane.add(label);
        textPane.add(locationField);
        textPane.add(coordNS);
        textPane.add(coordEW);
 
        add(textPane);
        add(buttonPane);
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
 
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
 
        if (OK.equals(cmd)) { //Process the password.
            String inputLoc = locationField.getText();
            Number inputCNS = Float.parseFloat(coordNS.getText());
            Number inputCEW = Float.parseFloat(coordEW.getText());
            
            if (r2r.addLocation(inputLoc,inputCNS,inputCEW)) {
            	JOptionPane.showMessageDialog(controllingFrame,
            			"Success! The Location " + inputLoc + " is now on the Rome2Rio System.");
            	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            } else {
                JOptionPane.showMessageDialog(controllingFrame,
                    "Invalid City. Try to change the Name or the coordinates.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }
 
            locationField.selectAll();
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
    	locationField.requestFocusInWindow();
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
        final AddLocation newContentPane = this;
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
        frame.pack();
        frame.setVisible(true);
    }

}