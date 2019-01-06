package rome2rio;

public class Main {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("-test")) {
			TestRome2Rio t = new TestRome2Rio();
			t.testAllValidTests();
		} else {
			Rome2Rio r2r = new Rome2Rio();

			// menu with functionalities
		}
	}
}
