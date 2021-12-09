package com.microservices.orders.services;

import java.util.List;
import java.util.Optional;


import com.microservices.orders.dtos.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.orders.entity.Order;
import com.microservices.orders.repositories.OrderRepository;

@Service
@Slf4j
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public String addorder(  Order order ){
		orderRepository.insert( order );
		return ("Added Successfully");		
	}
	
	
    public List<Order> fetchAllOrders() {
		return  orderRepository.findAll();
    }

	public Order update(Order order) {
		Optional<Order> optionalOrder = Optional.ofNullable(orderRepository.findByOrderId(order.getOrderId()));
		if(optionalOrder.isPresent()){
			return  orderRepository.save(order);
		}else{
			return null;
		}

	}

	public ResponseDto delete(Order order) {
		Optional<Order> optionalOrder = Optional.ofNullable(orderRepository.findByOrderId(order.getOrderId()));
		if(optionalOrder.isPresent()){
			orderRepository.delete(order);
			return new  ResponseDto(true);
		}else{
			return new  ResponseDto(false);
		}

	}
}
