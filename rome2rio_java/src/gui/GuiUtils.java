package gui;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import rome2rio.Rome2Rio;

public class GuiUtils {

	public GuiUtils() {

	}

	public static String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}

		return null;
	}

	public static void populateLocationsAndRoutes(Rome2Rio r2r) {

		r2r.changeToAdmin("default");

		r2r.addLocation("Boston", "EUA", 42.35000, -71.06667); // 42°21′N 71°04′W
		r2r.addLocation("Cape Town", "South Africa", -33.93333, 18.41667); // 33°56′S 18°25′E
		r2r.addLocation("Pretoria", "South Africa", -25.75000, 28.18333); // 25°45′S 28°11′E
		r2r.addLocation("Geneva", "Switzerland", 46.20000, 6.15000); // 46°12′N 6°09′E
		r2r.addLocation("Zagreb", "Croatia", 45.81667, 15.98333); // 45°49′N 15°59′E
		r2r.addLocation("Messina", "Italy", 38.18333, 15.55000); // 38°11′N 15°33′E
		r2r.addLocation("Detroit", "EUA", 82.50000, -62.33333); // 82°30′N 62°20′W
		r2r.addLocation("São Paulo", "Brazil", -23.55000, -46.63333); // 23°33′S 46°38′W
		r2r.addLocation("Newcastle", "UK", 54.96667, -1.61667); // 54°58′N 1°37′W
		r2r.addLocation("Nantes", "France", 47.21667, -1.55000); // 47°13′N 1°33′W
		r2r.addLocation("Leipzig", "Germany", 51.33333, 12.38333); // 51°20′N 12°23′E
		r2r.addLocation("Prague", "Czech Republic", 50.08333, 14.41667); // 50°05′N 14°25′E
		r2r.addLocation("Birmingham", "UK", 52.48333, -1.90000); // 52°29′N 1°54′W
		r2r.addLocation("Sofia", "Bulgaria", 42.70000, 23.33333); // 42°42′N 23°20′E
		r2r.addLocation("Rotterdam", "Netherlands", 51.93333, 4.48333); // 51°56′N 4°29′E
		r2r.addLocation("Cork", "Ireland", 51.90000, -8.46667); // 51°54′N 8°28′W
		r2r.addLocation("Milwaukee", "EUA", 43.05000, -87.95000); // 43°03′N 87°57′W
		r2r.addLocation("Ponta Delgada", "Portugal", 37.81667, -25.75000); // 37°49′N 25°45′W
		r2r.addLocation("Rome", "Italy", 41.90000, 12.50000); // 41°54′N 12°30′E
		r2r.addLocation("Moscow", "Russia", 55.75000, 37.61667); // 55°45′N 37°37′E
		r2r.addLocation("Göteborg", "Sweden", 57.70000, 11.96667); // 57°42′N 11°58′E
		r2r.addLocation("Vancouver", "Canada", 49.25000, -123.10000); // 49°15′N 123°06′W
		r2r.addLocation("Las Vegas", "EUA", 36.18333, -115.13333); // 36°11′N 115°08′W
		r2r.addLocation("Beirut", "Lebanon", 33.88333, 35.51667); // 33°53′N 35°31′E

		r2r.addWayBetweenLocations("Boston", "Cape Town", rome2rio.quotes.PLANEQuote.getInstance(), 12, 123, 400);

		r2r.addWayBetweenLocations("Pretoria", "Boston", rome2rio.quotes.BUSQuote.getInstance(), 2, 120, 5);
		r2r.addNewTransportationType("Boston", "Cape Town", rome2rio.quotes.BUSQuote.getInstance(), 2, 130, 5);

		r2r.addWayBetweenLocations("Pretoria", "Cape Town", rome2rio.quotes.BUSQuote.getInstance(), 2, 12, 35);
		r2r.addNewTransportationType("Pretoria", "Cape Town", rome2rio.quotes.TRAINQuote.getInstance(), 1, 12, 60);
		r2r.addNewTransportationType("Pretoria", "Cape Town", rome2rio.quotes.CARQuote.getInstance(), 1, 12, 70);

		r2r.changeToClient();
	}
}