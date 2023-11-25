import java.time.LocalDate;
import java.time.Period;
import java.time.Year;

public class Employee {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private LocalDate joiningDate;

    private double vacationLeave;
    private double sickLeave;

    public Employee(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.joiningDate = joiningDate;
    }
    public void setVacationLeave(double vacationLeave) {
        this.vacationLeave = vacationLeave;
    }

    public void setSickLeave(double sickLeave) {
        this.sickLeave = sickLeave;
    }

    public static boolean isLeapYear(int year) {
        Year y = Year.of(year);
        return y.isLeap();
    }

    public double calculateLeave(LeaveType leaveType, int totalLeaveDays) {
        if (joiningDate == null) {
            throw new IllegalStateException("Joining date is not set.");
        }
        LocalDate endDateOfYear = LocalDate.of(joiningDate.getYear(), 12, 31);
        if (joiningDate.isAfter(endDateOfYear)) {
            throw new IllegalArgumentException("Joining date cannot be after the end of the year.");
        }
        int daysBetween = Period.between(joiningDate, endDateOfYear).getDays() + 1;
        int totalDaysInYear = isLeapYear(joiningDate.getYear()) ? 366 : 365;
        double leaveDays = (double) daysBetween * totalLeaveDays / totalDaysInYear;
        return leaveDays > 0.5 ? Math.ceil(leaveDays) : Math.floor(leaveDays);
    }


    public static class Officer extends Employee {
        public Officer(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate) {
            super(id, name, dateOfBirth, email, joiningDate);
        }

        @Override
        public double calculateLeave(LeaveType leaveType, int totalLeaveDays) {
            if (leaveType == LeaveType.VACATION) {
                totalLeaveDays = 15;
            } else {
                totalLeaveDays = 10;
            }
            return super.calculateLeave(leaveType, totalLeaveDays);
        }
    }

    public static class Staff extends Employee {
        public Staff(int id, String name, LocalDate dateOfBirth, String email, LocalDate joiningDate) {
            super(id, name, dateOfBirth, email, joiningDate);
        }

        @Override
        public double calculateLeave(LeaveType leaveType, int totalLeaveDays) {
            if (leaveType == LeaveType.VACATION) {
                totalLeaveDays = 10;
            } else {
                totalLeaveDays = 7;
            }
            double leave= super.calculateLeave(leaveType, totalLeaveDays);
            return leave;
        }
    }

    public double getVacationLeave() {
        return vacationLeave;
    }



    public double getSickLeave() {
        return sickLeave;
    }

    public String getInfo() {
        return "ID: " + id + ", Name: " + name + ", Date of Birth: " + dateOfBirth + ", Email: " + email + ", Joining Date: " + joiningDate + ", Vacation Leave: " + vacationLeave + ", Sick Leave: " + sickLeave;
    }

}
