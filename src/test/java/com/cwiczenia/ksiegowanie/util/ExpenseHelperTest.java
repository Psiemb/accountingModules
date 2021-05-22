package com.cwiczenia.ksiegowanie.util;

import com.cwiczenia.ksiegowanie.entity.ExpenseInternalEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ExpenseHelperTest {

    private ExpenseHelper expenseHelper;

    @BeforeEach
    void setUp() {
        this.expenseHelper = new ExpenseHelper();
    }

    @Test
    void sumOfInvoicesOnCostList() {
        // when
        Integer result = expenseHelper.sumCostValue(null);
        // then
        assertEquals(0, result);
    }

    @Test
    void elementOnCostValueListIsNull() {
        // given
        List<ExpenseInternalEntity> expenses = Arrays.asList(null, new ExpenseInternalEntity());

        // when
        Integer result = expenseHelper.sumCostValue(expenses);

        // then
        assertEquals(10, result);
    }

    @Test
    void checkSumWhenInvoiceValueIsPlus() {
        // given
        List<ExpenseInternalEntity> expenses = Arrays.asList(new ExpenseInternalEntity().setCostValue(5), new ExpenseInternalEntity().setCostValue(10));

        // when
        Integer result = expenseHelper.sumCostValue(expenses);

        // then
        assertEquals(15, result);
    }

    @Test
    void checkResultWhenInvoiceValueIsMinus() {
        // given
        List<ExpenseInternalEntity> expens = Arrays.asList(new ExpenseInternalEntity().setCostValue(-10), new ExpenseInternalEntity().setCostValue(-101010));

        // when
        Integer result = expenseHelper.sumCostValue(expens);
        // then
        assertEquals(0, result);
    }

    @Test
    void costValueIsMinusAndAnotherIsPlus() {
        // given
        List<ExpenseInternalEntity> expens = Arrays.asList(new ExpenseInternalEntity().setCostValue(-10), new ExpenseInternalEntity().setCostValue(101010));

        // when
        Integer result = expenseHelper.sumCostValue(expens);
        // then
        assertEquals(101010, result);
    }

    @Test
    void listCostValueIsNull() {
        // given
        List<ExpenseInternalEntity> expens = null;

        // when
        Integer result = expenseHelper.sumCostValue(expens);
        // then
        assertEquals(0, result);
    }


    @Test
    void costValueIsZeroAndListCostValueIsNull() {
        // given
        List<ExpenseInternalEntity> expens = null;
//        ExpenseWEWNETRZNY_MODEL expenseWEWNETRZNY_model = new ExpenseWEWNETRZNY_MODEL();
//        expenseWEWNETRZNY_model.setCostValue(0);

        // when
        List<ExpenseInternalEntity> result = expenseHelper.getListCostValue(0, expens);
        // then
        assertTrue(result.isEmpty());
    }


}