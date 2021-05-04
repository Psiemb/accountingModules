package com.cwiczenia.ksiegowanie.entity;

import javax.xml.crypto.Data;

public class Income {

    private Data actualDate;
    private double incomeValue;
    private boolean receivedPayment;

    public Income() {
    }

    public Income(double incomeValue) {
        this.incomeValue = incomeValue;
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

    public double getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(double incomeValue) {
        this.incomeValue = incomeValue;
    }

    public boolean isReceivedPayment() {
        return receivedPayment;
    }

    public void setReceivedPayment(boolean receivedPayment) {
        this.receivedPayment = receivedPayment;
    }
}
