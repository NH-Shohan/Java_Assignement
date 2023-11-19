import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class FormsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Nahim Hossain Shohan</h1>");
        out.println("<form method='post'>");
        out.println("<fieldset>");
        out.println("<legend>Account Information:</legend>");
        out.println("<table style='height: 200px'>");
        out.println("<tbody>");
        out.println("<tr>");
        out.println("<td>");
        out.println("<label><b>Username:</b></label>");
        out.println("</td>");
        out.println("<td><input type='text' name='uname' required /></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("<label><b>Password:</b></label>");
        out.println("</td>");
        out.println("<td><input type='password' name='password' required /></td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("<label><b>Confirm Password:</b></label>");
        out.println("</td>");
        out.println("<td>");
        out.println("<input type='password' name='confirmPassword' required />");
        out.println("</td>");
        out.println("</tr>");
        out.println("</tbody>");
        out.println("</table>");
        out.println("<input type='submit' name='submit' value='Submit' required />");
        out.println("</fieldset>");
        out.println("</form>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        String username = request.getParameter("uname");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        PrintWriter out = response.getWriter();

        if (username.equals("admin") && password.equals("123456") && confirmPassword.equals("123456")){
            HttpSession session = request.getSession(true);
            response.sendRedirect("home");
            session.setAttribute("uname", username);
        }
        out.println("<h1>Thanks For Submitting! - Shohan</h1>");
    }
}