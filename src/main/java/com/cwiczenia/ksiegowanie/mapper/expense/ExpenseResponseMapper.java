package com.cwiczenia.ksiegowanie.mapper.expense;

import com.cwiczenia.ksiegowanie.entity.expense.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.response.expense.ExpenseResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ExpenseResponseMapper {

    //TODO: trzeba zmienic get(0)
    public ExpenseResponse mapToResponse222(List<ExpenseInternalEntity> source) {
//        if (Objects.isNull(source) || source.isEmpty() ||  Objects.isNull(source.get(0)) || source.get(0).getCostValue() < 0) {
        if (CollectionUtils.isEmpty(source) ||  Objects.isNull(source.get(0)) || source.get(0).getCostValue() < 0) {
            // throw exeption
            // return new ExpenseResponse();
            return null;
        }
        int costValue = source.get(0).getCostValue();

        ExpenseResponse resultToBeReturn = new ExpenseResponse();
        resultToBeReturn.setCostValueR(costValue);

        return resultToBeReturn;
    }

    public ExpenseResponse mapToResponse(List<ExpenseInternalEntity> source) {
        if (CollectionUtils.isEmpty(source)) {
            return null;
        }

        Integer sumOfCostValues = Optional.of(source)
                .orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .map(ExpenseInternalEntity::getCostValue)
                .reduce(0, Integer::sum);

        ExpenseResponse resultToBeReturn = new ExpenseResponse();
        resultToBeReturn.setCostValueR(sumOfCostValues);

        return resultToBeReturn;
    }
}
