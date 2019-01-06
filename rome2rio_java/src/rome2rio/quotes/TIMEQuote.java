package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TIMEQuote {
	private static int hc = 0;
	private static TIMEQuote instance = null;

	public TIMEQuote() {

		if (Utils.equals(hc, 0)) {
			hc = super.hashCode();
		}
	}

	public static TIMEQuote getInstance() {

		if (Utils.equals(instance, null)) {
			instance = new TIMEQuote();
		}

		return instance;
	}

	public int hashCode() {

		return hc;
	}

	public boolean equals(final Object obj) {

		return obj instanceof TIMEQuote;
	}

	public String toString() {

		return "<TIME>";
	}
}
