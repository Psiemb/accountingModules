package com.cwiczenia.ksiegowanie.mapper.income;

import com.cwiczenia.ksiegowanie.entity.income.Income;
import com.cwiczenia.ksiegowanie.request.income.IncomeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class IncomeRequestMapperTest {

    private IncomeRequestMapper incomeRequestMapper;

    @BeforeEach
    void setUp() {
        this.incomeRequestMapper = new IncomeRequestMapper();
    }

    @Test
    void returnNullWhenIncomeRequestIsNull() {
        //given

        //when

        //then
        assertNull(incomeRequestMapper.mapToIncomeInfo(null));

    }

    @Test
    void returnNullWhenIncomeValueIsMinus() {
        //given
        IncomeRequest incomeRequest = new IncomeRequest();
        incomeRequest.setIncomeValue(-32);
        //when

        Income income = incomeRequestMapper.mapToIncomeInfo(incomeRequest);
        //then
        assertNull(income);
    }

//    void ret


}