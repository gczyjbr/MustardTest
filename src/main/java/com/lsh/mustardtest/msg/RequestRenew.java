package com.lsh.mustardtest.msg;

import java.util.Date;

/**
 * 刘森华
 * 2019/04/19
 */

public class RequestRenew {
    private String orderCode; //订单编号
    private Integer productID;
    private Date createDate;    //创建日期
    private Integer duration;   //时长
    private Date endDate;       //截至时间

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
