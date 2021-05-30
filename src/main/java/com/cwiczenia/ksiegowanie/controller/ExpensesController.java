package com.cwiczenia.ksiegowanie.controller;

import com.cwiczenia.ksiegowanie.dao.AccountingManager;
import com.cwiczenia.ksiegowanie.entity.expense.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.entity.income.Income;
import com.cwiczenia.ksiegowanie.mapper.expense.ExpenseRequestMapper;
import com.cwiczenia.ksiegowanie.mapper.expense.ExpenseResponseMapper;
import com.cwiczenia.ksiegowanie.mapper.income.IncomeRequestMapper;
import com.cwiczenia.ksiegowanie.mapper.income.IncomeResponseMapper;
import com.cwiczenia.ksiegowanie.request.expense.ExpenseRequest;
import com.cwiczenia.ksiegowanie.request.expense.RequestById;
import com.cwiczenia.ksiegowanie.request.income.IncomeRequest;
import com.cwiczenia.ksiegowanie.request.income.RequestByIdIncome;
import com.cwiczenia.ksiegowanie.response.expense.ExpenseInfo;
import com.cwiczenia.ksiegowanie.response.expense.ExpenseResponse;
import com.cwiczenia.ksiegowanie.response.income.IncomeInfo;
import com.cwiczenia.ksiegowanie.response.income.IncomeResponse;
import com.cwiczenia.ksiegowanie.util.ExpenseHelper;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ExpensesController {

    private final ExpenseHelper expenseHelper;
    private final AccountingManager accountingManager;
    private final ExpenseResponseMapper expenseResponseMapper;
    private final ExpenseRequestMapper expenseRequestMapper;
    private final IncomeRequestMapper incomeRequestMapper;
    private final IncomeResponseMapper incomeResponseMapper;

    public ExpensesController(ExpenseHelper expenseHelper, AccountingManager accountingManager, ExpenseResponseMapper expenseResponseMapper, ExpenseRequestMapper expenseRequestMapper, IncomeRequestMapper incomeRequestMapper, IncomeResponseMapper incomeResponseMapper) {
        this.expenseHelper = expenseHelper;
        this.accountingManager = accountingManager;
        this.expenseResponseMapper = expenseResponseMapper;
        this.expenseRequestMapper = expenseRequestMapper;
        this.incomeRequestMapper = incomeRequestMapper;
        this.incomeResponseMapper = incomeResponseMapper;
    }
    @GetMapping("/allIncomes")
    public IncomeInfo getAllIncomes() {
        List<Income> allIncomes = accountingManager.findAllIncomes();
        Integer sumOfAllIncomes = expenseHelper.sumIncomeValue(allIncomes);
        IncomeResponse incomeResponse = incomeResponseMapper.mapIncomeToResponse(allIncomes);

        IncomeInfo incomeInfo = new IncomeInfo();
        incomeInfo.setIncomes((List<IncomeResponse>) incomeResponse);
        incomeInfo.setSum(sumOfAllIncomes);

        return incomeInfo;
    }

    @GetMapping("/incomesByReceivedPayment")
    public IncomeInfo getByReceivedPaymentOfIncome(@RequestParam boolean paid) {
        Optional<Income> receivedPaymentOfIncome = accountingManager.findByReceivedPaymentOfIncome(paid);
        List<Income> incomes = Arrays.asList(receivedPaymentOfIncome.get());
        Integer integer = expenseHelper.sumIncomeValue(incomes);

        IncomeResponse incomeResponse = incomeResponseMapper.mapIncomeToResponse(incomes);

        IncomeInfo incomeInfo = new IncomeInfo();
        incomeInfo.setIncomes((List<IncomeResponse>) incomeResponse);
        incomeInfo.setSum(integer);
        return incomeInfo;

    }

    @GetMapping("/byIdIncome")
    public Optional<Income> getByIdIncome(@RequestParam RequestByIdIncome requestByIdIncome) {
        Income income = incomeRequestMapper.mapToIncomeById(requestByIdIncome);
        return accountingManager.findByIdIncome(income.getId());
    }


    @PostMapping("/addIncome")
    public boolean addIncome(@RequestBody IncomeRequest incomeRequest) {
        Income income = incomeRequestMapper.mapToIncomeInfo(incomeRequest);
        return accountingManager.saveIncome(income);
    }


    @PostMapping("/addExpense")

    public boolean addExpense(@RequestBody ExpenseRequest expensesRequest) {
        ExpenseInternalEntity expenseInternalEntity = expenseRequestMapper.mapToExpenseInternalEntity(expensesRequest);

        return accountingManager.save(expenseInternalEntity);
    }

    @DeleteMapping("/deleteById")
    public void deleteExpense(@RequestParam Long index) {
        accountingManager.deleteById(index);
    }

    @GetMapping("/byId")
    public Optional<ExpenseInternalEntity> getById(@RequestParam RequestById requestById) {
        ExpenseInternalEntity expenseById = expenseRequestMapper.mapToExpenseInternalEntityBYId(requestById);
        return accountingManager.findById(expenseById.getId());
    }


    @GetMapping("/allExpenses")
    public ExpenseInfo getAllExpenses() {
        List<ExpenseInternalEntity> all = accountingManager.findAll();
        Integer sum = expenseHelper.sumCostValue(all);

        ExpenseResponse expenseResponse = expenseResponseMapper.mapToResponse(all);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponse));
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;
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

        ExpenseResponse expenseResponse = expenseResponseMapper.mapToResponse(listCostValue);
        List<ExpenseResponse> expenseResponses = Arrays.asList(expenseResponse);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(expenseResponses);
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;

    }

    @GetMapping("/expensesByConstructionSiteNo")
    public ExpenseInfo getExpensesByConstructionSiteNo(@RequestParam(required = false) String requestConstruction) {
        if (Objects.isNull(requestConstruction)) {
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

    @GetMapping("/sumPaidExpenses")
    public ExpenseInfo getSumPaidExpenses() {
        List<ExpenseInternalEntity> allExpenses = accountingManager.findAll();
        List<ExpenseInternalEntity> paidCostList = expenseHelper.getPaidExpenses(allExpenses);
        Integer sum = expenseHelper.sumCostValue(paidCostList);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(paidCostList);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponseAfterMapping));
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;
    }

    @GetMapping("/expensesByNameOfConstructionSite")
    public ExpenseInfo getExpensesByNameOfConstructionSite(@RequestParam String name) {

        Optional<ExpenseInternalEntity> byName = accountingManager.findByName(name);
        if (Objects.isNull(byName)) {
            return null;
        }
        ExpenseInfo expenseInfo = new ExpenseInfo();
        List<ExpenseInternalEntity> expenseInternalEntities = Arrays.asList(byName.get());
        Integer sum = expenseHelper.sumCostValue(expenseInternalEntities);

        ExpenseResponse expenseResponse = expenseResponseMapper.mapToResponse(expenseInternalEntities);
        List<ExpenseResponse> expenseResponses = Arrays.asList(expenseResponse);

        expenseInfo.setExpenseResponses(expenseResponses);
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;
    }

}
