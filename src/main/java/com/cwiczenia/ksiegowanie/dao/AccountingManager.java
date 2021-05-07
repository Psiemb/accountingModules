package com.cwiczenia.ksiegowanie.dao;

import com.cwiczenia.ksiegowanie.AccoutingRepo;
import com.cwiczenia.ksiegowanie.entity.ConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.CostNoForConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountingManager {

    private AccoutingRepo accoutingRepo;

    @Autowired
    public AccountingManager(AccoutingRepo accoutingRepo) {
        this.accoutingRepo = accoutingRepo;
    }

    public Optional<Expense> findById(Long id){
        return accoutingRepo.findById(id);
    }

    public Iterable<Expense> findAll(){
        return accoutingRepo.findAll();
    }

    public Expense save(Expense expense){
        return accoutingRepo.save(expense);
    }

    public void deleteById(Long id){
        accoutingRepo.deleteById(id);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
//        save(new Expense(1L, 3400, "Wieliczka","Transport obcy", false));
    }

}
