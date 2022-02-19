/* ------------
Goal: graph functions by taking in the number of functions
and the necessary coffecients/values for each as well as the bounds 
---------------*/

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;
import java.lang.Throwable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Main extends JFrame {

  static ArrayList<Function> functions = new ArrayList<Function>();
  static final double STEP = 0.01;
  static ArrayList<Double> xValues;
  static double minX, minY, maxX, maxY; // bounds of graph

  static Color[] colors = new Color[]{Color.red, Color.orange, Color.green, Color.blue, Color.pink}; // different colors for distinction between graphs

  static int WINDOW_X = 900, WINDOW_Y = 600;

  static int dotX = 5, dotY = 5;

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);

    System.out.println("How many functions do you want to graph?");
    int num_funcs = console.nextInt();
    for (int i = 0; i < num_funcs; i++) {
      Function f = getFunction(console); // creates a function of the type specified
                                         // uses console to take in the necessary    coffecients 
      functions.add(f); // adds it to the functions arraylist
    }
    System.out.println();
    // takes in the bounds for the graph 
    minX = getDouble(console, "Input minimum x value for the graph:");
    maxX = getDouble(console, "Input maximum x value for the graph:");
    xValues = getXValuesInRange(minX, maxX, STEP); // creates list of all x values

    minY = getDouble(console, "Input minimum y value for the graph:");
    maxY = getDouble(console, "Input maximum y value for the graph:");

    for (Function f : functions) { // to string method to convert the inputs to equations
      System.out.println(f.toString());
    }

    Main test = new Main();
    test.setSize(WINDOW_X, WINDOW_Y);
    test.setResizable(false);
    test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    test.setVisible(true);
  }
  public static double getDouble(Scanner console, String msg) {
    System.out.println(msg);
    return console.nextDouble();
  }
  public static Function getFunction(Scanner console) { //creates a function

    Function p;
    
    System.out.print("Which type of function would you like?\n1. Polynomial\n2. Exponential\n3. Logarithmic\n"); // 3 options -> polynomial, exponential and log

    int fType = console.nextInt();

    if (fType == 1) { // creates a function based on the type and specifications
      
      System.out.println("What degree polynomial would you like?");

      int degree = console.nextInt(); // takes in necessary coffecients for a polynomial functions
      double[] coefficients = new double[degree + 1];

      System.out.println("Enter the " + coefficients.length + " coefficients of the function in standard form:");

      for (int i = 0; i < coefficients.length; i ++) {
        coefficients[i] = console.nextDouble();
      }

      p = new Polynomial(coefficients);
      
    } else if (fType == 2) {
      // takes in necssary coffecients for an exponential function
      System.out.println("Enter the values of a, b, and c if the function is in the form y = a*b^x + c");
      double a = console.nextDouble();
      double b = console.nextDouble();
      double c = console.nextDouble();

      p = new Exponential(a, b, c);

    } else if (fType == 3) {
      //takes in necessary values for a log function
      System.out.println("Enter the values of a, b, h, k, and c if the function is in the form y = a * log_b(hx + k) + c");

      double a = console.nextDouble();
      double b = console.nextDouble();
      double h = console.nextDouble();
      double k = console.nextDouble();
      double c = console.nextDouble();

      if (b < 0 || b == 1) { //checks for impossible input
        System.out.println("Cant have a negative number or 1 as the base");
        throw new ArithmeticException();
      }

      p = new Logarithm(a, b, h, k, c);

    } else {
      p = new Polynomial(new double[]{0});
    }

    return p;
  }
  // calculates the x values based on the min/max x and the step
  public static ArrayList<Double> getXValuesInRange(double left, double right, double step) {
    ArrayList<Double> result = new ArrayList<Double>();
    for (double i = left; i <= right; i += step) {
      result.add(i);
    }
    return result;
  }
  // draws the grap
  public void paint(Graphics g) {
    for (int i = 0; i < functions.size(); i ++) {
      Function f = functions.get(i);
      if (i < colors.length)
        g.setColor(colors[i]);
      else
        g.setColor(new Color((int) (200 * Math.random()), (int) (200 * Math.random()), (int) (200 * Math.random())));
      g.drawString(f.toString(), 20, 50 + 30 * i);
      graph(g, f);
    }

    // draw axes
    g.setColor(Color.black);
    g.drawLine(scaleX(0), 0, scaleX(0), WINDOW_Y - 1);
    g.drawLine(0, scaleY(0), WINDOW_X - 1, scaleY(0));

    // draw tickmarks
    double graph_width = maxX - minX;
    int x_tick_length = (int) Math.pow(10, (int)(Math.log(graph_width) / Math.log(10)) - 1);
    int minval = (int) -maxX;
    for (int i = minval; i < maxX; i += x_tick_length) {
      g.drawLine(scaleX(i), scaleY(0) - 5, scaleX(i), scaleY(0) + 5);
      g.drawString(String.valueOf(i), scaleX(i), scaleY(0) + 15);
    }
    double graph_height = maxY - minY;
    int y_tick_length = (int) Math.pow(10, (int) ((Math.log(graph_height) / Math.log(10))) - 1);
    int minval2 = (int) -maxY;
    for (int i = minval2; i < maxY; i += y_tick_length) {
      if(i !=0){
      g.drawLine(scaleX(0) - 5, scaleY(i), scaleX(0) + 5, scaleY(i));
      g.drawString(String.valueOf(i), scaleX(0) + 10, scaleY(i) + 5);
      }
    }

  }

  public void graph(Graphics g, Function f) {
    for (double x : xValues) {
      double y = f.evaluate(x);
      int scaledX = scaleX(x);
      int scaledY = scaleY(y);
      if (scaledX >= 0 && scaledX < WINDOW_X && scaledY >= 0 && scaledY < WINDOW_Y) {
        g.fillOval(scaledX, scaledY, dotX, dotY);
      }
    }

  }

  public int scaleX(double x) {
    // scaling minX -> maxX to 0 to 1200
    return (int) Math.round(WINDOW_X * (x - minX) / (maxX - minX));
  }

  public int scaleY(double y) {
    // scaling minY -> maxY to 900 to 0
    return (int) Math.round(WINDOW_Y - WINDOW_Y * (y - minY) / (maxY - minY));
  }
}