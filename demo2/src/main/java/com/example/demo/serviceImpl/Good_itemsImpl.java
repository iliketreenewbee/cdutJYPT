package com.example.demo.serviceImpl;

import com.example.demo.bean.Good_items;
import com.example.demo.bean.Good_itemsExample;
import com.example.demo.bean.Products;
import com.example.demo.bean.ProductsExample;
import com.example.demo.dao.Good_itemsMapper;
import com.example.demo.dao.ProductsMapper;
import com.example.demo.service.Good_itemsServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("GooditemService")
public class Good_itemsImpl implements Good_itemsServ{
    @Autowired
    private Good_itemsMapper good_itemsMapper;
    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public int Insert(Good_items goodItems) {
        return good_itemsMapper.insert(goodItems);
    }

    @Override
    @Transactional
    public int Delete(int id) {
        //先将该类商品下架
        Products record = new Products();
        record.setIsval("false");
        ProductsExample example = new ProductsExample();
        ProductsExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(id);
        productsMapper.updateByExampleSelective(record,example);
        //删除该类别
        return good_itemsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int LogicDelete(int id) {
        //先将该类商品下架
        Products record = new Products();
        record.setIsval("false");
        ProductsExample example = new ProductsExample();
        ProductsExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(id);
        productsMapper.updateByExampleSelective(record,example);
        //逻辑删除该类别
        Good_items good_items_record = new Good_items();
        good_items_record.setIsval("false");
        return good_itemsMapper.updateByPrimaryKeySelective(good_items_record);
    }

    @Override
    public int LogicInsert(int id) {
        //先将该类商品上架
        Products record = new Products();
        record.setIsval("true");
        ProductsExample example = new ProductsExample();
        ProductsExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(id);
        productsMapper.updateByExampleSelective(record,example);
        //逻辑增加该类别
        Good_items good_items_record = new Good_items();
        good_items_record.setIsval("true");
        return good_itemsMapper.updateByPrimaryKeySelective(good_items_record);
    }

    @Override
    public List<Good_items> SelectAll() {
        Good_itemsExample example = new Good_itemsExample();
        return good_itemsMapper.selectByExample(example);
    }

    @Override
    public List<Good_items> SelectIsVal() {
        Good_itemsExample example = new Good_itemsExample();
        Good_itemsExample.Criteria criteria = example.createCriteria();
        criteria.andIsvalEqualTo("true");
        return good_itemsMapper.selectByExample(example);
    }

    @Override
    public List<Good_items> SelectWithKey(String key) {
        Good_itemsExample example = new Good_itemsExample();
        Good_itemsExample.Criteria criteria = example.createCriteria();
        criteria.andIsvalEqualTo("true");
        criteria.andItemLike(key);
        return good_itemsMapper.selectByExample(example);
    }

    @Override
    public int Update(Good_items goodItems) {
        //先将该类商品上架
        Products record = new Products();
        record.setTypeName(goodItems.getItem());
        ProductsExample example = new ProductsExample();
        ProductsExample.Criteria criteria = example.createCriteria();
        criteria.andTypeIdEqualTo(goodItems.getId());
        productsMapper.updateByExampleSelective(record,example);
        return good_itemsMapper.updateByPrimaryKeySelective(goodItems);
    }
}
