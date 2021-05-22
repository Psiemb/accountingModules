package com.cwiczenia.ksiegowanie.mapper;

import com.cwiczenia.ksiegowanie.entity.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.request.ExpenseRequest;
import com.cwiczenia.ksiegowanie.request.RequestById;

import java.util.Objects;

public class ExpenseRequestMapper {

    public ExpenseInternalEntity mapToExpenseInternalEntity(ExpenseRequest expenseRequest) {
        if (Objects.isNull(expenseRequest) || Objects.isNull(expenseRequest.getCostNoForConstructionSiteNo()) || Objects.isNull(expenseRequest.getConstructionSiteNo())
                || Objects.isNull(expenseRequest.getConstructionSiteNo().getConstruction()) || (expenseRequest.getConstructionSiteNo().getConstruction().isEmpty())
                || Objects.isNull(expenseRequest.getCostNoForConstructionSiteNo().getType()) || (expenseRequest.getCostNoForConstructionSiteNo().getType()).isEmpty()
                || (expenseRequest.getCostValue() < 0)) {
            return null;
        }

        ExpenseInternalEntity expenseInternalEntity = new ExpenseInternalEntity();
        expenseInternalEntity.setCostValue(expenseRequest.getCostValue());
        expenseInternalEntity.setConstructionSiteNo(expenseRequest.getConstructionSiteNo());
        expenseInternalEntity.setCostNoForConstructionSiteNo(expenseRequest.getCostNoForConstructionSiteNo());
        expenseInternalEntity.setPaidCost(expenseRequest.isPaidCost());

        return expenseInternalEntity;
    }

    public ExpenseInternalEntity mapToExpenseInternalEntityBYId(RequestById requestById) {
        if (Objects.isNull(requestById) || requestById.getId() < 1) {
            return null;
        }

        ExpenseInternalEntity expenseInternalEntity = new ExpenseInternalEntity();
        expenseInternalEntity.setId(requestById.getId());

        return expenseInternalEntity;
    }
//    public ExpenseResponse mapToResponse(List<ExpenseInternalEntity> source) {
//        if (Objects.isNull(source) || Objects.isNull(source.get(0)) || source.get(0).getCostValue() < 0) {
//            // throw exeption
//            // return new ExpenseResponse();
//            return null;
//        }
//        int costValue = source.get(0).getCostValue();
//
//        ExpenseResponse resultToBeReturn = new ExpenseResponse();
//        resultToBeReturn.setCostValueR(costValue);
//
//        return resultToBeReturn;
//    }
}
