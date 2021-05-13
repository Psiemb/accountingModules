package com.cwiczenia.ksiegowanie.response;

import java.util.Date;

public class ExpenseResponse {

    private Date actualDataR = new Date();
    private int costValueR;
    private boolean paidCostR;

    public ExpenseResponse() {
    }

    public ExpenseResponse(Date actualDataR, int costValueR, boolean paidCostR) {
        this.actualDataR = actualDataR;
        this.costValueR = costValueR;
        this.paidCostR = paidCostR;
    }

    public Date getActualDataR() {
        return actualDataR;
    }

    public void setActualDataR(Date actualDataR) {
        this.actualDataR = actualDataR;
    }

    public int getCostValueR() {
        return costValueR;
    }

    public void setCostValueR(int costValueR) {
        this.costValueR = costValueR;
    }

    public boolean isPaidCostR() {
        return paidCostR;
    }

    public void setPaidCostR(boolean paidCostR) {
        this.paidCostR = paidCostR;
    }
}

