package com.cwiczenia.ksiegowanie.dao;

import com.cwiczenia.ksiegowanie.entity.ConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.CostNoForConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.ExpenseWEWNETRZNY_MODEL;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountingManager {

    //    private AccoutingRepo accoutingRepo;
    private List<ExpenseWEWNETRZNY_MODEL> expenseWEWNETRZNYMODELList = new ArrayList<>();

//    @Autowired
//    public AccountingManager(AccoutingRepo accoutingRepo) {
//        this.accoutingRepo = accoutingRepo;
//    }

    public Optional<ExpenseWEWNETRZNY_MODEL> findById(Long id) {
//        return accoutingRepo.findById(id);
        return expenseWEWNETRZNYMODELList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<ExpenseWEWNETRZNY_MODEL> findAll() {
//        return accoutingRepo.findAll();
        return expenseWEWNETRZNYMODELList;
    }

    public boolean save(ExpenseWEWNETRZNY_MODEL expenseWEWNETRZNYMODEL) {
//        return accoutingRepo.save(expense);
        return expenseWEWNETRZNYMODELList.add(expenseWEWNETRZNYMODEL);
    }

    public void deleteById(Long id) {
//        accoutingRepo.deleteById(id);
        expenseWEWNETRZNYMODELList.remove(id);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new ExpenseWEWNETRZNY_MODEL(1L, 3400, new ConstructionSiteNo("Wieliczka"), new CostNoForConstructionSiteNo("Transport obcy"), false));
        save(null);
    }

}
