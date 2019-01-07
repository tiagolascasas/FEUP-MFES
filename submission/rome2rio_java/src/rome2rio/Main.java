package rome2rio;

import gui.MainMenu;

public class Main {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("-test")) {
			TestRome2Rio t = new TestRome2Rio();
			t.testAll();
		} else {
			MainMenu.main(null);
		}
	}
}
