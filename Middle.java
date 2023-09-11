import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Middle {
  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    
    int m = in.nextInt();

    ArrayList<Integer> numbers = new ArrayList<Integer>();

    for (int i = 0; i < m; i++) {
      int first = in.nextInt();
      if (first == 1) {
        int second = in.nextInt();
        if (numbers.size() == 0) {
          numbers.add(second);

        } else {
          ArrayList<Integer> newNumbers = addNumber(numbers, second);
          numbers = newNumbers;
        }

      } else if (first == 0) {
        int middle = (numbers.size() - 1 ) /2;
        out.println(numbers.get(middle));
      }
    }
    // YOUR CODE GOES HERE.
    //
    out.close();
  }

  public static ArrayList<Integer> addNumber(ArrayList<Integer> oldNumbers, int newNumber) {
    ArrayList<Integer> newNumbers = new ArrayList<Integer>();
    boolean added = false;

    int i = 0;
    while (i < oldNumbers.size()) {
      
      if (!added) {
        if (oldNumbers.get(i) <= newNumber) {

          newNumbers.add(oldNumbers.get(i));
          i++;
        } else {
          newNumbers.add(newNumber);
          added = true;
        }
      } else {
        newNumbers.add(oldNumbers.get(i));
        i++;
      }

      
    }
    if (added == false) {
      newNumbers.add(newNumber);
    }

    

    return newNumbers;
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
}
