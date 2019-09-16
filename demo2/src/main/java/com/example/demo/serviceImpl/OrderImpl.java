package com.example.demo.serviceImpl;

import com.example.demo.bean.Order;
import com.example.demo.bean.OrderExample;
import com.example.demo.bean.Shopping_car;
import com.example.demo.dao.OrderMapper;
import com.example.demo.dao.ProductsMapper;
import com.example.demo.dao.Shopping_carMapper;
import com.example.demo.service.OrderServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("OrderService")
public class OrderImpl implements OrderServ {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private Shopping_carMapper shopping_carMapper;
    @Autowired
    private ProductsMapper productsMapper;

    @Override
    @Transactional
    public int Insert(Order order, List<Shopping_car> shopC_list) {
        //更新购物车中的商品状态shopc为order
        shopping_carMapper.updateShopcToOrder(shopC_list);
        //更新商品表中的商品数量
        productsMapper.updateCountMany(shopC_list);
        //插入新增的order记录
        return orderMapper.insert(order);
    }

    @Override
    public int Delete(int OrderId) {
        return orderMapper.deleteByPrimaryKey(OrderId);
    }

    @Override
    public int LogicDelete(int OrderId) {
        Order record = new Order();
        record.setId(OrderId);
        record.setOrderStatus("logicDel");
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Order> SelectAll() {
        return orderMapper.selectByExample(new OrderExample());
    }

    @Override
    public List<Order> SelectByUserId(int UserId) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(UserId);
        criteria.andOrderStatusNotEqualTo("logicDel");
        return orderMapper.selectByExample(example);
    }

    @Override
    public List<Order> SelectByOwnerId(int OwnerId) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(OwnerId);
        return orderMapper.selectByExample(example);
    }

    @Override
    public int UpdateSatus(Order order) {
        return orderMapper.updateByPrimaryKey(order);
    }
}
