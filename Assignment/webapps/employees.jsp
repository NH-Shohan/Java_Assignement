<%@ taglib uri="https://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Employee Details</title>
</head>
<body>
  <h1>Employee Details</h1>
  <c:forEach var="employee" items="${employees}">
      <p>ID: ${employee.id}</p>
      <p>Name: ${employee.name}</p>
      <p>Date of Birth: ${employee.dateOfBirth}</p>
      <p>Email: ${employee.email}</p>
      <p>Joining Date: ${employee.joiningDate}</p>
      <p>Vacation Leave: ${employee.vacationLeave}</p>
      <p>Sick Leave: ${employee.sickLeave}</p>
  </c:forEach>
</body>
</html>