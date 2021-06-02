package com.cwiczenia.ksiegowanie.controller;

import com.cwiczenia.ksiegowanie.dao.AccountingManager;
import com.cwiczenia.ksiegowanie.entity.income.Income;
import com.cwiczenia.ksiegowanie.mapper.income.IncomeRequestMapper;
import com.cwiczenia.ksiegowanie.mapper.income.IncomeResponseMapper;
import com.cwiczenia.ksiegowanie.request.income.IncomeRequest;
import com.cwiczenia.ksiegowanie.response.income.IncomeInfo;
import com.cwiczenia.ksiegowanie.response.income.IncomeResponse;
import com.cwiczenia.ksiegowanie.util.Helper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class IncomeController {

    private final AccountingManager accountingManager;
    private final Helper helper;
    private final IncomeRequestMapper incomeRequestMapper;
    private final IncomeResponseMapper incomeResponseMapper;

    public IncomeController(AccountingManager accountingManager, Helper helper, IncomeRequestMapper incomeRequestMapper, IncomeResponseMapper incomeResponseMapper) {
        this.accountingManager = accountingManager;
        this.helper = helper;
        this.incomeRequestMapper = incomeRequestMapper;
        this.incomeResponseMapper = incomeResponseMapper;
    }

    @GetMapping("/allIncomes")
    public ResponseEntity<IncomeInfo> getAllIncomes() {
        List<Income> allIncomes = accountingManager.findAllIncomes();
        Integer sumOfAllIncomes = helper.sumIncomeValue(allIncomes);
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
        Integer integer = helper.sumIncomeValue(incomes);
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


    private IncomeInfo createIncomeInfoResponse(Integer integer, IncomeResponse incomeResponse) {
        IncomeInfo incomeInfo = new IncomeInfo();
        incomeInfo.setIncomes(Collections.singletonList(incomeResponse));
        incomeInfo.setSum(integer);
        return incomeInfo;
    }

}
