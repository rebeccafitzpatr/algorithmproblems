import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Middle {
  public static void main(String[] args) {
    FastScanner in = new FastScanner();
    PrintWriter out = new PrintWriter(System.out);
    
    int m = in.nextInt();

    int[] numbers = new int[1];
    int count = 0;


    for (int i = 0; i < m; i++) {
      int first = in.nextInt();
      if (first == 1) {
        int second = in.nextInt();
        if (count < numbers.length) {
          numbers[count] = second;
          
        } else {
          int[] newArray = new int[numbers.length*2];
          for (int j = 0; j < numbers.length; j++) {
            newArray[j] = numbers[j];
            
            
          }
          newArray[count] = second;
          numbers = newArray;
        }
        count++;

      } else if (first == 0) {
        
        out.println(findMiddle(numbers, count));
      }
    }
    // YOUR CODE GOES HERE.
    //
    out.close();
  }

  public static int findMiddle(int[] numbers, int count) {
    

      int size = count;
      int output = 0;
  
      for (int i = 0; i <= (size - 1)/2; i++) {
        int min = i;
  
        for (int j = i; j < size; j++) {
          if (numbers[j] < numbers[min]) {
            min = j;
          }
        }
        int temp = numbers[min];
        numbers[min] = numbers[i];
        numbers[i] = temp;
        output = temp;
  
      }
  
      return output;
  
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
