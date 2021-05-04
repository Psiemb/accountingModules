package com.cwiczenia.ksiegowanie.response;

import com.cwiczenia.ksiegowanie.entity.Expense;

import java.util.List;

public class ExpenseInfo {

    private List<Expense> list;
    private double sumOfExpenses;

    public List<Expense> getList() {
        return list;
    }

    public void setList(List<Expense> list) {
        this.list = list;
    }

    public double getSumOfExpenses() {
        return sumOfExpenses;
    }

    public void setSumOfExpenses(double sumOfExpenses) {
        this.sumOfExpenses = sumOfExpenses;
    }
}
