package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import rome2rio.EdgeType;
import rome2rio.Path;
import rome2rio.Rome2Rio;

@SuppressWarnings("serial")
public class RouteSearchResults extends JPanel implements ListSelectionListener {

	private static final int LAST_Y = 510;
	private static final int LAST_X = 980;
	private static final int INIT_CORD = 10;

	public JSplitPane splitPane;
	public JList<String> routeParts;
	public JPanel panelInfo;
	public JLabel labelInfoPane;
	public JLabel labelTotalTime;
	public JLabel labelTotalPrice;
	public JLabel labelTotalDistance;

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

		add(splitPane);
	}

	private JPanel bestPathPanel(Path bestPath, String source, String target) {

		JPanel panelBestPath = new JPanel();
		panelBestPath.setBounds(16, 16, 946, 69);
		panelBestPath.setBorder(new EmptyBorder(20, 5, 10, 10));
		panelBestPath.setLayout(null);

		JLabel label = new JLabel("Having " + source.toUpperCase() + " as source and " + target.toUpperCase()
				+ " as target and for the Criteria " + bestPath.criteria.toString().replaceAll("(<|>)", "")
				+ " and Transport Type " + bestPath.travelType.toString().replaceAll("(<|>)", "")
				+ ", the best Route is:", JLabel.LEFT);
		label.setBounds(6, 6, 934, 18);
		label.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label.setForeground(Color.BLACK);

		panelBestPath.add(label);

		JPanel bestPanel = new JPanel();
		bestPanel.setBounds(16, 17, 946, 69);
		bestPanel.setBorder(new EmptyBorder(20, 5, 10, 10));
		bestPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label2 = new JLabel(PathUtils.pathToString(bestPath) + " ::::> ");
		label2.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label2.setForeground(Color.BLACK);
		bestPanel.add(label2);

		JLabel label2Distance = new JLabel("Total distance: " + PathUtils.calculateTotalDistance(bestPath) + "km;",
				JLabel.LEFT);
		label2Distance.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label2Distance.setForeground(Color.BLACK);
		bestPanel.add(label2Distance);

		JLabel label2Time = new JLabel("Total duration: " + PathUtils.calculateTotalTime(bestPath) + "min;",
				JLabel.LEFT);
		label2Time.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label2Time.setForeground(Color.BLACK);
		bestPanel.add(label2Time);

		JLabel label2Price = new JLabel("Total price: " + PathUtils.calculateTotalPrice(bestPath) + "€;", JLabel.LEFT);
		label2Price.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label2Price.setForeground(Color.BLACK);
		bestPanel.add(label2Price);

		panelBestPath.add(bestPanel);

		add(panelBestPath);

		return panelBestPath;
	}

	private JPanel listScrollPane(Path[] paths) {

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);

		String[] pathsStrings = null;
		if (paths.length != 0) {
			pathsStrings = new String[paths.length];
			for (int i = 0; i < pathsStrings.length; i++) {
				pathsStrings[i] = PathUtils.pathToString(paths[i]);
			}
		}

		pathsList = new JList<String>(pathsStrings);

		pathsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pathsList.setSelectedIndex(5);
		pathsList.setLayoutOrientation(JList.VERTICAL);
		pathsList.setVisibleRowCount(25);
		pathsList.addListSelectionListener(this);

		JScrollPane listScroller = new JScrollPane(pathsList);
		panel.add(listScroller);

		return panel;
	}

	private JPanel infoPane() {

		panelInfo = new JPanel();
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.PAGE_AXIS));
		panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelInfo.setBackground(Color.BLACK);

		Path selectedPath = paths[selectedPathIndex];

		labelInfoPane = new JLabel(PathUtils.pathToString(selectedPath) + " ::::> ");
		labelInfoPane.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		labelInfoPane.setForeground(Color.WHITE);

		labelTotalDistance = new JLabel("Total distance: " + PathUtils.calculateTotalDistance(selectedPath) + "km;");
		labelTotalDistance.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		labelTotalDistance.setForeground(Color.WHITE);

		labelTotalPrice = new JLabel("Total price: " + PathUtils.calculateTotalPrice(selectedPath) + "€;");
		labelTotalPrice.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		labelTotalPrice.setForeground(Color.WHITE);

		labelTotalTime = new JLabel("Total duration: " + PathUtils.calculateTotalTime(selectedPath) + "min;");
		labelTotalTime.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		labelTotalTime.setForeground(Color.WHITE);

		panelInfo.add(labelInfoPane, Component.CENTER_ALIGNMENT);
		panelInfo.add(labelTotalDistance, Component.CENTER_ALIGNMENT);
		panelInfo.add(labelTotalPrice, Component.CENTER_ALIGNMENT);
		panelInfo.add(labelTotalTime, Component.CENTER_ALIGNMENT);

		panelInfo.add(Box.createRigidArea(new Dimension(0, 5)), Component.CENTER_ALIGNMENT);

		ArrayList<EdgeType> routes = PathUtils.getEdgeTypesOfPath(selectedPath);

		String[] routesStrings = new String[routes.size()];

		String[] locs = PathUtils.pathToString(selectedPath).split("->");

		for (int i = 0; i < routes.size(); i++) {
			routesStrings[i] = locs[i] + " -> " + locs[i + 1] + " : "
					+ routes.get(i).travelType.toString().replaceAll("(<|>)", "") + "; Distance: "
					+ routes.get(i).distance.longValue() + "km; Price: " + routes.get(i).price.longValue()
					+ "€; Duration: " + routes.get(i).time.longValue() + "min;";
		}
		routeParts = new JList<String>(routesStrings);

		routeParts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		routeParts.setSelectedIndex(0);
		routeParts.setLayoutOrientation(JList.VERTICAL);
		routeParts.setVisibleRowCount(25);

		panelInfo.add(routeParts, Component.CENTER_ALIGNMENT);

		return panelInfo;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<?> list = (JList<?>) e.getSource();
		selectedPathIndex = list.getSelectedIndex();
		setInfoPane();
	}

	public void setInfoPane() {

		Path selectedPath = paths[selectedPathIndex];
		labelInfoPane.setText(PathUtils.pathToString(selectedPath) + " ::::> ");
		labelTotalDistance.setText("Total distance: " + PathUtils.calculateTotalDistance(selectedPath) + "km;");
		labelTotalPrice.setText("Total price: " + PathUtils.calculateTotalPrice(selectedPath) + "€;");
		labelTotalTime.setText("Total duration: " + PathUtils.calculateTotalTime(selectedPath) + "min;");

		ArrayList<EdgeType> routes = PathUtils.getEdgeTypesOfPath(selectedPath);

		String[] routesStrings = new String[routes.size()];

		String[] locs = PathUtils.pathToString(selectedPath).split("->");

		for (int i = 0; i < routes.size(); i++) {
			routesStrings[i] = locs[i] + " -> " + locs[i + 1] + " : "
					+ routes.get(i).travelType.toString().replaceAll("(<|>)", "") + "; Distance: "
					+ routes.get(i).distance.longValue() + "km; Price: " + routes.get(i).price.longValue()
					+ "€; Duration: " + routes.get(i).time.longValue() + "min;";
		}

		panelInfo.remove(routeParts);

		routeParts = new JList<String>(routesStrings);

		routeParts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		routeParts.setSelectedIndex(0);
		routeParts.setLayoutOrientation(JList.VERTICAL);
		routeParts.setVisibleRowCount(25);

		panelInfo.add(routeParts, Component.CENTER_ALIGNMENT);

		panelInfo.revalidate();
		panelInfo.repaint();
	}
}
