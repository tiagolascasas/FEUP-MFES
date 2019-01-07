package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.overture.codegen.runtime.IO;
import org.overture.codegen.runtime.Utils;

import rome2rio.Node;
import rome2rio.Path;
import rome2rio.Rome2Rio;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class RouteSearchResults extends JPanel implements ListSelectionListener {

	private static final int LAST_Y = 510;
	private static final int LAST_X = 980;
	private static final int INIT_CORD = 10;

	public JSplitPane splitPane;
	public JLabel labelInfoPane;
	
	public int selectedPathIndex;
	Path[] paths;
	
	public JList<String> pathsList;

	public RouteSearchResults(Rome2Rio r2r, Path bestPath, Path[] paths, String source, String target) {

		this.setBounds(INIT_CORD, INIT_CORD, LAST_X, LAST_Y);
		this.setBackground(Color.BLACK);
		this.setOpaque(true);
		setLayout(null);
		
		this.paths = paths;
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane(paths), infoPane());
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(true);
		splitPane.setBorder(new EmptyBorder(20, 5, 10, 10));
		
		JPanel panelBestPath;

		panelBestPath = bestPathPanel(bestPath, source, target);
		splitPane.setPreferredSize(new Dimension(panelBestPath.getWidth(), 100));
		splitPane.setBounds(16, 90, 946, 395);
		
		for(int i=0; i< paths.length; i++) {
			paths[i].print();
			System.out.println("\n");
		}
		
		add(splitPane);
	}

	private JPanel bestPathPanel(Path bestPath, String source, String target) {
		
		JPanel panelBestPath = new JPanel();
		panelBestPath.setBounds(16, 16, 946, 69);
		panelBestPath.setBorder(new EmptyBorder(20, 5, 10, 10));
		panelBestPath.setLayout(null);
		
		JLabel label = new JLabel("Having " + source.toUpperCase() + " as source and " + target.toUpperCase() + " as target and for the Criteria " + bestPath.criteria.toString().replaceAll("(<|>)", "") + " and Transport Type " + bestPath.travelType.toString().replaceAll("(<|>)", "") + ", the best Route is:", JLabel.LEFT);
		label.setBounds(6, 6, 934, 18);
		label.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label.setForeground(Color.BLACK);
		
		panelBestPath.add(label);
		
		JPanel bestPanel = new JPanel();
		bestPanel.setBounds(16, 16, 946, 69);
		bestPanel.setBorder(new EmptyBorder(20, 5, 10, 10));
		bestPanel.setLayout(null);
		
		JLabel label2 = new JLabel(pathToString(bestPath) + " with total distance " + ", total price " + "and total duration ", JLabel.LEFT);
		label2.setBounds(6, 10, 909, 42);
		label2.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label2.setForeground(Color.BLACK);
		
		bestPanel.add(label2);
		
		panelBestPath.add(bestPanel);
		
		add(panelBestPath);
		
		return panelBestPath;
	}
	
	public String pathToString(Path path) {
		
		String res = "";
		Number i = 1L;
		Boolean whileCond_3 = true;
		while (whileCond_3) {
			whileCond_3 = i.longValue() <= path.path.size();
			if (!(whileCond_3)) {
				break;
			}
			if(i.longValue() != 1L) 
				res += " -> ";
			{
				res += ((Node) Utils.get(path.path, i)).location;
				i = i.longValue() + 1L;
			}
		}
		
		return res;
	}
	
	public String[] calculateTotals(Path path) {
		
		String[] res = new String[3];
		
		Number totalPrice = 0;
		Number totalDistance = 0;
		Number totalTime = 0;
		
		Number i = 1L;
		Boolean whileCond_3 = true;
		while (whileCond_3) {
			whileCond_3 = i.longValue() <= path.path.size();
			if (!(whileCond_3)) {
				break;
			}
			{
				// ((Node) Utils.get(path.path, i)).location;
				i = i.longValue() + 1L;
			}
		}
		
		return res;
	}
	

	private JPanel listScrollPane(Path[] paths) {

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
	
		String[] locationsStrings = null;
		if (paths.length != 0) {
			locationsStrings = new String[(paths.length)];
			for (int i = 0; i < (locationsStrings.length); i++) {
				locationsStrings[i] = pathToString(paths[i]);
			}
		}

		pathsList = new JList<String>(locationsStrings);

		pathsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pathsList.setSelectedIndex(0);
		pathsList.setLayoutOrientation(JList.VERTICAL);
		pathsList.setVisibleRowCount(15);
		pathsList.setSize(300, 400);
		pathsList.addListSelectionListener(this);

		JScrollPane listScroller = new JScrollPane(pathsList);
		panel.add(listScroller);

		return panel;
	}


	private JPanel infoPane() {

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		
		Path selectedPath = paths[selectedPathIndex];
		
		labelInfoPane = new JLabel(pathToString(selectedPath) + " with total distance " + ", total price " + "and total duration ", JLabel.LEFT);
		labelInfoPane.setBounds(6, 10, 909, 42);
		labelInfoPane.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		labelInfoPane.setForeground(Color.WHITE);
		
		panel.add(labelInfoPane);
		
		return panel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<?> list = (JList<?>)e.getSource();
		selectedPathIndex = list.getSelectedIndex();	
		setInfoPane();
	}
	
	public void setInfoPane() {
		Path selectedPath = paths[selectedPathIndex];
		labelInfoPane.setText(pathToString(selectedPath));

	}
	
	

}
