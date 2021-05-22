package com.cwiczenia.ksiegowanie.controller;

import com.cwiczenia.ksiegowanie.dao.AccountingManager;
import com.cwiczenia.ksiegowanie.entity.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.mapper.ExpenseRequestMapper;
import com.cwiczenia.ksiegowanie.mapper.ExpenseResponseMapper;
import com.cwiczenia.ksiegowanie.request.ExpenseRequest;
import com.cwiczenia.ksiegowanie.request.RequestById;
import com.cwiczenia.ksiegowanie.response.ExpenseInfo;
import com.cwiczenia.ksiegowanie.response.ExpenseResponse;
import com.cwiczenia.ksiegowanie.util.ExpenseHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ExpensesController {
    private final ExpenseHelper expenseHelper;

    private final AccountingManager accountingManager;
    private final ExpenseResponseMapper expenseResponseMapper;
    private final ExpenseRequestMapper expenseRequestMapper;

    public ExpensesController(ExpenseHelper expenseHelper, AccountingManager accountingManager, ExpenseResponseMapper expenseResponseMapper, ExpenseRequestMapper expenseRequestMapper) {
        this.expenseHelper = expenseHelper;
        this.accountingManager = accountingManager;
        this.expenseResponseMapper = expenseResponseMapper;
        this.expenseRequestMapper = expenseRequestMapper;
    }

    @PostMapping("/addExpense")
    public boolean addExpense(@RequestBody ExpenseRequest expensesRequest) {
        //TODO: w request wymgasz, by uzytkownik wyslal Ci request zgodny z Twoim wewnętrznym modelem
        //TODO: mapper, ktory zmapuje obietk z request na wewnetrzny model
        //TODO: Unit testy do mapper
        ExpenseInternalEntity expenseInternalEntity = expenseRequestMapper.mapToExpenseInternalEntity(expensesRequest);

        return accountingManager.save(expenseInternalEntity);
    }

    @DeleteMapping("/deleteById")
    public void deleteExpense(@RequestParam Long index) {
        accountingManager.deleteById(index);
    }

    @GetMapping("/byId")
    public Optional<ExpenseInternalEntity> getById(@RequestParam RequestById requestById) {
        //TODO: zwracasz wewneßrzny model!!!
        //TODO: stworzyc nowa klase Response
        //TODO: stworzy mappera, który zmapuje wewnętrzny model na tą nowa klase
        //TODO: zwrócić nowa klase (obiekt)
        ExpenseInternalEntity expenseById = expenseRequestMapper.mapToExpenseInternalEntityBYId(requestById);
        return accountingManager.findById(expenseById.getId());
    }

    @GetMapping("/allexpenses")
    public ExpenseInfo getAllexpenses() {
        List<ExpenseInternalEntity> all = accountingManager.findAll();
        Integer sum = expenseHelper.sumCostValue(all);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(all);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponseAfterMapping));
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

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(listCostValue);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponseAfterMapping));
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

    @GetMapping("/expensesByCategory")
    public ExpenseInfo getExpensesByCategory(@RequestParam(required = false) double costValue, @RequestParam(required = true) String wieliczka) {
        List<ExpenseInternalEntity> all = accountingManager.findAll();
        ExpenseInfo expenseInfo = new ExpenseInfo();
        Integer sum = expenseHelper.sumCostValue(all);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(all);

        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponseAfterMapping));
        expenseInfo.setSumOfExpenses(sum);

        return expenseInfo;
    }

}
