package com.cwiczenia.ksiegowanie.util;

import com.cwiczenia.ksiegowanie.entity.ExpenseWEWNETRZNY_MODEL;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ExpenseHelper {

    public Integer sumCostValue(List<ExpenseWEWNETRZNY_MODEL> listCostValue) {
        return listCostValue.stream()
                .map(ExpenseWEWNETRZNY_MODEL::getCostValue)
                .reduce(0, Integer::sum);
    }

    public List<ExpenseWEWNETRZNY_MODEL> getListCostValue(int costValue, List<ExpenseWEWNETRZNY_MODEL> actualList) {
        return Optional.ofNullable(actualList)//Jeśli actualList jest nullem to wykonaj na pustej liscie (tej z orElse). Jezeli nie to tryb normalny.
                .orElse(new ArrayList<>())
                .stream()
                .filter(Objects::nonNull) //To uchroni przed nulami linjka poniżej "e".
                .filter(e -> e.getCostValue() == costValue)
                .collect((Collectors.toList()));
    }

    public List<ExpenseWEWNETRZNY_MODEL> getAllExpenses(Spliterator<ExpenseWEWNETRZNY_MODEL> spliterator) {
        return StreamSupport
                .stream(spliterator, false)
                .collect(Collectors.toList());
    }

    public List<ExpenseWEWNETRZNY_MODEL> getConstructionList(String requestConstruction, List<ExpenseWEWNETRZNY_MODEL> actualList) {
        List<ExpenseWEWNETRZNY_MODEL> collectConstructionList = actualList.stream()
                .filter(e -> e.getConstructionSiteNo().getConstruction().equals(requestConstruction))
                .collect((Collectors.toList()));
        return collectConstructionList;
    }
    public List<ExpenseWEWNETRZNY_MODEL> getPaidCostList(Boolean requestPaidCost, List<ExpenseWEWNETRZNY_MODEL> actualList) {
        List<ExpenseWEWNETRZNY_MODEL> collectConstructionList = actualList.stream()
                .filter(e -> e.isPaidCost() == requestPaidCost)
                .collect(Collectors.toList());
        return collectConstructionList;
    }


}
