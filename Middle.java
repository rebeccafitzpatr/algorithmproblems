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

    LinkedList nodes = new LinkedList();
    int count = 0;
    int tally = 0;


    int first = in.nextInt();
    int second = in.nextInt();
    Node node = new Node(second);
    nodes.add(node);


    for (int i = 1; i < m; i++) {
      first = in.nextInt();
      if (first == 1) {
        second = in.nextInt();
        node = new Node(second);
        tally = nodes.add(node) + tally;

        count ++;

        if (count == 2) {
          if (tally == 2) {
            nodes.increaseMiddle();
          } else if (tally < 0) {
            nodes.decreaseMiddle();
          }

          tally= 0;
          count = 0;
        } 

      } else if (first == 0) {
        
        out.println(nodes.getMiddleValue());
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
 

  static class LinkedList {
    

    int size = 0;

    Node first = null;
    Node last = null;
    Node middle = null;

    public Node getLast() {
      return last;
    }

    public Node getFirst() {
      return first;
    }

    public int getMiddleValue() {
      return middle.getValue();
    }

    public void increaseMiddle() {
      middle = middle.getNext();
    }

    public void decreaseMiddle() {
      middle = middle.getBefore();
    }

    public int add (Node node) {
      if (size ==0) {
        this.first = node;
        this.last = node;
        this.middle = node;
        size ++;
        return 0;
      } else {
        Node check = middle;
        if (node.getValue() > middle.getValue()) {
          while (!(check.equals(last)) && check.getNext().getValue() < node.getValue()) {
            check = check.getNext();

          }
          append(check, node);
          return 1;
        } else if (node.getValue() < middle.getValue()) {
          while (!(check.equals(first)) && check.getBefore().getValue() > node.getValue()) {
            check = check.getBefore();
          }
          prepend(check, node);

          return -1;
        } else {
          append(middle, node);
          return 1;
        }
      }
    }

    public void append(Node prevNode, Node newNode) {
        if (prevNode.getNext() != null) {
          newNode.setNext(prevNode.getNext());
          prevNode.getNext().setBefore(newNode);
          prevNode.setNext(newNode);
          newNode.setBefore(prevNode);
          

        } else {
          prevNode.setNext(newNode);
          newNode.setBefore(prevNode);
          newNode.setNext(null);
          this.last = newNode;
        }
        size++;

      }
      
    

    public void prepend(Node afterNode, Node newNode) {
      if (getFirst().equals(afterNode)) {
        this.first = newNode;
        newNode.setNext(afterNode);
        afterNode.setBefore(newNode);
      
        size ++;
      } else {
        if (afterNode.getBefore() != null) {
          newNode.setNext(afterNode);
          afterNode.getBefore().setNext(newNode);
          newNode.setBefore(afterNode.getBefore());
          afterNode.setBefore(newNode);

        } else {
          newNode.setNext(afterNode);
          afterNode.setBefore(newNode);
          newNode.setBefore(null);
        }

        size++;

      }
      
    }
    

  }
  static class Node {
    private Node next;
    private Node before;
    private int value;

    public Node(int value) {
      this.value = value;
      this.next = null;
      this.before = null;
    }

    public void setNext(Node next) {
      this.next = next;
    }

    public Node getNext() {
      return next;
    }
    public void setBefore(Node before) {
      this.before = before;
    }

    public Node getBefore() {
      return before;
    }

    public int getValue() {
      return value;
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
