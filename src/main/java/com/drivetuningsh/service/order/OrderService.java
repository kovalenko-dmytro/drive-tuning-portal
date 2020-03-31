package com.drivetuningsh.service.order;

import com.drivetuningsh.dto.OrderRequestDTO;
import com.drivetuningsh.entity.order.Order;
import com.drivetuningsh.entity.user.User;
import com.drivetuningsh.exception.ApplicationException;

import java.util.List;
import java.util.Locale;

public interface OrderService {
    List<Order> findUserOrders(User user);
    Order findUserOrderById(long orderId, Locale locale) throws ApplicationException;
    void save(OrderRequestDTO orderRequestDto, Locale locale) throws ApplicationException;
    void update(OrderRequestDTO orderRequestDto, Locale locale) throws ApplicationException;
    void delete(long orderId);
}
