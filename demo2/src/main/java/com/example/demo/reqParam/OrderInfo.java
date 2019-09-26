package com.example.demo.reqParam;

import com.example.demo.bean.Order;
import com.example.demo.bean.Shopping_car;

import java.util.List;

public class OrderInfo {
    private List<Order> order_list;
    private List<Shopping_car> shopC_list;

    public List<Order> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<Order> order_list) {
        this.order_list = order_list;
    }

    public List<Shopping_car> getShopC_list() {
        return shopC_list;
    }

    public void setShopC_list(List<Shopping_car> shopC_list) {
        this.shopC_list = shopC_list;
    }
}
