package com.bigdata.Project2.Service;

import com.bigdata.Project2.Model.Department;
import com.bigdata.Project2.Model.Employee;
import com.bigdata.Project2.Model.Expense;
import com.bigdata.Project2.Repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    private List<String> locations = new ArrayList<>();
    private List<String> currencies = new ArrayList<>();
    private List<String> paymentMethods = new ArrayList<>();

    public ExpenseService(){
        //-----------locations-----------
        locations.add("Berlin");
        locations.add("Germany");
        locations.add("India");
        locations.add("Japan");
        locations.add("Korea");
        locations.add("London");

        //-----------currencies-----------
        currencies.add("EUR");
        currencies.add("TL");
        currencies.add("USD");
        currencies.add("PBD");

        //----------payment-methods----------
        paymentMethods.add("CASH");
        paymentMethods.add("CARD");
    }


    @Transactional
    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    public void update(Expense expense) {
        expenseRepository.save(expense);
    }

    public void delete(Integer id) {
        expenseRepository.deleteById(id);
    }

    public List<String> getLocations(){return locations;}

    public List<String> getCurrencies(){return currencies;}

    public List<String> getPaymentMethods(){return paymentMethods;}
}
