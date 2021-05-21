package com.cwiczenia.ksiegowanie.dao;

import com.cwiczenia.ksiegowanie.entity.ConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.CostNoForConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.ExpenseInternalEntity;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountingManager {

    private List<ExpenseInternalEntity> expenseWEWNETRZNYMODELList = new ArrayList<>();

    public Optional<ExpenseInternalEntity> findById(Long id) {
        return expenseWEWNETRZNYMODELList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<ExpenseInternalEntity> findAll() {
        return expenseWEWNETRZNYMODELList;
    }

    public boolean save(ExpenseInternalEntity expenseWEWNETRZNYMODEL) {
        return expenseWEWNETRZNYMODELList.add(expenseWEWNETRZNYMODEL);
    }

    public void deleteById(Long id) {
        expenseWEWNETRZNYMODELList.remove(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new ExpenseInternalEntity(1L, 3400, new ConstructionSiteNo("Wieliczka"), new CostNoForConstructionSiteNo("Transport obcy"), false));
    }

}
