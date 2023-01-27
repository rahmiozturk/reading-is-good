package com.getir.readingisgood.domain.order.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.getir.readingisgood.common.exception.NotEnoughStockException;
import com.getir.readingisgood.domain.book.data.BookDataService;
import com.getir.readingisgood.domain.book.entity.BookEntity;
import com.getir.readingisgood.domain.customer.data.CustomerDataService;
import com.getir.readingisgood.domain.customer.entity.CustomerEntity;
import com.getir.readingisgood.domain.order.business.OrderService;
import com.getir.readingisgood.domain.order.converter.OrderConverter;
import com.getir.readingisgood.domain.order.converter.OrderDetailConverter;
import com.getir.readingisgood.domain.order.data.OrderDataService;
import com.getir.readingisgood.domain.order.entity.OrderDetailEntity;
import com.getir.readingisgood.domain.order.entity.OrderEntity;
import com.getir.readingisgood.domain.order.entity.OrderEntity.OrderStatus;
import com.getir.readingisgood.domain.order.model.OrderDetailDto;
import com.getir.readingisgood.domain.order.model.OrderDto;
import com.getir.readingisgood.domain.order.model.request.CreateOrderRequest;
import com.getir.readingisgood.domain.order.model.request.DateIntervalRequest;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderDataService orderDataService;
	private final OrderConverter orderConverter;
	private final CustomerDataService customerDataService;
	private final BookDataService bookDataService;
	private final OrderDetailConverter orderDetailConverter;

	public OrderServiceImpl(OrderDataService orderDataService, OrderConverter orderConverter,
			CustomerDataService customerDataService, BookDataService bookDataService,
			OrderDetailConverter orderDetailConverter) {
		this.orderDataService = orderDataService;
		this.orderConverter = orderConverter;
		this.customerDataService = customerDataService;
		this.bookDataService = bookDataService;
		this.orderDetailConverter = orderDetailConverter;
	}

	@Override
	@Transactional
	public OrderDto createOrder(CreateOrderRequest createReq) {
		CustomerEntity customerEntity = customerDataService.getCustomerById(createReq.getCustomerId());
		List<OrderDetailEntity> orderDetailList = new ArrayList<OrderDetailEntity>();
		OrderEntity orderEntity = new OrderEntity();
		// order details
		createReq.getBookList().stream().forEach(req -> {
			BookEntity bookEntity = bookDataService.getBookById(req.getBookId());
			int remaningAmount = bookEntity.getAmount() - req.getAmount();
			if (remaningAmount < 0) {
				throw new NotEnoughStockException();
			}
			bookEntity.setAmount(remaningAmount);
			bookDataService.saveBook(bookEntity);
			OrderDetailEntity detailEntity = new OrderDetailEntity();
			detailEntity.setAmount(req.getAmount());
			detailEntity.setBookId(bookEntity.getId());
			detailEntity.setBookName(bookEntity.getBookName());
			detailEntity.setPrice(bookEntity.getPrice());
			detailEntity.setOrder(orderEntity);
			orderDetailList.add(detailEntity);
		});

		orderEntity.setCustomer(customerEntity);
		orderEntity.setOrderTime(new Date());
		orderEntity.setOrderDetails(orderDetailList);
		orderEntity.setOrderStatus(OrderStatus.ORDERED);

		BigDecimal totalPrice = orderDetailList.stream()
				.map(detail -> detail.getPrice().multiply(BigDecimal.valueOf(detail.getAmount())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		orderEntity.setTotalPrice(totalPrice);

		long totalAmount = orderDetailList.stream().mapToLong(OrderDetailEntity::getAmount).sum();
		orderEntity.setTotalAmount((int) totalAmount);

		return orderConverter.convertToDto(orderDataService.saveOrder(orderEntity));

	}

	@Override
	public List<OrderDetailDto> getOrderDetailByOrderId(Long orderId) {

		List<OrderDetailEntity> detailEntityList = orderDataService.getOrderDetailByOrderId(orderId);
		if (!CollectionUtils.isEmpty(detailEntityList)) {
			return detailEntityList.stream()
					.map(orderDetailEntity -> orderDetailConverter.convertToDto(orderDetailEntity))
					.collect(Collectors.toList());
		}
		return new ArrayList<OrderDetailDto>();
	}

	@Override
	public List<OrderDto> getOrdersByCustomerId(Long customerId) {

		List<OrderEntity> orderEntityList = orderDataService.getOrdersByCustomerId(customerId);
		if (!CollectionUtils.isEmpty(orderEntityList)) {
			return orderEntityList.stream().map(orderEntity -> orderConverter.convertToDto(orderEntity))
					.collect(Collectors.toList());
		}
		return new ArrayList<OrderDto>();
	}

	@Override
	public List<OrderDto> getAllByOrderTimeDateBetween(DateIntervalRequest dateIntervalRequest) {
		return orderDataService
				.findAllByOrderTimeBetween(dateIntervalRequest.getStartDate(), dateIntervalRequest.getEndDate())
				.stream().map(orderEntity -> orderConverter.convertToDto(orderEntity)).collect(Collectors.toList());

	}

}
