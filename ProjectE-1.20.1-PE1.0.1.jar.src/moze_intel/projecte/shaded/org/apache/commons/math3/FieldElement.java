package moze_intel.projecte.shaded.org.apache.commons.math3;

import moze_intel.projecte.shaded.org.apache.commons.math3.exception.MathArithmeticException;
import moze_intel.projecte.shaded.org.apache.commons.math3.exception.NullArgumentException;

public interface FieldElement<T> {
  T add(T paramT) throws NullArgumentException;
  
  T subtract(T paramT) throws NullArgumentException;
  
  T negate();
  
  T multiply(int paramInt);
  
  T multiply(T paramT) throws NullArgumentException;
  
  T divide(T paramT) throws NullArgumentException, MathArithmeticException;
  
  T reciprocal() throws MathArithmeticException;
  
  Field<T> getField();
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math3\FieldElement.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */