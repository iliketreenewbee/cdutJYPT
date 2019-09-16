package com.example.demo.serviceImpl;

import com.example.demo.bean.User;
import com.example.demo.bean.UserExample;
import com.example.demo.dao.UserMapper;
import com.example.demo.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserImpl implements UserServ {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int Insert(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int Delete(int id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int BanUser(int id) {
        User record = new User();
        record.setId(id);
        record.setType("ban");
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<User> SelectAll() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public List<User> LoginByPhone(Integer phone) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone);
        return userMapper.selectByExample(example);
    }

    @Override
    public int UpdateInfo(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
