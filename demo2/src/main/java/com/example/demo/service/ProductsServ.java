package com.example.demo.service;

import com.example.demo.bean.Products;
import com.example.demo.bean.Shopping_car;

import java.util.List;

public interface ProductsServ {

    List<Products> SelectAll();

    List<Products> SelectByShopOwner(int ownerId);

    List<Products> SelectWtithKey(String key);

    int Insert(Products products);

    int Delete(int id);

    int LogicDelete(int id);

    int LogicInsert(int id);

    int Update(Products products);

    List<Products> SelectNotEnough(List<Shopping_car> shopC_list);
}
