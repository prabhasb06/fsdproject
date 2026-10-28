package com.fsd2.salary;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/salary-report")
public class SalaryReportServlet extends HttpServlet {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("employeeName");
        double salary = Double.parseDouble(request.getParameter("salary"));
        int workingDays = Integer.parseInt(request.getParameter("workingDays"));
        int leaves = Integer.parseInt(request.getParameter("leaves"));
        double hoursPerDay =
                Double.parseDouble(request.getParameter("hoursPerDay"));

        try {
            EmployeeDAO.insertEmployee(
                    name, salary, workingDays, leaves, hoursPerDay
            );
        } catch (Exception e) {
            throw new ServletException("Could not save employee data", e);
        }

        int presentDays = workingDays - leaves;
        double attendance = presentDays * 100.0 / workingDays;

        int deductionRate;

        if (attendance >= 90) {
            deductionRate = 0;
        } else if (attendance >= 75) {
            deductionRate = 5;
        } else if (attendance >= 60) {
            deductionRate = 10;
        } else {
            deductionRate = 20;
        }

        double deduction = salary * deductionRate / 100;
        double netSalary = salary - deduction;
        double totalHours = presentDays * hoursPerDay;

        double overtimeHours = 0;

        if (hoursPerDay > 8) {
            overtimeHours = presentDays * (hoursPerDay - 8);
        }

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();


out.println("""
<!DOCTYPE html>
<html>
<head>
    <title>Salary Report</title>
    <style>
        body {
            margin: 0;
            min-height: 100vh;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .report {
            width: 500px;
            background: white;
            padding: 35px;
            border-radius: 18px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.25);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .row {
            display: flex;
            justify-content: space-between;
            padding: 13px;
            margin: 8px 0;
            background: #f3f5ff;
            border-radius: 8px;
        }

        .label {
            color: #555;
            font-weight: bold;
        }

        .value {
            color: #333;
        }

        .net {
            background: #d9fbe5;
            color: #08752d;
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="report">
<h1>Employee Salary Report</h1>
""");

out.println("<div class='row'><span class='label'>Employee</span><span class='value'>"
        + name + "</span></div>");

out.println("<div class='row'><span class='label'>Present Days</span><span class='value'>"
        + presentDays + "</span></div>");

out.printf("<div class='row'><span class='label'>Attendance</span><span class='value'>%.2f%%</span></div>%n",
        attendance);

out.printf("<div class='row'><span class='label'>Working Hours</span><span class='value'>%.2f</span></div>%n",
        totalHours);

out.println("<div class='row'><span class='label'>Deduction Rate</span><span class='value'>"
        + deductionRate + "%</span></div>");

out.printf("<div class='row'><span class='label'>Deduction</span><span class='value'>%.2f</span></div>%n",
        deduction);

out.printf("<div class='row net'><span class='label'>Net Salary</span><span class='value'>%.2f</span></div>%n",
        netSalary);

out.printf("<div class='row'><span class='label'>Overtime Hours</span><span class='value'>%.2f</span></div>%n",
        overtimeHours);

out.println("""
</div>
</body>
</html>
""");
    }
}
