import java.lang.Math;

public class Logarithm extends Function{

  private double a, b, h, k, c;

  public Logarithm(double a, double b, double h, double k, double c) {
    this.a = a;
    this.b = b;
    this.h = h;
    this.k = k;
    this.c = c;
  }

  public String toString() { // converts the coffecients to a string of the function so it can be printed out
    String res = "";
    if (a != 1 || a != 0) {
      if (a % 1.0 == 0) {
        res += (int) a + " * ";
      } else {
        res += a + " * ";
      }
    }

    if (a != 0 && b > 0 && b != 1) {
      if (b % 1.0 == 0) {
        res += "log_" + (int) b;
      } else {
        res += "log_" + b;
      }
    }

    if (h != 1 || h != 0 || h != -1) {
      if (h % 1.0 == 0) {
        res += "(" + (int) h + "x";
      } else {
        res += "(" + h + "x";
      }
    } else if (h == 1) {
      res += "(x";
    } else if (h == 0){
      res += "(";
    } else if (h == -1) {
      res += "(-x";
    }

    if (k > 0) {
      if (k % 1.0 == 0)
        res += " + " + (int) k + ")";
      else
        res += " + " + k + ")";
    } else if (k == 0) {
      res += ")";
    } else {
      if (k % 1.0 == 0)
        res += " - " + (int) (-k) + ")";
      else
        res += " - " + (-k) + ")";
    }

    if (c > 0) {
      if (c % 1.0 == 0)
        res += " + " + (int) c;
      else
        res += " + " + c;
    } else if (c < 0) {
      if (c % 1.0 == 0)
        res += " - " + (int) (-c);
      else
        res += " - " + (-c);
    }
    
    return res;
  }

  public double evaluate(double x) { // finds the y value at a given x
    return  a * Math.log(h * x + k) / Math.log(b) + c;    
  }
}