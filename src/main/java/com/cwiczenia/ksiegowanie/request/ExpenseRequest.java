package com.cwiczenia.ksiegowanie.request;

import com.cwiczenia.ksiegowanie.entity.ConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.CostNoForConstructionSiteNo;

public class ExpenseRequest {

    private double costValue;
    private ConstructionSiteNo constructionSiteNo;
    private CostNoForConstructionSiteNo costNoForConstructionSiteNo;
    private boolean paidCost;

    public double getCostValue() {
        return costValue;
    }

    public void setCostValue(double costValue) {
        this.costValue = costValue;
    }

    public ConstructionSiteNo getConstructionSiteNo() {
        return constructionSiteNo;
    }

    public void setConstructionSiteNo(ConstructionSiteNo constructionSiteNo) {
        this.constructionSiteNo = constructionSiteNo;
    }

    public CostNoForConstructionSiteNo getCostNoForConstructionSiteNo() {
        return costNoForConstructionSiteNo;
    }

    public void setCostNoForConstructionSiteNo(CostNoForConstructionSiteNo costNoForConstructionSiteNo) {
        this.costNoForConstructionSiteNo = costNoForConstructionSiteNo;
    }

    public boolean isPaidCost() {
        return paidCost;
    }

    public void setPaidCost(boolean paidCost) {
        this.paidCost = paidCost;
    }
}