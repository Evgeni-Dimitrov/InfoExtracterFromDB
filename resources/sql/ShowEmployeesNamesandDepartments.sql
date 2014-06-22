SELECT `first_name`,`last_name`, dept_name
      FROM employees
      JOIN dept_emp
      ON dept_emp.emp_no = employees.emp_no
      JOIN departments 
      ON dept_emp.dept_no = departments.dept_no;