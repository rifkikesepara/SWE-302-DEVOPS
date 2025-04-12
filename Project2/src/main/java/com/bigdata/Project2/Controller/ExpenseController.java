package com.bigdata.Project2.Controller;

import com.bigdata.Project2.Model.Employee;
import com.bigdata.Project2.Model.Expense;
import com.bigdata.Project2.Service.ExpenseService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PutMapping("/update-expense")
    void update(@RequestBody Expense expense ) {
        expenseService.update(expense);
    }

    @PostMapping("/save-expense")
    void save(@RequestBody Expense expense ) {
        expenseService.save(expense);
    }

    @DeleteMapping("/delete-expense/{id}")
    void delete(@PathVariable Integer id ) {
        expenseService.delete(id);
    }
}
