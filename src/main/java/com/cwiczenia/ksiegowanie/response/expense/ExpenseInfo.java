package com.cwiczenia.ksiegowanie.response.expense;

import java.util.List;

public class ExpenseInfo {

    private List<ExpenseResponse> expenseResponses;
    private int sumOfExpenses;
    private int dddd;

    public List<ExpenseResponse> getExpenseResponses() {
        return expenseResponses;
    }

    public void setExpenseResponses(List<ExpenseResponse> expenseResponses) {
        this.expenseResponses = expenseResponses;
    }

    public double getSumOfExpenses() {
        return sumOfExpenses;
    }

    public void setSumOfExpenses(int sumOfExpenses) {
        this.sumOfExpenses = sumOfExpenses;
    }

    public int getDddd() {
        return dddd;
    }

    public void setDddd(int dddd) {
        this.dddd = dddd;
    }
}
