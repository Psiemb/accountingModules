package com.cwiczenia.ksiegowanie.response.income;

import com.cwiczenia.ksiegowanie.entity.income.Income;

import java.util.List;

public class IncomeInfo {

    private List<Income> incomes;
    private int sum;

    public IncomeInfo(List<Income> incomes, int sum) {
        this.incomes = incomes;
        this.sum = sum;
    }

    public IncomeInfo() {

    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
