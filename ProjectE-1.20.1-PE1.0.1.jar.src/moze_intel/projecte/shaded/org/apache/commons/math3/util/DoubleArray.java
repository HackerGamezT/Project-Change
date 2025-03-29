package moze_intel.projecte.shaded.org.apache.commons.math3.util;

public interface DoubleArray {
  int getNumElements();
  
  double getElement(int paramInt);
  
  void setElement(int paramInt, double paramDouble);
  
  void addElement(double paramDouble);
  
  void addElements(double[] paramArrayOfdouble);
  
  double addElementRolling(double paramDouble);
  
  double[] getElements();
  
  void clear();
}


/* Location:              C:\Users\Brent\Downloads\ProjectE-1.20.1-PE1.0.1.jar!\moze_intel\projecte\shaded\org\apache\commons\math\\util\DoubleArray.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */