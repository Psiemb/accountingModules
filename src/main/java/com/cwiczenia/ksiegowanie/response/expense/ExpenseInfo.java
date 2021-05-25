package com.cwiczenia.ksiegowanie.response.expense;

import java.util.List;

public class ExpenseInfo {

    private List<ExpenseResponse> expenseResponses;
    private int sumOfExpenses;

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
}
