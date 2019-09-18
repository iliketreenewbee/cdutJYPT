package com.example.demo.dao;

import com.example.demo.bean.Good_items;
import com.example.demo.bean.Good_itemsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Good_itemsMapper {
    int countByExample(Good_itemsExample example);

    int deleteByExample(Good_itemsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Good_items record);

    int insertSelective(Good_items record);

    List<Good_items> selectByExample(Good_itemsExample example);

    Good_items selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Good_items record, @Param("example") Good_itemsExample example);

    int updateByExample(@Param("record") Good_items record, @Param("example") Good_itemsExample example);

    int updateByPrimaryKeySelective(Good_items record);

    int updateByPrimaryKey(Good_items record);
}