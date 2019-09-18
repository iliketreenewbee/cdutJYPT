package com.example.demo.service;

import com.example.demo.bean.Good_items;

import java.util.List;

public interface Good_itemsServ {

    int Insert(Good_items goodItems);

    int Delete(int id);

    int LogicDelete(int id);

    int LogicInsert(int id);

    List<Good_items> SelectAll();

    List<Good_items> SelectIsVal();

    List<Good_items> SelectWithKey(String key);

    int Update(Good_items goodItems);
}
