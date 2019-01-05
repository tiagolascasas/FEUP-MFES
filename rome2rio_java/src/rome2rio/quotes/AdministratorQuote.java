package rome2rio.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class AdministratorQuote {
  private static int hc = 0;
  private static AdministratorQuote instance = null;

  public AdministratorQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static AdministratorQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new AdministratorQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof AdministratorQuote;
  }

  public String toString() {

    return "<Administrator>";
  }
}
