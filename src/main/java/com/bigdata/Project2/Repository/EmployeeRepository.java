package com.bigdata.Project2.Repository;

import com.bigdata.Project2.Model.Department;
import com.bigdata.Project2.Model.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
//    @Query("SELECT e FROM Employee e LEFT JOIN e.department d ON e.department = d.deptno WHERE d.deptno IS NOT NULL")
//    List<Employee> findAllEmployeesWithDepartment();

    @Query("select e from Employee e")
    List<Employee> findAllEmployees();

    @Query("select d from Department d")
    List<Department> findAllDepartments();

    List<Employee> getEmployeesByEmpno(Integer empno);

    Employee getEmployeeByEmpno(Integer empno);
}
