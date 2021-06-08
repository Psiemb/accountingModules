package com.cwiczenia.ksiegowanie.mapper;

import com.cwiczenia.ksiegowanie.entity.expense.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.mapper.expense.ExpenseResponseMapper;
import com.cwiczenia.ksiegowanie.response.expense.ExpenseResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ExpenseResponseMapperTest {

    private ExpenseResponseMapper expenseResponseMapper;

    @BeforeEach
    void setUp() {
        this.expenseResponseMapper = new ExpenseResponseMapper();
    }

    @Test
    void returnNullWhenListEqualsNull() {
        assertNull(expenseResponseMapper.mapToResponse(null));
    }

    // testujemy metode wkladajać pista liste

    @Test
    void whenEmptyList() {
        // when
        ExpenseResponse result = expenseResponseMapper.mapToResponse(new ArrayList<>());

        //then
        assertNull(result);
    }

    @Test
    void returnNullWhenListHasNullElement() {
        // given
        List<ExpenseInternalEntity> list = Arrays.asList(null, new ExpenseInternalEntity());

        // when
        ExpenseResponse result = expenseResponseMapper.mapToResponse(list);

        // then
        assertNull(result);
    }

    @Test
    void returnZeroWhenDefaultValueInFirstElement() {
        // given
        List<ExpenseInternalEntity> list = Arrays.asList(new ExpenseInternalEntity(), new ExpenseInternalEntity());

        // when
        ExpenseResponse result = expenseResponseMapper.mapToResponse(list);

        // then
        assertEquals(0, result.getCostValueR());
    }

    @Test
    void test() {
        // given
        ExpenseInternalEntity item = new ExpenseInternalEntity();
        item.setCostValue(100);

        ExpenseInternalEntity item2 = new ExpenseInternalEntity();
        item2.setCostValue(200);

        List<ExpenseInternalEntity> list = Arrays.asList(item, item2);

        // when
        ExpenseResponse result = expenseResponseMapper.mapToResponse(list);

        //then
        assertEquals(300, result.getCostValueR());
    }

    @Test
    void returnZeroWhenCostValueIsNegative() {
        // given
        ExpenseInternalEntity item = new ExpenseInternalEntity();
        item.setCostValue(-100);

        List<ExpenseInternalEntity> list = Arrays.asList(item, new ExpenseInternalEntity());

        // when
        ExpenseResponse result = expenseResponseMapper.mapToResponse(list);

        //then
        assertNull(result);
    }

}