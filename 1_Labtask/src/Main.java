import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.Year;

public class Main {
    public static void main(String[] args) {
        // Example usage
        calculateLeave("Staff", "01/01/2023");
        calculateLeave("Staff", "12/12/2023");
        calculateLeave("Officer", "01/01/2023");
        calculateLeave("Officer", "12/12/2023");
    }

    public static void calculateLeave(String employeeType, String joiningDateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date joiningDate = dateFormat.parse(joiningDateStr);
            LocalDate startDate = LocalDate.ofInstant(joiningDate.toInstant(), java.time.ZoneId.systemDefault());
            int joiningYear = startDate.getYear();

            // Check if the year is a leap year
            int totalDaysInYear = Year.of(joiningYear).length();

            LocalDate endDateOfYear = LocalDate.of(joiningYear, 12, 31);

            long totalDays = startDate.datesUntil(endDateOfYear.plusDays(1)).count();

            int vacationLeave;
            int sickLeave;

            if ("Staff".equals(employeeType)) {
                vacationLeave = (totalDays * 10 < totalDaysInYear / 2.0) ? (int) Math.floor((totalDays * 10.0) / totalDaysInYear) : (int) Math.ceil((totalDays * 10.0) / totalDaysInYear);
                sickLeave = (totalDays * 7 < totalDaysInYear / 2.0) ? (int) Math.floor((totalDays * 7.0) / totalDaysInYear) : (int) Math.ceil((totalDays * 7.0) / totalDaysInYear);
            } else if ("Officer".equals(employeeType)) {
                vacationLeave = (totalDays * 15 < totalDaysInYear / 2.0) ? (int) Math.floor((totalDays * 15.0) / totalDaysInYear) : (int) Math.ceil((totalDays * 15.0) / totalDaysInYear);
                sickLeave = (totalDays * 10 < totalDaysInYear / 2.0) ? (int) Math.floor((totalDays * 10.0) / totalDaysInYear) : (int) Math.ceil((totalDays * 10.0) / totalDaysInYear);
            } else {
                System.out.println("Invalid employee type");
                return;
            }

            System.out.println("Employee Type: " + employeeType);
            System.out.println("Joining Date: " + joiningDateStr);
            System.out.println("Vacation Leave: " + vacationLeave);
            System.out.println("Sick Leave: " + sickLeave);
            System.out.println("------------------------");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
