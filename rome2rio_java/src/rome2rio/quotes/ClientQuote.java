package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ClientQuote {
	private static int hc = 0;
	private static ClientQuote instance = null;

	public ClientQuote() {

		if (Utils.equals(hc, 0)) {
			hc = super.hashCode();
		}
	}

	public static ClientQuote getInstance() {

		if (Utils.equals(instance, null)) {
			instance = new ClientQuote();
		}

		return instance;
	}

	public int hashCode() {

		return hc;
	}

	public boolean equals(final Object obj) {

		return obj instanceof ClientQuote;
	}

	public String toString() {

		return "<Client>";
	}
}
