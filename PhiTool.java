import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;
public class PhiTool {

  protected final static double phi=(1 + Math.sqrt(5))/2;
  protected static double[] part = { };

  protected static class PhiCut {
    double maxvalue;
    Stack <Double> parts = new Stack <Double>();

    private PhiCut(double maxvalue) {
      double volume = maxvalue;
      for (int i=0; i<11; i++) {
        this.parts.push(volume);
        volume /= phi;
      }
    }

    private double pop() {
      return this.parts.pop();
    }

    private boolean empty() {
      return this.parts.empty();
    }

    private double random() {
      Object[] array = this.parts.toArray();
      return Double.parseDouble((array[(int)((Math.random() * array.length))]).toString());
    }

    public String toString() {
      String phistack = "";
      ListIterator<Double> ListIterator = this.parts.listIterator(this.parts.size());
      while (ListIterator.hasPrevious()) {
        phistack += (ListIterator.previous() + "\n");
      }
      return phistack.trim();
    }
  }

  public static void main(String[] args) {
    PhiCut ratios = new PhiCut(interpretInputDouble(args));
    if(interpretRandom(args)) {
      System.out.println(ratios.random());
    } else {
      System.out.println(ratios);
    }
  }

  private static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch(NumberFormatException e) {
      return false;
    }
  }

  private static double interpretInputDouble(String[] args) {
    for(int i=0; i<args.length; i++) {
      if(isNumeric(args[i])) return Double.parseDouble(args[i]);
    }
    return 100;
  }

  private static boolean interpretRandom(String[] args) {
    for(int i=0; i<args.length; i++) {
      if(args[i].equals("--random")) return true;
    }
    return false;
  }
}
