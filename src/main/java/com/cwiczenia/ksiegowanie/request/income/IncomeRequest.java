package com.cwiczenia.ksiegowanie.request.income;

import javax.xml.crypto.Data;

public class IncomeRequest {
    private int incomeValue;
    private boolean receivedPayment;

    public IncomeRequest(Long id, int incomeValue, boolean receivedPayment) {

        this.incomeValue = incomeValue;
        this.receivedPayment = receivedPayment;
    }

    public IncomeRequest() {

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
