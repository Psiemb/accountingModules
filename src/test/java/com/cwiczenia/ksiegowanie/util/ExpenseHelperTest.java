package com.cwiczenia.ksiegowanie.util;

import com.cwiczenia.ksiegowanie.entity.ExpenseInternalEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseHelperTest {

    private ExpenseHelper expenseHelper;

    @BeforeEach
    void setUp() {
        this.expenseHelper = new ExpenseHelper();
    }

    @Test
    void test2() {
        // when
        Integer result = expenseHelper.sumCostValue(null);
        // then
        assertEquals(0, result);
    }

    @Test
    void test3ElementOnCostValueListIsNull() {
        // given
        List<ExpenseInternalEntity> expens = Arrays.asList(null, new ExpenseInternalEntity());

        // when
        Integer result = expenseHelper.sumCostValue(expens);

        // then
        assertEquals(10, result);
    }

    @Test
    void dodatanieWartosciFaktury() {
        // given
        List<ExpenseInternalEntity> expens = Arrays.asList(new ExpenseInternalEntity().setCostValue(5), new ExpenseInternalEntity().setCostValue(10));

        // when
        Integer result = expenseHelper.sumCostValue(expens);

        // then
        assertEquals(10, result);
    }

    @Test
    void test() {
        // given
        List<ExpenseInternalEntity> expens = Arrays.asList(new ExpenseInternalEntity().setCostValue(5), new ExpenseInternalEntity().setCostValue(10));

        // when
        Integer result = expenseHelper.sumCostValue(expens);

        // then
        assertEquals(15, result);
    }

    @Test
    void test4CostValuesAreMinus() {
        // given
        List<ExpenseInternalEntity> expens = Arrays.asList(new ExpenseInternalEntity().setCostValue(-10), new ExpenseInternalEntity().setCostValue(-101010));

        // when
        Integer result = expenseHelper.sumCostValue(expens);
        // then
        assertEquals(0, result);
    }

    @Test
    void test5CostValueIsMinusAndAnotherIsPlus() {
        // given
        List<ExpenseInternalEntity> expens = Arrays.asList(new ExpenseInternalEntity().setCostValue(-10), new ExpenseInternalEntity().setCostValue(101010));


        // when
        Integer result = expenseHelper.sumCostValue(expens);
        // then
        assertEquals(101000, result);
    }

    @Test
    void testListCostValueIsNull() {
        // given
        List<ExpenseInternalEntity> expens = null;

        // when
        Integer result = expenseHelper.sumCostValue(expens);
        // then
        assertEquals(0, result);
    }


    @Test
    void testCostValueIsNullAndListCostValueIsNull() {
        // given
        List<ExpenseInternalEntity> expens = null;
//        ExpenseWEWNETRZNY_MODEL expenseWEWNETRZNY_model = new ExpenseWEWNETRZNY_MODEL();
//        expenseWEWNETRZNY_model.setCostValue(0);

        // when
        List<ExpenseInternalEntity> result = expenseHelper.getListCostValue(2, expens);
        // then
        assertEquals(15, result);
    }


}