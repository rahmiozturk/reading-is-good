package com.getir.readingisgood.domain.order.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.readingisgood.domain.order.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	List<OrderEntity> findByCustomer_Id(Long customerId);

	List<OrderEntity> findAllByOrderTimeBetween(Date startDate, Date endDate);

}
