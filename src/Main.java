import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many employees you want to add? ");
        int employees = scanner.nextInt();

        for (int i = 0; i < employees; i++) {
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