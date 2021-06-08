package com.cwiczenia.ksiegowanie.dao;

import com.cwiczenia.ksiegowanie.entity.expense.ConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.expense.CostNoForConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.expense.ExpenseInternalEntity;
import com.cwiczenia.ksiegowanie.entity.income.Income;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountingManager {

    private final List<ExpenseInternalEntity> expenseWEWNETRZNYMODELList = new ArrayList<>();
    private final List<Income> incomes = new ArrayList<>();

    public List<Income> findAllIncomes(){
        return incomes;
    }

    public Optional<Income> findByReceivedPaymentOfIncome(boolean receivedPayment){
        return incomes.stream()
                .filter(e -> e.isReceivedPayment() == receivedPayment)
                .findFirst();
    }

    public Optional<Income> findByIdIncome(Long id) {
        return incomes.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public boolean saveIncome(Income income){
        return incomes.add(income);
    }


    public Optional<ExpenseInternalEntity> findById(Long id) {
        return expenseWEWNETRZNYMODELList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }


    public Optional<ExpenseInternalEntity> findByName(String name) {
        return expenseWEWNETRZNYMODELList.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
    }

    public List<ExpenseInternalEntity> findAll() {
        return expenseWEWNETRZNYMODELList;
    }

    public boolean save(ExpenseInternalEntity expenseWEWNETRZNYMODEL) {
        return expenseWEWNETRZNYMODELList.add(expenseWEWNETRZNYMODEL);
    }

    public boolean deleteById(Long id) {
        return expenseWEWNETRZNYMODELList.remove(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new ExpenseInternalEntity(1L, 3400, new ConstructionSiteNo("Wieliczka"), new CostNoForConstructionSiteNo("Transport obcy"), false, "WW"));
        saveIncome(new Income(1L,342,true));
    }

}
