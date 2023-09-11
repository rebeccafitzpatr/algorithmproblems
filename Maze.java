import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Maze {
// given a table of size n x n, where each cell is totally separated by boundaries on all sides, and a sequence of m pairs of adjacent cells in the table.
// We will process the pairs in sequence. The first pair contains two integers, n and m. 
// And the following m pairs contains to integers that are neighbouring in the table, of which we will destroy the boundary between them.

  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    int n = in.nextInt();
    int m = in.nextInt();

    ArrayList<Bag> bags = new ArrayList<Bag>();
    HashMap<Integer, int[]> pairs = new HashMap<Integer, int[]>();

    for (int i = 0; i < m; i++) {
      int first = in.nextInt();
      int second = in.nextInt();
      int x;
      int y;
      Bag xBag = null;
      Bag yBag = null;
      boolean newEntry = true;
      boolean addPair = true;

      if (first > second) {
        x = second;
        y = first;
      } else {
        x = first;
        y = second;
      }

      

      ArrayList<Bag> bagToRemove = new ArrayList<Bag>();
      for (Bag bag : bags) {
        if (bag.findInBag(x) & bag.findInBag(y)) {
          addPair = false;
        } else {}
      

        if (bag.findInBag(x)) {
          xBag = bag;
          bagToRemove.add(bag);
        }
        if (bag.findInBag(y)) {
          yBag = bag;
          bagToRemove.add(bag);
        }
      }

      if (addPair == true) {

        for (int key : pairs.keySet()) {
            if (key == x) {
              newEntry = false;
              int[] oldValues = pairs.get(key);
              int[] values = new int[oldValues.length + 1]; 
              for (int j = 0; j < oldValues.length; j++) {
                values[j] = oldValues[j];
              }
              values[values.length -1] = y;
              pairs.put(x, values);

            }
        }
        if (newEntry == true) {
          pairs.put(x, new int[] {y});

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

    out.print("+");
    for (int p = 0; p < n-1; p ++) {
      out.print("-+");
    }
    out.println("-+");
    for (int i = 0; i < n; i++) {
      out.print("|");
      for (int j = n*i; j < n * (i + 1); j++) {
        boolean boundaryBroken = false;
        out.print(" ");
        for (int key : pairs.keySet()) {
          if (key == j) {
            int[] values = pairs.get(key);
            for (int value : values) {
              if (value == j + 1) {
                out.print(" ");
                boundaryBroken = true;
              }
            }


             
          }
        }

        if (boundaryBroken == false) {
              if (j == n * (i+1) -1) {
                out.println("|");
              } else {
              out.print("|");
              }
        }
      }

      

      if ( i == n - 1 ) {
        out.print("+");
        for (int p = 0; p < n-1; p ++) {
          out.print("-+");
        }
        out.println("-+");
      } else {
      

      for (int j = n*i; j < n * (i + 1); j++) {
        boolean boundaryBroken = false;
        out.print("+");
        for (int key : pairs.keySet()) {
          if (key == j) {
            int[] values = pairs.get(key);
            for (int value : values) {
              if (value == j + n) {
                out.print(" ");
                boundaryBroken = true;
              }
            }

          }
        }

        if (boundaryBroken == false) {
          
          out.print("-");
          
        }
      }
      out.println("+");
    }
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
