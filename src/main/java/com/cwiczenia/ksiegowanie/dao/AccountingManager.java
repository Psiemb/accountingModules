package com.cwiczenia.ksiegowanie.dao;

import com.cwiczenia.ksiegowanie.entity.ConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.CostNoForConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountingManager {

    //    private AccoutingRepo accoutingRepo;
    private List<Expense> expenseList = new ArrayList<>();

    @Autowired
    public AccountingManager(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

//    @Autowired
//    public AccountingManager(AccoutingRepo accoutingRepo) {
//        this.accoutingRepo = accoutingRepo;
//    }

    public Optional<Expense> findById(Long id) {
//        return accoutingRepo.findById(id);
        return expenseList.stream()
                .filter(e -> e.getId().equals(id))
                .findAny();
    }

    public Iterable<Expense> findAll() {
//        return accoutingRepo.findAll();
        return expenseList;
    }

    public boolean save(Expense expense) {
//        return accoutingRepo.save(expense);
        return expenseList.add(expense);
    }

    public void deleteById(Long id) {
//        accoutingRepo.deleteById(id);
        expenseList.remove(id);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new Expense(1L, 3400, new ConstructionSiteNo("Wieliczka"), new CostNoForConstructionSiteNo("Transport obcy"), false));
    }

}
