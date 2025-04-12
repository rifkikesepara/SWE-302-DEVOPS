package com.bigdata.Project2.Controller;

import com.bigdata.Project2.Model.Employee;
import com.bigdata.Project2.Model.Expense;
import com.bigdata.Project2.Repository.ExpenseRepository;
import com.bigdata.Project2.Service.EmployeeService;
import com.bigdata.Project2.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Controller
public class PageController {
    private final EmployeeService employeeService;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private ExpenseService expenseService;

    public PageController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/table")
    String greetingPage(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployeesWithDepartment());
        model.addAttribute("employee",new Employee());
        return "home";
    }

    @GetMapping("/addEmployee")
    public String addEmp(Model model) {
        model.addAttribute("depts", employeeService.getDepartments());
        return "add";
    }

    @PostMapping("/updateEmployee/{id}")
    public String updateEmp(Model model, @PathVariable Integer id) {
        Employee employee= employeeService.getEmployeeById(id);
        model.addAttribute("depts", employeeService.getDepartments());
        model.addAttribute("employee",employee);
        return "add";
    }

    @PostMapping("/update")
    public String submitEmployee(
            @RequestParam Integer id,
            @RequestParam String ename,
            @RequestParam String job,
            @RequestParam Integer mgr,
            @RequestParam String hiredate,
            @RequestParam Integer sal,
            @RequestParam(required = false) Integer comm,
            @RequestParam Integer deptno,
            Model model
    ){

        employeeService.update(new Employee(comm,id,ename,job,mgr,hiredate,sal,null,deptno));
        model.addAttribute("employees", employeeService.getAllEmployeesWithDepartment());
        return "redirect:/table";
    }

    @PostMapping("/submit")
    public String submitEmployee(
            @RequestParam String ename,
            @RequestParam String job,
            @RequestParam Integer mgr,
            @RequestParam String hiredate,
            @RequestParam Integer sal,
            @RequestParam(required = false) Integer comm,
            @RequestParam Integer deptno,
            Model model
    ) {
        employeeService.save(new Employee(comm,null,ename,job,mgr,hiredate,sal,null,deptno));
        model.addAttribute("employees", employeeService.getAllEmployeesWithDepartment());
        return "home";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id, Model model) {
        String apiUrl = "http://localhost:8080/delete-employee/"+id;

        restTemplate.delete(apiUrl);
        model.addAttribute("employees", employeeService.getAllEmployeesWithDepartment());
        return "redirect:/table";
    }

    @GetMapping("/expenses")
    public String test(Model model) {
        model.addAttribute("expenses",expenseRepository.findAll());
        return "expenses";
    }

    @GetMapping("/addExpense")
    public String addExpense(Model model) {
        model.addAttribute("locations",expenseService.getLocations());
        model.addAttribute("currencies",expenseService.getCurrencies());
        model.addAttribute("methods",expenseService.getPaymentMethods());

        return "editExpense";
    }

    @PostMapping("/updateExpense/{id}")
    public String updateExpense(Model model, @PathVariable Integer id) {
        model.addAttribute("expense",expenseRepository.findById(id).get());
        model.addAttribute("locations",expenseService.getLocations());
        model.addAttribute("currencies",expenseService.getCurrencies());
        model.addAttribute("methods",expenseService.getPaymentMethods());

        return "editExpense";
    }

    @PostMapping("/update-expense")
    public String updateExpense(
            @RequestParam Integer id,
            @RequestParam Float amount,
            @RequestParam String category,
            @RequestParam String description,
            @RequestParam LocalDate date,
            @RequestParam String method,
            @RequestParam String location,
            @RequestParam String currency,
            Model model
    ){
        expenseService.update(new Expense(id,amount,category,description,date,method,location,currency));
        model.addAttribute("expenses",expenseRepository.findAll());
        return "redirect:/expenses";
    }

    @PostMapping("/submit-expense")
    public String submitExpense(
            @RequestParam Float amount,
            @RequestParam String category,
            @RequestParam String description,
            @RequestParam LocalDate date,
            @RequestParam String method,
            @RequestParam String location,
            @RequestParam String currency,
            Model model
    ){
        expenseService.save(new Expense(null,amount,category,description,date,method,location,currency));
        model.addAttribute("expenses",expenseRepository.findAll());
        return "redirect:/expenses";
    }


    @PostMapping("/deleteExpense/{id}")
    public String deleteExpense(@PathVariable Integer id, Model model) {
        expenseService.delete(id);
        model.addAttribute("expenses",expenseRepository.findAll());
        return "redirect:/expenses";
    }

    @Autowired
    private RestTemplate restTemplate;



}
