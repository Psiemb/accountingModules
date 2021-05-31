package com.cwiczenia.ksiegowanie.mapper.expense;

import com.cwiczenia.ksiegowanie.entity.expense.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.response.expense.ExpenseResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ExpenseResponseMapper {

    //TODO: trzeba zmienic get(0)
    public ExpenseResponse mapToResponse(List<ExpenseInternalEntity> source) {
        if (Objects.isNull(source) || Objects.isNull(source.get(0)) || source.get(0).getCostValue() < 0) {
            // throw exeption
            // return new ExpenseResponse();
            return null;
        }
        int costValue = source.get(0).getCostValue();

        ExpenseResponse resultToBeReturn = new ExpenseResponse();
        resultToBeReturn.setCostValueR(costValue);

        return resultToBeReturn;
    }
}
