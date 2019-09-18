package com.example.demo.dao;

import com.example.demo.bean.Shopping_car;
import com.example.demo.bean.Shopping_carExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Shopping_carMapper {
    int countByExample(Shopping_carExample example);

    int deleteByExample(Shopping_carExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Shopping_car record);

    int insertSelective(Shopping_car record);

    List<Shopping_car> selectByExample(Shopping_carExample example);

    Shopping_car selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Shopping_car record, @Param("example") Shopping_carExample example);

    int updateByExample(@Param("record") Shopping_car record, @Param("example") Shopping_carExample example);

    int updateByPrimaryKeySelective(Shopping_car record);

    int updateByPrimaryKey(Shopping_car record);

    int updateShopcToOrder(List<Shopping_car> shopC_list);
}