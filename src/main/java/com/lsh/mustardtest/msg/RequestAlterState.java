package com.lsh.mustardtest.msg;

/**
 * 刘森华
 * 2019/04/19
 */

public class RequestAlterState {

    private String orderCode;   //订单编号
    private String status;      //状态

    private Integer warehouseID;
    private int numSS;
    private int numS;
    private int numM;
    private int numL;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(Integer warehouseID) {
        this.warehouseID = warehouseID;
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
