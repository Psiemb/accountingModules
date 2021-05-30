package com.cwiczenia.ksiegowanie.mapper.income;

import com.cwiczenia.ksiegowanie.entity.income.Income;
import com.cwiczenia.ksiegowanie.response.income.IncomeResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class IncomeResponseMapper {

    public IncomeResponse mapIncomeToResponse(List<Income> source) {
        if (Objects.isNull(source) || Objects.isNull((source.get(0))) || source.get(0).getIncomeValue() < 0) {
            return null;
        }
        int incomeValue = source.get(0).getIncomeValue();

        IncomeResponse incomeResponse = new IncomeResponse();
        incomeResponse.setIncomeValue(incomeValue);

        return incomeResponse;
    }
}
