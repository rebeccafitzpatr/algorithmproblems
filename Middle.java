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

    int[] numbers = new int[m];
    
    int count = 0;
    
    for( int i = 0; i < m; i ++) {
    int first = in.nextInt();
    if (first == 1) {
      int second = in.nextInt();
      numbers[count] = second;
      count++;
    } else if (first ==0) {
      mergeSort(numbers, 0, count-1);
      out.println(numbers[(count-1)/2]);
    }
  }
    //
    out.close();
  }

  public static void mergeSort(int[] a, int start, int end) {
    if (start == end) {
      return;
    }

    int mid = (start + end)/2;
    mergeSort(a, start, mid);
    mergeSort(a, mid + 1, end);
    combine(a, start, mid, end);
  }

  public static void combine(int[] a, int start, int mid, int end) {
    
    int[] aux = new int[end - start + 1];
    int m = start;
    int n = mid + 1;

    for (int i = 0; i < end - start + 1; i++) {
      if ( m > mid) {
        aux[i] = a[n];
        n++;
      } else if (n > end) {
        aux[i] = a[m];
        m++;
      } else if (a[m] < a[n]) {
        aux[i] = a[m];
        m++;
      } else {
        aux[i] = a[n];
        n++;
      }

      

      }
    for (int k = start; k <= end; k++) {
      a[k] = aux[k - start];
    }
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
