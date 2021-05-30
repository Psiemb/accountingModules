package com.cwiczenia.ksiegowanie.response.income;

import com.cwiczenia.ksiegowanie.entity.income.Income;

import java.util.List;

public class IncomeInfo {

    private List<IncomeResponse> incomes;
    private int sum;

    public IncomeInfo() {
    }

    public IncomeInfo(List<IncomeResponse> incomes, int sum) {
        this.incomes = incomes;
        this.sum = sum;
    }

    public List<IncomeResponse> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<IncomeResponse> incomes) {
        this.incomes = incomes;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
