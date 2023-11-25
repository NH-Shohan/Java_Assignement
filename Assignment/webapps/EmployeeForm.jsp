<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
  <title>Employee Form</title>
</head>
<body>
  <h1>Employee Form</h1>
  <form action="employee" method="post">
      <label for="id">ID:</label><br>
      <input type="text" id="id" name="id"><br>
      <label for="name">Name:</label><br>
      <input type="text" id="name" name="name"><br>
      <label for="dob">Date of Birth:</label><br>
      <input type="date" id="dob" name="dob"><br>
      <label for="email">Email:</label><br>
      <input type="text" id="email" name="email"><br>
      <label for="joiningDate">Joining Date:</label><br>
      <input type="date" id="joiningDate" name="joiningDate"><br>
      <label for="typeOfEmp">Type of Employee:</label><br>
      <select id="typeOfEmp" name="typeOfEmp">
        <option value="1">Officer</option>
        <option value="2">Staff</option>
      </select><br>
      <input type="submit" value="Submit">
  </form>
</body>
</html>
