package com.getir.readingisgood.domain.order.data;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getir.readingisgood.common.exception.OrderIdNotFoundException;
import com.getir.readingisgood.domain.order.entity.OrderDetailEntity;
import com.getir.readingisgood.domain.order.entity.OrderEntity;
import com.getir.readingisgood.domain.order.repository.OrderDetailRepository;
import com.getir.readingisgood.domain.order.repository.OrderRepository;

@Service
@Transactional
public class OrderDataService {

	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;

	public OrderDataService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
		this.orderRepository = orderRepository;
		this.orderDetailRepository = orderDetailRepository;
	}

	public OrderEntity saveOrder(OrderEntity entity) {
		return orderRepository.save(entity);
	}

	public OrderEntity getOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow(OrderIdNotFoundException::new);
	}

	public List<OrderDetailEntity> getOrderDetailByOrderId(Long orderId) {
		return orderDetailRepository.findByOrder_Id(orderId);

	}

	public List<OrderEntity> getOrdersByCustomerId(Long customerId) {
		return orderRepository.findByCustomer_Id(customerId);
	}

	public List<OrderEntity> findAllByOrderTimeBetween(Date startDate, Date endDate) {
		return orderRepository.findAllByOrderTimeBetween(startDate, endDate);
	}
}
