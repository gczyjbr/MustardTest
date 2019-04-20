package com.lsh.mustardtest.msg;

import java.util.Date;

/**
 * 刘森华
 * 2019/04/19
 */

public class RequestOrder {
    private String orderCode;   //订单编号
    private String address;     //用户地址
    private String sender;      //用户姓名
    private Integer mobile;     //电话
    private String userMessage; //备注
    private Date createDate;    //创建日期
    private Date payDate;       //付款时间
    private Date endDate;       //截至时间
    private String status;      //状态
    private Integer duration;   //时长
    private Integer userID;     //用户id

    WarehouseNumBean warehouseNumBean;  //各类型产品数量

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public WarehouseNumBean getWarehouseNumBean() {
        return warehouseNumBean;
    }

    public void setWarehouseNumBean(WarehouseNumBean warehouseNumBean) {
        this.warehouseNumBean = warehouseNumBean;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
