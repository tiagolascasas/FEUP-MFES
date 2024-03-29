package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.plaf.basic.BasicMenuBarUI;

import rome2rio.Rome2Rio;

@SuppressWarnings("serial")
public class TopBar extends JMenuBar {

	public JMenu loginMenu = null;
	public JMenuItem login = null;
	public JMenuItem changePassword = null;
	public JMenuItem addLocation = null;
	public JMenuItem addRoute = null;
	public JMenuItem removeLocation = null;

	public TopBar(MainMenu mm) {

		this.add(Box.createRigidArea(new Dimension(this.getWidth(), 40)));

		this.setUI(new BasicMenuBarUI() {

			@Override
			public void paint(Graphics g, JComponent c) {
				g.setColor(Color.black);
				g.fillRect(0, 0, c.getWidth(), c.getHeight());
			}
		});
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		this.setForeground(Color.WHITE);

		addLogoOnMenu(mm);

	}

	private void addLogoOnMenu(MainMenu mm) {

		try {

			JMenu menu = new JMenu("");
			Image logo = new ImageIcon(ImageIO.read(new File("images/rome2rio_logo.png"))).getImage();
			menu.setIcon(new ImageIcon(logo.getScaledInstance(140, 40, java.awt.Image.SCALE_SMOOTH)));
			menu.setBackground(Color.black);
			menu.setOpaque(false);

			JMenuItem returnToChooser = new JMenuItem("Return To Main Menu");
			returnToChooser.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mm.setUserMenu();
				}
			});
			menu.add(returnToChooser);

			this.add(menu);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public JMenu administratorMenu() {

		JMenu adminMenu = new JMenu("Administrator");
		adminMenu.setFont(new Font("American Typewriter", Font.PLAIN, 13));
		adminMenu.setOpaque(false);
		adminMenu.setMnemonic(KeyEvent.VK_A);
		adminMenu.getAccessibleContext().setAccessibleDescription("Administrator Menu");
		loginMenu = adminMenu;
		return adminMenu;

	}

	public void loginAsAdministrator(Rome2Rio r2r) {

		removeButtonsWhenClient();

		login = new JMenuItem("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (r2r.status == rome2rio.quotes.ClientQuote.getInstance())
					passwordCaller(r2r);
			}
		});
		loginMenu.add(login);
	}

	private void removeButtonsWhenClient() {

		if (login != null) {
			loginMenu.remove(login);
			login = null;
		}
		if (changePassword != null) {
			loginMenu.remove(changePassword);
			changePassword = null;
		}
		if (addLocation != null) {
			loginMenu.remove(addLocation);
			addLocation = null;
		}
		if (addRoute != null) {
			loginMenu.remove(addRoute);
			addRoute = null;
		}
		if (removeLocation != null) {
			loginMenu.remove(removeLocation);
			removeLocation = null;
		}
	}

	public void passwordCaller(Rome2Rio r2r) {
		PasswordPanel passcode = new PasswordPanel(new JFrame(""), r2r, this);
		passcode.createAndShowGUI();
	}

	public void logoutAsAdministrator(Rome2Rio r2r) {

		loginMenu.remove(login);
		addAdminCalls(r2r);

		login = new JMenuItem("Logout");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (r2r.status == rome2rio.quotes.AdministratorQuote.getInstance()) {
					r2r.changeToClient();
					loginAsAdministrator(r2r);
				}
			}
		});
		loginMenu.add(login);
	}

	public void addAdminCalls(Rome2Rio r2r) {

		changePassword = new JMenuItem("Change Password");
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordEditor passcode = new PasswordEditor(new JFrame(""), r2r);
				passcode.createAndShowGUI();
			}
		});

		addLocation = new JMenuItem("Add Location");
		addLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddLocation addLocation = new AddLocation(new JFrame(""), r2r);
				addLocation.createAndShowGUI();
			}
		});

		addRoute = new JMenuItem("Add Route");
		addRoute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRoute addRoute = new AddRoute(new JFrame(""), r2r);
				addRoute.createAndShowGUI();
			}
		});

		removeLocation = new JMenuItem("Remove Location");
		removeLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveLocation removeLocation = new RemoveLocation(new JFrame(""), r2r);
				removeLocation.createAndShowGUI();
			}
		});

		loginMenu.add(changePassword);
		loginMenu.add(addLocation);
		loginMenu.add(addRoute);
	}

}
