package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ANYQuote {
  private static int hc = 0;
  private static ANYQuote instance = null;

  public ANYQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ANYQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ANYQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ANYQuote;
  }

  public String toString() {

    return "<ANY>";
  }
}
