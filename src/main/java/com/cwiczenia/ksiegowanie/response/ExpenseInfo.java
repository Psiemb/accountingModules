package com.cwiczenia.ksiegowanie.response;

import com.cwiczenia.ksiegowanie.entity.ExpenseWEWNETRZNY_MODEL;

import java.util.List;

public class ExpenseInfo {

//    private List<ExpenseWEWNETRZNY_MODEL> list;
//    private List<JakisObiektDlaKlienta> list;
    private List<ExpenseResponse> expenseResponses;
    private double sumOfExpenses;

//    public List<ExpenseWEWNETRZNY_MODEL> getList() {
//        return list;
//    }
//
//    public void setList(List<ExpenseWEWNETRZNY_MODEL> list) {
//        this.list = list;
//    }


    public List<ExpenseResponse> getExpenseResponses() {
        return expenseResponses;
    }

    public void setExpenseResponses(List<ExpenseResponse> expenseResponses) {
        this.expenseResponses = expenseResponses;
    }

    public double getSumOfExpenses() {
        return sumOfExpenses;
    }

    public void setSumOfExpenses(double sumOfExpenses) {
        this.sumOfExpenses = sumOfExpenses;
    }
}
