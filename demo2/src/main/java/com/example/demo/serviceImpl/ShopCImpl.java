package com.example.demo.serviceImpl;

import com.example.demo.bean.Shopping_car;
import com.example.demo.bean.Shopping_carExample;
import com.example.demo.dao.Shopping_carMapper;
import com.example.demo.service.ShopCServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("ShopCService")
public class ShopCImpl implements ShopCServ {
    @Autowired
    private Shopping_carMapper shopping_carMapper;
    @Override
    public int InsertGood(Shopping_car shopC) {
        shopC.setTime(new Date());
        return shopping_carMapper.insertSelective(shopC);
    }

    @Override
    public int Delete(int shopCId) {
        return shopping_carMapper.deleteByPrimaryKey(shopCId);
    }

    @Override
    public List<Shopping_car> SelectShopC(int UserId) {
        Shopping_carExample example = new Shopping_carExample();
        Shopping_carExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(UserId);
        criteria.andStatusEqualTo("shopc");
        return shopping_carMapper.selectByExample(example);
    }

    @Override
    public List<Shopping_car> SelectOrder(int OrderId) {
        Shopping_carExample example = new Shopping_carExample();
        Shopping_carExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(OrderId);
        criteria.andStatusEqualTo("order");
        return shopping_carMapper.selectByExample(example);
    }
}
