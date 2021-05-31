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
import com.cwiczenia.ksiegowanie.response.expense.ExpenseInfo;
import com.cwiczenia.ksiegowanie.response.expense.ExpenseResponse;
import com.cwiczenia.ksiegowanie.response.income.IncomeInfo;
import com.cwiczenia.ksiegowanie.response.income.IncomeResponse;
import com.cwiczenia.ksiegowanie.util.ExpenseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<IncomeInfo> getAllIncomes() {
        List<Income> allIncomes = accountingManager.findAllIncomes();
        Integer sumOfAllIncomes = expenseHelper.sumIncomeValue(allIncomes);
        IncomeResponse incomeResponse = incomeResponseMapper.mapIncomeToResponse(allIncomes);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createIncomeInfoResponse(sumOfAllIncomes, incomeResponse));
    }

    @GetMapping("/incomesByReceivedPayment")
    public ResponseEntity<IncomeInfo> getByReceivedPaymentOfIncome(@RequestParam boolean paid) {
        Optional<Income> receivedPaymentOfIncome = accountingManager.findByReceivedPaymentOfIncome(paid);
        if (!receivedPaymentOfIncome.isPresent()) {
            //TODO: not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<Income> incomes = Collections.singletonList(receivedPaymentOfIncome.get());
        Integer integer = expenseHelper.sumIncomeValue(incomes);
        IncomeResponse incomeResponse = incomeResponseMapper.mapIncomeToResponse(incomes);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createIncomeInfoResponse(integer, incomeResponse));
    }

    @GetMapping("/byIdIncome")
    public ResponseEntity<Income> getByIdIncome(@RequestParam Long idIncome) {
        Optional<Income> byIdIncome = accountingManager.findByIdIncome(idIncome);
        return byIdIncome
//                .map(wartoscNotNull -> ResponseEntity.ok(wartoscNotNull))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

//    @GetMapping("/byIdIncom2e")
//    public ResponseEntity<Income> getByIdIncome2(@RequestParam Long idIncome) {
//        Optional<Income> byIdIncome = accountingManager.findByIdIncome(idIncome);
//        if (!byIdIncome.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        return ResponseEntity.ok(byIdIncome.get());
//}


    @PostMapping("/addIncome")
    public ResponseEntity<Void> addIncome(@RequestBody IncomeRequest incomeRequest) {
        Income income = incomeRequestMapper.mapToIncomeInfo(incomeRequest);
        accountingManager.saveIncome(income);

        //TODO; to jest tylko po to, byś wiedział że dzięki RespnseEntity możesz zwracać customowe informacje także w Headerach
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("testKey", "testValue");

//        ResponseAddIncomeResponse responseAddIncomeResponse = new ResponseAddIncomeResponse();

        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(responseAddIncomeResponse)
//                .headers(httpHeaders)
                .build();
    }


    @PostMapping("/addExpense")
    public ResponseEntity<Boolean> addExpense(@RequestBody ExpenseRequest expensesRequest) {
        ExpenseInternalEntity expenseInternalEntity = expenseRequestMapper.mapToExpenseInternalEntity(expensesRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountingManager.save(expenseInternalEntity));
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteExpense(@RequestParam Long index) {
        accountingManager.deleteById(index);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/byId")
    public ResponseEntity<Optional<ExpenseInternalEntity>> getById(@RequestParam RequestById requestById) {
        ExpenseInternalEntity expenseById = expenseRequestMapper.mapToExpenseInternalEntityBYId(requestById);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountingManager.findById(expenseById.getId()));
    }


    @GetMapping("/allExpenses")
    public ResponseEntity<ExpenseInfo> getAllExpenses() {
        List<ExpenseInternalEntity> all = accountingManager.findAll();
        Integer sum = expenseHelper.sumCostValue(all);

        ExpenseResponse expenseResponse = expenseResponseMapper.mapToResponse(all);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getExpenseInfo(sum, expenseResponse));
    }

    @GetMapping("/expensesByCostValue")
    public ResponseEntity<ExpenseInfo> getExpensesByCostValue(@RequestParam(required = false) Integer costValue) {
        if (Objects.isNull(costValue) || costValue <= 0) {
            //throw BadRequest
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<ExpenseInternalEntity> actualList = accountingManager.findAll();
        List<ExpenseInternalEntity> listCostValue = expenseHelper.getListCostValue(costValue, actualList);
        Integer sum = expenseHelper.sumCostValue(listCostValue);

        ExpenseResponse expenseResponse = expenseResponseMapper.mapToResponse(listCostValue);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getExpenseInfo(sum, expenseResponse));
    }

    @GetMapping("/expensesByConstructionSiteNo")
    public ResponseEntity<ExpenseInfo> getExpensesByConstructionSiteNo(@RequestParam(required = false) String requestConstruction) {
        //TODO; nie zwracamy new Expensinfo tylko już ResponeEntity...
        if (Objects.isNull(requestConstruction)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<ExpenseInternalEntity> allExpenses = accountingManager.findAll();
        List<ExpenseInternalEntity> collectConstructionList = expenseHelper.getConstructionList(requestConstruction, allExpenses);
        Integer sum = expenseHelper.sumCostValue(allExpenses);

        ExpenseResponse expenseResponseAfterMapping = expenseResponseMapper.mapToResponse(collectConstructionList);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getExpenseInfo(sum, expenseResponseAfterMapping));
    }

    @GetMapping("/sumPaidExpenses")
    public ResponseEntity<ExpenseInfo> getSumPaidExpenses() {
        List<ExpenseInternalEntity> allExpenses = accountingManager.findAll();
        List<ExpenseInternalEntity> paidCostList = expenseHelper.getPaidExpenses(allExpenses);
        Integer sum = expenseHelper.sumCostValue(paidCostList);

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
        Integer sum = expenseHelper.sumCostValue(expenseInternalEntities);

        ExpenseResponse expenseResponse = expenseResponseMapper.mapToResponse(expenseInternalEntities);
        List<ExpenseResponse> expenseResponses = Collections.singletonList(expenseResponse);

        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(expenseResponses);
        expenseInfo.setSumOfExpenses(sum);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseInfo);
    }

    private IncomeInfo createIncomeInfoResponse(Integer integer, IncomeResponse incomeResponse) {
        IncomeInfo incomeInfo = new IncomeInfo();
        incomeInfo.setIncomes(Collections.singletonList(incomeResponse));
        incomeInfo.setSum(integer);
        return incomeInfo;
    }

    private ExpenseInfo getExpenseInfo(Integer sum, ExpenseResponse expenseResponse) {
        ExpenseInfo expenseInfo = new ExpenseInfo();
        expenseInfo.setExpenseResponses(Collections.singletonList(expenseResponse));
        expenseInfo.setSumOfExpenses(sum);
        return expenseInfo;
    }
}
