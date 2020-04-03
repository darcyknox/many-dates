import java.util.*;
import java.lang.Math;
import java.lang.String;

class ManyDates {

  /** Goes through a column and returns if all the column is
  *** a valid months column. O(n) time complexity.
  */
  private boolean validMonths(List<Integer> col) {
    for (int i = 0; i < col.size(); i++) {
      if (col.get(i) > 12) {
        return false;
      }
    }
    return true;
  }


  /** Goes through a column and returns if all the column is
  *** a valid days column. O(n) time complexity.
  */
  private boolean validDays(List<Integer> col) {
    for (int i = 0; i < col.size(); i++) {
      if (col.get(i) > 31) {
        return false;
      }
    }
    return true;
  }


  // by rows
  private boolean[] findMonths(int[] row) {
    boolean[] result = new boolean[3];
    for (int i : row) {
      if (i <= 12) {
        result[i] = true;
      } else {
        result[i] = false;
      }
    }
    return result;
  }

  // by rows
  private boolean[] findDays(int[] row) {
    boolean[] result = new boolean[3];
    for (int i : row) {
      if (i <= 31) {
        result[i] = true;
      } else {
        result[i] = false;
      }
    }
    return result;
  }


  public static void main(String[] args) {

    Scanner stdin = new Scanner(System.in);

    int n1, n2, n3;
    ArrayList<Integer> col1 = new ArrayList<Integer>();
    ArrayList<Integer> col2 = new ArrayList<Integer>();
    ArrayList<Integer> col3 = new ArrayList<Integer>();


    /** Splits each line of input by the / separator
    *** Adds each number to an array by positions
    *** - all days are in the same ArrayList
    *** - all months are in the same ArrayList
    *** - all years are in the same ArrayList
    */
    while (stdin.hasNextLine()) {

      String line = stdin.nextLine();

      String[] split = line.split("/");

      n1 = Integer.parseInt(split[0]);
      n2 = Integer.parseInt(split[1]);
      n3 = Integer.parseInt(split[2]);

      col1.add(n1);
      col2.add(n2);
      col3.add(n3);

      System.out.print(n1 + "\t");
      System.out.print(n2 + "\t");
      System.out.print(n3 + "\n");

    }

    ManyDates test = new ManyDates();

    System.out.println(col1.toString());
    System.out.println(col2.toString());
    System.out.println(col3.toString());

    boolean possibleMonth1 = test.validMonths(col1);
    boolean possibleMonth2 = test.validMonths(col2);
    boolean possibleMonth3 = test.validMonths(col3);

    boolean[] possibleMonthCols = {possibleMonth1, possibleMonth2, possibleMonth3};

    System.out.println(possibleMonth1);
    System.out.println(possibleMonth2);
    System.out.println(possibleMonth3);

    
  }
}
