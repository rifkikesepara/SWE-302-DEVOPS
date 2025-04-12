package com.bigdata.Project2.Service;

import com.bigdata.Project2.DTO.EmployeeResponse;
import com.bigdata.Project2.Model.Department;
import com.bigdata.Project2.Model.Employee;
import com.bigdata.Project2.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private List<Department> departments = new ArrayList<>();

    public EmployeeService()
    {
        departments.add(new Department(10,"IT","İstanbul"));
        departments.add(new Department(20,"HR","İstanbul"));
        departments.add(new Department(30,"Management","Ankara"));
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeResponse> getAllEmployeesWithDepartment() {
        List<Employee> test= employeeRepository.findAllEmployees();
        
        List<EmployeeResponse> response = new ArrayList<EmployeeResponse>(test.size());
        for(int i=0;i<test.size();i++)
        {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            employeeResponse.setComm(test.get(i).getComm());
            employeeResponse.setImg(test.get(i).getImg());
            employeeResponse.setEname(test.get(i).getEname());
            employeeResponse.setDeptno(departments.get( test.get(i).getDepartment()/10-1).getDeptno());
            employeeResponse.setEmpno(test.get(i).getEmpno());
            employeeResponse.setHiredate(test.get(i).getHiredate());
            employeeResponse.setJob(test.get(i).getJob());
            employeeResponse.setSal(test.get(i).getSal());
            employeeResponse.setMgr(test.get(i).getMgr());
            employeeResponse.setLoc(departments.get( test.get(i).getDepartment()/10-1).getLoc());
            employeeResponse.setDname(departments.get( test.get(i).getDepartment()/10-1).getDname());
            response.add(employeeResponse);
        }
        return response;
    }

    @Transactional
    public void save(Employee employee) {

        employeeRepository.save(employee);
    }

    public void update(Employee employeeDTO) {

        employeeRepository.save(employeeDTO);
    }

    public void delete(Integer id) {

        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.getEmployeeByEmpno(id);
    }

    public List<Department> getDepartments(){return departments;}
}
