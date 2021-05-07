package com.cwiczenia.ksiegowanie.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Expense {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Date actualData = new Date();
    private int costValue;
    private ConstructionSiteNo constructionSiteNo;
    private CostNoForConstructionSiteNo costNoForConstructionSiteNo;
    private boolean paidCost;


    public Expense() {
    }

    public Expense(boolean paidCost) {
        this.paidCost = paidCost;
    }

    public Expense(Long id, int costValue, ConstructionSiteNo constructionSiteNo, CostNoForConstructionSiteNo costNoForConstructionSiteNo, boolean paidCost) {
        this.id = id;
        this.costValue = costValue;
        this.constructionSiteNo = constructionSiteNo;
        this.costNoForConstructionSiteNo = costNoForConstructionSiteNo;
        this.paidCost = paidCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getActualData() {
        return actualData;
    }

    public void setActualData(Date actualData) {
        this.actualData = actualData;
    }

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
