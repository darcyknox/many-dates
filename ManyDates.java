import java.util.*;
import java.lang.Math;
import java.lang.String;

class ManyDates {

  private static boolean isValidDate = true;
  private static String errorString = "";

  /** Goes through a column and returns if all the column is
  *** a valid months column. O(n) time complexity.
  */
  // needs to return a score for how LIKELY it is that it's the months column
  // maybe a percentage of valid months
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

    String line = "";
    String[] split;

    /** Splits each line of input by the / separator
    *** Adds each number to an array by positions
    *** - all days are in the same ArrayList
    *** - all months are in the same ArrayList
    *** - all years are in the same ArrayList
    */
    while (stdin.hasNextLine()) {

      line = stdin.nextLine();

      split = line.split("/");

      n1 = Integer.parseInt(split[0]); // first number
      n2 = Integer.parseInt(split[1]); // second number
      n3 = Integer.parseInt(split[2]); // third number

      col1.add(n1); // add the first number to the ArrayList of first numbers
      col2.add(n2); // add the second number to the ArrayList of second numbers
      col3.add(n3); // add the third number to the ArrayList of third numbers

    }

    final long start = System.nanoTime();

    ManyDates test = new ManyDates();

    /**
    System.out.println("col1: " + col1.toString());
    System.out.println("col2: " + col2.toString());
    System.out.println("col3: " + col3.toString());
    */

    boolean possibleMonth1 = test.validMonths(col1);
    boolean possibleMonth2 = test.validMonths(col2);
    boolean possibleMonth3 = test.validMonths(col3);

    boolean[] possibleMonthCols = {possibleMonth1, possibleMonth2, possibleMonth3};

    /**
    System.out.println(possibleMonth1);
    System.out.println(possibleMonth2);
    System.out.println(possibleMonth3);
    */

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

    /**
    System.out.println("Day column index is " + Integer.toString(dayColIndex));
    System.out.println("Month column index is " + Integer.toString(monthColIndex));
    System.out.println("Year column index is " + Integer.toString(yearColIndex));

    System.out.println("Days " + Arrays.toString(days));
    System.out.println("Months " + Arrays.toString(months));
    System.out.println("Years " + Arrays.toString(years));
    */

    int[] daysInEachMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // array of string to store the input months as strings
    String[] monthStrings = new String[months.length];

    // get the month string depending on the month number
    // format the date if it is a 2 digit number
    for (int i = 0; i < months.length; i++) {

      errorString = "";
      isValidDate = true;

      // FILL IN OTHER LEAP YEAR CONDITIONS
      if (years[i] % 4 == 0) {
        daysInEachMonth[1] = 29;
        // System.out.println("Leap year " + Integer.toString(years[i]));
      } else if (daysInEachMonth[1] == 29){
        daysInEachMonth[1] = 28;
      }

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

      // checks whether the day is within the days in that particular month
      if (days[i] > daysInEachMonth[months[i] - 1]) {
        isValidDate = false; // DAY OUT OF RANGE FOR GIVEN MONTH
      }

      // adds 2000 to the year if between 0 and 49
      // adds 1900 to the year if between 50 and 99
      // invalid date if out of range
      if (years[i] >= 0 && years[i] < 50) {
        years[i] += 2000;
      } else if (years[i] >= 50 && years[i] < 100) {
        years[i] += 1900;
      } else if ((years[i] >= 100 && years[i] < 1753) | years[i] > 3000) {
        isValidDate = false; // YEAR OUT OF RANGE
      }

    }

    String dayString;
    for (int i = 0; i < col1.size(); i++) {
      if (days[i] < 10) {
        dayString = "0" + Integer.toString(days[i]);
      } else {
        dayString = Integer.toString(days[i]);
      }
      System.out.println(dayString + " " + monthStrings[i] + " " + Integer.toString(years[i]));
    }

    final long end = System.nanoTime();

    System.out.println((end - start)/ Math.pow(10, 9));
  }
}
