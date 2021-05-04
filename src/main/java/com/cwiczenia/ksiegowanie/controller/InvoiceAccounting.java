package com.cwiczenia.ksiegowanie.controller;

import com.cwiczenia.ksiegowanie.dao.AccountingManager;
import com.cwiczenia.ksiegowanie.entity.Expense;
import com.cwiczenia.ksiegowanie.response.ExpenseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceAccounting {

    private AccountingManager accountingManager;

    @Autowired
    public InvoiceAccounting(AccountingManager accountingManager) {
        this.accountingManager = accountingManager;
    }

    private List<Expense> expenseList = new ArrayList<>();

    //    @GetMapping("/expense")
//    public Expense expense(){
//        return new Expense();
//    }

    @GetMapping("/byId")
    public Optional<Expense> getById(@RequestParam(defaultValue = "1") Long index) {
        return accountingManager.findById(index);
    }

    //    @GetMapping("/allexpenses")
//    public Iterable<Expense> getAllexpenses() {
//        return accountingManager.findAll();
//    }
    @GetMapping("/allexpenses")
    public ExpenseInfo getAllexpenses() {
        Iterable<Expense> all = accountingManager.findAll();

        
//        double result = 0;
//        while(all.iterator().hasNext()){
//            double num = all.iterator().next();
//            result +=num;
//        }
//        for (Expense next: all){
//            result +=next;
//        }

//        all.forEach(Expense::getCostValue);
//
//                .map(Expense::getCostValue)
//                .reduce(0.0, Double::sum);
//
//        ExpenseInfo expenseInfo = new ExpenseInfo();
//        expenseInfo.setList(this.expenseList);
//        expenseInfo.setSumOfExpenses(sumOfExpenses);
//
//        return expenseInfo;

    }


    @PostMapping("/addExpense")
    public Expense addExpense(@RequestBody Expense expense) {
        return accountingManager.save(expense);
    }

    @DeleteMapping("/deleteById")
    public void deleteExpense(@RequestParam Long index) {
        accountingManager.deleteById(index);
    }


//    @PostMapping("/addexpense1")
//    public List<Expense> addExpense1(@RequestBody ExpenseRequest request, @RequestParam(defaultValue = "1") Long id) {
//        ConstructionSiteNo constructionSiteNo = request.getConstructionSiteNo();
//        CostNoForConstructionSiteNo costNoForConstructionSiteNo = request.getCostNoForConstructionSiteNo();
//        double costValue = request.getCostValue();
//        boolean paidCost = request.isPaidCost();
//        Expense expense = new Expense(costValue, constructionSiteNo, costNoForConstructionSiteNo, paidCost);
//        expenseList.add(expense);
//
//        return expenseList;
//    }

//
//    @PostMapping("/addexpense")
//    public ExpenseInfo addExpense(@RequestBody ExpenseRequest request) {
//        ConstructionSiteNo constructionSiteNo = request.getConstructionSiteNo();
//        CostNoForConstructionSiteNo costNoForConstructionSiteNo = request.getCostNoForConstructionSiteNo();
//        double costValue = request.getCostValue();
//        boolean paidCost = request.isPaidCost();
//        Expense expense = new Expense(costValue, constructionSiteNo, costNoForConstructionSiteNo, paidCost);
//        expenseList.add(expense);
//
//        Double sumOfExpenses = expenseList.stream()
//                .map(Expense::getCostValue)
//                .reduce(0.0, Double::sum);
//
//        ExpenseInfo expenseInfo = new ExpenseInfo();
//        expenseInfo.setList(this.expenseList);
//        expenseInfo.setSumOfExpenses(sumOfExpenses);
//
//        return expenseInfo;
//    }


}
