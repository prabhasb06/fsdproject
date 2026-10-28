CREATE DATABASE fsd2_salary;

USE fsd2_salary;

CREATE TABLE employee_salary (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_name VARCHAR(100) NOT NULL,
    monthly_salary DECIMAL(10,2) NOT NULL,
    working_days INT NOT NULL,
    leaves INT NOT NULL,
    hours_per_day DECIMAL(4,2) NOT NULL
);
USE fsd2_salary;

INSERT INTO employee_salary
(employee_name, monthly_salary, working_days, leaves, hours_per_day)
VALUES
('Asha', 30000, 26, 2, 8.5),
('Ravi', 28000, 26, 5, 8),
('Neha', 35000, 26, 0, 9),
('Imran', 25000, 26, 8, 7.5),
('Priya', 42000, 26, 3, 8),
('Kiran', 22000, 26, 10, 8),
('Meera', 50000, 26, 1, 9),
('Arjun', 32000, 26, 6, 8.25),
('Divya', 27000, 26, 4, 7.5),
('Sahil', 24000, 26, 12, 8);
select * from employee_salary;
WITH calculations AS (
    SELECT
        employee_id,
        employee_name,
        monthly_salary,
        working_days,
        leaves,
        hours_per_day,

        working_days - leaves AS present_days,

        ROUND(
            (working_days - leaves) * 100.0 / working_days,
            2
        ) AS attendance_percentage,

        ROUND(
            (working_days - leaves) * hours_per_day,
            2
        ) AS total_working_hours,

        CASE
            WHEN hours_per_day > 8
            THEN ROUND(
                (working_days - leaves) * (hours_per_day - 8),
                2
            )
            ELSE 0
        END AS overtime_hours
    FROM employee_salary
),

final_calculations AS (
    SELECT *,
        CASE
            WHEN attendance_percentage >= 90 THEN 0
            WHEN attendance_percentage >= 75 THEN 5
            WHEN attendance_percentage >= 60 THEN 10
            ELSE 20
        END AS deduction_rate
    FROM calculations
)

SELECT
    employee_id,
    employee_name,
    monthly_salary,
    present_days,
    attendance_percentage,
    total_working_hours,
    overtime_hours,
    deduction_rate,

    ROUND(
        monthly_salary * deduction_rate / 100,
        2
    ) AS salary_deduction,

    ROUND(
        monthly_salary -
        (monthly_salary * deduction_rate / 100),
        2
    ) AS net_salary

FROM final_calculations;