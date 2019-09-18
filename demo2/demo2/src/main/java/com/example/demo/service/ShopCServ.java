package com.example.demo.service;

import com.example.demo.bean.Shopping_car;

import java.util.List;

public interface ShopCServ {

    int InsertGood(Shopping_car shopC);

    int Delete(int shopCId);

    List<Shopping_car> SelectShopC(int UserId);

    List<Shopping_car> SelectOrder(int OrderId);
}
