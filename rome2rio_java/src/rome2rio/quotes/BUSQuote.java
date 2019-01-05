package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class BUSQuote {
  private static int hc = 0;
  private static BUSQuote instance = null;

  public BUSQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static BUSQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new BUSQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof BUSQuote;
  }

  public String toString() {

    return "<BUS>";
  }
}
