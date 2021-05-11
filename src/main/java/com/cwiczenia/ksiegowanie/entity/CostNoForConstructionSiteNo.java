package com.cwiczenia.ksiegowanie.entity;

public class CostNoForConstructionSiteNo {

    private String type;

    public CostNoForConstructionSiteNo(String type) {
        this.type = type;
    }

    //    PLACE, TRANSPORT_OBCY
//    , TRANSPORT_WLASNY, MATERIALY_PODSTAWOWE, ZURAWIE,
//    ZAPLECZE_TOALETY, MATERIALY_POMOCNICZE_PALIWO,OCHRONA,SZALUNKI, KOSZTY_INNE


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
