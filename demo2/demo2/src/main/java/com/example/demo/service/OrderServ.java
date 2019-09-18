package com.example.demo.service;

import com.example.demo.bean.Order;
import com.example.demo.bean.Shopping_car;

import java.util.List;

public interface OrderServ {

    int Insert(List<Order> order_list, List<Shopping_car> shopC_list);

    int Delete(int OrderId);

    int LogicDelete(int OrderId);

    List<Order> SelectAll();

    List<Order> SelectByUserId(int UserId);

    List<Order> SelectByOwnerId(int OwnerId);

    int UpdateSatus(Order order);
}
