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
  private static boolean validDays(List<Integer> col) {
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

      n1 = Integer.parseInt(split[0]); // first number
      n2 = Integer.parseInt(split[1]); // second number
      n3 = Integer.parseInt(split[2]); // third number

      col1.add(n1); // add the first number to the ArrayList of first numbers
      col2.add(n2); // add the second number to the ArrayList of second numbers
      col3.add(n3); // add the third number to the ArrayList of third numbers

    }

    ManyDates test = new ManyDates();

    System.out.println("col1: " + col1.toString());
    System.out.println("col2: " + col2.toString());
    System.out.println("col3: " + col3.toString());

    boolean possibleMonth1 = test.validMonths(col1);
    boolean possibleMonth2 = test.validMonths(col2);
    boolean possibleMonth3 = test.validMonths(col3);

    boolean[] possibleMonthCols = {possibleMonth1, possibleMonth2, possibleMonth3};

    System.out.println(possibleMonth1);
    System.out.println(possibleMonth2);
    System.out.println(possibleMonth3);

    // Assign the month column index to be the first possible valid month column
    int dayColIndex = 3;
    int monthColIndex = 3;
    int yearColIndex = 3;
    for (int i = 0; i < possibleMonthCols.length; i++) {
      if (possibleMonthCols[i]) {
        monthColIndex = i;
        break;
      }
    }

    Integer[] days = new Integer[col1.size()];
    Integer[] months = new Integer[col1.size()];
    Integer[] years = new Integer[col1.size()];



    // Assign the other 2 columns by checking if a column is valid for days.
    if (monthColIndex > 2) {
      System.out.println("No possible month format");
    } else if (monthColIndex == 2) {
      months = col3.toArray(months);
      if (validDays(col2)) {
        dayColIndex = 1;
        days = col2.toArray(days);
        yearColIndex = 0;
        years = col1.toArray(years);
      } else {
        dayColIndex = 0;
        days = col1.toArray(days);
        yearColIndex = 1;
        years = col2.toArray(years);
      }
    } else if (monthColIndex == 1) {
      months = col2.toArray(months);
      if (validDays(col3)) {
        dayColIndex = 2;
        days = col3.toArray(days);
        yearColIndex = 0;
        years = col1.toArray(years);
      } else {
        dayColIndex = 0;
        days = col1.toArray(days);
        yearColIndex = 2;
        years = col3.toArray(years);
      }
    } else if (monthColIndex == 0) {
      months = col1.toArray(months);
      if (validDays(col3)) {
        dayColIndex = 2;
        days = col3.toArray(days);
        yearColIndex = 1;
        years = col2.toArray(years);
      } else {
        dayColIndex = 1;
        days = col2.toArray(days);
        yearColIndex = 2;
        years = col3.toArray(years);
      }
    }

    System.out.println("Day column index is " + Integer.toString(dayColIndex));
    System.out.println("Month column index is " + Integer.toString(monthColIndex));
    System.out.println("Year column index is " + Integer.toString(yearColIndex));

    System.out.println(Arrays.toString(days));
    System.out.println(Arrays.toString(months));
    System.out.println(Arrays.toString(years));

    String[] monthStrings = new String[months.length];

    for (int i = 0; i < months.length; i++) {

      switch (months[i]){
        case 1:
          monthStrings[i] = "Jan";
          break;
        case 2:
          monthStrings[i] = "Feb";
          break;
        case 3:
          monthStrings[i] = "Mar";
          break;
        case 4:
          monthStrings[i] = "Apr";
          break;
        case 5:
          monthStrings[i] = "May";
          break;
        case 6:
          monthStrings[i] = "Jun";
          break;
        case 7:
          monthStrings[i] = "Jul";
          break;
        case 8:
          monthStrings[i] = "Aug";
          break;
        case 9:
          monthStrings[i] = "Sep";
          break;
        case 10:
          monthStrings[i] = "Oct";
          break;
        case 11:
          monthStrings[i] = "Nov";
          break;
        case 12:
          monthStrings[i] = "Dec";
          break;
        default:
          System.out.println("Month not in valid range from 1-12.");
          break;
      }

      if (years[i] >= 0 && years[i] < 50) {
        years[i] += 2000;
      } else if (years[i] >= 50 && years[i] < 100) {
        years[i] += 1900;
      }

    }

    System.out.println(Arrays.toString(monthStrings));

    for (int i = 0; i < col1.size(); i++) {
      System.out.println(Integer.toString(days[i]) + " " + monthStrings[i] + " " + Integer.toString(years[i]));
    }

  }
}
