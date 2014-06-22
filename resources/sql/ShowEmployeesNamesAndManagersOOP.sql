SELECT employees.first_name,employees.last_name,dept_manager.emp_no
      from employees 
      join dept_emp on dept_emp.emp_no = employees.emp_no 
      join dept_manager on dept_manager.dept_no=dept_emp.dept_no
	  LIMIT 100 ;