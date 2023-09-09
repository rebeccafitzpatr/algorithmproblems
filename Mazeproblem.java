import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Mazeproblem {
// given a table of size n x n, where each cell is totally separated by boundaries on all sides, and a sequence of m pairs of adjacent cells in the table.
// We will process the pairs in sequence. The first pair contains two integers, n and m. 
// And the following m pairs contains to integers that are neighbouring in the table, of which we will destroy the boundary between them.

  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    int n = in.nextInt();
    int m = in.nextInt();

    ArrayList<Bag> bags = new ArrayList<Bag>();

    for (int i = 0; i < m; i++) {
      int x = in.nextInt();
      int y = in.nextInt();
      Bag xBag = null;
      Bag yBag = null;
      boolean sameBag = false;

      ArrayList<Bag> bagToRemove = new ArrayList<Bag>();
      for (Bag bag : bags) {
        if (bag.findInBag(x)) {
          xBag = bag;
          bagToRemove.add(bag);
        }
        if (bag.findInBag(y)) {
          yBag = bag;
          bagToRemove.add(bag);
        }
      }
      for(Bag bag: bagToRemove) {
        bags.remove(bag);
      }

      if (xBag == null) {
        xBag = new Bag(x);
      }
      if (yBag == null) {
        yBag = new Bag(y);
      }

      Bag mergeBag = new Bag(xBag.getValues(), yBag.getValues());

      bags.remove(xBag);
      bags.remove(yBag);
      bags.add(mergeBag);

    }

    System.out.println("+-+-+-+-+");

    for (int k = 0; k < n ; k ++) {
      System.out.print("|");
      for (int l = 4*k; l < 4*(k+1); l++) {
        boolean output = false;
        
        for (Bag bag : bags) {
          if (bag.findInBag(l) & bag.findInBag(l + 1)) {
            System.out.print(" ");
            output = true;
          }
        }
        if (output == false) {
          System.out.print("|");
        }
        System.out.print(" ");
        
      }
      System.out.println("|");


      
      System.out.print("+");
      for (int l = 4*k; l < 4*(k+1); l++) {
        boolean output = false;
        
        for (Bag bag : bags) {
          if (bag.findInBag(l) & bag.findInBag(l + 4)) {
            System.out.print(" +");
            output = true;

          }
        }

        if (output == false) {
          System.out.print("-+");
        }
        System.out.print(" ");
        
      }
      System.out.println("+");

    }

    out.close();
  }

  static class FastScanner {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer("");
      String next() {
        while (!st.hasMoreTokens())
          try {
            st = new StringTokenizer(br.readLine());
          } catch (IOException e) {
        }
        return st.nextToken();
      }
      int nextInt() {
      return Integer.parseInt(next());
    }
  }

  static class Bag {
    private int[] values;

    public Bag(int value) {
      values = new int[1];
      values[0] = value;
    }

    public Bag(int[] bag1, int[] bag2) {
      int size = bag1.length + bag2.length;
      values = new int[size];
      for (int i = 0; i < bag1.length; i++) {
        values[i] = bag1[i];
      }

      for (int j = 0; j < bag2.length; j++) {
        values[j + bag1.length] = bag2[j];
      }
    }

    public int[] getValues() {
      return values;
    }

    public boolean findInBag(int valueToFind) {
      for (int value : values) {
        if (value == valueToFind) {
          return true;
        }
      }

      return false;
    }
  }
}
