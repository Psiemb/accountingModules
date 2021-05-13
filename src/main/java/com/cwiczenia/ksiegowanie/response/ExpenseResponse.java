package com.cwiczenia.ksiegowanie.response;

import java.util.Date;

public class ExpenseResponse {

    private Date actualData = new Date();
    private int costValue;
    

    public ExpenseResponse(Date actualData, int costValue) {
        this.actualData = actualData;
        this.costValue = costValue;
    }
}

