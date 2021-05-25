package com.cwiczenia.ksiegowanie.mapper.income;

import com.cwiczenia.ksiegowanie.entity.income.Income;
import com.cwiczenia.ksiegowanie.request.income.IncomeRequest;
import com.cwiczenia.ksiegowanie.request.income.RequestByIdIncome;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class IncomeRequestMapper {

    public Income mapToIncomeInfo(IncomeRequest incomeRequest) {
        if (Objects.isNull(incomeRequest) || (incomeRequest.getIncomeValue() < 0)) {
            return null;
        }
        Income income = new Income();
        income.setIncomeValue(incomeRequest.getIncomeValue());
        income.setReceivedPayment(incomeRequest.isReceivedPayment());

        return income;
    }

    public Income mapToIncomeById(RequestByIdIncome requestByIdIncome) {
        if (Objects.isNull(requestByIdIncome) || requestByIdIncome.getId() < 0) {
            return null;
        }
        Income income = new Income();
        income.setId(requestByIdIncome.getId());
        return income;
    }
}
