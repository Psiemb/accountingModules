package com.cwiczenia.ksiegowanie.response.income;

import javax.xml.crypto.Data;

public class IncomeResponse {

    private Data actualDate;
    private int incomeValue;
    private boolean receivedPayment;

    public IncomeResponse(Data actualDate, int incomeValue, boolean receivedPayment) {
        this.actualDate = actualDate;
        this.incomeValue = incomeValue;
        this.receivedPayment = receivedPayment;
    }

    public IncomeResponse() {

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
