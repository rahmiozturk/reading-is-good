package com.getir.readingisgood.domain.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.readingisgood.domain.order.entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {

	List<OrderDetailEntity> findByOrder_Id(Long orderId);

}
