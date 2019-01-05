package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PLANEQuote {
  private static int hc = 0;
  private static PLANEQuote instance = null;

  public PLANEQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static PLANEQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new PLANEQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof PLANEQuote;
  }

  public String toString() {

    return "<PLANE>";
  }
}
