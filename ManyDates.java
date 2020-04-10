import java.util.*;
import java.lang.Math;
import java.lang.String;

class ManyDates {

  private static boolean isValidDate;
  private static String errorString;

  // returns the percentage of valid months in a column
  private static double monthScore(List<Integer> col) {
    double countValidMonths = 0;
    for (int i = 0; i < col.size(); i++) {
      if (col.get(i) <= 12) {
        countValidMonths++;
      }
    }
    System.out.print("Score: ");
    System.out.println(countValidMonths/col.size());
    return countValidMonths/col.size();
  }

  // returns the percentage of valid days in a column
  private static double dayScore(List<Integer> col) {
    double countValidDays = 0;
    for (int i = 0; i < col.size(); i++) {
      if (col.get(i) <= 31) {
        countValidDays++;
      }
    }
    System.out.print("Score: ");
    System.out.println(countValidDays/col.size());
    return countValidDays/col.size();
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

    double col1MonthScore = monthScore(col1);
    double col2MonthScore = monthScore(col2);
    double col3MonthScore = monthScore(col3);

    double[] monthScores = {col1MonthScore, col2MonthScore, col3MonthScore};

    int monthColIndex = 0;
    double maxScore = monthScores[monthColIndex];
    for (int i = 1; i < 3; i++) {
      if (monthScores[i] > maxScore) {
        monthColIndex = i;
        maxScore = monthScores[i];
      }
    }

    Integer[] days = new Integer[col1.size()];
    Integer[] months = new Integer[col1.size()];
    Integer[] years = new Integer[col1.size()];

    // Assign the other 2 columns by checking if a column is valid for days.
    if (monthColIndex == 2) {
      months = col3.toArray(months);
      if (dayScore(col2) > dayScore(col1)) {
        days = col2.toArray(days);
        years = col1.toArray(years);
      } else {
        days = col1.toArray(days);
        years = col2.toArray(years);
      }
    } else if (monthColIndex == 1) {
      months = col2.toArray(months);
      if (dayScore(col3) > dayScore(col1)) {
        days = col3.toArray(days);
        years = col1.toArray(years);
      } else {
        days = col1.toArray(days);
        years = col3.toArray(years);
      }
    } else if (monthColIndex == 0) {
      months = col1.toArray(months);
      if (dayScore(col3) > dayScore(col2)) {
        days = col3.toArray(days);
        years = col2.toArray(years);
      } else {
        days = col2.toArray(days);
        years = col3.toArray(years);
      }
    }

    int[] daysInEachMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // array to store our number months converted to strings
    String[] monthStrings = new String[months.length];

    // Checks and Formatting
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

      // converting month as number its 3 letter string
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
        errorString = " - INVALID: Day out of range for given month";
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
        errorString = " - INVALID: Year out of range";
      }

      String dayString;
      if (!isValidDate) {
        System.out.println(Integer.toString(days[i]) + "/" + Integer.toString(months[i]) + "/" + Integer.toString(years[i]) + errorString);
      } else {
        if (days[i] < 10) {
          dayString = "0" + Integer.toString(days[i]);
        } else {
          dayString = Integer.toString(days[i]);
        }
        System.out.println(dayString + " " + monthStrings[i] + " " + Integer.toString(years[i]));
      }

    }

    final long end = System.nanoTime();

    System.out.println((end - start)/ Math.pow(10, 9));
  }
}
