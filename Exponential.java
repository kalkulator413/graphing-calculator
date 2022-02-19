import java.lang.Math;

public class Exponential extends Function {

  private double a, b, c;

  public Exponential(double a, double b, double c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public String toString() { // converts the coffecients to a string of the function so it can be printed out
    String res = "";

    if (a % 1.0 == 0 && a != 0) {
      if (a != 1 || a != -1)
        res += (int) a + "*";
      else if (a == -1)
        res += "-";
    } else if (a!= 0) {
      res += a + "*";
    }

    if (b % 1.0 == 0 && a != 0) {
      res += "(" + (int) b  + ")^x";
    } else if (a!= 0) {
      res += "(" + b + ")^x";
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
    return a * Math.pow(b, x) + c;
  }
}
