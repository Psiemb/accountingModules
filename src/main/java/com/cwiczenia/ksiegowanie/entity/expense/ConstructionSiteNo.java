package com.cwiczenia.ksiegowanie.entity.expense;


public class ConstructionSiteNo {

    private String construction;

    public ConstructionSiteNo(String construction) {
        this.construction = construction;
    }

    public ConstructionSiteNo() {

    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }
}
