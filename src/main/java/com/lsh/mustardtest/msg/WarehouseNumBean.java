package com.lsh.mustardtest.msg;

/**
 * 刘森华
 * 2019/04/19
 */

public class WarehouseNumBean {
    private Integer wareHouseID;
    private int numSS;
    private int numS;
    private int numM;
    private int numL;

    public WarehouseNumBean(Integer wareHouseID, int numSS, int numS, int numM, int numL){
        this.wareHouseID = wareHouseID;
        this.numSS = numSS;
        this.numS = numS;
        this.numM = numM;
        this.numL = numL;
    }

    public Integer getWareHouseID() {
        return wareHouseID;
    }

    public void setWareHouseID(Integer wareHouseID) {
        this.wareHouseID = wareHouseID;
    }

    public int getNumSS() {
        return numSS;
    }

    public void setNumSS(int numSS) {
        this.numSS = numSS;
    }

    public int getNumS() {
        return numS;
    }

    public void setNumS(int numS) {
        this.numS = numS;
    }

    public int getNumM() {
        return numM;
    }

    public void setNumM(int numM) {
        this.numM = numM;
    }

    public int getNumL() {
        return numL;
    }

    public void setNumL(int numL) {
        this.numL = numL;
    }
}
