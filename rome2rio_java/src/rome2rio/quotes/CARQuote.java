package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CARQuote {
  private static int hc = 0;
  private static CARQuote instance = null;

  public CARQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static CARQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new CARQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof CARQuote;
  }

  public String toString() {

    return "<CAR>";
  }
}
