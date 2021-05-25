package com.cwiczenia.ksiegowanie.entity.income;

import javax.xml.crypto.Data;

public class Income {

    private Long id;
    private Data actualDate;
    private int incomeValue;
    private boolean receivedPayment;

    public Income() {
    }

    public Income(int incomeValue) {
        this.incomeValue = incomeValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Income(boolean receivedPayment) {
        this.receivedPayment = receivedPayment;
    }

    public Data getActualDate() {
        return actualDate;
    }

    public void setActualDate(Data actualDate) {
        this.actualDate = actualDate;
    }

    public int getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(int incomeValue) {
        this.incomeValue = incomeValue;
    }

    public boolean isReceivedPayment() {
        return receivedPayment;
    }

    public void setReceivedPayment(boolean receivedPayment) {
        this.receivedPayment = receivedPayment;
    }

}
