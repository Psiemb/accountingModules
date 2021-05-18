package com.cwiczenia.ksiegowanie.controller;

import com.cwiczenia.ksiegowanie.dao.AccountingManager;
import com.cwiczenia.ksiegowanie.entity.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.mapper.ExpenseResponseMapper;
import com.cwiczenia.ksiegowanie.response.ExpenseInfo;
import com.cwiczenia.ksiegowanie.response.ExpenseResponse;
import com.cwiczenia.ksiegowanie.util.ExpenseHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class InvoiceAccounting {

    private final ExpenseHelper expenseHelper;
    private final AccountingManager accountingManager;
    private final ExpenseResponseMapper expenseResponseMapper;

    public InvoiceAccounting(ExpenseHelper expenseHelper, AccountingManager accountingManager, ExpenseResponseMapper expenseResponseMapper) {
        this.expenseHelper = expenseHelper;
        this.accountingManager = accountingManager;
        this.expenseResponseMapper = expenseResponseMapper;
    }


    @GetMapping("/expensesByCostValue")
    public ExpenseInfo getExpensesByCostValue(@RequestParam(required = false, defaultValue = "0") int costValue) {
        if (costValue <= 0) {
            //throw BadRequest
            return new ExpenseInfo();
        }

        List<ExpenseInternalEntity> actualList = accountingManager.findAll();
        List<ExpenseInternalEntity> listCostValue = expenseHelper.getListCostValue(costValue, actualList);
        Integer sum = expenseHelper.sumCostValue(listCostValue);

        //TODO: add mapper which map wewnetrznyMODEL to some response
        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(listCostValue);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponseAfterMapping));
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;

    }

//        @GetMapping("/expensesByCostValue")
//        public ExpenseInfo getExpensesByCategory2(@RequestParam(required = false, defaultValue = "0") int costValue) {
//            TODO: imlementujesz sobie logikę, gdy ktoś poda cost value
//            List<Expense> actualList = accountingManager.findAll();
//
//        List<Expense> actualList = StreamSupport
//                .stream(all.spliterator(), false)
//                .collect(Collectors.toList());

//            ExpenseInfo expenseInfo = new ExpenseInfo();
//
//            if(costValue <= 0) {
//                TODO: implemtnacja gdy ktos nie poda costValue
//                return new ExpenseInfo();
//            } else {
//                List<Expense> listCostValue = actualList.stream()
//                        .filter(e -> e.getCostValue() == costValue)
//                        .collect((Collectors.toList()));
//                Integer sum = listCostValue.stream()
//                        .map(e -> e.getCostValue())
//                        .reduce(0, Integer::sum);
//
//                expenseInfo.setList(listCostValue);
//                expenseInfo.setSumOfExpenses(sum);
//
//                return expenseInfo;
//            }


    @GetMapping("/expensesByConstructionSiteNo")
    public ExpenseInfo getExpensesByConstructionSiteNo(@RequestParam(required = false, defaultValue = "Wieliczka") String requestConstruction) {
        if (Objects.isNull(requestConstruction) || requestConstruction.equals("Wieliczka")) {
//            TODO: implemtnacj gdy ktos nie poda nazyw budowy
            return new ExpenseInfo();
        }
        List<ExpenseInternalEntity> allExpenses = accountingManager.findAll();
        List<ExpenseInternalEntity> collectConstructionList = expenseHelper.getConstructionList(requestConstruction, allExpenses);
        Integer sum = expenseHelper.sumCostValue(allExpenses);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(collectConstructionList);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponseAfterMapping));
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;

    }


    @GetMapping("/expensesByPaidCost")
    public ExpenseInfo getExpensesByConstructionSiteNo(@RequestParam(required = false) Boolean requestPaidCost) {
        if (requestPaidCost.equals(false)) {
//            TODO: implemtnacj gdy ktos nie poda nazyw budowy
            return new ExpenseInfo();
        }
        List<ExpenseInternalEntity> all = accountingManager.findAll();
        List<ExpenseInternalEntity> collectConstructionList = expenseHelper.getPaidCostList(requestPaidCost, all);
        Integer sum = expenseHelper.sumCostValue(all);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(collectConstructionList);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponseAfterMapping));
//            expenseInfo.setList(collectConstructionList);
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;

    }

    @GetMapping("/byId")
    public Optional<ExpenseInternalEntity> getById(@RequestParam(defaultValue = "1") Long index) {
        return accountingManager.findById(index);
    }

    @GetMapping("/allexpenses")
    public ExpenseInfo getAllexpenses() {
        List<ExpenseInternalEntity> all = accountingManager.findAll();
        Integer sum = expenseHelper.sumCostValue(all);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(all);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponseAfterMapping));
//        expenseInfo.setList(actualList);
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;
    }

    @GetMapping("/expensesByCategory")
    public ExpenseInfo getExpensesByCategory(@RequestParam (required = false) double costValue, @RequestParam (required = true) String wieliczka) {
        List<ExpenseInternalEntity> all = accountingManager.findAll();
        ExpenseInfo expenseInfo = new ExpenseInfo();
        Integer sum = expenseHelper.sumCostValue(all);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(all);

        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponseAfterMapping));
//        expenseInfo.setList(actualList);
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;
    }


    @PostMapping("/addExpense")
    public boolean addExpense(@RequestBody ExpenseInternalEntity expenseWEWNETRZNYMODEL) {
//        return accountingManager.save(expense);
        return accountingManager.save(expenseWEWNETRZNYMODEL);
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
