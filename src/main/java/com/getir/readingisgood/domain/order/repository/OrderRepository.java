package com.getir.readingisgood.domain.order.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.getir.readingisgood.domain.customer.model.CustomerReportDto;
import com.getir.readingisgood.domain.order.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	List<OrderEntity> findByCustomer_Id(Long customerId);

	List<OrderEntity> findAllByOrderTimeBetween(Date startDate, Date endDate);

    @Query(value = "SELECT MONTHNAME(o.order_time) as Month, "
    		+ "count(o.id) as TotalOrderCount, "
    		+ "sum(o.total_amount) as TotalBookCount, "
    		+ "sum(o.total_price) as TotalPrice "
    		+ "FROM ORDERS o "
    		+ "WHERE o.customer_id =?1  AND YEAR(o.order_time) = YEAR(CURRENT_TIMESTAMP) "
    		+ "GROUP BY MONTHNAME(o.order_time)", nativeQuery=true)
	List<CustomerReportDto> getStatsReport(Long customerId);

}
