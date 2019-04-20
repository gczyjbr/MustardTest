package com.lsh.mustardtest.msg;

/**
 * 刘森华
 * 2019/04/19
 */

public class RequestAlterState {

    private String orderCode;   //订单编号
    private String status;      //状态
    WarehouseNumBean warehouseNumBean;  //各仓库数量

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

    public WarehouseNumBean getWarehouseNumBean() {
        return warehouseNumBean;
    }

    public void setWarehouseNumBean(WarehouseNumBean warehouseNumBean) {
        this.warehouseNumBean = warehouseNumBean;
    }
}
