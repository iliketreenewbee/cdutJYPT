package com.example.demo.dao;

import com.example.demo.bean.Products;
import com.example.demo.bean.ProductsExample;
import java.util.List;

import com.example.demo.bean.Shopping_car;
import org.apache.ibatis.annotations.Param;

public interface ProductsMapper {
    int countByExample(ProductsExample example);

    int deleteByExample(ProductsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Products record);

    int insertSelective(Products record);

    List<Products> selectByExample(ProductsExample example);

    Products selectByPrimaryKey(Integer id);

    List<Products> selectCountNotEnough(List<Shopping_car> shopC_list);

    int updateByExampleSelective(@Param("record") Products record, @Param("example") ProductsExample example);

    int updateByExample(@Param("record") Products record, @Param("example") ProductsExample example);

    int updateByPrimaryKeySelective(Products record);

    int updateByPrimaryKey(Products record);

    int updateCountMany(List<Shopping_car> shopC_list);
}