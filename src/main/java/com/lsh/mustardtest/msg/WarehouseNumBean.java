package com.lsh.mustardtest.msg;

/**
 * 刘森华
 * 2019/04/19
 */

public class WarehouseNumBean {
    private Integer warehouseID;
    private int numSS;
    private int numS;
    private int numM;
    private int numL;

    public WarehouseNumBean(Integer warehouseID, int numSS, int numS, int numM, int numL) {
        this.warehouseID = warehouseID;
        this.numSS = numSS;
        this.numS = numS;
        this.numM = numM;
        this.numL = numL;
    }

    public Integer getWareHouseID() {
        return warehouseID;
    }

    public void setWareHouseID(Integer wareHouseID) {
        this.warehouseID = wareHouseID;
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
