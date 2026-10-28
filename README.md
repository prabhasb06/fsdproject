# Employee Salary Management App

An academic Java web application that calculates employee attendance and salary summaries, displays a Servlet report, and stores submitted records in MySQL.

## Stack

- Java 25 / Eclipse Enterprise
- Jakarta Servlet API and Apache Tomcat 10.1
- MySQL and MySQL Connector/J
- HTML/CSS and JDBC (`PreparedStatement`)

## Database setup

1. In MySQL Workbench, open and run `employee_salary.sql` to create `fsd2_salary`, its `employee_salary` table, ten sample records, and the report queries.
2. Set the `FSD2_DB_PASSWORD` environment variable to your local MySQL password. Optionally set `FSD2_DB_USER` (defaults to `root`). Do not commit local credentials.
3. Import this folder into Eclipse as an existing project. Configure a Tomcat 10.1 server and run the project.
4. Open `http://localhost:8080/EmployeeSalaryApp/` and submit the form. Submitted web records are inserted into the database.

## Salary rules used by this project

- Present days = working days minus leaves.
- Attendance >= 90%: 0% deduction; >= 75%: 5%; >= 60%: 10%; otherwise: 20%.
- Overtime is hours above eight per present day.

These are project assumptions for demonstration, not payroll or legal guidance.

## Project structure

```text
src/main/java/com/fsd2/salary/   Java calculation, Servlet, JDBC connection and DAO
src/main/webapp/                 HTML form and WEB-INF dependency
employee_salary.sql              Schema, sample data and report queries
```
