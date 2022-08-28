package com.example.mall.service;

import com.example.mall.model.request.CreateOrderReq;
import com.example.mall.model.vo.OrderStatisticsVO;
import com.example.mall.model.vo.OrderVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface OrderService {
    String create(CreateOrderReq createOrderReq);

    OrderVO detail(String orderNo);

    PageInfo listForCustomer(Integer pageNum, Integer pageSize);

    void cancel(String orderNo);

    String qrCode(String orderNo);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    void pay(@RequestParam String orderNo);

    void deliver(@RequestParam String orderNo);

    void finish(@RequestParam String orderNo);

    List<OrderStatisticsVO> statistics(Date startDate, Date endDate);
}
