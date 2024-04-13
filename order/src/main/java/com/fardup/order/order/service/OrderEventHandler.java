package com.fardup.order.order.service;

import com.fardup.order.order.controller.model.OrderCreateModel;
import com.fardup.order.order.orm.Order;
import com.fardup.order.order.orm.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
//@RequiredArgsConstructor
public class OrderEventHandler {

    @Autowired
    private OrderRepository orderRepository;

    @EventHandler
    @Transactional
    public void create(OrderCreateModel orderCreateModel){
        Order order=new Order();
        BeanUtils.copyProperties(orderCreateModel,order);
        orderRepository.save(order);
    }
}
