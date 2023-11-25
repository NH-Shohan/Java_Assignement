import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/EmployeeForm.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get form data
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            LocalDate dob = LocalDate.parse(request.getParameter("dob"));
            String email = request.getParameter("email");
            LocalDate joiningDate = LocalDate.parse(request.getParameter("joiningDate"));
            int typeOfEmp = Integer.parseInt(request.getParameter("typeOfEmp"));

            // Create Employee object
            Employee employee;
            if (typeOfEmp == 1) {
                employee = new Employee.Officer(id, name, dob, email, joiningDate);
            } else {
                employee = new Employee.Staff(id, name, dob, email, joiningDate);
            }

            // Calculate leave days
            double vacationLeave = employee.calculateLeave(LeaveType.VACATION, typeOfEmp == 1 ? 15 : 10);
            double sickLeave = employee.calculateLeave(LeaveType.SICK, typeOfEmp == 1 ? 10 : 7);

            // Set leave days
            employee.setVacationLeave(vacationLeave);
            employee.setSickLeave(sickLeave);

            // Store Employee object in request scope
            request.setAttribute("employee", employee);

            // Forward to JSP to display employee details
            RequestDispatcher dispatcher = request.getRequestDispatcher("employees.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException | DateTimeParseException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
