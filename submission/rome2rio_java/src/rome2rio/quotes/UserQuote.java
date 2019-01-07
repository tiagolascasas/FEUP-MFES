package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UserQuote {
	private static int hc = 0;
	private static UserQuote instance = null;

	public UserQuote() {

		if (Utils.equals(hc, 0)) {
			hc = super.hashCode();
		}
	}

	public static UserQuote getInstance() {

		if (Utils.equals(instance, null)) {
			instance = new UserQuote();
		}

		return instance;
	}

	public int hashCode() {

		return hc;
	}

	public boolean equals(final Object obj) {

		return obj instanceof UserQuote;
	}

	public String toString() {

		return "<User>";
	}
}
