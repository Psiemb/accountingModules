package com.cwiczenia.ksiegowanie.controller;

import com.cwiczenia.ksiegowanie.dao.AccountingManager;
import com.cwiczenia.ksiegowanie.entity.expense.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.mapper.expense.ExpenseRequestMapper;
import com.cwiczenia.ksiegowanie.mapper.expense.ExpenseResponseMapper;
import com.cwiczenia.ksiegowanie.request.expense.ExpenseRequest;
import com.cwiczenia.ksiegowanie.request.expense.RequestById;
import com.cwiczenia.ksiegowanie.response.expense.ExpenseInfo;
import com.cwiczenia.ksiegowanie.response.expense.ExpenseResponse;
import com.cwiczenia.ksiegowanie.util.Helper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ExpensesController {

    private final AccountingManager accountingManager;
    private final Helper helper;
    private final ExpenseResponseMapper expenseResponseMapper;
    private final ExpenseRequestMapper expenseRequestMapper;


    public ExpensesController(AccountingManager accountingManager, Helper helper, ExpenseResponseMapper expenseResponseMapper, ExpenseRequestMapper expenseRequestMapper) {
        this.accountingManager = accountingManager;
        this.helper = helper;
        this.expenseResponseMapper = expenseResponseMapper;
        this.expenseRequestMapper = expenseRequestMapper;
    }


    @PostMapping("/expenses")
    public ResponseEntity<Void> addExpense(@RequestBody ExpenseRequest expensesRequest) {
        ExpenseInternalEntity expenseInternalEntity = expenseRequestMapper.mapToExpenseInternalEntity(expensesRequest);
        if (accountingManager.save(expenseInternalEntity)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (accountingManager.deleteById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        return ResponseEntity.status(HttpStatus.OK)
//                .build();
    }

    //    @GetMapping("/expenses/{id}")
    @GetMapping("/byId")
    public ResponseEntity<ExpenseInternalEntity> getById(@RequestParam RequestById requestById) {
        ExpenseInternalEntity expenseById = expenseRequestMapper.mapToExpenseInternalEntityBYId(requestById);
        Optional<ExpenseInternalEntity> byId = accountingManager.findById(expenseById.getId());
        if (!byId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok() //ResponseEntity.status(HttpStatus.OK)
                .body(byId.get());

//        return byId.map(expenseInternalEntity -> ResponseEntity.ok() //ResponseEntity.status(HttpStatus.OK)
//                .body(expenseInternalEntity)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping("/allExpenses")
    public ResponseEntity<ExpenseInfo> getAllExpenses() {
        List<ExpenseInternalEntity> all = accountingManager.findAll();
        Integer sum = helper.sumCostValue(all);

        ExpenseResponse expenseResponse = expenseResponseMapper.mapToResponse(all);

        return ResponseEntity.ok()
                .body(getExpenseInfo(sum, expenseResponse));
    }

    @GetMapping("/expensesByCostValue")
    public ResponseEntity<ExpenseInfo> getExpensesByCostValue(@RequestParam Integer costValue) {
        if (Objects.isNull(costValue) || costValue <= 0) {
            //throw BadRequest
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<ExpenseInternalEntity> actualList = accountingManager.findAll();
        List<ExpenseInternalEntity> listCostValue = helper.getListCostValue(costValue, actualList);
        Integer sum = helper.sumCostValue(listCostValue);

        ExpenseResponse expenseResponse = expenseResponseMapper.mapToResponse(listCostValue);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getExpenseInfo(sum, expenseResponse));
    }

    @GetMapping("/expensesByConstructionSiteNo")
    public ResponseEntity<ExpenseInfo> getExpensesByConstructionSiteNo(@RequestParam String requestConstruction) {
        //TODO; nie zwracamy new Expensinfo tylko ju?? ResponeEntity...
        if (Objects.isNull(requestConstruction)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<ExpenseInternalEntity> allExpenses = accountingManager.findAll();
        List<ExpenseInternalEntity> collectConstructionList = helper.getConstructionList(requestConstruction, allExpenses);
        Integer sum = helper.sumCostValue(allExpenses);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(collectConstructionList);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getExpenseInfo(sum, expenseResponseAfterMapping));
    }

    @GetMapping("/sumPaidExpenses")
    public ResponseEntity<ExpenseInfo> getSumPaidExpenses() {
        List<ExpenseInternalEntity> allExpenses = accountingManager.findAll();
        List<ExpenseInternalEntity> paidCostList = helper.getPaidExpenses(allExpenses);
        Integer sum = helper.sumCostValue(paidCostList);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(paidCostList);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getExpenseInfo(sum, expenseResponseAfterMapping));
    }

    @GetMapping("/expensesByNameOfConstructionSite")
    public ResponseEntity<ExpenseInfo> getExpensesByNameOfConstructionSite(@RequestParam String name) {

        Optional<ExpenseInternalEntity> byName = accountingManager.findByName(name);
        if (!byName.isPresent()) {
            //TODO; return ResponseEntity a nie null
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
        List<ExpenseInternalEntity> expenseInternalEntities = Collections.singletonList(byName.get());
        Integer sum = helper.sumCostValue(expenseInternalEntities);

        ExpenseResponse expenseResponse = expenseResponseMapper.mapToResponse(expenseInternalEntities);
        List<ExpenseResponse> expenseResponses = Collections.singletonList(expenseResponse);

        ExpenseInfo expenseInfo = createExpenseInfoResponse(sum, expenseResponses);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseInfo);
    }

    public ExpenseInfo createExpenseInfoResponse(Integer sum, List<ExpenseResponse> expenseResponses) {
        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(expenseResponses);
        expenseInfo.setSumOfExpenses(sum);
        return expenseInfo;
    }

    private ExpenseInfo getExpenseInfo(Integer sum, ExpenseResponse expenseResponse) {
        ExpenseInfo expenseInfo = createExpenseInfoResponse(sum, Collections.singletonList(expenseResponse));
        return expenseInfo;
    }
}
