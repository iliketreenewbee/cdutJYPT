package com.example.demo.service;

import com.example.demo.bean.User;

import java.util.List;

public interface UserServ {

    int Insert(User user);

    int Delete(int id);

    int BanUser(int id);

    List<User> SelectAll();

    List<User> LoginByPhone(String phone);

    int UpdateInfo(User user);
}
