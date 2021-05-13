package com.cwiczenia.ksiegowanie.util;

import com.cwiczenia.ksiegowanie.entity.ExpenseWEWNETRZNY_MODEL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseHelperTest {

    private ExpenseHelper expenseHelper;

    @BeforeEach
    void setUp() {
        this.expenseHelper = new ExpenseHelper();
    }

    @Test
    void test() {
        // given
        List<ExpenseWEWNETRZNY_MODEL> expens = Arrays.asList(new ExpenseWEWNETRZNY_MODEL().setCostValue(5), new ExpenseWEWNETRZNY_MODEL().setCostValue(10));

        // when
        Integer result = expenseHelper.sumCostValue(expens);
        // then
        assertEquals(15, result);
    }

}