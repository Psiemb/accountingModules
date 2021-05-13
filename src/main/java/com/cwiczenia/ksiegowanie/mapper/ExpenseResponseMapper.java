package com.cwiczenia.ksiegowanie.mapper;

import com.cwiczenia.ksiegowanie.entity.ExpenseWEWNETRZNY_MODEL;
import com.cwiczenia.ksiegowanie.response.ExpenseResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpenseResponseMapper {

    public ExpenseResponse mapToResponse(List<ExpenseWEWNETRZNY_MODEL> expenseWEWNETRZNY_models){

        ExpenseWEWNETRZNY_MODEL expenseWEWNETRZNY_model = expenseWEWNETRZNY_models.get(0);
        ExpenseResponse resultToBeReturn = new ExpenseResponse();

        resultToBeReturn.setCostValueR(expenseWEWNETRZNY_model.getCostValue());

        return resultToBeReturn;
    }


}
