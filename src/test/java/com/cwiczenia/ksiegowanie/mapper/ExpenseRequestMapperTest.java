package com.cwiczenia.ksiegowanie.mapper;

import com.cwiczenia.ksiegowanie.entity.ConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.CostNoForConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.request.ExpenseRequest;
import com.cwiczenia.ksiegowanie.request.RequestById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class ExpenseRequestMapperTest {

    private ExpenseRequestMapper expenseRequestMapper;

    @BeforeEach
    void setUp() {
        this.expenseRequestMapper = new ExpenseRequestMapper();
    }

    @Test
    void returnNullWhenRequestIsNull() {
        //given
//        AddExpensesRequest addExpensesRequest = null;
//
        //when
//        ExpenseInternalEntity expense = expenseRequestMapper.mapToRequest(addExpensesRequest);
//
        //then
//        Assertions.assertNull(expense);

        assertNull(expenseRequestMapper.mapToExpenseInternalEntity(null));
    }
    @Test
    void returnNullWhenCostNoForConstructionSiteNoIsNull() {
        //given
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setCostNoForConstructionSiteNo(null);

        //when
        ExpenseInternalEntity expense = expenseRequestMapper.mapToExpenseInternalEntity(expenseRequest);

        //then
        assertNull(expense);
    }

    @Test
    void returnNullWhenConstructionSiteNoIsNull() {
        //given
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setConstructionSiteNo(null);

        //when
        ExpenseInternalEntity expense = expenseRequestMapper.mapToExpenseInternalEntity(expenseRequest);

        //then
        assertNull(expense);
    }
    @Test
    void returnNullWhenConstructionIsNull() { //Czy dobrze że stworzyłem obiekt typu ConstructionSiteNo a nie AddExpensesRequest
        //given
        ExpenseRequest expenseRequest = new ExpenseRequest();
        ConstructionSiteNo constructionSiteNo = new ConstructionSiteNo();
        constructionSiteNo.setConstruction(null);

        //when
        ExpenseInternalEntity expense = expenseRequestMapper.mapToExpenseInternalEntity(expenseRequest);

        //then
        assertNull(expense);
    }
    @Test
    void returnNullWhenConstructionIsEmpty() {
        //given
        ExpenseRequest expenseRequest = new ExpenseRequest();
        ConstructionSiteNo constructionSiteNo = new ConstructionSiteNo();
        constructionSiteNo.setConstruction("");

        //when
        ExpenseInternalEntity expense = expenseRequestMapper.mapToExpenseInternalEntity(expenseRequest);

        //then
        assertNull(expense);
    }

    @Test
    void returnNullWhenTypeIsNull(){
        //given
        ExpenseRequest expenseRequest = new ExpenseRequest();
        CostNoForConstructionSiteNo costNoForConstructionSiteNo = new CostNoForConstructionSiteNo();
        costNoForConstructionSiteNo.setType(null);

        //when
        ExpenseInternalEntity expense = expenseRequestMapper.mapToExpenseInternalEntity(expenseRequest);

        //then
        assertNull(expense);
    }

    @Test
    void returnNullWhenTypeIsEmpty(){
        //given
        ExpenseRequest expenseRequest = new ExpenseRequest();
        CostNoForConstructionSiteNo costNoForConstructionSiteNo = new CostNoForConstructionSiteNo();
        costNoForConstructionSiteNo.setType("");

        //when
        ExpenseInternalEntity expense = expenseRequestMapper.mapToExpenseInternalEntity(expenseRequest);

        //then
        assertNull(expense);
    }

    @Test
    void returnNullWhenCostValueIsMinus(){
        //given
        ExpenseRequest expenseRequest = new ExpenseRequest();
        expenseRequest.setCostValue(-2);

        //when
        ExpenseInternalEntity expense = expenseRequestMapper.mapToExpenseInternalEntity(expenseRequest);

        //then
        assertNull(expense);
    }



    @Test
    void returnNullWhenIdRequestByIdIsNull(){
      assertNull(expenseRequestMapper.mapToExpenseInternalEntityBYId(null));
    }


    @Test
    void returnNullWhenIdIsMinus(){
        //given
        RequestById requestById = new RequestById();
        requestById.setId(-1L);

        //when
        ExpenseInternalEntity expense = expenseRequestMapper.mapToExpenseInternalEntityBYId(requestById);

        //then
        assertNull(expense);
    }

    @Test
    void returnNullWhenIdIsZero(){
        //given
        RequestById requestById = new RequestById();
        requestById.setId(0L);

        //when
        ExpenseInternalEntity expense = expenseRequestMapper.mapToExpenseInternalEntityBYId(requestById);

        //then
        assertNull(expense);
    }
}