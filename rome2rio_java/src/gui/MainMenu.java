package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.overture.codegen.runtime.VDMSet;

import rome2rio.Path;
import rome2rio.Rome2Rio;

public class MainMenu {

	public Rome2Rio r2r;

	public JFrame mainMenuFrame;
	public TopBar menuBar;
	public JMenu adminMenu;
	public JPasswordField passwordField;
	public JPanel panelInEdition;

	public MainMenu() {
		initialize();
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu mainMenu = new MainMenu();
					mainMenu.mainMenuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	private void initialize() {

		r2r = new Rome2Rio();
		GuiUtils.populateLocationsAndRoutes(r2r);

		// menu with functionalities
		setMainMenuFrame();
	}

	private void setMainMenuFrame() {

		mainMenuFrame = new JFrame("Rome2Rio");
		mainMenuFrame.setResizable(false);

		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuFrame.getContentPane().setLayout(null);
		mainMenuFrame.setBackground(Color.LIGHT_GRAY);

		mainMenuFrame.setSize(1000, 600);

		try {
			mainMenuFrame.setContentPane(
					new JLabel((Icon) new ImageIcon(ImageIO.read(new File("images/rome2rio_background.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		menuBar = new TopBar(this);
		mainMenuFrame.setJMenuBar(menuBar);

		setAdministratorMenu();
		setUserMenu();
	}

	private void setAdministratorMenu() {

		adminMenu = menuBar.administratorMenu();
		menuBar.loginAsAdministrator(r2r);
		menuBar.add(adminMenu);
	}

	public void setUserMenu() {

		if (panelInEdition != null) {
			mainMenuFrame.remove(panelInEdition);
			panelInEdition = null;
		}

		RouteChooser routeChooser = new RouteChooser(r2r, this);
		panelInEdition = routeChooser;
		mainMenuFrame.add(panelInEdition);

		mainMenuFrame.revalidate();
		mainMenuFrame.repaint();
	}

	public void setResultsPanel(Path bestPath, VDMSet vdmSet, String source, String target) {

		if (panelInEdition != null) {
			mainMenuFrame.remove(panelInEdition);
			panelInEdition = null;
		}

		Path[] paths = new Path[vdmSet.size()];
		int i = 0;
		for (Iterator<?> iterator_18 = vdmSet.iterator(); iterator_18.hasNext();) {
			paths[i] = (Path) iterator_18.next();
			i++;
		}

		RouteSearchResults routeSearchResults = new RouteSearchResults(r2r, bestPath, paths, source, target);
		panelInEdition = routeSearchResults;
		mainMenuFrame.add(panelInEdition);

		mainMenuFrame.revalidate();
		mainMenuFrame.repaint();
	}

}
