package com.fardup.order.order.service;

import com.fardup.order.order.controller.model.OrderCreateModel;
import com.fardup.order.order.orm.Order;
import com.fardup.order.order.orm.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@ProcessingGroup("order-group")
public class OrderEventHandler {


    private final OrderRepository orderRepository;

    @EventHandler
    @Transactional
    public void create(OrderCreateModel orderCreateModel){
        Order order=new Order();
        BeanUtils.copyProperties(orderCreateModel,order);
        order.setId(null);
        orderRepository.save(order);
    }
}
