package moze_intel.projecte.shaded.org.apache.commons.math3.exception.util;

import java.io.Serializable;
import java.util.Locale;

public interface Localizable extends Serializable {
  String getSourceString();
  
  String getLocalizedString(Locale paramLocale);
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\exceptio\\util\Localizable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */