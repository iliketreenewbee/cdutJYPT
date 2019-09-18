package com.example.demo.bean;

import java.util.Date;

public class Order {
    private Integer id;

    private Integer userId;

    private Integer ownerId;

    private Float allPrice;

    private Date time;

    private String orderStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Float getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Float allPrice) {
        this.allPrice = allPrice;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }
}