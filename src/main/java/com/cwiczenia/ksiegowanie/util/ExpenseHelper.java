package com.cwiczenia.ksiegowanie.util;

import com.cwiczenia.ksiegowanie.entity.ExpenseInternalEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExpenseHelper {


    //TODO: listCostValue == null
    //TODO: elementy w liscie listCostValue == null
    //TODO: getCostValue =- null
    // TODO: getCostValue == ujemna licza -> co wtedy? Sumujesz?
    //TODO: Unit Testy
    public Integer sumCostValue(List<ExpenseInternalEntity> listCostValue) {
        return Optional.ofNullable(listCostValue)
                .orElse(new ArrayList<>())
                .stream()
                .filter(Objects::nonNull)
                .map(ExpenseInternalEntity::getCostValue)
                .reduce(0, Integer::sum);
    }

    public List<ExpenseInternalEntity> getListCostValue(int costValue, List<ExpenseInternalEntity> actualList) {
        return Optional.ofNullable(actualList)//Jeśli actualList jest nullem to wykonaj na pustej liscie (tej z orElse). Jezeli nie to tryb normalny.
                .orElse(new ArrayList<>())
                .stream()
                .filter(Objects::nonNull) //To uchroni przed nulami linjka poniżej "e".
                .filter(e -> e.getCostValue() == costValue)
                .collect((Collectors.toList()));
    }

    public List<ExpenseInternalEntity> getConstructionList(String requestConstruction, List<ExpenseInternalEntity> actualList) {
//        if(StringUtils.isBlank(requestConstruction)) {
        if (Objects.isNull(requestConstruction)) {
            return Collections.emptyList();
        }

        return Optional.ofNullable(actualList)
                .orElse(new ArrayList<>())
                .stream()
                .filter(Objects::nonNull)
                .filter(e -> Objects.nonNull(e.getConstructionSiteNo()))
                .filter(e -> Objects.nonNull(e.getConstructionSiteNo().getConstruction()))
                .filter(e -> requestConstruction.equals(e.getConstructionSiteNo().getConstruction()))
                .collect((Collectors.toList()));
    }

    public List<ExpenseInternalEntity> getPaidCostList(Boolean requestPaidCost, List<ExpenseInternalEntity> actualList) {
        List<ExpenseInternalEntity> collectConstructionList = actualList.stream()
                .filter(e -> e.isPaidCost() == requestPaidCost)
                .collect(Collectors.toList());
        return collectConstructionList;
    }


}
