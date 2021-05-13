package com.cwiczenia.ksiegowanie.util;

import com.cwiczenia.ksiegowanie.entity.ExpenseWEWNETRZNY_MODEL;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExpenseHelper {

    public Integer sumCostValue(List<ExpenseWEWNETRZNY_MODEL> listCostValue) {
        return listCostValue.stream()
                .map(ExpenseWEWNETRZNY_MODEL::getCostValue)
                .reduce(0, Integer::sum);
    }

    public List<ExpenseWEWNETRZNY_MODEL> getListCostValue(int costValue, List<ExpenseWEWNETRZNY_MODEL> actualList) {
        return Optional.ofNullable(actualList)//Jeśli actualList jest nulem to wykonaj na putesj liscie (tej z orElse). Jezeli nie to tryb normalny.
                .orElse(new ArrayList<>())
                .stream()
                .filter(Objects::nonNull) //To uchroni przed nulami linjka poniżej "e".
                .filter(e -> e.getCostValue() == costValue)
                .collect((Collectors.toList()));
    }
}
