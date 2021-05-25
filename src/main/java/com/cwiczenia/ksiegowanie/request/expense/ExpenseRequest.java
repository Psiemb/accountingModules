package com.cwiczenia.ksiegowanie.request.expense;

import com.cwiczenia.ksiegowanie.entity.expense.ConstructionSiteNo;
import com.cwiczenia.ksiegowanie.entity.expense.CostNoForConstructionSiteNo;

public class ExpenseRequest {

    private int costValue;
    private ConstructionSiteNo constructionSiteNo;
    private CostNoForConstructionSiteNo costNoForConstructionSiteNo;
    private boolean paidCost;

    public int getCostValue() {
        return costValue;
    }

    public void setCostValue(int costValue) {
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
