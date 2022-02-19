import java.lang.Math;

public class Polynomial extends Function {

  public double[] coeffs;

  public Polynomial(double[] input) {
    coeffs = input;
  }

  public String toString() { // converts the coffecients to a string of the function so it can be printed out
  
    String result = "";
    for (int i = 0; i < coeffs.length; i++) {
      String term = "";
      if (coeffs[i] != 1.0 || i == coeffs.length - 1) {
        if (coeffs[i] % 1.0 == 0) {
          term += (int) coeffs[i];
        } else {
          term += coeffs[i];
        }
      }
      int degree = coeffs.length - i - 1;
      if (degree > 1) {
        term += "x^" + degree;
      } else if (degree == 1) {
        term += "x";
      }

      if(coeffs[i]>=0){
        term += " + ";
      }else{
        term += " ";
      }
      
      if (coeffs[i] == 0) {
        term = "";
      }

      result += term;

    }
    if(coeffs.length>1){
      return result.substring(0, result.length() - 3);
    }else{
       return result;
    }
  }

  public double evaluate(double x) { // finds the y value at a given x
    double result = 0;
    for (int i = 0; i < coeffs.length; i++) {
      result += coeffs[i] * Math.pow(x, coeffs.length - i - 1);
    }
    return result;
  }

}