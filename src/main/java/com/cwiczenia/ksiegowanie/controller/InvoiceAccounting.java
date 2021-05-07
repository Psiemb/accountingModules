package com.cwiczenia.ksiegowanie.controller;

import com.cwiczenia.ksiegowanie.dao.AccountingManager;
import com.cwiczenia.ksiegowanie.entity.Expense;
import com.cwiczenia.ksiegowanie.response.ExpenseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class InvoiceAccounting {

    private AccountingManager accountingManager;

    @Autowired
    public InvoiceAccounting(AccountingManager accountingManager) {
        this.accountingManager = accountingManager;
    }

    private List<Expense> expenseList = new ArrayList<>();


    @GetMapping("/expensesByCostValue")
    public ExpenseInfo getExpensesByCategory2(@RequestParam(required = false, defaultValue = "0") int costValue) {
        //TODO: imlementujesz sobie logikę, gdy ktoś poda cost value
        Iterable<Expense> all = accountingManager.findAll();

        List<Expense> actualList = StreamSupport
                .stream(all.spliterator(), false)
                .collect(Collectors.toList());

        ExpenseInfo expenseInfo = new ExpenseInfo();

        if(costValue <= 0) {
            //TODO: implemtnacja gdy ktos nie poda costValue
            return new ExpenseInfo();
        } else {
            List<Expense> listCostValue = actualList.stream()
                    .filter(e -> e.getCostValue() == costValue)
                    .collect((Collectors.toList()));
            Integer sum = listCostValue.stream()
                    .map(e -> e.getCostValue())
                    .reduce(0, Integer::sum);

            expenseInfo.setList(listCostValue);
            expenseInfo.setSumOfExpenses(sum);

            return expenseInfo;
        }

    }
    @GetMapping("/expensesByConstructionSiteNo")
    public ExpenseInfo getExpensesByConstructionSiteNo(@RequestParam(required = false, defaultValue = "Wieliczka") String requestConstruction){
        Iterable<Expense> all = accountingManager.findAll();

        List<Expense> actualList = StreamSupport
                .stream(all.spliterator(), false)
                .collect(Collectors.toList());

        ExpenseInfo expenseInfo = new ExpenseInfo();

        if(requestConstruction.equals("Wieliczka")) {
//            TODO: implemtnacj gdy ktos nie poda nazyw budowy
            return new ExpenseInfo();
        } else {
            List<Expense> collectConstructionList = actualList.stream()
                    .filter(e -> e.getConstructionSiteNo().getConstruction().equals(requestConstruction))
                    .collect((Collectors.toList()));

            Integer sum = collectConstructionList.stream()
                    .map(Expense::getCostValue)
                    .reduce(0, Integer::sum);

            expenseInfo.setList(collectConstructionList);
            expenseInfo.setSumOfExpenses(sum);

            return expenseInfo;
        }
    }
    @GetMapping("/expensesByPaidCost")
    public ExpenseInfo getExpensesByConstructionSiteNo(@RequestParam(required = false) Boolean requestPaidCost){
        Iterable<Expense> all = accountingManager.findAll();

        List<Expense> actualList = StreamSupport
                .stream(all.spliterator(), false)
                .collect(Collectors.toList());

        ExpenseInfo expenseInfo = new ExpenseInfo();

        if(requestPaidCost.equals(false)) {
//            TODO: implemtnacj gdy ktos nie poda nazyw budowy
            return new ExpenseInfo();
        } else {
            List<Expense> collectConstructionList = actualList.stream()
                    .filter(e -> e.isPaidCost() == requestPaidCost)
                    .collect(Collectors.toList());

            Integer sum = collectConstructionList.stream()
                    .map(Expense::getCostValue)
                    .reduce(0, Integer::sum);

            expenseInfo.setList(collectConstructionList);
            expenseInfo.setSumOfExpenses(sum);

            return expenseInfo;
        }
    }



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

        List<Expense> actualList = StreamSupport
                .stream(all.spliterator(), false)
                .collect(Collectors.toList());

        Integer sum = actualList.stream()
                .map(e -> e.getCostValue())
                .reduce(0, Integer::sum);

        ExpenseInfo expenseInfo = new ExpenseInfo();

        expenseInfo.setList(actualList);
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;
    }

    @GetMapping("/expensesByCategory")
    public ExpenseInfo getExpensesByCategory(@RequestParam double costValue, @RequestParam String wieliczka)

//    @QueryParam("place") enum place),@QueryParam("transportobcy") enum trenasportobcy),@QueryParam("paindcost") double paidcost)
    {
        Iterable<Expense> all = accountingManager.findAll();

        List<Expense> actualList = StreamSupport
                .stream(all.spliterator(), false)
                .collect(Collectors.toList());

        if (costValue != 0) {
            List<Integer> listCostValue = actualList.stream()
                    .map(e -> e.getCostValue())
                    .collect((Collectors.toList()));
        }


        Integer sum = actualList.stream()
                .map(e -> e.getCostValue())
                .reduce(0, Integer::sum);

        ExpenseInfo expenseInfo = new ExpenseInfo();

        expenseInfo.setList(actualList);
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;
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
