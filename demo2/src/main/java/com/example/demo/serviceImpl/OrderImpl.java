package com.example.demo.serviceImpl;

import com.example.demo.bean.Order;
import com.example.demo.bean.OrderExample;
import com.example.demo.bean.Shopping_car;
import com.example.demo.bean.Shopping_carExample;
import com.example.demo.dao.OrderMapper;
import com.example.demo.dao.ProductsMapper;
import com.example.demo.dao.Shopping_carMapper;
import com.example.demo.service.OrderServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public int Insert(List<Order> order_list, List<Shopping_car> shopC_list) {
        System.out.println("开始插入订单");
        //插入新增的order记录
        Date orderTime = new Date();
        for(int i=0;i<order_list.size();i++){
            System.out.println("所属类："+order_list.get(0).getClass());
            order_list.get(i).setTime(orderTime);
            order_list.get(i).setOrderStatus("已下单");
        }
      int flag = orderMapper.insertMany(order_list);
        System.out.println("插入订单完成");
       for(int i=0;i<order_list.size();i++){
            System.out.println("插入id为："+order_list.get(i).getId());
        }
        System.out.println("当前订单id："+shopC_list.get(0).getOrderId());
        for(int i=0;i<shopC_list.size();i++){
           Shopping_car shopC = shopC_list.get(i);
            for(int j=0;j<order_list.size();j++){
                Order order = order_list.get(j);
                if(shopC.getOwnerId() == order.getOwnerId()){
                    shopC_list.get(i).setOrderId(order.getId());
                }
            }
        }
        System.out.println("当前订单id："+shopC_list.get(0).getOrderId());
        //更新购物车中的商品状态shopc为order
        shopping_carMapper.updateShopcToOrder(shopC_list);
        //更新商品表中的商品数量
        productsMapper.updateCountMany(shopC_list);
        return flag;
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
    @Transactional
    public int UpdateSatus(Order order) {
        if (order.getOrderStatus().equals("退单")){
            Shopping_carExample example = new Shopping_carExample();
            Shopping_carExample.Criteria criteria = example.createCriteria();
            criteria.andOrderIdEqualTo(order.getId());
            Shopping_car shopC = new Shopping_car();
            shopC.setStatus("back");
            shopping_carMapper.updateByExampleSelective(shopC,example);
            List<Shopping_car> shopC_list = shopping_carMapper.selectByExample(example);
            for (int i=0;i<shopC_list.size();i++){
                int count = -shopC_list.get(i).getCount();
                shopC_list.get(i).setCount(count);
            }
            productsMapper.updateCountMany(shopC_list);
            return orderMapper.updateByPrimaryKey(order);
        }else {
            return orderMapper.updateByPrimaryKey(order);
        }
    }
}
