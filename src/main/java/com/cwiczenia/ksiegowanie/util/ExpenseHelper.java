package com.cwiczenia.ksiegowanie.util;

import com.cwiczenia.ksiegowanie.entity.ExpenseInternalEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExpenseHelper {

    public Integer sumCostValue(List<ExpenseInternalEntity> listCostValue) {
        return Optional.ofNullable(listCostValue)
                .orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .filter(e -> e.getCostValue() > 0)
                .map(ExpenseInternalEntity::getCostValue)
                .reduce(0, Integer::sum);
    }

    public List<ExpenseInternalEntity> getListCostValue(int costValue, List<ExpenseInternalEntity> actualList) {
        if (costValue <= 0) {
            return Collections.emptyList();
        }

        return Optional.ofNullable(actualList)//Jeśli actualList jest nullem to wykonaj na pustej liscie (tej z orElse). Jezeli nie to tryb normalny.
                .orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull) //To uchroni przed nulami linjka poniżej "e".
                .filter(e -> e.getCostValue() == costValue)
                .collect((Collectors.toList()));
    }

    public List<ExpenseInternalEntity> getConstructionList(String requestConstruction, List<ExpenseInternalEntity> actualList) {
//        if(StringUtils.isBlank(requestConstruction)) {
        //TODO: WAZNE!! GDY String to sprawdzasz czy jest null LUB czy jest blank
        if (Objects.isNull(requestConstruction) || requestConstruction.isEmpty()) {
            return Collections.emptyList();
        }

        return Optional.ofNullable(actualList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(e -> Objects.nonNull(e.getConstructionSiteNo()))
                .filter(e -> Objects.nonNull(e.getConstructionSiteNo().getConstruction()))
                .filter(e -> requestConstruction.equals(e.getConstructionSiteNo().getConstruction()))
                .collect((Collectors.toList()));
    }

    public List<ExpenseInternalEntity> getPaidExpenses(List<ExpenseInternalEntity> expenses) {
        return Optional.ofNullable(expenses)
                .orElse(Collections.emptyList()).stream()
                .filter(ExpenseInternalEntity::isPaidCost)
                .collect(Collectors.toList());
    }


}
