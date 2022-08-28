package com.example.mall.model.dao;

import com.example.mall.model.entity.Order;
import com.example.mall.model.query.OrderStatisticsQuery;
import com.example.mall.model.vo.OrderStatisticsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByOrderNo(String orderNo);

    List<Order> selectForCustomer(Integer userId);

    List<Order> selectAllForAdmin();

    List<OrderStatisticsVO> selectOrderStatistics(@Param("query") OrderStatisticsQuery query);
}