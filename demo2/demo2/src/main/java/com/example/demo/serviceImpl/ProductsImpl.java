package com.example.demo.serviceImpl;

import com.example.demo.bean.Products;
import com.example.demo.bean.ProductsExample;
import com.example.demo.bean.Shopping_car;
import com.example.demo.bean.Shopping_carExample;
import com.example.demo.dao.ProductsMapper;
import com.example.demo.dao.Shopping_carMapper;
import com.example.demo.service.ProductsServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("ProductService")
public class ProductsImpl implements ProductsServ {
    @Autowired
    private ProductsMapper productsMapper;
    @Autowired
    private Shopping_carMapper shopping_carMapper;
    @Override
    public List<Products> SelectAll() {
        ProductsExample example = new ProductsExample();
        return productsMapper.selectByExample(example);
    }

    @Override
    public List<Products> SelectByShopOwner(int ownerId) {
        ProductsExample example = new ProductsExample();
        ProductsExample.Criteria criteria =  example.createCriteria();
        criteria.andOwnerIdEqualTo(ownerId);
        return productsMapper.selectByExample(example);
    }

    @Override
    public List<Products> SelectWtithKey(String key) {
        //模糊查询商品名、类别、店铺
        ProductsExample example = new ProductsExample();
        ProductsExample.Criteria criteria1 = example.createCriteria();
        ProductsExample.Criteria criteria2 = example.createCriteria();
        ProductsExample.Criteria criteria3 = example.createCriteria();
        criteria1.andIsvalEqualTo("true");
        criteria1.andTypeNameEqualTo(key);
        criteria2.andIsvalEqualTo("true");
        criteria2.andNameLike(key);
        criteria3.andIsvalEqualTo("true");
        criteria3.andShopOwnerEqualTo(key);
        example.or(criteria2);
        example.or(criteria3);
        return productsMapper.selectByExample(example);
    }

    @Override
    public int Insert(Products products) {
        return productsMapper.insertSelective(products);
    }

    @Override
    public int Delete(int id) {
        return productsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int LogicDelete(int id) {
        Products record = new Products();
        record.setId(id);
        record.setIsval("false");
        return productsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int LogicInsert(int id) {
        Products record = new Products();
        record.setId(id);
        record.setIsval("true");
        return productsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int Update(Products products) {
        //更新购物车中的信息
        Shopping_carExample example = new Shopping_carExample();
        Shopping_carExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(products.getId());
        criteria.andStatusEqualTo("shopc");
        Shopping_car shopC = new Shopping_car();
        shopC.setProductName(products.getName());
        shopC.setPrice(products.getPrice());
        shopC.setImgPath(products.getImgPath());
        shopping_carMapper.updateByExampleSelective(shopC,example);
        //更新商品信息
        return productsMapper.updateByPrimaryKeySelective(products);
    }

    @Override
    public List<Products> SelectNotEnough(List<Shopping_car> shopC_list) {
        return productsMapper.selectCountNotEnough(shopC_list);
    }
}
