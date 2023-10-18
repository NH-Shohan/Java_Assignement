import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.time.Year;

enum EmployeeType {
    OFFICER, STAFF
}

public class EmployeeManagement {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int currentYear = Year.now().getValue();

        System.out.print("How many employees you want to add? ");
        int numberOfEmployees = scanner.nextInt();

        for (int i = 0; i < numberOfEmployees; i++) {
            System.out.println("\n___________________________________________");
            System.out.println("Choose employee type:");
            System.out.println("1. Officer");
            System.out.println("2. Staff");
            System.out.print("Enter your choice (1 or 2): ");
            int choice = scanner.nextInt();
            EmployeeType type = (choice == 1) ? EmployeeType.OFFICER : EmployeeType.STAFF;

            System.out.print("Enter "+ (i + 1) +" employee ID: ");
            String id = scanner.next();
            System.out.print("Enter "+ (i + 1) +" employee name: ");
            String name = scanner.next();
            System.out.print("Enter employee date of birth (dd/MM/yyyy): ");
            Date dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
            System.out.print("Enter "+ (i + 1) +" employee email: ");
            String email = scanner.next();
            System.out.print("Enter employee joining date (dd/MM/yyyy): ");
            Date joiningDate = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.next());
            System.out.println("___________________________________________\n");

            Employee employee = new Employee(id, name, dateOfBirth, email, joiningDate, type);
            employee.displayDetails();
        }

        scanner.close();
    }
}

class Employee {
    private String id;
    private String name;
    private Date dateOfBirth;
    private String email;
    private Date joiningDate;
    private EmployeeType type;

    public Employee(String id, String name, Date dateOfBirth, String email, Date joiningDate, EmployeeType type) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.joiningDate = joiningDate;
        this.type = type;
    }

    public void displayDetails() {
        int currentYear = Year.now().getValue();
        int vacationLeave = calculateLeave(type, currentYear, GetLeave.getVacationDays(type), joiningDate);
        int sickLeave = calculateLeave(type, currentYear, GetLeave.getSickDays(type), joiningDate);

        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Date of Birth: " + new SimpleDateFormat("dd/MM/yyyy").format(dateOfBirth));
        System.out.println("Email: " + email);
        System.out.println("Joining Date: " + new SimpleDateFormat("dd/MM/yyyy").format(joiningDate));
        System.out.println("Vacation Leave: " + vacationLeave);
        System.out.println("Sick Leave: " + sickLeave);
    }

    private int calculateLeave(EmployeeType targetType, int currentYear, int totalLeaveDays, Date joiningDate) {
        int joinYear = joiningDate.getYear();
        int totalDaysInYear = isLeapYear(currentYear) ? 366 : 365;

        if (type == targetType && joinYear == currentYear) {
            int lastDateOfYear = isLeapYear(currentYear) ? 366 : 365;
            double leave = ((lastDateOfYear - joiningDate.getDate() + 1) * totalLeaveDays) / totalDaysInYear;
            return (leave < 0.5) ? (int) Math.floor(leave) : (int) Math.ceil(leave);
        } else {
            return 0;
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}

class GetLeave {
    public static int getVacationDays(EmployeeType type) {
        return (type == EmployeeType.OFFICER) ? 15 : 10;
    }

    public static int getSickDays(EmployeeType type) {
        return (type == EmployeeType.OFFICER) ? 10 : 7;
    }
}
