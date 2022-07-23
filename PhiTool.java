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

    private boolean isNotEmpty() {
      return !this.parts.empty();
    }

    private double random() {
      Object[] array = this.parts.toArray();
      return Double.parseDouble((array[(int)((Math.random() * array.length))]).toString());
    }

    private double[] storeParts() {
      Object[] array = this.parts.toArray();
      part = new double[array.length];
      for(int i=0; i<array.length; i++) {
        part[i] = Double.parseDouble(array[i].toString());
      }
      return part;
    }

    private String mixPhiValues() {
      double phi1, phi2, quality;
      String phimix = "";
      while(this.isNotEmpty()) {
        quality = phi1 = this.pop();
        phimix += "\n[" + phi1 + "]";
        for(int i=0; i<10; i++) {
          phi2 = randomPart();
          if(phi2<phi1) {
            phimix += " " + phi2;
            phi1 -= phi2;
          }
        }
        phimix += String.format(" [QUAL %.0f",(100.5-(phi1/quality)*100));
        phimix += "%]";
      }
      return phimix.trim();
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
    if(interpretRandomFit(args)) {
      System.out.println(ratios.mixPhiValues());
    } else if(interpretRandom(args)) {
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

  private static double interpretSecondInputDouble(String[] args) {
    boolean searching = false;
    for(int i=0; i<args.length; i++) {
      if(isNumeric(args[i]) && searching) return Double.parseDouble(args[i]);
      if(isNumeric(args[i])) searching = true;
    }
    return 100;
  }

  private static boolean interpretRandom(String[] args) {
    for(int i=0; i<args.length; i++) {
      if(args[i].equals("--random")) return true;
    }
    return false;
  }

  private static boolean interpretRandomFit(String[] args) {
    boolean randomfit = false;
    for(int i=0; i<args.length; i++) {
      if(args[i].equals("--random-fit")) randomfit = true;
    }
    if(randomfit) {
      PhiCut paint = new PhiCut(interpretSecondInputDouble(args));
      paint.storeParts();
    }
    return randomfit;
  }

  private static double randomPart() {
    return part[(int)(Math.random() * part.length)];
  }
}
