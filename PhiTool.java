import java.util.Arrays;
import java.util.Stack;
public class PhiTool {

  protected static double phi=(1 + Math.sqrt(5))/2;
  protected static double[] part = { };

  protected static class PhiCut {
    double maxvalue;
    Stack <Double> parts = new Stack <Double>();

    private PhiCut(double maxvalue) {
      double volume = maxvalue/phi;
      for (int i=0; i<10; i++) {
        this.parts.push(volume);
        volume /= phi;
      }
    }

    public double pop() {
      return this.parts.pop();
    }
  }

  public static void main(String[] args) {
    PhiCut ratios = new PhiCut(100);
    double ratio=0.0;
    while(ratio<61) {
      ratio=ratios.pop();
      System.out.println(ratio);
    }
    System.out.println("100");
  }
}
