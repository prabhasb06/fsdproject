package com.fsd2.salary;

import java.util.Scanner;

public class EmployeeSalary {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);

	        System.out.print("Enter employee name: ");
	        String name = sc.nextLine();

	        System.out.print("Enter monthly salary: ");
	        double salary = sc.nextDouble();

	        System.out.print("Enter total working days: ");
	        int workingDays = sc.nextInt();

	        System.out.print("Enter leaves taken: ");
	        int leaves = sc.nextInt();

	        System.out.print("Enter working hours per day: ");
	        double hoursPerDay = sc.nextDouble();

	        if (workingDays <= 0 || leaves < 0 ||
	                leaves > workingDays || hoursPerDay < 0) {

	            System.out.println("Invalid input.");
	            sc.close();
	            return;
	        }

	        int presentDays = workingDays - leaves;

	        double attendancePercentage =
	                (presentDays * 100.0) / workingDays;

	        double totalWorkingHours =
	                presentDays * hoursPerDay;

	        int deductionRate;

	        if (attendancePercentage >= 90) {
	            deductionRate = 0;
	        } else if (attendancePercentage >= 75) {
	            deductionRate = 5;
	        } else if (attendancePercentage >= 60) {
	            deductionRate = 10;
	        } else {
	            deductionRate = 20;
	        }

	        double salaryDeduction =
	                salary * deductionRate / 100;

	        double netSalary =
	                salary - salaryDeduction;

	        double overtimeHours = 0;

	        if (hoursPerDay > 8) {
	            overtimeHours =
	                    presentDays * (hoursPerDay - 8);
	        }

	        System.out.println("\n----- Employee Salary Report -----");
	        System.out.println("Employee Name: " + name);
	        System.out.println("Present Days: " + presentDays);
	        System.out.printf(
	                "Attendance: %.2f%%%n",
	                attendancePercentage
	        );
	        System.out.printf(
	                "Total Working Hours: %.2f%n",
	                totalWorkingHours
	        );
	        System.out.println(
	                "Deduction Rate: " + deductionRate + "%"
	        );
	        System.out.printf(
	                "Salary Deduction: %.2f%n",
	                salaryDeduction
	        );
	        System.out.printf(
	                "Net Salary: %.2f%n",
	                netSalary
	        );
	        System.out.printf(
	                "Overtime Hours: %.2f%n",
	                overtimeHours
	        );

	        sc.close();

	}

}
